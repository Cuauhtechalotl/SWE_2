package sample;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
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
import models.PhotographerPM;

import java.util.*;
import java.util.stream.Collectors;

public class PhotographerController {

    List<PhotographerPM> photographers = new ArrayList<>();
    private ObservableList<PhotographerPM> data;

    @FXML TableView table;
    @FXML TableColumn<PhotographerPM,String> tID;
    @FXML TableColumn<PhotographerPM,String> tVorname;
    @FXML TableColumn<PhotographerPM,String> tNachname;
    @FXML TableColumn<PhotographerPM,String> tGeburtstag;
    @FXML TableColumn<PhotographerPM,String> tNotizen;

    @FXML TextField firstField;
    @FXML TextField surField;
    @FXML TextField birthField;
    @FXML TextField noteField;

// model ->pm: List<PicturePM> pms = List<Picture>
//                                  .stream()
//                                  .map(i -> new PicturePM(i))
//                                  .collect(Collectors.toList())
// dann GUI: Liste.setItems(FXCollections.observableArrayList(pms)

    @FXML public void loadDataHandler(ActionEvent event) {
        loadData();
    }


    @FXML public void loadData() {
        List<Photographer> list = BL.getBl().getPhotographers();
        photographers = list.stream().map(i -> new PhotographerPM(i)).collect(Collectors.toList());
        data = FXCollections.observableArrayList(photographers);
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
        tVorname.setOnEditCommit(edited -> {edited.getRowValue().setVorname(edited.getNewValue()); BL.getBl().editPhotographer(edited.getRowValue().getPhotographer());});
        tNachname.setCellFactory(TextFieldTableCell.forTableColumn());
        tNachname.setOnEditCommit(edited -> {edited.getRowValue().setNachname(edited.getNewValue()); BL.getBl().editPhotographer(edited.getRowValue().getPhotographer());});
        tNotizen.setCellFactory(TextFieldTableCell.forTableColumn());
        tNotizen.setOnEditCommit(edited -> {edited.getRowValue().setNotizen(edited.getNewValue()); BL.getBl().editPhotographer(edited.getRowValue().getPhotographer());});
        table.setEditable(true);
    }

    @FXML public void deleteEntry(KeyEvent event) {
        if (event.getCode().equals(KeyCode.DELETE) && table.getSelectionModel().getSelectedItem() != null)
        {
            int i = table.getSelectionModel().getSelectedIndex();
            BL.getBl().deletePhotographer(data.get(i).getPhotographer());
            data.remove(i);
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
