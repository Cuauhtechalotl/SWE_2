package sample;


import models.Photographer;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


class Database{

    private String pathToPictures;
    private String dbURL;
    private String dbDriver;
    private String user;
    private String pw;

    private static Database firstInstance = null;

    private Database() {}   //constructor has to be empty -> singleton, load data after init

    public static Database getInstance() throws InterruptedException {

        if(firstInstance == null) {
            synchronized (Database.class) {
                if (firstInstance == null) {

                    // If the instance isn't needed it isn't created
                    // This is known as lazy instantiation
                    firstInstance = new Database();

                }
            }
        }
        // Under either circumstance this returns the instance
        return firstInstance;
    }

    public Connection connect() {

        try {
            Connection db = DriverManager.getConnection(dbURL, user, pw);

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
            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbURL, user, pw);
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

    public void setProperties() throws InterruptedException {
        Configmanager config = Configmanager.getInstance();
        List<String> properties = config.getConfig();
        this.pathToPictures = properties.get(0);
        this.dbURL = properties.get(1);
        this.dbDriver = properties.get(2);
        this.user = properties.get(3);
        this.pw = properties.get(4);

        // path url driver user pw

    }
}
