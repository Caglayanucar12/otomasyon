package controllers;

import essentials.SQL;
import essentials.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.loginLogModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private Button kasaHareketleri_button;

    @FXML
    private Label userLabel;

    @FXML
    private TableColumn<loginLogModel, String> table_date;

    @FXML
    private Button kullaniciListele_button;

    @FXML
    private TableView<loginLogModel> user_log_table;

    @FXML
    private TableColumn<loginLogModel, String> table_user;

    @FXML
    private Button main_urunGirisi_button;

    @FXML
    private Button personelButton;

    @FXML
    private Button urunListele_button;

    @FXML
    void urunGirisi_clicked(ActionEvent event)
    {
        try {
            Stage stage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../designs/urunGirisi.fxml"));
            stage.setTitle("Çağlayan Uçar Masraf Takip Otomasyonu");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void kullanicilariListele_clicked(ActionEvent event)
    {
        try {
            Stage stage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../designs/kullaniciListele.fxml"));
            stage.setTitle("Çağlayan Uçar Masraf Takip Otomasyonu");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void urunleriListele_clicked(ActionEvent event)
    {
        try {
            Stage stage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../designs/urunListele.fxml"));
            stage.setTitle("Çağlayan Uçar Masraf Takip Otomasyonu");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void personel_clicked(ActionEvent event)
    {

        try {
            Stage stage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../designs/personelGelirGider.fxml"));
            stage.setTitle("Çağlayan Uçar Masraf Takip Otomasyonu");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void kasa_hareketleri_clicked(ActionEvent event)
    {

        try {
            Stage stage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("../designs/kasaHareketleri.fxml"));
            stage.setTitle("Çağlayan Uçar Masraf Takip Otomasyonu");
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String username = LoginController.usr.username;

        userLabel.setText(username);

        getUserLogs();
        
    }

    ObservableList<loginLogModel> log_list = FXCollections.observableArrayList();

    private void getUserLogs()
    {
        log_list.clear();

        try
        {
            Connection connection = SQL.getConnection();

            String query = "Select * from login_logs";
            PreparedStatement ps;
            ResultSet rs;

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                log_list.add(new loginLogModel(rs.getString("username"), rs.getString("date")));
                user_log_table.setItems(log_list);
            }

            connection.close();
        }
        catch (Exception e){}

        table_user.setCellValueFactory(new PropertyValueFactory<>("username"));
        table_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    }
}
