package cn.edu.zafu.dao;

/**
 * Created by lizhangqu on 16/1/24.
 */

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class FutureResultHandler implements ResultHandler {

    BlockingQueue queue;

    public FutureResultHandler() {
        queue = new LinkedBlockingQueue(1);
    }

    @SuppressWarnings("unchecked")
    public void handleResult(ResultContext context) {
        try {
            queue.put(context.getResultObject());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Object get() {
        try {
            return queue.poll(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return null;
        }
    }
}
