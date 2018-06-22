package internals;

import com.google.common.collect.Lists;
import dto.ServiceDTO;
import http.ConfigUtil;
import http.HttpRequest;
import http.HttpResponse;
import http.HttpUtil;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

import com.google.gson.reflect.TypeToken;
import spi.Transaction;

public class ConfigServiceLocator {
    private HttpUtil m_httpUtil;
    private AtomicReference<List<ServiceDTO>> m_configServices;
    private Type m_responseType;
    private ScheduledExecutorService m_executorService;
    private ConfigUtil m_configUtil;

    public ConfigServiceLocator() {
        List<ServiceDTO> initial = Lists.newArrayList();
        m_configServices = new AtomicReference<>(initial);
        m_responseType = new TypeToken<List<ServiceDTO>>() {
        }.getType();
        m_httpUtil = new HttpUtil();
        m_executorService = Executors.newScheduledThreadPool(1);
        m_configUtil = new ConfigUtil();
    }

    public List<ServiceDTO> getConfigServices() {
        if (m_configServices.get().isEmpty()) {
            updateConfigServices();
        }
        return m_configServices.get();
    }

    private synchronized void updateConfigServices() {
        String url = "";
        HttpRequest request = new HttpRequest(url);
        int maxRetries = 2;
        for (int i = 0; i < maxRetries; i++) {
            Transaction transaction = new Transaction();
            transaction.addData("url",url);
            try{
                HttpResponse<List<ServiceDTO>> response = m_httpUtil.doGet(request,m_responseType);
                transaction.setStatus("SUCCESS");
                List<ServiceDTO> services = response.getBody();
                m_configServices.set(services);
                return;
            }catch (Throwable ex){
                ex.printStackTrace();
            }

            try {
                m_configUtil.getOnErrorRetryIntervalTimeUnit().sleep(m_configUtil.getOnErrorRetryInterval());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

    }


}
