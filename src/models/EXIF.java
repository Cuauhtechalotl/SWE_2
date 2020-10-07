package models;

public class EXIF {
    String Iso;
    String Blende;
    String Belichtung;

    public EXIF(String iso, String blende, String belichtung) {
        this.Iso = iso;
        this.Blende = blende;
        this.Belichtung = belichtung;
    }

    public String getIso(){
        return Iso;
    }

    public String getBlende(){
        return Blende;
    }

    public String getBelichtung(){
        return Belichtung;
    }

    public void setBelichtung(String belichtung) {
        Belichtung = belichtung;
    }

    public void setBlende(String blende) {
        Blende = blende;
    }

    public void setIso(String iso) {
        Iso = iso;
    }
}
