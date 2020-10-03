package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    Button button;

    ListView<String> listView;

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("PicDB Manager");

        //setup database connection
        Database picdb = new Database();
        picdb.connect();

        //picdb.loadPictureFolder("./resources/bilder/");   //only run once to populate db


        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        //StackPane root = new StackPane();


        //Image image = new Image();

        //VBox root = new VBox();


        // on windows: "C:/...."

        /*
        File file = new File("./resources/bilder/test.jpg");
        String localUrl = file.toURI().toURL().toString();
        Image image = new Image(localUrl);

        ImageView imageView = new ImageView(image);
*/

//        HBox hbox = new HBox(imageView);
//
//        imageView.setViewport(imagebox);
//        Scene scene = new Scene(hbox, 200,100);

 //       root.getChildren().addAll(imageView);


        Scene scene = new Scene(root, 840, 700);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();    //displays scene to user

        //System.out.println(System.getProperty("user.dir"));
    }

    public static void main(String[] args) {
        launch(args);


//        try {
//            SWE2.insert("INSERT INTO Fotografen_innen(Fotografen_ID, Vorname, Nachname, Geburtstag, Notizen) values (null, 'Michael', 'Schuhmacher', '1972-07-24', 'mag Schokokuchen');");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

    }
}
