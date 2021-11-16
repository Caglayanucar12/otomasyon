package controllers;

import essentials.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.islemlerimModel;
import models.urunModel;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class IslemlerimController implements Initializable {

    @FXML
    private TableColumn<islemlerimModel, String> table_aciklama;

    @FXML
    private TableView<islemlerimModel> urun_table;

    @FXML
    private TableColumn<islemlerimModel, String> table_fiyat;

    ObservableList<islemlerimModel> islem_list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        try
        {
            islem_list.clear();

            Connection connection = SQL.getConnection();

            String query = "Select * from personel_islem where username = ?";
            PreparedStatement ps;
            ResultSet rs;

            ps = connection.prepareStatement(query);
            ps.setString(1, LoginController.usr.username);
            rs = ps.executeQuery();

            while (rs.next())
            {
                islem_list.add(new islemlerimModel(rs.getString("aciklama"), rs.getInt("fiyat")+" TL"));
                urun_table.setItems(islem_list);
            }

            connection.close();
        }
        catch (Exception e){}

        table_aciklama.setCellValueFactory(new PropertyValueFactory<>("aciklama"));
        table_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));

    }
}
