package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BLTest {

    @Test
    void addPhotographer() {
    }

    @Test
    void editPhotographer() {
    }

    @Test
    void deletePhotographer() {
    }

    @Test
    void allPhotographersLoaded() {
        DALFactory.useMock();
        BL bl = new BL();
        bl.loadPhotographers();
        assertEquals(bl.getPhotographers().size(),1);
    }

    @Test
    void CorrectPhotographersLoaded() {
        DALFactory.useMock();
        BL bl = new BL();
        bl.loadPhotographers();
        assertEquals(bl.getPhotographers().get(0).getNachname(),"Mustermann");
    }

    @Test
    void getPhotographers() {
    }

    @Test
    void editPicture() {
    }
}