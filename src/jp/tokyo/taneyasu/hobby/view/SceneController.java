package jp.tokyo.taneyasu.hobby.view;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TooManyListenersException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import jp.tokyo.taneyasu.hobby.data.BaudRate;
import jp.tokyo.taneyasu.hobby.data.Databits;
import jp.tokyo.taneyasu.hobby.data.FlowControl;
import jp.tokyo.taneyasu.hobby.data.Parity;
import jp.tokyo.taneyasu.hobby.data.PortNumber;
import jp.tokyo.taneyasu.hobby.data.Stopbits;
import jp.tokyo.taneyasu.hobby.utility.CommPort;
import jp.tokyo.taneyasu.hobby.utility.ReceiveTask;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tanef
 */
public class SceneController implements Initializable, SerialPortEventListener  {
    
    @FXML
    private TextArea sendTextArea;
    @FXML
    private TextArea outTextArea;
    @FXML
    private TextArea monitorTextArea;
    @FXML
    private ComboBox<PortNumber> portNumberComboBox;
    @FXML
    private ComboBox<BaudRate> baudRateComboBox;
    @FXML
    private ComboBox<Databits> DatabitsComboBox;
    @FXML
    private ComboBox<Stopbits> StopbitsComboBox;
    @FXML
    private ComboBox<FlowControl> FlowControlComboBox;
    @FXML
    private ComboBox<Parity> ParityComboBox;
    
    CommPort commPort;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        portNumberComboBox.getItems().addAll(FXCollections.observableArrayList(PortNumber.values()));
        portNumberComboBox.setConverter(new PortNumber.PortNumberStringConverter());
        portNumberComboBox.setValue(PortNumber.COM2);
        
        baudRateComboBox.getItems().addAll(FXCollections.observableArrayList(BaudRate.values()));
        baudRateComboBox.setConverter(new BaudRate.BaudRateStringConverter());
        baudRateComboBox.setValue(BaudRate.RATE_9600);    
        
        DatabitsComboBox.getItems().addAll(FXCollections.observableArrayList(Databits.values()));
        DatabitsComboBox.setConverter(new Databits.DatabitsStringConverter());
        DatabitsComboBox.setValue(Databits.BITS_8); 
        
        StopbitsComboBox.getItems().addAll(FXCollections.observableArrayList(Stopbits.values()));
        StopbitsComboBox.setConverter(new Stopbits.StopbitsStringConverter());
        StopbitsComboBox.setValue(Stopbits.BITS_1); 
        
        FlowControlComboBox.getItems().addAll(FXCollections.observableArrayList(FlowControl.values()));
        FlowControlComboBox.setConverter(new FlowControl.FlowControlStringConverter());
        FlowControlComboBox.setValue(FlowControl.RTSCTS_IN); 
        
        ParityComboBox.getItems().addAll(FXCollections.observableArrayList(Parity.values()));
        ParityComboBox.setConverter(new Parity.ParityStringConverter());
        ParityComboBox.setValue(Parity.NONE); 
        
    }
    
    @FXML
    private void portListCheck(){
        
    }
    
    @FXML
    private void openPort(){
    commPort = new CommPort(portNumberComboBox.getValue().getName(),
            baudRateComboBox.getValue().getNumber(), 
            DatabitsComboBox.getValue().getNumber(),
            StopbitsComboBox.getValue().getNumber(),
            FlowControlComboBox.getValue().getNumber(),
            ParityComboBox.getValue().getNumber()); 
    setMonitorTextArea("open port:" + portNumberComboBox.getValue().getName());
    
    try {
        commPort.getPort().addEventListener(this);
    } catch (TooManyListenersException ex) {
        ex.printStackTrace();
    }
    }
    
    @FXML
    private void startRecord(){ 
   
        try {
            byte b = (byte) 0x00;
            commPort.getOut().write(b);
            Thread.sleep(50);
        } catch ( IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
      byte[] array = new byte[10];
       
       array[0] = (byte) 0x01;
       array[1] = (byte) 0x31;
       array[2] = (byte) 0x04;
       array[3] = (byte) 0x00;
       array[4] = (byte) 0x00;
       array[5] = (byte) 0x00;
       array[6] = (byte) 0x00;
       array[7] = (byte) 0x00;
       array[8] = (byte) 0x36; 
       array[9] = (byte) 0x00;  
       
        try {
            for (int i=0; i<=9 ;i++){
            commPort.getOut().write(array[i]);
            setOutTextArea("-->" + String.valueOf(array[i]));
           }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
            
    }
    
   @FXML 
    private void stopREC(){
                try {
            byte b = (byte) 0x00;
            commPort.getOut().write(b);
            Thread.sleep(50);
        } catch ( IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
      byte[] array = new byte[10];
       
       array[0] = (byte) 0x01;
       array[1] = (byte) 0x33;
       array[2] = (byte) 0x04;
       array[3] = (byte) 0x00;
       array[4] = (byte) 0x00;
       array[5] = (byte) 0x00;
       array[6] = (byte) 0x00;
       array[7] = (byte) 0x00;
       array[8] = (byte) 0x38; 
       array[9] = (byte) 0x00;  
       
        try {
            for (int i=0; i<=9 ;i++){
            commPort.getOut().write(array[i]);
            setOutTextArea("-->" + String.valueOf(array[i]));
           }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    
    /*
        byte[] buffer = new byte[1024];
        try {
            int  i =0;
            System.out.println(21);
            while(true) { 
            buffer[i] =(byte) commPort.getIn().read();
            if(buffer[i]==-1) break;
            setOutTextArea("<--" + String.valueOf(buffer[i]));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    */
    
    }

    

    
    @FXML
    private void closePort(){
        if(commPort!=null){
            try {
                commPort.getIn().close();
                commPort.getOut().close();
            } catch (IOException ex) {
                Logger.getLogger(SceneController.class.getName()).log(Level.SEVERE, null, ex);
            }
        commPort.getPort().notifyOnDataAvailable(false);
        commPort.getPort().close();
        commPort = null;
        }
    }
    
    public void setOutTextArea(String str){
        String separator = System.getProperty("line.separator");
        outTextArea.setText(outTextArea.getText() + str + separator);
    }
    
    public void setMonitorTextArea(String str){
        String separator = System.getProperty("line.separator");
        monitorTextArea.setText(monitorTextArea.getText() + str + separator);
    }

    @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
                System.out.print(23);
                //recieveText();
            ExecutorService ex = Executors.newSingleThreadExecutor();
            ex.execute(new ReceiveTask(this));
             ex.shutdown();
                
            }
        }

    public CommPort getCommPort() {
        return commPort;
    }
        
}
    
