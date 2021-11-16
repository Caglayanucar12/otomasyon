package controllers;

import essentials.MessageBox;
import essentials.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.urunModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private TextField adet_textField;

    @FXML
    private TableView<urunModel> urun_table;

    @FXML
    private Label main_user_label;

    @FXML
    private TableColumn<urunModel, String> table_urun_adi;

    @FXML
    private TableColumn<urunModel, String> table_fiyat;

    ObservableList<urunModel> urun_list = FXCollections.observableArrayList();

    private void getProducts()
    {
        try
        {
            urun_list.clear();

            Connection connection = SQL.getConnection();

            String query = "Select * from products";
            PreparedStatement ps;
            ResultSet rs;

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                urun_list.add(new urunModel(rs.getString("urun_adi"), rs.getInt("fiyat")+" TL"));
                urun_table.setItems(urun_list);
            }

            connection.close();
        }
        catch (Exception e){}

        table_urun_adi.setCellValueFactory(new PropertyValueFactory<>("urun_adi"));
        table_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getProducts();

        String username = LoginController.usr.username;

        main_user_label.setText(username);

    }

    @FXML
    void al_clicked(ActionEvent event)
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        urunModel model = urun_table.getSelectionModel().getSelectedItem();

        int fiyat = Integer.parseInt(model.getFiyat().substring(0, model.getFiyat().length()-3));
        int toplam = fiyat * Integer.parseInt(adet_textField.getText());

        islemYap("Al", main_user_label.getText(), model.getUrun_adi(), fiyat, Integer.parseInt(adet_textField.getText()),toplam , formatter.format(date));

        MessageBox.showMessage("Kuru Temizleme Bilgi Sistemi","İşlem yapıldı!");
    }

    @FXML
    void sat_clicked(ActionEvent event)
    {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());

        urunModel model = urun_table.getSelectionModel().getSelectedItem();

        int fiyat = Integer.parseInt(model.getFiyat().substring(0, model.getFiyat().length()-3));
        int toplam = fiyat * Integer.parseInt(adet_textField.getText());

        islemYap("Sat", main_user_label.getText(), model.getUrun_adi(), fiyat, Integer.parseInt(adet_textField.getText()),toplam , formatter.format(date));

        MessageBox.showMessage("Kuru Temizleme Bilgi Sistemi","İşlem yapıldı!");

    }

    private void islemYap(String tur, String username, String urun_adi, int fiyat, int adet, int toplam, String tarih)
    {
        try
        {
            Connection connection = SQL.getConnection();

            String query = "Insert into islem(tur, user, urun_adi, fiyat, adet, toplam_fiyat, islem_tarihi) values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps;

            ps = connection.prepareStatement(query);

            ps.setString(1, tur);
            ps.setString(2, username);
            ps.setString(3, urun_adi);
            ps.setInt(4, fiyat);
            ps.setInt(5, adet);
            ps.setInt(6, toplam);
            ps.setString(7, tarih);

            ps.execute();

            connection.close();
        }
        catch (Exception e)
        {
            MessageBox.showMessage("Kuru Temizleme Bilgi Sistemi","İşlem yapılamadı!", "error");
        }

    }

    @FXML
    void harcama_clicked(ActionEvent event) {

        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../designs/Harcama.fxml"));
            stage.setTitle("Ã‡aÄŸlayan UÃ§ar Masraf Takip Otomasyonu");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void islemlerim_clicked(ActionEvent event)
    {

        try {

            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../designs/Islemlerim.fxml"));
            stage.setTitle("Ã‡aÄŸlayan UÃ§ar Masraf Takip Otomasyonu");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
