package controllers;

import essentials.MessageBox;
import essentials.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.urunModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class urunListeleController
{
    @FXML
    private TextField fiyat;

    @FXML
    private TableView<urunModel> urun_table;

    @FXML
    private TableColumn<urunModel, String> table_urun_adi;

    @FXML
    private TextField urun_adi;

    @FXML
    private TableColumn<urunModel, String> table_fiyat;

    @FXML
    private TextField searchBox;

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

    @FXML
    private void initialize()
    {
        getProducts();

        FilteredList<urunModel> filteredData = new FilteredList<>(urun_list, p->true);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(urun -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (urun.getUrun_adi().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<urunModel> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(urun_table.comparatorProperty());

        urun_table.setItems(sortedData);
    }

    @FXML
    void urun_table_clicked(MouseEvent event)
    {
        try
        {
            urunModel model = urun_table.getSelectionModel().getSelectedItem();

            urun_adi.setText(model.getUrun_adi());
            fiyat.setText(model.getFiyat().substring(0, model.getFiyat().length()-3));
        } catch(Exception ex){}
    }

    private void updateUrun(String urun_adi, int fiyat)
    {
        try
        {
            Connection connection = SQL.getConnection();

            String query = "Update products set fiyat = ? where urun_adi = ?";
            PreparedStatement ps;

            ps = connection.prepareStatement(query);
            ps.setInt(1, fiyat);
            ps.setString(2, urun_adi);
            ps.executeUpdate();

            connection.close();
        }
        catch (Exception e){}
    }

    @FXML
    void fiyat_guncelle_button(ActionEvent event)
    {
        if (urun_adi.getText().isEmpty() || fiyat.getText().isEmpty())
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi","Lütfen güncellemek istediğiniz ürünün adını ve fiyatını giriniz. Tablodan seçebilirsiniz.", "warning");
        }
        else
        {
            updateUrun(urun_adi.getText(), Integer.parseInt(fiyat.getText()));

            getProducts();

            MessageBox.showMessage("Masraf Takip Bilgi Sistemi", urun_adi.getText()+" ürününün fiyatı güncellendi.");
        }
    }

    private void deleteUrun(String urun_adi)
    {
        try
        {
            Connection connection = SQL.getConnection();

            String query = "DELETE from products where urun_adi = ?";
            PreparedStatement ps;

            ps = connection.prepareStatement(query);
            ps.setString(1, urun_adi);
            ps.execute();

            connection.close();
        }
        catch (Exception e){}
    }

    @FXML
    void urunu_kaldir_clicked(ActionEvent event)
    {
        urunModel model = urun_table.getSelectionModel().getSelectedItem();

        if (model != null)
        {
            deleteUrun(model.getUrun_adi());

            getProducts();

            urun_adi.setText("");
            fiyat.setText("");

            MessageBox.showMessage("Masraf Takip Bilgi Sistemi", urun_adi.getText()+" ürünü sistemden kaldırıldı.");
        }
        else
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi", "Öncelikle silmek istediğiniz ürünü seçmelisiniz!");
        }
    }
}
