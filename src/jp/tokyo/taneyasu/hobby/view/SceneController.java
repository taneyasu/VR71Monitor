package jp.tokyo.taneyasu.hobby.view;

import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.concurrent.ScheduledService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.util.Duration;
import jp.tokyo.taneyasu.hobby.data.BaudRate;
import jp.tokyo.taneyasu.hobby.data.Databits;
import jp.tokyo.taneyasu.hobby.data.FlowControl;
import jp.tokyo.taneyasu.hobby.data.Parity;
import jp.tokyo.taneyasu.hobby.data.PortNumber;
import jp.tokyo.taneyasu.hobby.data.Stopbits;
import jp.tokyo.taneyasu.hobby.data.VoltageData;
import jp.tokyo.taneyasu.hobby.state.Context;
import jp.tokyo.taneyasu.hobby.state.ReadyState;
import jp.tokyo.taneyasu.hobby.state.State;
import jp.tokyo.taneyasu.hobby.utility.CommPort;
import jp.tokyo.taneyasu.hobby.utility.RecordScheduledService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tanef
 */
public class SceneController implements Initializable, SerialPortEventListener,Context  {
    
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
    
    
    public CommPort commPort;
    private ScheduledService<Boolean> service;
    private List<VoltageData> voltageDataList = new ArrayList();
    private State state = ReadyState.getInstance();

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
        state.doScheduleTask(this);
    }
    
   @FXML 
    private void stopREC(){
    service.cancel();   
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
    public void setVoltageDataList(String receiveData){
        VoltageData voltageData = new VoltageData();
        List<String> list = new ArrayList<String>(Arrays.asList(receiveData.split(","))); 
        /*
        int ch1X = Integer.valueOf(Integer.toHexString(Integer.valueOf(list.get(6))));
        int ch1Y = Integer.valueOf(Integer.toHexString(Integer.valueOf(list.get(5))));
        int ch2X = Integer.valueOf(Integer.toHexString(Integer.valueOf(list.get(8))));
        int ch2Y = Integer.valueOf(Integer.toHexString(Integer.valueOf(list.get(7))));
        */
        int ch1X = Integer.valueOf(list.get(6));
        int ch1Y = Integer.valueOf(list.get(5));
        int ch2X = Integer.valueOf(list.get(8));
        int ch2Y = Integer.valueOf(list.get(7));
        voltageData.setTimeCh1(LocalTime.now());
        voltageData.setTimeCh2(LocalTime.now());
        //ch1 [5],[6],ch2[7][8], -1x(0.256(X-40) + 0.001Y) (負の場合、10進数), 0.256X + 0.001Y (正の場合、10進数)
        if(ch1X > 64){
            voltageData.setVoltCh1(-1*(0.256*(ch1X-64) + 0.001*ch1Y));  
        } else {
            voltageData.setVoltCh1(0.256*ch1X + 0.001*ch1Y);  
        }
        
        if(ch2X > 64){
            voltageData.setVoltCh2(-1*(0.256*(ch2X-40) + 0.001*ch2Y));  
        } else {
            voltageData.setVoltCh2(0.256*ch2X + 0.001*ch2Y);  
        }
   
        setOutTextArea("Ch1 Time:" + voltageData.getTimeCh1().toString() +", Ch1Voltage:" + voltageData.getVoltCh1() +"Ch2 Time:"+ voltageData.getTimeCh2().toString() +", Ch2 Voltage:"+ voltageData.getVoltCh2() );
        voltageDataList.add(voltageData);
    }
    
    

    @Override
    public void changeState(State state) {
        this.state = state;
    }
    
    @Override
        public void serialEvent(SerialPortEvent event) {
                state.doTask(this);

        }

    public CommPort getCommPort() {
        return commPort;
    }

    @Override
    public void setService(ScheduledService<Boolean> service) {
        this.service = service;
    }
    
}
    
