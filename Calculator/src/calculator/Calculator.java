/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import jssc.SerialPort;
import static jssc.SerialPort.MASK_RXCHAR;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;
import javafx.scene.text.Text;
import calculator.FXMLDocumentController;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

   


/**
 *
 * @author Rasha
 */
public class Calculator extends Application {
 
//    String portList[]=SerialPortList.getPortNames();
    String portName="COM3";
    SerialPort port = new SerialPort(portName);
    FXMLLoader fxmlLoader;
    Pane p;
    FXMLDocumentController fooController;
    static FXMLDocumentController myControllerHandle;
        
    String status;
    
    public void runTask()
    {
        //SerialPort serialPort = new SerialPort(port);
        try {
            port.openPort();
            port.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

            port.setEventsMask(MASK_RXCHAR);
            port.addEventListener((SerialPortEvent serialPortEvent) -> {
                if(serialPortEvent.isRXCHAR()){
                    try {
                        
                        //byte[] b = port.readBytes();
                        //int value = b[0] & 0xff;    //convert to int
                        //String st = String.valueOf(value);
                        String st = port.readString();
                        System.out.println(st);

                        //Update label in ui thread
                        Platform.runLater(() -> {
                            //if(st!=null)
                                //myControllerHandle.text.setText(st);
                                //myControllerHandle.text.appendText(st);
                                char x []=st.toCharArray();
                                switch(x[0])
                                {
                                    case '0':
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                    case '5':
                                    case '6':
                                    case '7':
                                    case '8':
                                    case '9':
                                        myControllerHandle.write_num_gui(st);
                                        System.out.println("write_num_gui");
                                        break;
                                    case 'C':
                                        myControllerHandle.clear_function_gui(st);
                                        System.out.println("clear_function_gui");
                                        break;
                                    case '=':
                                        myControllerHandle.equal_function_gui(st);
                                        System.out.println("equal_function_gui");
                                        break;
                                    case '+':
                                    case '-':
                                    case '/':
                                    case '*':
                                    case '%':
                                    case '^':
                                        myControllerHandle.operation_gui(st);
                                        System.out.println("operation_gui");
                                        break;
                                    default:
                                        System.out.println("default");
                                        if(st.equals("1/x")) {
                                        myControllerHandle.inverse_function_gui(st);
                                        System.out.println("inverse_function_gui");
                                        break;
                                        }
                                    if(st.equals("root")){
                                        myControllerHandle.root_function_gui(st);
                                        System.out.println("root_function_gui");
                                    }
                                    if(st.equals( "⌫")){
                                        myControllerHandle.remove_one_char_gui(st);
                                        System.out.println("remove_one_char_gui");
                                    }
                                    
                                        break;
                                        
                                }
                                
                        });
                        
                    } catch (SerialPortException ex) {
                         Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
                    try {
                        port.closePort();
                    } catch (SerialPortException ex1) {
                        Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    }
                    
                }
            });
            
            
        } catch (SerialPortException ex) {
            Logger.getLogger(Calculator.class.getName())
                    .log(Level.SEVERE, null, ex);
            System.out.println("SerialPortException: " + ex.toString());
            try {
                        port.closePort();
                    } catch (SerialPortException ex1) {
                        Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex1);
                    }
        }
    }
    
    public void startTask()
    {
    Runnable task = new Runnable() {
        @Override
        public void run() {
            runTask();
           // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    };
    Thread backgroundThread =new Thread(task);
    backgroundThread.start();
    }
   

    @Override
    public void start(Stage stage) throws Exception {
        //fxmlLoader = new FXMLLoader();
        //p = fxmlLoader.load(getClass().getResource("FXMLDocumentController.fxml").openStream());
        //fooController = (FXMLDocumentController) fxmlLoader.getController();
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loader.load();

    //Now we have access to getController() through the instance... don't forget the type cast
        myControllerHandle = (FXMLDocumentController)loader.getController();
        startTask();      
        
        Scene scene = new Scene(root);

        scene.getStylesheets().add("/CSS/samples.css");
        stage.setOpacity(0.899);
        
        stage.setScene(scene);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
