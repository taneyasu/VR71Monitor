/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.data;

import gnu.io.SerialPort;
import java.util.Arrays;
import javafx.util.StringConverter;

/**
 *
 * @author tanef
 */
public enum FlowControl {
        
    FLOW_NONE(SerialPort.FLOWCONTROL_NONE,"SerialPort.FLOWCONTROL_NONE"),
    RTSCTS_IN(SerialPort.FLOWCONTROL_RTSCTS_IN,"SerialPort.FLOWCONTROL_RTSCTS_IN"),
    RTSCTS_OUT(SerialPort.FLOWCONTROL_RTSCTS_OUT,"SerialPort.FLOWCONTROL_RTSCTS_OUT"),
    XONXOFF_IN(SerialPort.FLOWCONTROL_XONXOFF_IN,"SerialPort.FLOWCONTROL_XONXOFF_IN"),
    XONXOFF_OUT(SerialPort.FLOWCONTROL_XONXOFF_OUT,"SerialPort.FLOWCONTROL_XONXOFF_OUT");
    
    private int number;
    private String name;
    
    FlowControl(int aNumber, String aName){
        number = aNumber;
        name = aName;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }    
    
    	public static class FlowControlStringConverter extends StringConverter<FlowControl>{
            
		@Override
		public String toString(FlowControl object) {

			if (object == null){
                            return "データなし";
                        }
			return object.getName();
		}
        
		@Override
		public FlowControl fromString(String aName) {
                return Arrays.stream(FlowControl.values())
                        .filter(flowControl -> flowControl.name.equalsIgnoreCase(aName))
                        .findFirst()
                        .get();
                }
                
	}
}
