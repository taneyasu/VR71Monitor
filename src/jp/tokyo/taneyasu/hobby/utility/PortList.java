/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.utility;

import gnu.io.CommPortIdentifier;
import java.util.Enumeration;

/**
 *
 * @author tanef
 */
public class PortList {
    Enumeration portList;
    
    public PortList(){
         portList = CommPortIdentifier.getPortIdentifiers();
    }
    
    public void showList(){
        CommPortIdentifier portID;
        while(portList.hasMoreElements()){
            portID = (CommPortIdentifier)portList.nextElement();
            System.out.print("PortName:" + portID.getName() + ",");
            if(portID.isCurrentlyOwned()){
                System.out.print("owned,");
            } else {
                System.out.print("not owned,");
            }
            switch(portID.getPortType()){
                case CommPortIdentifier.PORT_SERIAL:
                    System.out.println("Kind：Serial");
                    break;
                case CommPortIdentifier.PORT_PARALLEL:
                    System.out.print("Kind:Parallel");
                    break;
            }
        }
    }
    
}
