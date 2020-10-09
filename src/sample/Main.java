package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends Application {

    Button button;

    ListView<String> listView;

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        primaryStage.setTitle("PicDB Manager");

        String separator = System.getProperty("file.separator");

        List<String> configValues = new ArrayList<>(
                Arrays.asList(
                        //filepath to pictures folder
                        System.getProperty("user.dir")+"SWE_2" + separator + "resources" + separator + "bilder" + separator,
                        //database address
                        "jdbc:mysql://127.0.0.1:3306/picdb?serverTimezone=Europe/Berlin",
                        //database driver
                        "com.mysql.cj.jdbc.Driver",
                        //username
                        "picdb user",
                        //pw
                        "swe2"
                ));

        //setup configmanager
        Configmanager config = Configmanager.getInstance();
        config.createConfig(configValues);

        //setup database connection
        DALDatabase picdb = DALDatabase.getInstance();
        picdb.setProperties();      //loads properties from config
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

        Scene scene = new Scene(root, 840, 700);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();    //displays scene to user

    }

    public static void main(String[] args) {
        launch(args);

    }
}
