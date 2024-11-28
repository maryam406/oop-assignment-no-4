package com.example.username;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        String imagePath = "file:/C:/Users/HP/Downloads/images(2.jpeg";
        System.out.println("Image Path: " + imagePath);
        Image image = new Image(imagePath);
        ImageView imageView=new ImageView(imagePath);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);


        String filepath="C:\\Users\\HP\\IdeaProjects\\username\\file.txt";
        Filemaking.createFile(filepath);

        Label username=new Label("UserName:");
        Label Password=new Label("Password:");

        TextField Usertext=new TextField();
        PasswordField paswordtext =new PasswordField();

        Button login=new Button("Login");
        Button exit=new Button("Exit");
        HBox hbox=new HBox();
        hbox.getChildren().addAll(login,exit);
        hbox.setSpacing(20);

        exit.setOnAction(actionEvent -> {
            stage.hide();
        });

        login.setOnAction(actionEvent -> {

            String Username = Usertext.getText();
            String password = paswordtext.getText();

            boolean loginSuccess = false;

            // Check if the credentials match any record in the file
            try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] credentials = line.split(":");
                    if (credentials.length == 2 && credentials[0].equals(Username) && credentials[1].equals(password)) {
                        loginSuccess = true;

                        break;

                    }
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            if (loginSuccess) {
                // Show a new stage (welcome screen) if login is successful
                Stage welcomeStage = new Stage();
                VBox welcomeLayout = new VBox(20, new Label("Welcome, " + Username + "!"));
                Scene welcomeScene = new Scene(welcomeLayout, 200, 100);
                welcomeStage.setTitle("Welcome");
                welcomeStage.setScene(welcomeScene);
                welcomeStage.show();
            } else {
                // Show an alert if login fails
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Failed");
                alert.setHeaderText("Invalid Username or Password");
                alert.setContentText("Please check your credentials and try again.");
                alert.showAndWait();
            }


        });





        GridPane gridPane = new GridPane();


        gridPane.setVgap(10); // Vertical gap between rows
        gridPane.setHgap(20); // Horizontal gap between columns
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(imageView, 0, 0);
        gridPane.add(username,0,1);
        gridPane.add(Usertext,1,1);
        gridPane.add(Password,0,2);
        gridPane.add(paswordtext,1,2);
        gridPane.add(hbox,0,3);

        VBox layout = new VBox(20, gridPane);
        layout.setAlignment(Pos.CENTER);




        Scene scene = new Scene(layout , 500, 200);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}