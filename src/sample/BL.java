package sample;

import models.Photographer;
import models.Picture;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BL {


    private static BL bl = null;
    private DAL dal;

    public BL() {
        try {
            dal = DALFactory.getDAL();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static BL getBl() {
        if (bl == null)
        {
            bl = new BL();
        }
        return bl;
    }

    public void addPhotographer(Photographer photographer) {
        dal.create_photographer(photographer);
    }

    public void editPhotographer(Photographer photographer) {
        dal.edit_photographer(photographer);
    }

    public void deletePhotographer(Photographer photographer) {
        dal.delete_photographer(photographer);
    }


    public List<Photographer> getPhotographers() {
        return dal.load_photographers();
    }


    public void editPicture(Picture picture) {
        try {
            dal.edit_picture(picture);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> searchPictures(String search) {
        List<String> results = new ArrayList<>();
        if(search.length()==0) {
            return dal.loadColumn("Bild","Dateipfad");
        }
        else {
            try {
                return dal.searchForEntry(search);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public Picture getPicture(String path) {
        return dal.get_picture(path);
    }
}

