package models;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Picture {
    int id;
    Photographer photographer;
    EXIF exif;
    IPTC iptc;
    String notizen;

    public void loadPicture(int id, EXIF exif, IPTC iptc) {
        this.id = id;
        this.exif = exif;
        this.iptc = iptc;
    }

    public int getId() {
        return id;
    }

    public EXIF getExif() {
        return exif;
    }

    public IPTC getIptc() {
        return iptc;
    }

    public List<DataTupel> getExifList() {
        List<DataTupel> exifData = new ArrayList<>();
        exifData.add(new DataTupel("ISO",exif.getIso()));
        exifData.add(new DataTupel("Blende",exif.getBlende()));
        exifData.add(new DataTupel("Belichtung",exif.getBelichtung()));
        return exifData;
    }

}
