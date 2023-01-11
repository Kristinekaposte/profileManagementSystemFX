package com.example.profilemanagementsystemfx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class UserController implements Initializable {
    Alert alert;
    Stage stage;
    PreparedStatement preparedStatement;
    Connection conn;


    @FXML
    private TextField firstNameTextInput;
    @FXML
    private TextField lastNameTextInput;
    @FXML
    private TextField genderTextInput;
    @FXML
    private TextField ageTextInput;
    @FXML
    private TextField emailTextInput;
    @FXML
    private TextField phoneTextInput;
    @FXML
    private TextField passwordTextInput;
    @FXML
    private TextField deleteIDButtonTextInput;
    @FXML
    private TextField updateIDButtonTextInput;

    @FXML
    private TextField loginPasswordTextInput;

    @FXML
    private TextField loginUsernameTextInput;
    @FXML
    private TableColumn<User, String> idColumn;
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> genderColumn;
    @FXML
    private TableColumn<User, String> ageColumn;
    @FXML
    private TableColumn<User, String> emailColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;
    @FXML
    private TableColumn<User, String> passwordColumn;
    @FXML
    private TableColumn<User, String> usernameColumn;



    @FXML
    private TableView<User> table;
    @FXML
    private AnchorPane myAnchorPane;

    public UserController() throws SQLException {
    }



    @FXML
    void signupButtonClicked(ActionEvent event) throws SQLException {
        String firstName,lastName,gender,age,email,phone,password;
        firstName = firstNameTextInput.getText();
        lastName = lastNameTextInput.getText();
        gender = genderTextInput.getText();
        age = ageTextInput.getText();
        email=emailTextInput.getText();
        phone = phoneTextInput.getText();
        password=passwordTextInput.getText();
        try {
            preparedStatement = getConnection().
                    prepareStatement("INSERT INTO UserData (firstName,lastName,gender,age,email,phone,password ) VALUES \n" +
                            "(?,?,?,?,?,?,?)");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,gender);
            preparedStatement.setInt(4, Integer.parseInt(age));
            preparedStatement.setString(5,email);
            preparedStatement.setInt(6, Integer.parseInt(phone));
            preparedStatement.setString(7,password);

            preparedStatement.executeUpdate();

            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("USER Registation");
            alert.setContentText("USER Record Addedddd!");

            alert.showAndWait();

            loadTableData();

            firstNameTextInput.setText("");
            lastNameTextInput.setText("");
            genderTextInput.setText("");
            ageTextInput.setText("");
            emailTextInput.setText("");
            phoneTextInput.setText("");
            passwordTextInput.setText("");


            firstNameTextInput.requestFocus();


        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }
    @FXML
    void updateButtonClicked(ActionEvent event) throws  SQLException{
        String id,firstName,lastName,gender,age,email,phone,password;
        id = updateIDButtonTextInput.getText();
        firstName = firstNameTextInput.getText();
        lastName = lastNameTextInput.getText();
        gender = genderTextInput.getText();
        age = ageTextInput.getText();
        email=emailTextInput.getText();
        phone = phoneTextInput.getText();
        password=passwordTextInput.getText();

        String updateUserData = "UPDATE  userData SET firstName=?,lastName=?,gender=?,age=?,email=?,phone=?,password=?  where id =?";
        try (Connection connection =  getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(updateUserData);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,gender);
            preparedStatement.setInt(4, Integer.parseInt(age));
            preparedStatement.setString(5,email);
            preparedStatement.setInt(6, Integer.parseInt(phone));
            preparedStatement.setString(7,password);
            preparedStatement.setInt(8, Integer.parseInt(id));

            preparedStatement.executeUpdate();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("USER Update");
            alert.setContentText("USER Record Updated!");

            alert.showAndWait();

            loadTableData();
            //CLEARS THE TEXT INPUT AFTER DATA IS ADDED
            updateIDButtonTextInput.setText("");
            firstNameTextInput.setText("");
            lastNameTextInput.setText("");
            genderTextInput.setText("");
            ageTextInput.setText("");
            emailTextInput.setText("");
            phoneTextInput.setText("");
            passwordTextInput.setText("");

            // AUTOMATICALLY BRINGS CURSOR INTO FIRST NAME FIELD
            firstNameTextInput.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void deleteButtonClicked(ActionEvent event) {
        String id;
        id = deleteIDButtonTextInput.getText();
        String deleteUserData = "DELETE FROM userData where id=?";
        try (Connection connection =  getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(deleteUserData);
            preparedStatement.setInt(1, Integer.parseInt(id));

            preparedStatement.executeUpdate();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("REMOVE USER");
            alert.setContentText("USER Record Deleted!");

            alert.showAndWait();

            loadTableData();
            deleteIDButtonTextInput.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        }

    @FXML
    void loginButtonClicked(ActionEvent event) {
        String username,password;
        username = loginUsernameTextInput.getText();
        password = loginPasswordTextInput.getText();
        String loginUser = "SELECT * FROM userdata WHERE  username LIKE"+ "'" +  username + "'"+"AND password= "+ "'" + password + "'";
        try (Connection conn =  getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(loginUser);
            if (rs.next()){
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("USER LOGIN");
                alert.setContentText("USER Has been logged_in!");

                alert.showAndWait();

            }else {
                // if username or password is not correct show
                alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("USER LOGIN");
                alert.setContentText("Please enter valid username or password");

                alert.showAndWait();

            }




            loadTableData();
          //  loginUsernameTextInput.setText("");
           //  loginPasswordTextInput.setText("");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection
                ("jdbc:mysql://localhost:3306/java_class_db",
                        "root","admin");
    }
  @Override
    public  void initialize (URL url, ResourceBundle rb)  { // for fxml to load/modify table data
      try {
          loadTableData();
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }

    private void loadTableData() throws SQLException {
        getConnection();
        ObservableList<User> user = FXCollections.observableArrayList(); // A List that notifies when changes are made.
                                                                        // An ObservableList bound to the UI will keep  it
                                                                        //up-to-date when changes occur.  used especially for fxml table

        String getUserData = "SELECT * FROM userdata";
        try( Connection conn =  getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(getUserData);

            while (rs.next()){
              User user1 = new User();
              // setting up mySQL table column names what inserts data in FXML
              user1.setId(Integer.parseInt(rs.getString("id")));
              user1.setFirstName(rs.getString("firstName"));
              user1.setLastName(rs.getString("lastName"));
              user1.setGender(rs.getString("gender"));
              user1.setAge(Integer.parseInt(rs.getString("age")));
              user1.setEmail(rs.getString("email"));
              user1.setPhone(Integer.parseInt(rs.getString("phone")));
              user1.setPassword(rs.getString("password"));
              user1.setUsername(rs.getString("username"));
              user.add(user1);

                }
            table.setItems(user); // adds all data into table
    // displaying data into fxml table
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
            ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
            usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

            st.close();
        rs.close();
    } catch (Exception e) {
        e.printStackTrace();

}
    }
    }
