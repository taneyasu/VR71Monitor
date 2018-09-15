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
public enum PortNumber {
    
    COM1(1,"COM1"),
    COM2(2,"COM2"),
    COM3(3,"COM3"),
    COM4(4,"COM4"),
    COM5(5,"COM5"),
    COM6(6,"COM6"),
    COM7(7,"COM7"),
    COM8(8,"COM8"),
    COM9(9,"COM9"),
    COM10(10,"COM10"),
    COM11(11,"COM11"),
    COM12(12,"COM12"),
    COM13(13,"COM13"),
    COM14(14,"COM14"),
    COM15(15,"COM15");
    
    private int number;
    private String name;
    
    PortNumber(int aNumber, String aName){
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
    
    	public static class PortNumberStringConverter extends StringConverter<PortNumber>{
            
		@Override
		public String toString(PortNumber object) {

			if (object == null){
                            return "データなし";
                        }
			return object.getName();
		}
        
		@Override
		public PortNumber fromString(String aName) {
                return Arrays.stream(PortNumber.values())
                        .filter(portNumber -> portNumber.name.equalsIgnoreCase(aName))
                        .findFirst()
                        .get();
                }
                
	}
   
}
