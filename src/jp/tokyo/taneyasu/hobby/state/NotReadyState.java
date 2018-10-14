/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.state;

import java.util.List;
import jp.tokyo.taneyasu.hobby.data.VoltageData;

/**
 *
 * @author tanef
 */
public class NotReadyState implements State {
    private static NotReadyState singleton = new NotReadyState();
    private NotReadyState(){
    }
    public static State getInstance(){
        return singleton;
    } 

    @Override
    public void doTask(Context context) {
        System.out.print("notReadyで・す・よ");
    }

    @Override
    public void doScheduleTask(Context context) {
       System.out.print("notReadyで・す・よ");
    }
    
}
