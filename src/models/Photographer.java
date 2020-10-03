package models;

public class Photographer {
    int id;
    String vorname;
    String nachname;
    String geburtsdatum;
    String notizen;

    public int getId()
    {
        return id;
    }

    public String getVorname()
    {
        return vorname;
    }

    public String getNachname()
    {
        return nachname;
    }

    public String getGeburtsdatum()
    {
        return geburtsdatum;
    }

    public String getNotizen()
    {
        return notizen;
    }

    public void setNotizen(String notes) {
        notizen = notes;
    }

    public Photographer(int p_id, String vn, String nn, String gd, String nt)
    {
        id = p_id;
        vorname = vn;
        nachname = nn;
        geburtsdatum = gd;
        notizen = nt;
    }

    public Photographer(String vn, String nn, String gd, String nt)
    {
        vorname = vn;
        nachname = nn;
        geburtsdatum = gd;
        notizen = nt;
    }
}
