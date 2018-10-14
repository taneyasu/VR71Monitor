/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.state;

import javafx.concurrent.ScheduledService;

/**
 *
 * @author tanef
 */
public interface Context {
    public abstract void changeState(State state);
    public abstract void setVoltageDataList(String recievePvData);
    public abstract void setService(ScheduledService<Boolean> service);
}
