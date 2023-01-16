package com.example.profilemanagementsystemfx;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatWindowController implements Initializable {
Stage stage;
Scene scene;
    @FXML
    public ListView<Messages> chatWindow;// = new ListView<>();
    @FXML
    private TextField chatIDTextInput;

    @FXML
    private TextField chatTextInput1;

    @FXML
    private TextField chatTextInput2;
    @FXML
    private Label welcomeLabel;
  public static  ObservableList<Messages> chatMessages = FXCollections.observableArrayList();

    @FXML
    void returnButtonClicked(ActionEvent event) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(UserApplication.class.getResource("user-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }catch (IOException e){
        e.printStackTrace();
    }
}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/java_class_db",
                        "root","admin");
    }
    @FXML
    public  void sendButton2(ActionEvent event) {
        String messageToSend2 = chatTextInput2.getText();
        chatMessages.add(new Messages(messageToSend2,"User2"));
        chatWindow.setItems(chatMessages);
        chatTextInput2.setText("");
        System.out.println(chatMessages);
    }

    public void  displayUsername(String username){
        welcomeLabel.setText("WELCOME: "+username);

    }
    @FXML
    public void sendButton1(ActionEvent event) {
      //  ObservableList<Messages> chatMessages = FXCollections.observableArrayList();
            String messageToSend = chatTextInput1.getText();
            chatMessages.add(new Messages(messageToSend, "User1"));
            chatWindow.setItems(chatMessages); // refreshed only one line of text if observable list is here
          //  chatWindow.getItems().addAll(chatMessages); // stores all input text in new lines if observable list is here but of outside of method then returns all text from start

            System.out.println(chatMessages);


    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

           sendButton1(new ActionEvent());
           sendButton2(new ActionEvent());

        chatWindow.setCellFactory(new Callback<>() {
            @Override
            public ListCell<Messages> call(ListView<Messages> list) {
                ListCell<Messages> cell = new ListCell<>() {

                    Label lblUserLeft = new Label();
                    Label lblTextLeft = new Label();
                    HBox hBoxLeft = new HBox(lblUserLeft, lblTextLeft);

                    Label lblUserRight = new Label();
                    Label lblTextRight = new Label();
                    HBox hBoxRight = new HBox(lblTextRight, lblUserRight);

                    {
                        hBoxLeft.setAlignment(Pos.CENTER_LEFT);
                        hBoxLeft.setSpacing(5);
                        hBoxRight.setAlignment(Pos.CENTER_RIGHT);
                        hBoxRight.setSpacing(5);
                        hBoxRight.setBackground(new Background(new BackgroundFill(Color.BEIGE,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));
                        hBoxLeft.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD,
                                CornerRadii.EMPTY,
                                Insets.EMPTY)));
                    }

                    @Override
                    public void updateItem(Messages item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            //   System.out.println(item.getFrom_user());
                            if (item.getFrom_user().equals("User1")) {
                                lblUserLeft.setText(item.getFrom_user() + ":");
                                lblTextLeft.setText(item.getMessage());
                                setGraphic(hBoxLeft);
                            } else {
                                lblUserRight.setText(":" + item.getFrom_user());
                                lblTextRight.setText(item.getMessage());
                                setGraphic(hBoxRight);
                            }
                        }
                    }
                };
                return cell;
            }

        });
    }
}


















//   String insertInMessage = "INSERT INTO messages (to_user,from_user,message, time) VALUES \n" +
//     "(?,?,?,now())";




//        if(!messageToSend.isEmpty()){
//            HBox hBox = new HBox();
//            hBox.setAlignment(Pos.CENTER_RIGHT); //  chat box with text appers in right center
//            hBox.setPadding(new Insets(5,5,5,10));
//            // display text
//            Text text = new Text(messageToSend);
//            // wrap text in text flow, if text is too big in scroll pane it gets wrapped
//            TextFlow textFlow = new TextFlow(text);
//
//            textFlow.setPadding(new Insets(5,10,5,10));
//            text.setFill(Color.color(0.934,0.945,0.996));
//
//            hBox.getChildren().add(textFlow);
//            vbox_messenger1.getChildren().add(hBox);
//
//          //  server.sendMessageToUser(messageToSend);
//            chatTextInput1.clear(); // clears chat message area after text is sent
//        }



//    public  static  void addLabel (String messageFromUser2, VBox vBox){
//
//        HBox hBox = new HBox();
//        hBox.setAlignment(Pos.CENTER_LEFT); //  chat box with text appers in right center
//        hBox.setPadding(new Insets(5,5,5,10));
//        // display text
//
//        Text text = new Text(messageFromUser2);
//        // wrap text in text flow, if text is too big in scroll pane it gets wrapped
//        TextFlow textFlow = new TextFlow(text);
//        //change style
//        textFlow.setStyle("-fx-background-color: rgb(233, 233, 235)" +
//                "-fx-background-radius: 20px)");
//        textFlow.setPadding(new Insets(5,10,5,10));
//
//        hBox.getChildren().add(textFlow);
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//                vBox.getChildren().add(hBox);
//            }
//        });
//
//
//    }