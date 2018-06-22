package http;

import com.google.common.base.Strings;

import java.util.concurrent.TimeUnit;


public class ConfigUtil {
    //5分钟一刷新
    private int refreshInterval = 5;
    private TimeUnit refreshIntervalTimeUnit = TimeUnit.MINUTES;
    private int connectTimeout = 1000; //1 second
    private int readTimeout = 5000; //5 seconds
    private int loadConfigQPS = 2; //2 times per second
    private int longPollQPS = 2; //2 times per second
    //for on error retry
    private long onErrorRetryInterval = 1;//1 second
    private TimeUnit onErrorRetryIntervalTimeUnit = TimeUnit.SECONDS;//1 second

    private long longPollingInitialDelayInMills = 2000;//2 seconds
    private boolean autoUpdateInjectedSpringProperties = true;

    private String userId;

    public ConfigUtil() {
        initAutoUpdateInjectedSpringProperties();
        initConnectTimeout();
        initLongPollingInitialDelayInMills();
        initQPS();
        initReadTimeout();
        initRefreshInterval();
    }

    public TimeUnit getRefreshIntervalTimeUnit() {
        return refreshIntervalTimeUnit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private void initConnectTimeout() {
        String customizedConnectTimeout = System.getProperty("connectTimeout");
        if (!Strings.isNullOrEmpty(customizedConnectTimeout)) {
            try {
                connectTimeout = Integer.parseInt(customizedConnectTimeout);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    private void initReadTimeout() {
        String customizedReadTimeout = System.getProperty("readTimeout");
        if (!Strings.isNullOrEmpty(customizedReadTimeout)) {
            try {
                readTimeout = Integer.parseInt(customizedReadTimeout);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    private void initRefreshInterval() {
        String customizedRefreshInterval = System.getProperty("refreshInterval");
        if (!Strings.isNullOrEmpty(customizedRefreshInterval)) {
            try {
                refreshInterval = Integer.parseInt(customizedRefreshInterval);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    private void initQPS() {
        String customizedLoadConfigQPS = System.getProperty("apollo.loadConfigQPS");
        if (!Strings.isNullOrEmpty(customizedLoadConfigQPS)) {
            try {
                loadConfigQPS = Integer.parseInt(customizedLoadConfigQPS);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }

        String customizedLongPollQPS = System.getProperty("longPollQPS");
        if (!Strings.isNullOrEmpty(customizedLongPollQPS)) {
            try {
                longPollQPS = Integer.parseInt(customizedLongPollQPS);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    public int getLoadConfigQPS() {
        return loadConfigQPS;
    }

    public int getLongPollQPS() {
        return longPollQPS;
    }

    public long getOnErrorRetryInterval() {
        return onErrorRetryInterval;
    }

    public TimeUnit getOnErrorRetryIntervalTimeUnit() {
        return onErrorRetryIntervalTimeUnit;
    }

    private void initLongPollingInitialDelayInMills() {
        String customizedLongPollingInitialDelay = System.getProperty("longPollingInitialDelayInMills");
        if (!Strings.isNullOrEmpty(customizedLongPollingInitialDelay)) {
            try {
                longPollingInitialDelayInMills = Long.valueOf(customizedLongPollingInitialDelay);
            } catch (Throwable ex) {
                ex.printStackTrace();
            }
        }
    }

    public long getLongPollingInitialDelayInMills() {
        return longPollingInitialDelayInMills;
    }

    private void initAutoUpdateInjectedSpringProperties() {
        //get the information for update?
    }



}
