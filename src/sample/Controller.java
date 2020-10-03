package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.Scene;

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
    Image previewImage1,previewImage2,previewImage3,previewImage5,previewImage6;

    @FXML
    private ScrollBar scrollbar;

    @FXML
    private ListView<String> listViewPictures;
    @FXML
    private ListView<String> listViewEXIF;
    @FXML
    private ListView<String> listViewIPTC;

    private List<String> picturePaths;
    private int scrollbarValue = 0;


    @FXML
    public void initialize() throws SQLException, FileNotFoundException {  //contructor is called first then any @FXML, constructor doesnt have acces to any .fxml components, initialize() does.
        System.out.println("FXML initialized");

        Database picdb = new Database();
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
        Image img1 = new Image(new FileInputStream(System.getProperty("user.dir")+picturePaths.get(0+index).substring(1)));
        Image img2 = new Image(new FileInputStream(System.getProperty("user.dir")+picturePaths.get(1+index).substring(1)));
        Image img3 = new Image(new FileInputStream(System.getProperty("user.dir")+picturePaths.get(2+index).substring(1)));
        Image img4 = new Image(new FileInputStream(System.getProperty("user.dir")+picturePaths.get(3+index).substring(1)));
        Image img5 = new Image(new FileInputStream(System.getProperty("user.dir")+picturePaths.get(4+index).substring(1)));
        Image img6 = new Image(new FileInputStream(System.getProperty("user.dir")+picturePaths.get(5+index).substring(1)));
//        System.out.println("firstindex:" + 1+index);
//        System.out.println("lastindex:" + index+5);
        preview1.setImage(img1);
        preview2.setImage(img2);
        preview3.setImage(img3);
        preview4.setImage(img4);
        preview5.setImage(img5);
        preview6.setImage(img6);

    }

    private void setPreviewImage(int index) throws FileNotFoundException {

        Image img = new Image(new FileInputStream(System.getProperty("user.dir")+picturePaths.get(index-1).substring(1)));
        picture.setImage(img);

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
}
