package models;

public class IPTC {
    String Ueberschrift;
    String Ort;
    String Datum;

    public IPTC(String ueberschrift, String ort, String datum) {
        this.Ueberschrift = ueberschrift;
        this.Ort = ort;
        this.Datum = datum;
    }

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