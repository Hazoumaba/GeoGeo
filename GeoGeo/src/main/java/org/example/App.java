package org.example;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;


public class App extends Application {

    protected static ArrayList data=new ArrayList(0);
    protected static ArrayList Country = countryInfos.getInfos("", false);
    protected static  ArrayList Country2 = countryInfos.getInfos((String) Country.get(2), true);

    protected static int points;
    public static void main(String[] args) throws IOException {
        launch(args);
        fetchData();

    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Image image = new Image("file:///C:/Users/HP%20Pavillion/Desktop/JAVA/GeoGeo/src/main/java/org/example/img.png");  // Replace with the actual path to your image
        Image image1 = new Image(String.valueOf(Country.get(10)));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(120);
        imageView.setLayoutX(50);
        imageView.setLayoutY(30);
        ImageView imageView1 = new ImageView(image1);
        imageView1.setFitWidth(200);
        imageView1.setFitHeight(130);
        imageView1.setLayoutX(90);
        imageView1.setLayoutY(430);
        imageView1.setStyle("-fx-border-color: black; -fx-border-width: 20;");

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(8.0);
        dropShadow.setOffsetX(7.0);
        dropShadow.setOffsetY(7.0);
        dropShadow.setColor(Color.valueOf("#faedcd"));
        imageView1.setEffect(dropShadow);



        String[] words = ((String) Country.get(0)).split(" ");
        String result = String.join("\n", words);
        Text text = new Text(result);
        text.setStyle("-fx-font-size: 18px; -fx-fill: #faedcd; -fx-font-weight: bold;");


        Image backgroundImage = new Image("file:///D:/downloads/compass-near-old-maps%20(1).jpg");
        double desiredWidth = 800;
        double desiredHeight = 600;
        BackgroundSize backgroundSize = new BackgroundSize(desiredWidth, desiredHeight, false, false, true, true);
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                backgroundSize);

        Background backgroundWithImage = new Background(background);

        text.setLayoutX(153);
        text.setLayoutY(590);

        String[] tab= {"Capital" , "Contient" , "Currency (Code Or Name)", "Language (Code Or Name)",  "NameInFR", "CarSide", "Population <> than "+Country2.get(0)};
        Pane root = new Pane();
        TextField[] textAreas = new TextField[7];
        for (int i = 0; i < 7; i++) {
            textAreas[i] = new TextField();
            textAreas[i].setPromptText(tab[i]);

            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), textAreas[i]);
            scaleTransition.setFromX(1.0);
            scaleTransition.setToX(1.2);
            scaleTransition.setFromY(1.0);
            scaleTransition.setToY(1.2);

            double textX = 410;
            double textY = 390 + i * 40;
            textAreas[i].setLayoutX(textX);
            textAreas[i].setLayoutY(textY);
            textAreas[i].setMinSize(250, 20);
            textAreas[i].setMaxSize(450, 40);
            int finalI = i;
            textAreas[i].setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.ENTER) {
                    scaleTransition.play();
                    handleButtonClick(finalI,textAreas[finalI]);
                }
            });
            scaleTransition.setOnFinished(event -> {
                textAreas[finalI].setScaleX(1.0);
                textAreas[finalI].setScaleY(1.0);
            });
            root.getChildren().add(textAreas[i]);
        }
        Button res = new Button("SEND");
        res.setLayoutX(750);
        res.setLayoutY(450);
        res.setOnAction(event -> resButtonClick());
        res.setStyle("-fx-background-color: #fefae0; -fx-text-fill: black; -fx-font-size: 14px;-fx-font-weight: bold;");
        root.getChildren().add(res);
        root.getChildren().addAll(imageView1,text);
        Button next = new Button("SCORE");
        next.setLayoutX(750);
        next.setLayoutY(500);
        next.setStyle("-fx-background-color: #fefae0; -fx-text-fill: black; -fx-font-size: 14px;-fx-font-weight: bold;");
        next.setOnAction(event -> resButtonClick());
        root.getChildren().add(next);

        root.setBackground(backgroundWithImage);
        Scene scene = new Scene(root, 900, 700);

        primaryStage.setTitle("JavaFX Window");
        primaryStage.setScene(scene);
        primaryStage.show();

        res.setOnAction(e -> {
            try {
                fetchData();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            for (int i = 0; i <= 1; i++) {
                textAreas[i].clear();
                textAreas[i].appendText((String) Country.get(i+1));
            }
            textAreas[2].clear();
            textAreas[2].appendText((String) Country.get(3)+(String) Country.get(4));
            textAreas[3].clear();
            textAreas[3].appendText((String) Country.get(5)+(String) Country.get(6));
            for (int i = 4; i <= 5; i++) {
                textAreas[i].clear();
                textAreas[i].appendText((String) Country.get(i+3));
            }
            if (Integer.parseInt((String) Country.get(9))>Integer.parseInt((String) Country2.get(9))){
                textAreas[6].clear();
                textAreas[6].appendText("higher");
            }
            else {
                textAreas[6].clear();
                textAreas[6].appendText("lower");
            }

        });
        next.setOnAction(e -> {
            Pane root1 = new Pane();
            Label decorationLabel = new Label("YOU GOT "+String.valueOf(points)+" POINTS !");
            decorationLabel.setStyle("-fx-font-size: 18; -fx-text-fill: black; -fx-background-color: #faedcd; -fx-padding: 5;-fx-font-weight: bold;");
            decorationLabel.setLayoutX(0);
            decorationLabel.setLayoutY(450);
            decorationLabel.setMinSize(900, 20);
            decorationLabel.setMaxSize(1100, 40);
            decorationLabel.setAlignment(Pos.CENTER);
            root1.getChildren().add(decorationLabel);
            root1.setBackground(backgroundWithImage);
            Button exit = new Button("EXIT");
            exit.setStyle("-fx-background-color: #fefae0; -fx-text-fill: black; -fx-font-size: 14px;-fx-font-weight: bold;");
            exit.setLayoutX(425);
            exit.setLayoutY(500);
            exit.setOnAction(event -> resButtonClick());
            root1.getChildren().add(exit);
            Scene scene1 = new Scene(root1, 900, 700);
            primaryStage.setScene(scene1);
            exit.setOnAction(r -> System.exit(0));

        });

    }
    public static void fetchData() throws IOException{
        Platform.runLater(() -> {
            try {
                points=game.game(Country,Country2,data);
                ArrayList data=new ArrayList(0);
                ArrayList Country = countryInfos.getInfos("", false);
                ArrayList Country2 = countryInfos.getInfos((String) Country.get(2), true);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }


    private void handleButtonClick (int i,TextField inputField) {
        String userInput = inputField.getText();
        data.add(i,userInput);
    }
    private void resButtonClick () {
        System.out.println(points);
    }
}
