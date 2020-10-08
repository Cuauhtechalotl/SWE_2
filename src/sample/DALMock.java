package sample;

import models.EXIF;
import models.IPTC;
import models.Photographer;
import models.Picture;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DALMock implements DAL{

    List<Photographer> photographers;
    List<Picture> pictures;

    public DALMock() {
        photographers = new ArrayList<>();
        photographers.add(new Photographer(1, "Max", "Mustermann", "01.01.2000", "Notiz"));

        EXIF exif = new EXIF("800","1/1.8", "1/1000");
        IPTC iptc = new IPTC("Titel","Wien","01.01.2020");
        pictures = new ArrayList<>();
        Picture pic = new Picture();
        pic.loadPicture(1,"./test.jpg","Notiz",exif,iptc, photographers.get(0));
        pictures.add(pic);
    }

    @Override
    public void create_photographer(Photographer photographer) {

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
        return null;
    }

    @Override
    public void add_picture(String path) {

    }
}
