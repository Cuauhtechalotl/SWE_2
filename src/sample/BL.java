package sample;

import models.Photographer;
import models.Picture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BL {

    public List<Photographer> photographers = new ArrayList<Photographer>();

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
            bl.loadPhotographers();
        }
        return bl;
    }

    public void addPhotographer(Photographer photographer) {
        dal.create_photographer(photographer);
        loadPhotographers();
    }

    public void editPhotographer(Photographer photographer) {
        dal.edit_photographer(photographer);
        loadPhotographers();
    }

    public void deletePhotographer(Photographer photographer) {
        dal.delete_photographer(photographer);
    }

    public void loadPhotographers() {

        photographers = dal.load_photographers();
    }


    public List<Photographer> getPhotographers() {
        return photographers;
    }


    public void editPicture(Picture picture) {
        try {
            dal.edit_picture(picture);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

