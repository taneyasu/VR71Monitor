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
public enum Stopbits {
        
    BITS_1(SerialPort.STOPBITS_1,"SerialPort.STOPBITS_1"),
    BITS_1_5(SerialPort.STOPBITS_1_5,"SerialPort.STOPBITS_1_5"),
    BITS_2(SerialPort.STOPBITS_2,"SerialPort.STOPBITS_2");

    
    private int number;
    private String name;
    
    Stopbits(int aNumber, String aName){
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
    
    	public static class StopbitsStringConverter extends StringConverter<Stopbits>{
            
		@Override
		public String toString(Stopbits object) {

			if (object == null){
                            return "データなし";
                        }
			return object.getName();
		}
        
		@Override
		public Stopbits fromString(String aName) {
                return Arrays.stream(Stopbits.values())
                        .filter(stipbits -> stipbits.name.equalsIgnoreCase(aName))
                        .findFirst()
                        .get();
                }
                
	}
}
