package sample;

import models.EXIF;
import models.IPTC;
import models.Photographer;
import models.Picture;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DALMock implements DAL{

    List<Photographer> photographers;
    List<Picture> pictures;

    public DALMock() {
        photographers = new ArrayList<>();
        photographers.add(new Photographer(1, "Max", "Mustermann", "1990-03-04", "Notiz"));
        photographers.add(new Photographer(2, "Peter", "Raab", "1975-07-11", "Notizen"));
        photographers.add(new Photographer(3, "John", "Johnson", "1986-10-01", "noch mehr Notizen"));

        EXIF exif1 = new EXIF("800","1/1.4", "1/1000");
        IPTC iptc1 = new IPTC("Blume","Wien","2020-03-14");

        EXIF exif2 = new EXIF("1600","1/2", "1/250");
        IPTC iptc2 = new IPTC("Kerze","Klosterneuburg","2020-03-14");

        EXIF exif3 = new EXIF("200","1/5.6", "1/250");
        IPTC iptc3 = new IPTC("Haus","Wien","2020-03-14");

        pictures = new ArrayList<>();
        Picture pic1 = new Picture();
        pic1.loadPicture(1,"./Haus.jpg","Outdoor, S/W",exif3,iptc3, photographers.get(1));
        pictures.add(pic1);
        Picture pic2 = new Picture();
        pic2.loadPicture(2,"./Kerze.jpg","Kerzenfoto",exif2,iptc2, photographers.get(0));
        pictures.add(pic2);
        Picture pic3 = new Picture();
        pic3.loadPicture(3,"./Blume.jpg","Dies ist eine Blume",exif1,iptc1, photographers.get(0));
        pictures.add(pic3);
    }

    @Override
    public void create_photographer(Photographer photographer) {

        photographers.add(photographer);
    }

    @Override
    public void edit_photographer(Photographer photographer) {

    }

    @Override
    public void delete_photographer(Photographer photographer) {

    }

    @Override
    public ResultSet execute(String s) {
        return null;
    }

    @Override
    public void edit_picture(Picture picture) {

    }

    @Override
    public List<Photographer> load_photographers() {
        return photographers;
    }

    @Override
    public Picture get_picture(String path) {
        for(Picture x : pictures) {
            if(x.getPath().equals(path)) { return x;}
        }
        return null;
    }



    @Override
    public void add_picture(String path) {

    }

    @Override
    public List<String> loadColumn(String bild, String dateipfad) {
        return null;
    }

    @Override
    public List<String> searchForEntry(String search) throws SQLException {
        return null;
    }
}
