/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.data;

import java.time.LocalTime;

/**
 *
 * @author tanef
 */
public class VoltageData {
    
    LocalTime timeCh1;
    LocalTime timeCh2;
    double voltCh1;
    double voltCh2;

    public LocalTime getTimeCh1() {
        return timeCh1;
    }

    public void setTimeCh1(LocalTime timeCh1) {
        this.timeCh1 = timeCh1;
    }

    public LocalTime getTimeCh2() {
        return timeCh2;
    }

    public void setTimeCh2(LocalTime timeCh2) {
        this.timeCh2 = timeCh2;
    }

    public double getVoltCh1() {
        return voltCh1;
    }

    public void setVoltCh1(double voltCh1) {
        this.voltCh1 = voltCh1;
    }

    public double getVoltCh2() {
        return voltCh2;
    }

    public void setVoltCh2(double voltCh2) {
        this.voltCh2 = voltCh2;
    }
    
    
    
    
}
