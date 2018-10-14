/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.utility;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javax.xml.bind.DatatypeConverter;
import jp.tokyo.taneyasu.hobby.state.NotReadyState;
import jp.tokyo.taneyasu.hobby.state.ReadyState;
import jp.tokyo.taneyasu.hobby.view.SceneController;

/**
 *
 * @author tanef
 */
public class ReceiveTask extends Task<Boolean> {
    
    SceneController sceneController;
    CommPort commPort;
    
    public ReceiveTask(SceneController aSceneController){
        sceneController = aSceneController;
        commPort = sceneController.getCommPort();
    }

    @Override
    protected Boolean call() throws Exception {
        sceneController.changeState(NotReadyState.getInstance());
        StringBuilder builder = new StringBuilder();
        try {
            System.out.println(21);
            Thread.sleep(100);
            int counter = 0;
            String str = "";
            while(counter < 11) { 
            str = String.valueOf(commPort.getIn().read());
            System.out.println(counter+":"+str);
            if(!str.equals("-1")){
            builder.append(str).append(",");
            }
            counter++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //System.out.println(builder.toString());
        Thread.sleep(100);
        if(builder.toString().length()==26)Platform.runLater(()->sceneController.setVoltageDataList(builder.toString()));
        //sceneController.setOutTextArea("<--" + builder.toString() + ":" + builder.toString().length());
        sceneController.changeState(ReadyState.getInstance());
        return true;
    }
    
}
