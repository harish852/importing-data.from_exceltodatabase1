package com.example.dictionaryhari11december;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
public class Simple_Dictionary extends Application {

    Label meaningLabel;

    Label headerLabel;
    Button searchButton;
    TextField wordTextField;

    DictionaryUsingHashMap dictionaryUsingHashMap = new DictionaryUsingHashMap();

    ListView<String>suggestionList;
    Pane createContent(){

        Pane root=new Pane();
        root.setPrefSize(400,600);

        headerLabel=new Label("Enter Your Word");
        headerLabel.setTextFill(Color.BLACK);
        headerLabel.setFont(Font.font("Verdana" , FontWeight.BOLD,15));
        headerLabel.setTranslateX(35);;
        headerLabel.setTranslateY(20);

       wordTextField = new TextField();
        wordTextField.setTranslateX(30);
        wordTextField.setTranslateY(60);

         searchButton = new Button("Search");
        searchButton.setTranslateX(210);
        searchButton.setTranslateY(60);
        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                meaningLabel.setText("Button is clicked");
                wordTextField.getText();
                String word = wordTextField.getText();
                word=word.toLowerCase();
                if(word.isBlank() || word.isEmpty()){
                    meaningLabel.setText("please enter a word !");
                    meaningLabel.setTextFill(Color.RED);
                }
                else{
                    String meaning  = dictionaryUsingHashMap.getMeaning(word);
                    meaningLabel.setText(word.toUpperCase()+"  :  "+meaning);
                    meaningLabel.setTextFill(Color.STEELBLUE);
                }
            }
        });


        meaningLabel = new Label("Meaning : ");
        meaningLabel.setTranslateX(26);
        meaningLabel.setTranslateY(150);
        meaningLabel.setFont(Font.font("Verdana" , FontWeight.BOLD,14));
        meaningLabel.setTextFill(Color.STEELBLUE);
//
//        suggestionList = new ListView<>();
//        suggestionList.setTranslateX(20);
//        suggestionList.setTranslateY(100);
//        suggestionList.setMinSize(350,40);
//        suggestionList.setMaxSize(300,50);
//
//
//        String[] wordList = {"Hari","Pavan","Vicky"};
//        suggestionList.getItems().addAll(wordList);
//        suggestionList.setOrientation(Orientation.HORIZONTAL);
//
//        suggestionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                String selectedWord = suggestionList.getSelectionModel().getSelectedItem();
//                meaningLabel.setText(selectedWord);
//            }
//        });


        root.getChildren().addAll((Node) wordTextField,searchButton,meaningLabel,headerLabel);

        return root;
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Dictionary!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}