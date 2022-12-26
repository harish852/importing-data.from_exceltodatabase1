package com.example.snakeladderjan;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class snakeLadder extends Application {

    public static final int tileSize=60,height=10,width=10;
    int lowerLine  = tileSize*height;
    int diceValue;

    Label rolledDiceValueLabel;

    boolean firstPlayerTurn=true , secondPlayerTurn  = false, gameStarted = false;

    Button startGameButton;

    Player firstPlayer = new Player(tileSize, Color.BLACK,"Hari");
    Player secondPlayer = new Player(tileSize-5,Color.WHITE,"Vicky");
    Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+80);

        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }
//        root.getChildren().add(new Tile(tileSize)

        Image img = new Image("C:\\Users\\USER\\IdeaProjects\\SnakeLadderJan\\src\\snake.jpg");
        ImageView boardImage = new ImageView();
        boardImage.setImage(img);
        boardImage.setFitWidth(tileSize*width);
        boardImage.setFitHeight(tileSize*height);

        Button playerOneButton  = new Button("Player One");
        playerOneButton.setTranslateX(30);
        playerOneButton.setTranslateY(lowerLine+20);
        playerOneButton.setFont(Font.font("Verdana" , FontWeight.BOLD,20));
        playerOneButton.setTextFill(Color.STEELBLUE);



        Button playerTwoButton  = new Button("Player Two");
        playerTwoButton.setTranslateX(420);
        playerTwoButton.setTranslateY(lowerLine+20);
        playerTwoButton.setFont(Font.font("Verdana" , FontWeight.BOLD,20));
        playerTwoButton.setTextFill(Color.STEELBLUE);


        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStarted){
                    if(firstPlayerTurn){
                        setDiceValue();
                        firstPlayer.movePlayer(diceValue);
                        if(firstPlayer.playerWon()!=null){
                            rolledDiceValueLabel.setText(firstPlayer.playerWon());
                            firstPlayerTurn=true;
                            secondPlayerTurn=false;
                            gameStarted = false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Restart");

                        }
                        firstPlayerTurn=false;
                        secondPlayerTurn=true;
                    }
                }

            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if (gameStarted) {
                    if (secondPlayerTurn) {
                        setDiceValue();
                        secondPlayer.movePlayer(diceValue);
                        if (secondPlayer.playerWon() != null) {
                            rolledDiceValueLabel.setText(secondPlayer.playerWon());
                            firstPlayerTurn=true;
                            secondPlayerTurn=false;
                            gameStarted = false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Restart");
                        }
                        secondPlayerTurn=false;
                        firstPlayerTurn=true;
                    }
                }
            }
        });

        startGameButton = new Button("Start");
        startGameButton.setTranslateY(lowerLine+45);
        startGameButton.setTranslateX(270);
        startGameButton.setFont(Font.font("verdana" , FontWeight.BOLD, FontPosture.REGULAR,15));
        startGameButton.setTextFill(Color.DARKGREEN);
        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted=true;
                startGameButton.setText("Ongoing");
                startGameButton.setDisable(true);

            }
        });

        rolledDiceValueLabel = new Label("Let's begin");
        rolledDiceValueLabel.setTranslateY(lowerLine+20);
        rolledDiceValueLabel.setTranslateX(260);
        rolledDiceValueLabel.setFont(Font.font("verdana" , FontWeight.BOLD, FontPosture.REGULAR,15));
        rolledDiceValueLabel.setTextFill(Color.RED);


        root.getChildren().addAll(boardImage,playerOneButton,playerTwoButton,firstPlayer.getCoin(),secondPlayer.getCoin(),
                rolledDiceValueLabel,startGameButton);
//                secondPlayer.getCoin());

        return root;
    }

    private void setDiceValue(){
        diceValue = (int)(Math.random()*6+1);   //math.random is a metod which gives random value between 0 and 1
        rolledDiceValueLabel.setText("Dice Value : "+ diceValue);
    }


    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}