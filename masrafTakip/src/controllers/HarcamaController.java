package controllers;

import essentials.MessageBox;
import essentials.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class HarcamaController {

    @FXML
    private TextField fiyat_text;

    @FXML
    private TextField aciklama_text;

    @FXML
    void islemYap_clicked(ActionEvent event)
    {
        islemYap(LoginController.usr.username, aciklama_text.getText(), Integer.parseInt(fiyat_text.getText()));

        MessageBox.showMessage("Masraf Takip Bilgi Sistemi","İşlem yapıldı!");
    }

    void islemYap(String username, String aciklama, int fiyat)
    {
        try
        {
            Connection connection = SQL.getConnection();

            String query = "Insert into personel_islem(username, aciklama, fiyat) values(?, ?, ?)";
            PreparedStatement ps;

            ps = connection.prepareStatement(query);

            ps.setString(1, username);
            ps.setString(2, aciklama);
            ps.setInt(3, fiyat);

            ps.execute();

            connection.close();
        }
        catch (Exception e)
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi","İşlem yapılamadı!", "error");
        }
    }

}
