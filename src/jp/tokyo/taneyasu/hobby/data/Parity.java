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
public enum Parity {
    
    EVEN(SerialPort.PARITY_EVEN,"SerialPort.PARITY_EVEN"),
    MARK(SerialPort.PARITY_MARK,"SerialPort.PARITY_MARK"),
    NONE(SerialPort.PARITY_NONE,"SerialPort.PARITY_NONE"),
    ODD(SerialPort.PARITY_SPACE,"SerialPort.PARITY_SPACE"),
    SPASE(SerialPort.PARITY_SPACE,"SerialPort.PARITY_SPACE");
    
    private int number;
    private String name;
    
    Parity(int aNumber, String aName){
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
    
    	public static class ParityStringConverter extends StringConverter<Parity>{
            
		@Override
		public String toString(Parity object) {

			if (object == null){
                            return "データなし";
                        }
			return object.getName();
		}
        
		@Override
		public Parity fromString(String aName) {
                return Arrays.stream(Parity.values())
                        .filter(prity -> prity.name.equalsIgnoreCase(aName))
                        .findFirst()
                        .get();
                }
                
	}
}
