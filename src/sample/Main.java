package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main extends Application {

    final Logger IOLogger = LogManager.getLogger("Input Output");

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        IOLogger.info("start of setup");

        primaryStage.setTitle("PicDB Manager");

        String separator = System.getProperty("file.separator");

        List<String> configValues = new ArrayList<>(
                Arrays.asList(
                        //filepath to pictures folder
                        System.getProperty("user.dir")+"SWE_2" + separator + "sample/resources" + separator + "bilder" + separator,
                        //database address
                        "jdbc:mysql://127.0.0.1:3306/picdb?serverTimezone=Europe/Berlin",
                        //database driver
                        "com.mysql.cj.jdbc.Driver",
                        //username
                        "root",
                        //pw
                        "root"
                ));






        //setup configmanager
        Configmanager config = Configmanager.getInstance();
        config.createConfig(configValues);

        //setup database connection
        DALDatabase picdb = DALDatabase.getInstance();
        picdb.setProperties();      //loads properties from config
        picdb.connect();

//        picdb.loadPictureFolder("./main.resources/bilder/");   //only run once to populate db

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Scene scene = new Scene(root, 840, 700);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();    //displays scene to user
    }

    public static void main(String[] args) {
        launch(args);
    }
}
