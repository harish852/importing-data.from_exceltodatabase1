package com.example.snakeladderjan;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class snakeLadder extends Application {

    public static final int tileSize=40,height=10,width=10;
    int lowerLine  = tileSize*height;

    Player firstPlayer = new Player(tileSize, Color.BLACK,"Hari");
    Player secondPlayer = new Player(tileSize-10,Color.WHITE,"vedanth");
    Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+50);

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
        playerOneButton.setTranslateX(50);
        playerOneButton.setTranslateY(lowerLine+15);
        Button playerTwoButton  = new Button("Player Two");
        playerTwoButton.setTranslateX(200);
        playerTwoButton.setTranslateY(lowerLine+15);

        root.getChildren().addAll(boardImage,playerOneButton,playerTwoButton,firstPlayer.getCoin(), secondPlayer.getCoin());

        return root;
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