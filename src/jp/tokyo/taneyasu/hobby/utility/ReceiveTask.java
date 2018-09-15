/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.utility;

import java.io.IOException;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javax.xml.bind.DatatypeConverter;
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
        StringBuilder builder = new StringBuilder();
        try {
            System.out.println(21);
            int counter = 0;
            String str = "";
            while(counter < 30) { 
            str = String.valueOf(commPort.getIn().read());
            if(!str.equals("-1")){
            builder.append(str).append(",");
            }
            counter++;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         sceneController.setOutTextArea("<--" + builder.substring(0,builder.length() -1));
        return true;
    }
    
}
