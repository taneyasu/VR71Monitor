/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.state;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.ScheduledService;
import javafx.util.Duration;
import jp.tokyo.taneyasu.hobby.utility.ReceiveTask;
import jp.tokyo.taneyasu.hobby.utility.RecordScheduledService;
import jp.tokyo.taneyasu.hobby.view.SceneController;

/**
 *
 * @author tanef
 */
public class ReadyState implements State {
    private static ReadyState singleton = new ReadyState();
    private ReadyState(){
    }
    public static State getInstance(){
        return singleton;
    } 

    @Override
    public void doTask(Context context) {
            context.changeState(NotReadyState.getInstance());
            ExecutorService ex = Executors.newSingleThreadExecutor();
            ex.execute(new ReceiveTask((SceneController) context));
            ex.shutdown();
    }   

    @Override
    public void doScheduleTask(Context context) {
        ScheduledService<Boolean> service = new RecordScheduledService(((SceneController) context));
        service.setDelay(Duration.seconds(1));
        service.setPeriod(Duration.seconds(3));
        //context.setService(service);
        service.start();
    }
}
