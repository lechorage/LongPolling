package controller;

import bean.AjaxResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.PushMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/longpolling/")
public class LongPollingController {

    final Map<Integer, DeferredResult> deferredResultMap=new ConcurrentReferenceHashMap<>();

    public DeferredResult deferredResultlongPolling(){
        DeferredResult deferredResult = new DeferredResult(3000L);
        deferredResultMap.put(deferredResult.hashCode(),deferredResult);
        deferredResult.onCompletion(()->{
            deferredResultMap.remove(deferredResult.hashCode());
            System.err.println(deferredResultMap.size()+" Remaining");
        });
        return deferredResult;
    }

    public void returnLongPollingValue(){
        for (Map.Entry<Integer, DeferredResult> entry:deferredResultMap.entrySet()){
            entry.getValue().setResult("kl");
        }
    }

}
