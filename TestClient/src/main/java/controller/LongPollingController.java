package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import dto.PushMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/longpolling/")
public class LongPollingController {
    private static final Type notificationsTypeReference =
            new TypeToken<List<PushMsg>>() {
            }.getType();
    @Autowired
    private Gson gson;

    public DeferredResult<PushMsg> getMessage(
        @RequestParam(value = "userId") String userId,
        @RequestParam(value = "notifications") String notificationsAsString){
        List<PushMsg> msgs = null;
        try{
            msgs = gson.fromJson(notificationsAsString,notificationsTypeReference);
        }catch (Throwable ex){
            ex.printStackTrace();
        }

    }


}
