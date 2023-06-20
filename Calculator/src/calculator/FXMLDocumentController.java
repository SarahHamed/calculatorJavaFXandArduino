/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package calculator;

import mathTree.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author Rasha
 */
public class FXMLDocumentController implements Initializable {
    
    boolean setZero=false;
    
    public static Stage window;
    private Image dark_image;
    @FXML
    private ImageView option_image;
    @FXML
    private Text mode;
    @FXML
    protected TextField text;
    @FXML
    private ImageView standard_img;
    @FXML
    private ImageView Scientific_img;
    @FXML
    private ImageView Programmer_img;
    @FXML
    private ImageView dark_light_img;
    @FXML
    private ToggleButton selectedButton;
    
    MathTree calcTree = new MathTree();
       
        // System.out.println(calcTree.solve());
    @FXML
    private Button root;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        // TODO
        

        Image op_image=new Image(getClass().getResourceAsStream("/images/option.png"));
        option_image.setImage(op_image);
        
        Image st_image=new Image(getClass().getResourceAsStream("/images/standard.png"));
        standard_img.setImage(st_image);   
        
        Image sc_image=new Image(getClass().getResourceAsStream("/images/scientific_icon.png"));
        Scientific_img.setImage(sc_image);        
        
        Image pr_image=new Image(getClass().getResourceAsStream("/images/programmer.png"));
        Programmer_img.setImage(pr_image);   

         dark_image=new Image(getClass().getResourceAsStream("/images/darkMode.png"));
         //light_image=new Image(getClass().getResourceAsStream("/images/light.png"));
         dark_light_img.setImage(dark_image);  
        
        text.setText("0");
        
        
    }    

    @FXML
    private void write_num(ActionEvent event) {
         Button pressed = (Button)event.getSource();
         if(setZero)
         {setZero=false;
         text.setText("0");
         }
        if(text.getText().equals("0"))
         {text.setText("");
         }
            text.appendText(pressed.getText());
            System.out.println(pressed.getText());
               
    }
    @FXML
    protected void write_num_gui(String st) {
         if(setZero)
         {setZero=false;
         text.setText("0");
         }
        if(text.getText().equals("0"))
         {text.setText("");
         }
            text.appendText(st);
            System.out.println(st);
               
    }

    @FXML
    private void operation(ActionEvent event) {
        
         Button pressed = (Button)event.getSource();
         
         if(pressed.getText()=="C")
         {
             
         }
         
         
         if(setZero)
         {setZero=false;
         }
        text.appendText(pressed.getText());
    }

    @FXML
    protected void operation_gui(String st) {
        
         
         if(st=="C")
         {
             
         }
         if(setZero)
         {setZero=false;
         }
        text.appendText(st);
    }

    @FXML
    private void remove_one_char(ActionEvent event) {
        String x=text.getText();
        if(x.length()==1){
        text.setText("0");
        }
        else
            text.setText(text.getText(0,x.length()-1 ));
   
    }
    @FXML
    protected void remove_one_char_gui(String st) {
        String x=text.getText();
        if(x.length()==1){
        text.setText("0");
        }
        else
            text.setText(text.getText(0,x.length()-1 ));
   
    }

    @FXML
    private void clear_function(ActionEvent event) {
        text.setText("0");
    }
    
    @FXML
    protected void clear_function_gui(String st) {
        text.setText("0");
    }

    private void dot_function(ActionEvent event) {
        String x=text.getText();
        if( x.contains("."))
        {
        }
            
        else 
        {
                text.appendText(".");    
        }
    }
     protected void dot_function_gui(String st) {
        String x=text.getText();
        if( x.contains("."))
        {
        }            
        else 
        {
                text.appendText(".");    
        }
    }

    @FXML
    private void dark_light_mode(ActionEvent event) throws IOException {


        
    }

    @FXML
    private void equal_function(ActionEvent event) {
        if(calcTree.init(text.getText()))
        text.setText(calcTree.solve().toString());
        
        setZero=true;
    }
    
    @FXML
    protected void equal_function_gui(String st) {
        if(calcTree.init(text.getText()))
        text.setText(calcTree.solve().toString());
        
        setZero=true;
    }

    @FXML
    private void inverse_function(ActionEvent event) {
        Double x=1/Double.parseDouble(text.getText());
        System.out.println(x);
        
        text.setText(x.toString());
        
        setZero=true;
        
    }
@FXML
    protected void inverse_function_gui(String st) {
        Double x=1/Double.parseDouble(text.getText());
        System.out.println(x);
        
        text.setText(x.toString());
        
        setZero=true;
        
    }

    @FXML
    private void programer_mode(ActionEvent event) throws IOException {
        
try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLProgramer.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }

        
        
    }

    @FXML
    private void Scientific_mode(ActionEvent event) {
        

try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLScientific.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }

        
        
    }

    @FXML
    private void Standard_mode(ActionEvent event) {
        
try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
        
        
        
    }

    @FXML
    private void root_function(ActionEvent event) {
       // text.getText( );
        
    }
    @FXML
    protected void root_function_gui(String st) {
       // text.getText( );
        
    }
        
@FXML
    protected void func(String st) {
        text.setText( st);
        
    }
    
}
