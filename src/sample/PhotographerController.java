package sample;

import javafx.scene.control.cell.TextFieldTableCell;
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
    @FXML TableColumn<Photographer,String> tID;
    @FXML TableColumn<Photographer,String> tVorname;
    @FXML TableColumn<Photographer,String> tNachname;
    @FXML TableColumn<Photographer,String> tGeburtstag;
    @FXML TableColumn<Photographer,String> tNotizen;

    @FXML TextField firstField;
    @FXML TextField surField;
    @FXML TextField birthField;
    @FXML TextField noteField;

    @FXML public void loadDataHandler(ActionEvent event) {
        loadData();
    }

    @FXML public void loadData() {
        data = FXCollections.observableArrayList(BL.getBl().getPhotographers());
        table.setItems(data);
        tID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        tNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        tGeburtstag.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
        tNotizen.setCellValueFactory(new PropertyValueFactory<>("notizen"));
        table.getColumns().setAll(tID, tVorname, tNachname, tGeburtstag, tNotizen);
        setEditable();

    }

    @FXML public void setEditable() {
        tVorname.setCellFactory(TextFieldTableCell.forTableColumn());
        tVorname.setOnEditCommit(edited -> {edited.getRowValue().setVorname(edited.getNewValue()); BL.getBl().editPhotographer(edited.getRowValue());});
        tNachname.setCellFactory(TextFieldTableCell.forTableColumn());
        tNachname.setOnEditCommit(edited -> {edited.getRowValue().setNachname(edited.getNewValue()); BL.getBl().editPhotographer(edited.getRowValue());});
        tNotizen.setCellFactory(TextFieldTableCell.forTableColumn());
        tNotizen.setOnEditCommit(edited -> {edited.getRowValue().setNotizen(edited.getNewValue()); BL.getBl().editPhotographer(edited.getRowValue());});
        table.setEditable(true);
    }

    @FXML public void deleteEntry(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE) && table.getSelectionModel().getSelectedItem() != null)
        {
            int i = table.getSelectionModel().getSelectedIndex();
            BL.getBl().deletePhotographer(data.get(i));
            data.remove(i);
            BL.getBl().loadPhotographers();
            loadData();
        }
    }

    @FXML public void newEntry(ActionEvent event) {
        String vorname = firstField.getText();
        String nachname = surField.getText();
        String geburtstag = birthField.getText();
        String notizen = noteField.getText();
        BL.getBl().addPhotographer(new Photographer(vorname, nachname, geburtstag, notizen));
        loadData();
    }

}
