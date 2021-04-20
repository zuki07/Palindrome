



package palindrome;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class Palindrome extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    String starting_text="--Try a Palindrome--";
    String get_text_str, text_str;
    

    @Override
    public void start(Stage primaryStage) {
//                            SETUP BUTTON
        Button try_btn = new Button("Try");
        
//                            SETUP TEXTFIELD
        TextField type_input=new TextField();
        type_input.setAlignment(Pos.CENTER);
        type_input.setMaxWidth(375);
        type_input.setPromptText("--Click Here to Type in a Palindrome--");
        type_input.setFocusTraversable(false);
        
//                            SETUP LABEL
        Label results=new Label("");
        
//                            SETUP VBOX
        VBox vbox=new VBox(type_input, try_btn, results);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(15);
//                            SETUP SCENE
        Scene scene = new Scene(vbox, 600, 500);
        scene.getStylesheets().add("styles.css");
        
//                            KEY ENTER LAMBDA EVENT
        scene.setOnKeyReleased((KeyEvent ke) -> {
            if (ke.getCode().equals(KeyCode.ENTER)){
                run(results, type_input);
            }
        });
        
//                            TRY BUTTON LAMBDA EVENT
        try_btn.setOnAction(event ->{
            run(results, type_input);
        });

        primaryStage.setTitle("Palindrome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
//                            RUN METHOD
    public void run(Label results, TextField input){
         get_text_str=input.getText().toLowerCase();                                    //get text field value and lower case it
         
            if (get_text_str.equals("")){                                               //if empty text field, error
                results.setText("Please Enter a Palindrome");
                results.setStyle("-fx-background-color: red;"+
                                 "-fx-padding: 5 15;"+
                                "-fx-text-fill: black;");
            }
            else{
               results.setStyle("");                                                    //reset results style
               results.setStyle("-fx-padding: 5 15;");
               
               StringBuilder reverse_str=new StringBuilder();
               StringBuilder check_str=new StringBuilder();
               
               check_str=charOnlyStr(get_text_str, check_str);                          //add text field value to string builder, characters only method
               text_str=check_str.toString();                                           //convert string builder to a string
               
               reverse_str=stringRecursion(text_str, reverse_str);                      //reverse the order of characters only string

                if (reverse_str.toString().equals(text_str)){                           //if reverse_str string builder equals char only string, it is a palindrome
                    results.setText("'"+input.getText()+"'"+" is a Palindrome");
                }
                else{
                    results.setText("'"+input.getText()+"'"+" is not a Palindrome");    //if reverse_str string builder does not equal char only string, it is not a palindrome
                } 
            }
    }
    
//                                CHAR ONLY METHOD
    public static StringBuilder charOnlyStr(String str, StringBuilder clean_str_builder){
        int str_length=str.length();

        if (str_length>0){
            String char_str=str.substring(0,1);                                                 //get first char from string
            StringBuilder delete_str=new StringBuilder(str);                                    //create delete_str string builder

            if (char_str.equals(".") | char_str.equals(" ") | char_str.equals(",")){            // if "." or " " or "," then delete it
                delete_str.delete(0, 1);
            }
            else{
                clean_str_builder.append(char_str);                                             //if char is a character, then add it to clean_str_builder and delete it
                delete_str.delete(0, 1);
            }

            str=delete_str.toString();                                                          //new value of str is saved
            charOnlyStr(str, clean_str_builder);                                                //recursion till string has only characters
        }
        return clean_str_builder;
    }
    
//                                    STRING RECURSION METHOD
    public static StringBuilder stringRecursion(String str, StringBuilder reverse){
        int str_length=str.length();

        if (str_length>0){
            String char_str=str.substring(str.length()-1, str.length());                        //get the last character of the string
            StringBuilder delete_str=new StringBuilder(str);                                    //pass string into new string builder

            reverse.append(char_str);                                                           //add last char of sting to reverse string builder
            delete_str.delete(str_length-1, str_length);                                        //delete last position of delete_str string builder

            str=delete_str.toString();                                                          //convert delete_str to a string
            stringRecursion(str, reverse);                                                      //recursion
        }
        return reverse;
    }
    


}
