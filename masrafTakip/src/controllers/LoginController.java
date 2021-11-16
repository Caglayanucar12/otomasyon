package controllers;

import essentials.MessageBox;
import essentials.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import essentials.SQL;

public class LoginController {

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private TextField username;

    public static User usr;

    boolean checkLogin(String username, String password)
    {
        boolean okay = false;

        try
        {
            Connection connection = SQL.getConnection();

            String query = "Select * from users";
            PreparedStatement ps;
            ResultSet rs;

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                if (username.equals(rs.getString("username")) && password.equals(rs.getString("password")))
                {
                    okay = true;
                    break;
                }
            }

            connection.close();
        }
        catch (Exception e)
        {
            return false;
        }

        return okay;
    }

    @FXML
    void login(ActionEvent event)
    {
        if(username.getText().isEmpty() || password.getText().isEmpty())
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi","Lütfen kullanıcı adınızı ve şifrenizi giriniz!", "error");
        }
        else
        {
            if (checkLogin(username.getText(), password.getText())) {
                usr = new User(username.getText());

                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                Date date = new Date(System.currentTimeMillis());

                addLog(usr.username, formatter.format(date));

                try {
                    if (username.getText().equals("admin")) {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("../designs/AdminPage.fxml"));
                        stage.setTitle("Masraf Takip Otomasyonu - Admin Panel");
                        stage.setScene(new Scene(root));
                        stage.initStyle(StageStyle.UTILITY);
                        stage.show();
                    } else {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("../designs/MainPage.fxml"));
                        stage.setTitle("Ã‡aÄŸlayan UÃ§ar Masraf Takip Otomasyonu");
                        stage.setScene(new Scene(root));
                        stage.initStyle(StageStyle.UTILITY);
                        stage.show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Stage currStage = (Stage)scenePane.getScene().getWindow();
                currStage.close();
            }
            else
            {
                MessageBox.showMessage("Masraf Takip Bilgi Sistemi","Böyle bir kayıt bulunmamaktadır!", "warning");
            }
        }
    }

    private void addLog(String username, String date)
    {
        try
        {
            Connection connection = SQL.getConnection();

            String query = "Insert into login_logs(username, date) values(?, ?)";
            PreparedStatement ps;

            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, date);

            ps.execute();

            connection.close();
        }
        catch (Exception e){}
    }

    @FXML
    void signup(ActionEvent event)
    {
        try {
            Stage stage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../designs/SignUp.fxml"));
            stage.setTitle("Kayıt Ol");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
