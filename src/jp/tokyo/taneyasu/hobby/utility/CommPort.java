/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jp.tokyo.taneyasu.hobby.utility;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tanef
 */
public class CommPort {
    SerialPort port;
    OutputStream out;
    InputStream in;
    
    public CommPort(String portNumber, int baudRate, int databits, int stopbits, int flowControl, int parity){
        try {
            port = (SerialPort) CommPortIdentifier.getPortIdentifier(portNumber).open("Monitor", 500);
            port.setSerialPortParams(baudRate, databits , stopbits, parity);
            port.setFlowControlMode(flowControl);
            port.notifyOnDataAvailable(true);
            out = port.getOutputStream();
            in = port.getInputStream();
            
        } catch (NoSuchPortException | PortInUseException | UnsupportedCommOperationException | IOException ex) {
            ex.printStackTrace();
        }
    } 

    public SerialPort getPort() {
        return port;
    }

    public OutputStream getOut() {
        return out;
    }

    public InputStream getIn() {
        return in;
    }
    
    
}
