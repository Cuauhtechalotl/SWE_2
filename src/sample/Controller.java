package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import models.DataTupel;
import models.Picture;
import models.PicturePM;

public class Controller {

    @FXML
    private ImageView picture;
    @FXML
    private ImageView preview1, preview2, preview3, preview4, preview5, preview6;

    @FXML
    ImageView[] previewList = {
            preview1,
            preview2,
            preview3,
            preview4,
            preview5,
            preview6
    };

    @FXML
    Image previewImage1,previewImage2,previewImage3,previewImage4, previewImage5,previewImage6;

    @FXML
    private ScrollBar scrollbar;

    @FXML
    private ListView<String> listViewPictures;
    @FXML
    private ListView<String> listViewEXIF;
    @FXML
    private ListView<String> listViewIPTC;

    private List<PicturePM> pictures;
    private List<String> picturePaths;
    private PicturePM pic;
    private int scrollbarValue = 0;

    public ObservableList<DataTupel> exif = null;
    public ObservableList<DataTupel> iptc = null;


    @FXML
    public void initialize() throws SQLException, FileNotFoundException, InterruptedException {  //contructor is called first then any @FXML, constructor doesnt have acces to any .fxml components, initialize() does.
        System.out.println("FXML initialized");

        DALDatabase picdb = null;
        try {
            picdb = DALDatabase.getInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //populating listview
        picturePaths = picdb.loadColumn("Bild","Dateipfad");       //load db here
        handlePreviewCarousel(0);

//        for(ImageView view : previewList){
//            int i = 0;
//            //System.out.println(picturePaths.get(i));
//            //Image img = new Image(new FileInputStream(System.getProperty("user.dir")+picturePaths.get(i).substring(1)));
//            Image img = new Image(new FileInputStream("/home/ego/IdeaProjects/SWE2/resources/bilder/test2.jpg"));
//
//
//            //System.out.println(picturePaths.get(i).substring(1));
//            //Image img = new Image(new FileInputStream(picturePaths.get(i)));
//            view.setImage(img);
//
//            i++;
//        }

        //listView1.setItems(FXCollections.observableList(values));
//        listViewPictures.setItems(FXCollections.observableList(picturePaths));            // oldfxml
//        listView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                System.out.println();
//            }
//        });

        preview1.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
//                System.out.println("you clicked preview one");

                //getting the preview pictures ID
                String clickedPreviewId = event.getSource().toString();
                //splitting the properties
//                System.out.println(clickedPreviewId);
                String[] tokens = clickedPreviewId.split(",");

                try {
                    //sets the main picture to the clicked preview picture in the carousel
                    //cutting the substring to get previewID, setting main picture to preview picture
                    //picture.setImage(handlePreviewCarousel(Integer.parseInt(tokens[0].substring(20))));
                    setPreviewImage(Integer.parseInt(tokens[0].substring(20))+scrollbarValue);



                    //loadMetadata(tokens[0].substring(20))+scrollbarValue);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        preview2.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String clickedPreviewId = event.getSource().toString();
//                System.out.println(clickedPreviewId);
                String[] tokens = clickedPreviewId.split(",");
                try {
                    //picture.setImage(handlePreviewCarousel(Integer.parseInt(tokens[0].substring(20))));
                    setPreviewImage(Integer.parseInt(tokens[0].substring(20))+scrollbarValue);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        preview3.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String clickedPreviewId = event.getSource().toString();
//                System.out.println(clickedPreviewId);
                String[] tokens = clickedPreviewId.split(",");
                try {
//                    picture.setImage(handlePreviewCarousel(Integer.parseInt(tokens[0].substring(20))));
                    setPreviewImage(Integer.parseInt(tokens[0].substring(20))+scrollbarValue);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        preview4.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String clickedPreviewId = event.getSource().toString();
//                System.out.println(clickedPreviewId);
                String[] tokens = clickedPreviewId.split(",");
                try {
                    //picture.setImage(handlePreviewCarousel(Integer.parseInt(tokens[0].substring(20))));
                    setPreviewImage(Integer.parseInt(tokens[0].substring(20))+scrollbarValue);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        preview5.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String clickedPreviewId = event.getSource().toString();
//                System.out.println(clickedPreviewId);
                String[] tokens = clickedPreviewId.split(",");
                try {
                    //picture.setImage(handlePreviewCarousel(Integer.parseInt(tokens[0].substring(20))));
                    setPreviewImage(Integer.parseInt(tokens[0].substring(20))+scrollbarValue);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        preview6.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                String clickedPreviewId = event.getSource().toString();
//                System.out.println(clickedPreviewId);
                String[] tokens = clickedPreviewId.split(",");
                try {
                    //picture.setImage(handlePreviewCarousel(Integer.parseInt(tokens[0].substring(20))));
                    setPreviewImage(Integer.parseInt(tokens[0].substring(20))+scrollbarValue);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        scrollbar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number old_val, Number new_val) {

                scrollbar.setMin(0);
                scrollbar.setMax(picturePaths.size()-6);
                scrollbar.setLayoutX(new_val.intValue());

//                System.out.println("scrollbar value " + scrollbar.getLayoutX());
                scrollbarValue = (int) scrollbar.getLayoutX();

                try {
                    handlePreviewCarousel((int) scrollbar.getLayoutX());
                } catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        });

        Metadata metadata = new Metadata();

//        try {
//            //                          image to be changed  >> changed image
//            metadata.removeExifMetadata(image, image2);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ImageReadException e) {
//            e.printStackTrace();
//        } catch (ImageWriteException e) {
//            e.printStackTrace();
//        }


//        URL imageUrl = getClass().getResource("test.jpg");
//        Image image23 = new Image(imageUrl.toExternalForm());
//
//        picture.setImage(image23);

        //        System.out.println(System.getProperty("user.dir"));       //checking from which dir this file is running
    }


    @FXML
    public void loadMetadata(int index){

        Metadata metadataReader = new Metadata();

        String[] metaTagArray = new String[6];
        try {
            metaTagArray = metadataReader.listTags(System.getProperty("user.dir")+picturePaths.get(0+index).substring(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        TODO: read metadata tags into textbox when picture is selected
        
//
//
//        listViewEXIF.setItems(FXCollections.observableList(Arrays.asList(Arrays.copyOfRange(metaTagArray, 0, 3))));
//
//        listViewIPTC.setItems(FXCollections.observableList(Arrays.asList(Arrays.copyOfRange(metaTagArray, 3, 6))));

    }


    private void handlePreviewCarousel(int index) throws FileNotFoundException {

//        System.out.println("previewCarousel index " + index);
        Image[] images = new Image[6];
        for(int i=0;i<=5;i++) {
            images[i] = new Image(new FileInputStream(System.getProperty("user.dir")+picturePaths.get(i+index).substring(1)));
        }
        preview1.setImage(images[0]);
        preview2.setImage(images[1]);
        preview3.setImage(images[2]);
        preview4.setImage(images[3]);
        preview5.setImage(images[4]);
        preview6.setImage(images[5]);

        cachePhotos(index);

//        System.out.println("firstindex:" + 1+index);
//        System.out.println("lastindex:" + index+5);
    }

    private void cachePhotos(int index) {
        pictures = new ArrayList<>();
        for(int i=0;i<=6;i++) {
            String path = "."+picturePaths.get(i + index).substring(1);
            System.out.println(path);
            pictures.add(new PicturePM(BL.getBl().getPicture(path)));
        }
    }

    private void setPreviewImage(int index) throws FileNotFoundException {

        Image img = new Image(new FileInputStream(System.getProperty("user.dir")+picturePaths.get(index-1).substring(1)));
        picture.setImage(img);
        pic = pictures.get(index);
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void pressButton(ActionEvent event){
//        Image image = new Image(getClass().getResourceAsStream("file:/home/ego/IdeaProjects/SWE2/resources/bilder/test2.jpg"));
//        picture.setImage(image);
        picture.setImage(new Image("file:/home/ego/IdeaProjects/SWE2/resources/bilder/test2.jpg"));
        System.out.println("changed pic");
    }

    @FXML public void showPhotographers(ActionEvent event) {
        try {
            Parent layout = FXMLLoader.load(getClass().getResource("photographer.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Photographers");
            stage.setScene(new Scene(layout, 450, 450));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML TextField notes;

    @FXML TableView exifTable;
    @FXML TableView iptcTable;
    @FXML TableColumn<String,String> eProp;
    @FXML TableColumn<String,String> eVal;
    @FXML TableColumn<String,String> iProp;
    @FXML TableColumn<String,String> iVal;

    @FXML public void loadData() throws SQLException, InterruptedException {

        notes.setText(pic.getNotizen());

        List<DataTupel> exifData = pic.getExifList();
        exif = FXCollections.observableArrayList(exifData);
        exifTable.setItems(exif);
        eProp.setCellValueFactory(new PropertyValueFactory<>("property"));
        eVal.setCellValueFactory(new PropertyValueFactory<>("value"));
        exifTable.getColumns().setAll(eProp, eVal);

        List<DataTupel> iptcData = pic.getIptcList();
        iptc = FXCollections.observableArrayList(iptcData);
        iptcTable.setItems(iptc);
        iProp.setCellValueFactory(new PropertyValueFactory<>("property"));
        iVal.setCellValueFactory(new PropertyValueFactory<>("value"));
        iptcTable.getColumns().setAll(iProp, iVal);

        eVal.setCellFactory(TextFieldTableCell.forTableColumn());
        eVal.setOnEditCommit(edited -> {
            int x = edited.getTablePosition().getRow();
            switch (x) {
                case 0 : pic.getExif().setIso(edited.getNewValue()); break;
                case 1 : pic.getExif().setBlende(edited.getNewValue()); break;
                case 2 : pic.getExif().setBelichtung(edited.getNewValue()); break;
            }
            BL.getBl().editPicture(pic.getPicture());
            exif.get(x).setValue(edited.getNewValue());});

        iVal.setCellFactory(TextFieldTableCell.forTableColumn());
        iVal.setOnEditCommit(edited -> {
            int x = edited.getTablePosition().getRow();
            switch (x) {
                case 0 : pic.getIptc().setUeberschrift(edited.getNewValue()); break;
                case 1 : pic.getIptc().setOrt(edited.getNewValue()); break;
                case 2 : pic.getIptc().setDatum(edited.getNewValue()); break;
            }
            BL.getBl().editPicture(pic.getPicture());
            iptc.get(x).setValue(edited.getNewValue());});

        iptcTable.setEditable(true);
        exifTable.setEditable(true);
    }

    @FXML public void saveNotes(ActionEvent event) {
        String note = notes.getText();
        pic.setNotizen(note);
        BL.getBl().editPicture(pic.getPicture());
    }
}
