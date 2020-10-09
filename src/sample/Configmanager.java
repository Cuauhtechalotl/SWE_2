package sample;

import java.io.*;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//singletonPattern - lazy
public class Configmanager {

    private String separator = System.getProperty("file.separator");
    private String configPath = System.getProperty("user.dir")+ separator + "src" + separator + "sample" + separator + "config.txt";
    private List<String> configProperties = new ArrayList<>(
            Arrays.asList(
                    //filepath to pictures folder
                    "pathToPictures",
                    //database address
                    "dbURL",
                    //database driver
                    "dbDriver",
                    //username
                    "user",
                    //password
                    "pw"
            ));

    private List<String> configValues = new ArrayList<>();

    private static Configmanager firstInstance = null;

    private Configmanager() {}   //constructor has to be empty -> singleton, load data after init

    public static Configmanager getInstance() throws InterruptedException {

        if(firstInstance == null) {
            synchronized (Configmanager.class) {
                if (firstInstance == null) {

                    // If the instance isn't needed it isn't created
                    // This is known as lazy instantiation
                    firstInstance = new Configmanager();

                }
            }
        }
        // Under either circumstance this returns the instance
        return firstInstance;
    }

    //added config file to gitignore since different system paths can lead to problems windows/linux
    public void createConfig(List<String> configValues){
        //if file exists, it is being read and updated
        if(Files.exists(Paths.get(configPath))){
            System.out.println("config file already exists, using existing config file");

            try {
                FileReader fr = new FileReader(configPath);
                BufferedReader br = new BufferedReader(fr);
                String line;
                while((line=br.readLine())!=null) {

                    //System.out.println(line);

                    this.configValues.add(line);

                    //this.configValues.add(line);     //updating Values from configfile
                    //NOTE: updating configValues in this class, not the function parameter[createConfig(List<String> configValues)]
                }

                br.close();

            } catch (Exception e) {
                e.printStackTrace();
            }


            //if file does not exist, it is being created with preset values
        }else{
            //try block, can write into a text file several times
            try(FileWriter fw = new FileWriter(configPath, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)){

                for(int i=0; i<configProperties.size(); i++){
                    //printing one property per line
                    out. println(configProperties.get(i) + "=" + configValues.get(i));
                }
            }
            catch(IOException e){
                e.printStackTrace();
                System.out.println("IOException writing to config file.");
            }
        }
    }

    public List<String> getConfig (){
        System.out.println(configValues);


        List<String> values = new ArrayList<>();

        for(String x : configValues){
            values.add(x.substring(x.indexOf("=")+1));  //snipping everything away before the '=', +1 to snip the '=' itself
        }

        return values;  //values are configValues without the "path=" or "driver="
    }

    public String getPath(){
        return this.configValues.get(0);
    }

    public String getDbUrl(){
        return this.configValues.get(1);
    }

    public String getDbDriver(){
        return this.configValues.get(2);
    }

    public String getUser(){
        return this.configValues.get(3);
    }
}

