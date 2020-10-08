package sample;


import models.Photographer;
import models.Picture;
import models.EXIF;
import models.IPTC;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


class DALDatabase implements DAL{

//    private String pathToPictures=/home/ego/IdeaProjects/SWE_2SWE_2/resources/bilder/
//    private String dbURL=jdbc:mysql://127.0.0.1:3306/picdb?serverTimezone=Europe/Berlin
//    private String dbDriver=com.mysql.cj.jdbc.Driver
//    private String user=root


    private static DALDatabase firstInstance = null;

    public DALDatabase() {}   //constructor has to be empty -> singleton, load data after init

    public static DALDatabase getInstance() throws InterruptedException {

        if(firstInstance == null) {
            synchronized (DALDatabase.class) {
                if (firstInstance == null) {

                    // If the instance isn't needed it isn't created
                    // This is known as lazy instantiation
                    firstInstance = new DALDatabase();

                }
            }
        }
        // Under either circumstance this returns the instance
        return firstInstance;
    }

//    public database(String url, String name, String timezone, String driver, String username, String password){
//
//        String dbcUrl = url;
//        String dbcName = name;
//        String dbcTimezone = timezone;
//        String dbcDriver = driver;
//        String dbcUsername = username;
//        String dbcPassword = password;
//
//    }
//
//    //default(without Args): connects to localhost/mysql
//    public database(){
//
//        String dbcUrl = "jdbc:mysql://localhost:3306/";
//        String dbcName = "picdb";
//        String dbcTimezone = "?serverTimezone=Europe/Berlin";
//        String dbcDriver = "com.mysql.cj.jdbc.Driver";
//        String dbcUsername = "root";
//        String dbcPassword = "root";
//
//    }
//List<String> configValues
    public Connection connect() {

//        this.pathToPictures = configValues.get(0);
//        this.dbURL = configValues.get(1);
//        this.dbDriver = configValues.get(2);
//        this.user = configValues.get(3);


        try {
            //Connection db = DriverManager.getConnection(url, user, pwd);

            Connection db = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/picdb?serverTimezone=Europe/Berlin", "picdb_user", "swe2");


            if (db != null) {
                System.out.println("Connected to the database!");
                return db;
            } else {
                System.out.println("Failed to make connection!");
                throw new SQLException("Failed to make connection!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            System.out.println("database connect function");
            e.printStackTrace();
        }
        return null;

    }

    public void retrieve() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/picdb?serverTimezone=Europe/Berlin", "root", "root");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Fotografen_innen");
            while(rs.next())
            {
                int columnName1= rs.getInt("Fotografen_ID");
                String columnName2= rs.getString("Vorname");
                String columnName3= rs.getString("Nachname");
                String columnName4= rs.getString("Geburtstag");
                String columnName5= rs.getString("Notizen");

                System.out.println(columnName1+"\t"+ columnName2+"\t"+ columnName3+"\t"+ columnName4);
            }
        }catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public List<String> loadColumn(String table, String column) throws SQLException {
        List<String> columns = new ArrayList<String>();

        Connection con = connect();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select "+ column +" from "+ table +";");
        while(rs.next())
        {
            columns.add(rs.getString(column));

        }
        return columns;
    }

    public ResultSet execute(String query) {
        Connection db = this.connect();
        Statement st = null;
        try {
            st = db.createStatement();
            return st.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(String query) throws SQLException {
        try {
            Statement st = this.connect().createStatement();
            st.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create_photographer(Photographer photographer) {
        String[] entries = {photographer.getVorname(), photographer.getNachname(), photographer.getGeburtsdatum(), photographer.getNotizen()};
        for (int i = 0; i< entries.length; i++) {
            if (entries[i].length()==0) {
                entries[i] = "NULL";
            }
            else {
                entries[i] = "'"+entries[i]+"'";
            }
        }
        String query = "CALL create_photographer("+entries[0]+","+entries[1]+","+entries[2]+","+entries[3]+");";
        try {
            insert(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void edit_photographer(Photographer photographer) {
        String query = "CALL edit_photographer("+photographer.getId()+",'"+photographer.getVorname()+"','"+photographer.getNachname()+"','"+
                                                photographer.getGeburtsdatum()+"','"+photographer.getNotizen()+"');";
        try {
            insert(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete_photographer(Photographer photographer) {
        String query = "CALL delete_photographer("+photographer.getId()+");";
        try {
            insert(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Photographer> load_photographers() {
        List<Photographer> photographers = new ArrayList<Photographer>();
        try {
            ResultSet rs = execute("SELECT * FROM Fotografen_innen");
            while (rs.next()) {
                int id = rs.getInt("Fotografen_ID");
                String vorname = rs.getString("Vorname");
                String nachname = rs.getString("Nachname");
                String geburtstag = rs.getString("Geburtstag");
                String notizen = rs.getString("Notizen");
                Photographer photographer = new Photographer(id, vorname, nachname, geburtstag, notizen);
                photographers.add(photographer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return photographers;
    }
//    @Override
//    public void reset() throws SQLException {
//        //optional change to "C:" + File.seperator + "directory" + File.seperator + ....
//        Path filePath = Paths.get("./resources/SQL-Befehle/mysql.txt");
//        Charset charset = StandardCharsets.UTF_8;
//
//
//
//        try(BufferedReader bufferedReader = Files.newBufferedReader(filePath, charset)){
//            String line = "";
//            int value = 0;
//            while((value = bufferedReader.read()) != -1){
//                char c = (char) value;
//                if(c!= ';'){
//                    line += c;
//                }else if(c == ';'){
//                    line += ';';
//                    insert(line);
//                    System.out.println(line);
//                }
//            }
//        }catch (IOException ex){
//            System.out.format("I/O exception: ", ex);
//        }
//
//
////        try {
////            Statement st = this.connect().createStatement();
////            st.executeUpdate(query);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//    }


    public void add_picture(String picturePath){
        try {
            insert("INSERT INTO Bild(Bild_ID, Dateipfad, Notizen, ISO, Blende, Belichtungszeit, Ueberschrift, Ort, Datum, Fotografen_ID) values (null,'" + picturePath + "', 'gutes Bild', '800', '0.001', '0.2', 'Überschrift', 'Ort', 'Datum', null);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("ok");
    }

    public Picture get_picture(String paths) {
        Picture pic = new Picture();
        try {
            ResultSet rs = execute("SELECT * FROM bild WHERE Dateipfad = '" + paths + "';");
            rs.next();
            int id = rs.getInt("Bild_ID");
            String path = rs.getString("Dateipfad");
            String notizen = rs.getString("Notizen");
            String iso = rs.getString("ISO");
            String blende = rs.getString("Blende");
            String belichtung = rs.getString("Belichtungszeit");
            String ueberschrift = rs.getString("Ueberschrift");
            String ort = rs.getString("Ort");
            String datum = rs.getString("Datum");
            int photographerId = rs.getInt("Fotografen_ID");
            EXIF exif = new EXIF(iso, blende, belichtung);
            IPTC iptc = new IPTC(ueberschrift, ort, datum);
            Photographer photographer = new Photographer();
            if (photographerId > 0)
            {
                ResultSet rs2 = execute("SELECT * FROM Fotografen_innen WHERE Fotografen_ID = " + photographerId + ";");
                rs2.next();
                String vorname = rs2.getString("Vorname");
                String nachname = rs2.getString("Nachname");
                String geburtsdatum = rs2.getString("Geburtstag");
                String notizen2 = rs2.getString("Notizen");
                photographer = new Photographer(photographerId, vorname, nachname, geburtsdatum, notizen);
            }
            pic.loadPicture(id, path, notizen, exif, iptc, photographer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pic;
    }



    public void loadPictureFolder(String path){

        File folder = new File(path);
        File[] listofFiles = folder.listFiles();

        for(int i=0; i<listofFiles.length; i++){
            if(listofFiles[i].isFile() && listofFiles[i].getName().endsWith(".jpg")){
                try {
                    //System.out.println(listofFiles[i].getName()); //print filenames for debug purpose
                    add_picture(path+listofFiles[i].getName());
                    System.out.println(i + ": added <" + listofFiles[i].getName() + "> to database"); //print filenames for debug purpose
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void edit_picture(Picture picture) throws SQLException {
        Connection con = connect();
        PreparedStatement addPicture = con.prepareStatement("CALL edit_picture(?,?,?,?,?,?,?,?,?,?)");
        addPicture.setString(1,picture.getId());
        addPicture.setString(2,picture.getPath());
        addPicture.setString(3,picture.getNotizen());
        addPicture.setString(4,picture.getExif().getIso());
        addPicture.setString(5,picture.getExif().getBlende());
        addPicture.setString(6,picture.getExif().getBelichtung());
        addPicture.setString(7,picture.getIptc().getUeberschrift());
        addPicture.setString(8,picture.getIptc().getOrt());
        addPicture.setString(9,picture.getIptc().getDatum());
        if(picture.getPhotographer().getId() == null)
        {
            addPicture.setString(10,null);
        }
        else
        {
        addPicture.setString(10,picture.getPhotographer().getId());
        }

        System.out.println(addPicture.toString());
        addPicture.execute();
    }
}