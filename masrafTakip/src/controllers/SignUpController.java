package controllers;

import essentials.MessageBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import essentials.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignUpController {

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField repassword;

    @FXML
    private TextField username;

    private void signUp(String username, String password)
    {
        try
        {
            Connection connection = SQL.getConnection();

            String query = "Insert into users(username, password, maas) values(?, ?, ?)";
            PreparedStatement ps;

            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setInt(3, 0);

            ps.execute();

            connection.close();

            MessageBox.showMessage("Masraf Takip Bilgi Sistemi","Kayıt başarıyla oluşturuldu.");
        }
        catch (Exception e)
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi","Kayıt oluşturulamadı. Kayıt esnasında bir şeyler ters gitti.", "error");
        }
    }

    @FXML
    void signup(ActionEvent event)
    {
        if(username.getText().isEmpty() || password.getText().isEmpty() || repassword.getText().isEmpty())
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi","Lütfen kullanıcı adı ve şifre bölümlerini doldurunuz!", "error");
        }
        else if (!password.getText().equals(repassword.getText()))
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi","Şifreler aynı değil!", "error");
        }
        else
        {
            signUp(username.getText(), password.getText());
            username.setText("");
            password.setText("");
            repassword.setText("");
        }
    }
}
