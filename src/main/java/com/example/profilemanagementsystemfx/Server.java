//package com.example.profilemanagementsystemfx;
//
//import javafx.application.Platform;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Text;
//import javafx.scene.text.TextFlow;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.URL;
//import java.util.ResourceBundle;
//
//public class Server {
//
//    private ServerSocket serverSocket;// listen for incomming connections or receives one to communicate who ever connects
//    Socket socket;
//
//public Server(ServerSocket serverSocket){
//    this.serverSocket=serverSocket;
//    this.socket=serverSocket.accept();
//
//}
//
//}
//
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            server = new Server(new ServerSocket(1234));
//
//        }catch (IOException e){
//            e.printStackTrace();
//            System.out.println("Error creating server");
//        }
//
//        // AN SCROLL TO BOTTOM WHEN MESSAGES ARE SENT, SO WE DONT NEED TO DO IT MANUALLY
//        vbox_messenger1.heightProperty().addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
//                scrollPane1.setVvalue((Double) newValue); //  set vertical value
//            }
//
//        });
//
////        vbox_messenger2.heightProperty().addListener(new ChangeListener<Number>() {
////            @Override
////            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
////                scrollPane2.setVvalue((Double) newValue);
////            }
////        });
//
//        server.receiveMessageFromUser(vbox_messenger1);
//        sendButton1(new ActionEvent());
//    }
//
//
//    @FXML
//    public void sendButton1(ActionEvent event) {
//        String messageToSend = chatTextInput1.getText();
//        if(!messageToSend.isEmpty()){
//            HBox hBox = new HBox();
//            hBox.setAlignment(Pos.CENTER_RIGHT); //  chat box with text appers in right center
//            hBox.setPadding(new Insets(5,5,5,10));
//            // display text
//            Text text = new Text(messageToSend);
//            // wrap text in text flow, if text is too big in scroll pane it gets wrapped
//            TextFlow textFlow = new TextFlow(text);
//            //change style
//            textFlow.setStyle("-fx-color: rgb(239, 242, 255)" +
//                    "-fx-background-color: rgb(15, 125, 242)" +
//                    "-fx-background-radius: 20px)");
//            textFlow.setPadding(new Insets(5,10,5,10));
//            text.setFill(Color.color(0.934,0.945,0.996));
//
//            hBox.getChildren().add(textFlow);
//            vbox_messenger1.getChildren().add(hBox);
//
//            server.sendMessageToUser(messageToSend);
//            chatTextInput1.clear(); // clears chat message area after text is sent
//        }
//    }
//
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
