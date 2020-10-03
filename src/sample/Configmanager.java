package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

//singletonPattern
public class Configmanager {

    private String configPath = System.getProperty("user.dir")+""
    private List<String> configProperties;
    private List<String> configValues;

    private static Configmanager firstInstance = null;

    private Configmanager() {}   //constructor has to be empty -> singleton, load data after init

    public static Configmanager getInstance() throws InterruptedException {

        if(firstInstance == null) {
            synchronized (Configmanager.class) {
                if (firstInstance == null) {

                    firstInstance = new Configmanager();

                }
            }
        }
        return firstInstance;
    }

    public void createConfig(String message){

        if(Files.exists())



        // capturing and formatting time and date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        //try block, can write into a text file several times
        try(FileWriter fw = new FileWriter("config.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)){

            for(String property : configProperties){
                //printing one property per line
                out.println(property);
            }



        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("IOException writing to config file.");
        }
    }

    public void readConfig
}

