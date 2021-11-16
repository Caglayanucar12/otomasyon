package controllers;

import essentials.MessageBox;
import essentials.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class urunGirisiController
{
    @FXML
    private TextField urunGirisi_urunAdi_textField;

    @FXML
    private TextField urunGirisi_fiyat_textField;

    private void addUrun(String urun_adi, int fiyat)
    {
        try
        {
            Connection connection = SQL.getConnection();

            String query = "Insert into products(urun_adi, fiyat) values(?, ?)";
            PreparedStatement ps;

            ps = connection.prepareStatement(query);
            ps.setString(1, urun_adi);
            ps.setInt(2, fiyat);

            ps.execute();

            connection.close();

            urunGirisi_urunAdi_textField.setText("");
            urunGirisi_fiyat_textField.setText("");

        }
        catch (Exception e)
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi","Yeni ürün eklenemedi. Kayıt esnasında bir şeyler ters gitti.", "error");
        }
    }

    @FXML
    void urunGirisi_urunEkle_clicked(ActionEvent event)
    {
        if(urunGirisi_urunAdi_textField.getText().isEmpty() || urunGirisi_fiyat_textField.getText().isEmpty())
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi", "Lütfen eklenecek ürünün adını ve fiyatını giriniz!", "warning");
        }
        else
        {
            addUrun(urunGirisi_urunAdi_textField.getText(), Integer.parseInt(urunGirisi_fiyat_textField.getText()));

            MessageBox.showMessage("Masraf Takip Bilgi Sistemi", "Ürün başarıyla eklendi!");
        }
    }
}
