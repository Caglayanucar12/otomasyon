package controllers;

import essentials.MessageBox;
import essentials.SQL;
import essentials.User;
import essentials.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class kullaniciListeleController
{
    @FXML
    private TableView<User> kullanici_table;

    @FXML
    private TextField kullanici_adi;

    @FXML
    private TextField maas;

    @FXML
    private PasswordField sifre;

    @FXML
    private PasswordField tekrar_sifre;

    @FXML
    private TableColumn<User, String> table_kullanici_adi;

    @FXML
    private TableColumn<User, String> table_maas;

    @FXML
    private TextField searchBox;

    ObservableList<User> kullanici_list = FXCollections.observableArrayList();

    private void getUsers()
    {
        try
        {
            kullanici_list.clear();

            Connection connection = SQL.getConnection();

            String query = "Select * from users";
            PreparedStatement ps;
            ResultSet rs;

            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next())
            {
                kullanici_list.add(new User(rs.getString("username"), rs.getInt("maas")));
                kullanici_table.setItems(kullanici_list);
            }

            connection.close();
        }
        catch (Exception e){}

        table_kullanici_adi.setCellValueFactory(new PropertyValueFactory<>("username"));
        table_maas.setCellValueFactory(new PropertyValueFactory<>("maas"));
    }

    @FXML
    private void initialize()
    {
        getUsers();

        FilteredList<User> filteredData = new FilteredList<>(kullanici_list, p->true);

        searchBox.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(kullanici -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (kullanici.getUsername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        SortedList<User> sortedData = new SortedList<>(filteredData);

        sortedData.comparatorProperty().bind(kullanici_table.comparatorProperty());

        kullanici_table.setItems(sortedData);
    }

    private void updateSifre(String kullanici_adi, String password)
    {
        try
        {
            Connection connection = SQL.getConnection();

            String query = "Update users set password = ? where username = ?";
            PreparedStatement ps;

            ps = connection.prepareStatement(query);
            ps.setString(1, password);
            ps.setString(2, kullanici_adi);
            ps.executeUpdate();

            connection.close();
        }
        catch (Exception e){}
    }

    private void updateMaas(String kullanici_adi, int maas)
    {
        try
        {
            Connection connection = SQL.getConnection();

            String query = "Update users set maas = ? where username = ?";
            PreparedStatement ps;

            ps = connection.prepareStatement(query);
            ps.setInt(1, maas);
            ps.setString(2, kullanici_adi);
            ps.executeUpdate();

            connection.close();
        }
        catch (Exception e){}
    }

    @FXML
    void sifre_guncelle_button(ActionEvent event)
    {
        if (kullanici_adi.getText().isEmpty())
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi","Lütfen güncellemek istediğiniz kullanıcıyı tablodan seçiniz.", "warning");
        }

        else {

            if(sifre.getText().isEmpty() && tekrar_sifre.getText().isEmpty() && maas.getText().isEmpty())
            {
                MessageBox.showMessage("Masraf Takip Bilgi Sistemi","Lütfen güncellemek istediğiniz kullanıcının yeni bilgilerini giriniz.", "warning");
            }

            if (sifre.getText().isEmpty() && tekrar_sifre.getText().isEmpty() && !maas.getText().isEmpty())
            {

                updateMaas(kullanici_adi.getText(), Integer.parseInt(maas.getText()));

                MessageBox.showMessage("Masraf Takip Bilgi Sistemi", kullanici_adi.getText() + " kullanıcısının maaşı güncellendi.");

                kullanici_adi.setText("");
                sifre.setText("");
                tekrar_sifre.setText("");
                maas.setText("");

            }

            if (maas.getText().isEmpty() && (!sifre.getText().isEmpty() || !sifre.getText().isEmpty()))
            {
                if (!sifre.getText().equals(tekrar_sifre.getText())) {
                    MessageBox.showMessage("Masraf Takip Bilgi Sistemi", "Şifreler birbiriyle uyuşmuyor!", "warning");
                }
                else
                {
                    updateSifre(kullanici_adi.getText(), sifre.getText());

                    MessageBox.showMessage("Masraf Takip Bilgi Sistemi", kullanici_adi.getText() + " kullanıcısının şifresi güncellendi.");

                    kullanici_adi.setText("");
                    sifre.setText("");
                    tekrar_sifre.setText("");
                    maas.setText("");
                }
            }

            if(!sifre.getText().isEmpty() && !tekrar_sifre.getText().isEmpty() && !maas.getText().isEmpty())
            {
                updateMaas(kullanici_adi.getText(), Integer.parseInt(maas.getText()));

                if (!sifre.getText().equals(tekrar_sifre.getText())) {
                    MessageBox.showMessage("Masraf Takip Bilgi Sistemi", "Şifreler birbiriyle uyuşmuyor!", "warning");
                }
                else
                {
                    updateSifre(kullanici_adi.getText(), sifre.getText());
                }

                kullanici_adi.setText("");
                sifre.setText("");
                tekrar_sifre.setText("");
                maas.setText("");

                MessageBox.showMessage("Masraf Takip Bilgi Sistemi", kullanici_adi.getText() + " kullanıcısının bilgileri güncellendi.");

            }
        }
    }

    private void deleteKullanici(String username)
    {
        try
        {
            Connection connection = SQL.getConnection();

            String query = "DELETE from users where username = ?";
            PreparedStatement ps;

            ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.execute();

            connection.close();
        }
        catch (Exception e){}
    }

    @FXML
    void kullaniciyi_sil_clicked(ActionEvent event)
    {
        User model = kullanici_table.getSelectionModel().getSelectedItem();

        if (model != null)
        {
            deleteKullanici(model.getUsername());

            getUsers();

            kullanici_adi.setText("");

            MessageBox.showMessage("Masraf Takip Bilgi Sistemi", kullanici_adi.getText()+" kullanıcısı sistemden kaldırıldı.");
        }
        else
        {
            MessageBox.showMessage("Masraf Takip Bilgi Sistemi", "Öncelikle kaldırmak istediğiniz kullanıcıyı seçmelisiniz!");
        }
    }

    @FXML
    void kullanici_table_clicked(MouseEvent event)
    {
        try
        {
            User model = kullanici_table.getSelectionModel().getSelectedItem();

            kullanici_adi.setText(model.username);
            maas.setText(model.maas+"");
        } catch(Exception ex){}
    }
}
