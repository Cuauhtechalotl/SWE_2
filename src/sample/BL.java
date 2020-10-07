package sample;

import javafx.collections.ObservableList;
import models.DataTupel;
import models.Photographer;
import models.Picture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BL {

    public List<Photographer> photographers = new ArrayList<Photographer>();

    private static BL bl = null;

    public static BL getBl() {
        if (bl == null)
        {
            bl = new BL();
            bl.loadPhotographers();
        }
        return bl;
    }

    public void addPhotographer(Photographer photographer) {
        Database swe2 = null;
        try {
            swe2 = Database.getInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        swe2.create_photographer(photographer);
        loadPhotographers();
    }

    public void editPhotographer(Photographer photographer) {
        Database swe2 = null;
        try {
            swe2 = Database.getInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        swe2.edit_photographer(photographer);
        loadPhotographers();
    }

    public void deletePhotographer(Photographer photographer) {
        Database swe2 = null;
        try {
            swe2 = Database.getInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        swe2.delete_photographer(photographer);
    }

    public void loadPhotographers() {

        photographers = new ArrayList<Photographer>();
        Database SWE2 = null;
        try {
            SWE2 = Database.getInstance();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = SWE2.execute("SELECT * FROM Fotografen_innen");
            while(rs.next()) {
                int id = rs.getInt("Fotografen_ID");
                String vorname = rs.getString("Vorname");
                String nachname = rs.getString("Nachname");
                String geburtstag = rs.getString("Geburtstag");
                String notizen = rs.getString("Notizen");
                Photographer photographer = new Photographer(id, vorname, nachname, geburtstag, notizen);
                photographers.add(photographer);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Photographer> getPhotographers() {
        return photographers;
    }


    public void editPicture(Picture picture) {
        Database swe2 = null;
        try {
            swe2 = Database.getInstance();
            swe2.edit_picture(picture);
        } catch (InterruptedException | SQLException e) {
            e.printStackTrace();
        }
    }
}

