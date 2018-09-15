/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.data;

import java.util.Arrays;
import javafx.util.StringConverter;

/**
 *
 * @author tanef
 */
public enum BaudRate {
    
    RATE_4800(4800,"4800"),
    RATE_9600(9600,"9600"),
    RATE_19200(19200,"19200");
    
    private int number;
    private String name;
    
    BaudRate(int aNumber, String aName){
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
    
    	public static class BaudRateStringConverter extends StringConverter<BaudRate>{
            
		@Override
		public String toString(BaudRate object) {

			if (object == null){
                            return "データなし";
                        }
			return object.getName();
		}
        
		@Override
		public BaudRate fromString(String aName) {
                return Arrays.stream(BaudRate.values())
                        .filter(baudRate -> baudRate.name.equalsIgnoreCase(aName))
                        .findFirst()
                        .get();
                }
                
	}
}
