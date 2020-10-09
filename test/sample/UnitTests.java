package sample;

import models.Photographer;
import presentationmodels.PhotographerListPM;
import presentationmodels.PhotographerPM;
import models.Picture;
import org.junit.jupiter.api.Test;
import presentationmodels.PicturePM;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitTests {

    @Test
    void Should_Return_Picture_From_DB() {
        DALFactory.useMock();
        BL bl = new BL();
        Picture pic = bl.getPicture("./Blume.jpg");
        assertEquals(pic.getId(),"3");
    }

    @Test
    void Load_EXIF_Correctly() {
        DALFactory.useMock();
        BL bl = new BL();
        Picture pic = bl.getPicture("./Kerze.jpg");
        assertEquals(pic.getIptc().getUeberschrift(),"Kerze");
        assertEquals(pic.getIptc().getOrt(),"Klosterneuburg");
        assertEquals(pic.getIptc().getDatum(),"2020-03-14");
    }

    @Test
    void Get_Photographer_Of_Picture() {
        DALFactory.useMock();
        BL bl = new BL();
        Picture pic = bl.getPicture("./Haus.jpg");
        assertEquals(pic.getPhotographer().getNachname(),"Raab");
    }

    @Test
    void Created_PicturePM_Should_Have_Same_ID() {
        DALFactory.useMock();
        BL bl = new BL();
        Picture pic = bl.getPicture("./Haus.jpg");
        PicturePM picPM = new PicturePM(pic);
        assertEquals(pic.getId(),picPM.getId());
    }

    @Test
    void Created_PicturePM_Should_Contain_Same_EXIF() {
        DALFactory.useMock();
        BL bl = new BL();
        Picture pic = bl.getPicture("./Haus.jpg");
        PicturePM picPM = new PicturePM(pic);
        assertEquals(pic.getExif(),picPM.getExif());
    }

    @Test
    void Created_PicturePM_Should_Have_Same_Photograph() {
        DALFactory.useMock();
        BL bl = new BL();
        Picture pic = bl.getPicture("./Haus.jpg");
        PicturePM picPM = new PicturePM(pic);
        assertEquals(pic.getPhotographer(),picPM.getPhotographer());
    }



    @Test
    void Photographer_To_PhotographerPM_Should_Get_Name_Correctly() {
        DALFactory.useMock();
        BL bl = new BL();
        PhotographerPM grapherPM = new PhotographerPM(bl.getPhotographers().get(0));
        assertEquals(grapherPM.getNachname(),"Mustermann");
    }

    @Test
    void Photographers_To_PhotographerListPM_Should_Get_All_Photographers() {
        DALFactory.useMock();
        BL bl = new BL();
        List<Photographer> grapher = bl.getPhotographers();
        List<PhotographerPM> grapherPM = new ArrayList<>();
        for(Photographer x : grapher) { grapherPM.add(new PhotographerPM(x));};
        PhotographerListPM listPM = new PhotographerListPM(grapherPM);
        assertEquals(listPM.getPhotographers().size(),3);
    }

    @Test
    void PhotographerListPM_Should_Return_Full_Name_With_ID_For_DropdownMenu() {
        DALFactory.useMock();
        BL bl = new BL();
        List<Photographer> grapher = bl.getPhotographers();
        List<PhotographerPM> grapherPM = new ArrayList<>();
        for(Photographer x : grapher) { grapherPM.add(new PhotographerPM(x));};
        PhotographerListPM listPM = new PhotographerListPM(grapherPM);
        assertEquals(listPM.getNames().get(2),"3 John Johnson");
    }

    @Test
    void PhotographerListPM_Should_Return_All_Entries_For_Dropdown_Menu() {
        DALFactory.useMock();
        BL bl = new BL();
        List<Photographer> grapher = bl.getPhotographers();
        List<PhotographerPM> grapherPM = new ArrayList<>();
        for(Photographer x : grapher) { grapherPM.add(new PhotographerPM(x));};
        PhotographerListPM listPM = new PhotographerListPM(grapherPM);
        assertEquals(listPM.getNames().size(),3);
    }

    @Test
    void Should_Load_All_3_Photographers() {
        DALFactory.useMock();
        BL bl = new BL();
        assertEquals(bl.getPhotographers().size(),3);
    }

    @Test
    void Should_Load_First_ID_Photographer_First() {
        DALFactory.useMock();
        BL bl = new BL();
        assertEquals(bl.getPhotographers().get(0).getNachname(),"Mustermann");
    }
}