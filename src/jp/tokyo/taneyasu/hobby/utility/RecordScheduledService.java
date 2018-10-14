/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.utility;

import java.io.IOException;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import jp.tokyo.taneyasu.hobby.state.NotReadyState;
import jp.tokyo.taneyasu.hobby.state.ReadyState;
import jp.tokyo.taneyasu.hobby.view.SceneController;

/**
 *
 * @author tanef
 */
public class RecordScheduledService extends ScheduledService<Boolean> {

    SceneController sceneController;
    CommPort commPort;
    public static final byte[] PV_REQUEST = {(byte) 0x01,(byte) 0x33,(byte) 0x04,(byte) 0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte) 0x38,(byte) 0x00};
    public RecordScheduledService(SceneController aSceneController){
        sceneController = aSceneController;
        commPort = sceneController.getCommPort();
    }

    @Override
    protected Task<Boolean> createTask() {
        return new Task<Boolean>(){
            protected Boolean call(){
            sceneController.changeState(NotReadyState.getInstance());
            try {
                byte b = (byte) 0x00;
                commPort.getOut().write(b);
                Thread.sleep(50);

                for(int i = 0; i < PV_REQUEST.length; i++){
                  commPort.getOut().write(PV_REQUEST[i]);
                  System.out.println("-->" + String.valueOf(PV_REQUEST[i]));
            }
            } catch ( IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
            sceneController.changeState(ReadyState.getInstance());
            return true;
            }
       };
    }
    
}
