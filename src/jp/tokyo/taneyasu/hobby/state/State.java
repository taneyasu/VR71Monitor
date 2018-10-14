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
public interface State {
    public abstract void doTask(Context context);
    public abstract void doScheduleTask(Context context);
}
