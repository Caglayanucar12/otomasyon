package controllers;

import essentials.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.kasaModel;
import models.personelModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class KasaHareketleriKontroller implements Initializable {

    @FXML
    private TableColumn<kasaModel, String> table_urun;

    @FXML
    private Label gider;

    @FXML
    private TableView<kasaModel> urun_table;

    @FXML
    private TableColumn<kasaModel, String> table_user;

    @FXML
    private TableColumn<kasaModel, String> table_adet;

    @FXML
    private Label gelir;

    @FXML
    private TableColumn<kasaModel, String> table_total;

    @FXML
    private TableColumn<kasaModel, String> table_tur;

    @FXML
    private TableColumn<kasaModel, String> table_fiyat;

    ObservableList<kasaModel> log_list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        log_list.clear();

        try
        {
            Connection connection = SQL.getConnection();

            String query = "Select * from islem";
            PreparedStatement ps;
            ResultSet rs;

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                log_list.add(new kasaModel(rs.getString("tur"), rs.getString("user"), rs.getString("urun_adi"), rs.getInt("fiyat"), rs.getInt("adet"), rs.getInt("toplam_fiyat")));
                urun_table.setItems(log_list);
            }

            connection.close();
        }
        catch (Exception e){}

        int gel = 0;
        int gid = 0;

        for(kasaModel k:log_list)
        {
            if(k.getTur().equals("Al"))
            {
                gid += k.getFiyat();
            }
            else
            {
                gel+=k.getFiyat();
            }
        }

        gelir.setText(gel+" TL");
        gider.setText(gid+" TL");

        table_user.setCellValueFactory(new PropertyValueFactory<>("user"));
        table_adet.setCellValueFactory(new PropertyValueFactory<>("adet"));
        table_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
        table_tur.setCellValueFactory(new PropertyValueFactory<>("tur"));
        table_urun.setCellValueFactory(new PropertyValueFactory<>("urun_adi"));
        table_total.setCellValueFactory(new PropertyValueFactory<>("toplam_fiyat"));

    }
}
