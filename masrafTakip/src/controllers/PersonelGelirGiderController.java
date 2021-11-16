package controllers;

import essentials.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.loginLogModel;
import models.personelModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PersonelGelirGiderController implements Initializable {

    @FXML
    private TableColumn<personelModel, String> table_aciklama;

    @FXML
    private TableView<personelModel> urun_table;

    @FXML
    private TableColumn<personelModel, String> table_user;

    @FXML
    private Label fiyat;

    @FXML
    private TableColumn<personelModel, String> table_fiyat;


    ObservableList<personelModel> log_list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        log_list.clear();

        try
        {
            Connection connection = SQL.getConnection();

            String query = "Select * from personel_islem";
            PreparedStatement ps;
            ResultSet rs;

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                log_list.add(new personelModel(rs.getString("username"), rs.getString("aciklama"), rs.getInt("fiyat")));
                urun_table.setItems(log_list);
            }

            connection.close();
        }
        catch (Exception e){}

        table_user.setCellValueFactory(new PropertyValueFactory<>("username"));
        table_aciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
        table_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));

        int total = 0;

        for (personelModel p : log_list) {

            total+=p.getFiyat();
        }

        fiyat.setText(total+" TL");

    }
}
