package sample;


import models.Photographer;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Database{

    private static Database db = null;

    public static Database getInstance() {
        if (db == null)
            db = new Database();
        return db;
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

    public Connection connect() {

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

    public ResultSet execute(String query) throws SQLException {
        Connection db = this.connect();
        Statement st = db.createStatement();
        return st.executeQuery(query);
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

    public void delete_photographer(Photographer photographer) {
        String query = "CALL delete_photographer("+photographer.getId()+");";
        try {
            insert(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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


    public void addPicture(String picturePath) throws SQLException {
        insert("INSERT INTO Bild(Bild_ID, Dateipfad, Notizen, ISO, Blende, Belichtungszeit, Ueberschrift, Ort, Datum, Fotografen_ID) values (null,'" + picturePath + "', 'gutes Bild', '800', '0.001', '0.2', 'Überschrift', 'Ort', 'Datum', null);");
        System.out.println("ok");
    }



    public void loadPictureFolder(String path){

        File folder = new File(path);
        File[] listofFiles = folder.listFiles();

        for(int i=0; i<listofFiles.length; i++){
            if(listofFiles[i].isFile() && listofFiles[i].getName().endsWith(".jpg")){
                try {
                    //System.out.println(listofFiles[i].getName()); //print filenames for debug purpose
                    addPicture(path+listofFiles[i].getName());
                    System.out.println(i + ": added <" + listofFiles[i].getName() + "> to database"); //print filenames for debug purpose
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
