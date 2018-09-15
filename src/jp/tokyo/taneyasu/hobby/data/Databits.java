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
public enum Databits {
    
    BITS_5(SerialPort.DATABITS_5,"SerialPort.DATABITS_5"),
    BITS_6(SerialPort.DATABITS_6,"SerialPort.DATABITS_6"),
    BITS_7(SerialPort.DATABITS_7,"SerialPort.DATABITS_7"),
    BITS_8(SerialPort.DATABITS_8,"SerialPort.DATABITS_8");
    
    private int number;
    private String name;
    
    Databits(int aNumber, String aName){
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
    
    	public static class DatabitsStringConverter extends StringConverter<Databits>{
            
		@Override
		public String toString(Databits object) {

			if (object == null){
                            return "データなし";
                        }
			return object.getName();
		}
        
		@Override
		public Databits fromString(String aName) {
                return Arrays.stream(Databits.values())
                        .filter(databits -> databits.name.equalsIgnoreCase(aName))
                        .findFirst()
                        .get();
                }
                
	}
}
