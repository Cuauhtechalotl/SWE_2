package sample;

import models.Photographer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class PhotographerController {

    ObservableList<Photographer> data = null;

    @FXML TableView table;
    @FXML TableColumn tID;
    @FXML TableColumn tVorname;
    @FXML TableColumn tNachname;
    @FXML TableColumn tGeburtstag;
    @FXML TableColumn tNotizen;

    @FXML TextField firstField;
    @FXML TextField surField;
    @FXML TextField birthField;
    @FXML TextField noteField;

    @FXML public void loadDataHandler(ActionEvent event) {
        loadData();
    }

    @FXML public void loadData() {
        data = FXCollections.observableArrayList(Data.getData().getPhotographers());
        table.setItems(data);
        tID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        tNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        tGeburtstag.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
        tNotizen.setCellValueFactory(new PropertyValueFactory<>("notizen"));
        table.getColumns().setAll(tID, tVorname, tNachname, tGeburtstag, tNotizen);

    }

    @FXML public void deleteEntry(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE) && table.getSelectionModel().getSelectedItem() != null)
        {
            int i = table.getSelectionModel().getSelectedIndex();
            Data.getData().deletePhotographer(data.get(i));
            data.remove(i);
            Data.getData().loadPhotographers();
            loadData();
        }
    }

    @FXML public void newEntry(ActionEvent event) {
        String vorname = firstField.getText();
        String nachname = surField.getText();
        String geburtstag = birthField.getText();
        String notizen = noteField.getText();
        Data.getData().addPhotographer(new Photographer(vorname, nachname, geburtstag, notizen));
        loadData();
    }

}
