package models;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

public class Picture {
    int id;
    Photographer photographer;
    EXIF exif;
    IPTC iptc;

    public int getId() {
        return id;
    }

    public EXIF getExif() {
        return exif;
    }

    public IPTC getIptc() {
        return iptc;
    }

    public class EXIF {
        String Iso;
        String Blende;
        String Belichtung;

        public String getIso(){
            return Iso;
        }

        public String getBlende(){
            return Blende;
        }

        public String getBelichtung(){
            return Belichtung;
        }
    }

    public class IPTC {
        String Ueberschrift;
        String Ort;
        String Datum;

        public String getUeberschrift(){
            return Ueberschrift;
        }

        public String getOrt(){
            return Ort;
        }

        public String getDatum(){
            return Datum;
        }
    }
}
