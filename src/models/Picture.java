package models;


import java.util.ArrayList;
import java.util.List;

public class Picture {
    int id;
    String path;
    Photographer photographer;
    EXIF exif;
    IPTC iptc;
    String notizen;

    public void loadPicture(int id, String path, String notizen, EXIF exif, IPTC iptc) {
        this.id = id;
        this.path = path;
        this.notizen = notizen;
        this.exif = exif;
        this.iptc = iptc;
    }

    public void setNotizen(String notizen) {
        this.notizen = notizen;
    }

    public void setExif(EXIF exif) {
        this.exif = exif;
    }

    public void setIptc(IPTC iptc) {
        this.iptc = iptc;
    }

    public void setPhotographer(Photographer photographer) {
        this.photographer = photographer;
    }

    public String getId() {
        return String.valueOf(id);
    }

    public String getPath() {
        return path;
    }

    public EXIF getExif() {
        return exif;
    }

    public IPTC getIptc() {
        return iptc;
    }

    public String getNotizen() {
        return notizen;
    }

    public Photographer getPhotographer() {
        return photographer;
    }

    public List<DataTupel> getExifList() {
        List<DataTupel> exifData = new ArrayList<>();
        exifData.add(new DataTupel("ISO",exif.getIso()));
        exifData.add(new DataTupel("Blende",exif.getBlende()));
        exifData.add(new DataTupel("Belichtung",exif.getBelichtung()));
        return exifData;
    }


    public List<DataTupel> getIptcList() {
        List<DataTupel> iptcData = new ArrayList<>();
        iptcData.add(new DataTupel("Ueberschrift",iptc.getUeberschrift()));
        iptcData.add(new DataTupel("Ort",iptc.getOrt()));
        iptcData.add(new DataTupel("Datum",iptc.getDatum()));
        return iptcData;
    }

}
