package sample;

import models.Photographer;
import models.Picture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAL {
    void create_photographer(Photographer photographer);

    void edit_photographer(Photographer photographer);

    void delete_photographer(Photographer photographer);

    ResultSet execute(String s);

    void edit_picture(Picture picture) throws SQLException;

    List<Photographer> load_photographers();

    Picture get_picture(String path);

    void add_picture(String path);

    List<String> loadColumn(String bild, String dateipfad);
}
