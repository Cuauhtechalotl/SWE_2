package models;

public class PhotographerPM {
    int id;
    String vorname;
    String nachname;
    String geburtsdatum;
    String notizen;

    public PhotographerPM(Photographer photographer)
    {
        id = Integer.parseInt(photographer.getId());
        vorname = photographer.getVorname();
        nachname = photographer.getNachname();
        geburtsdatum = photographer.getGeburtsdatum();
        notizen = photographer.getNotizen();
    }

    public Photographer getPhotographer() {
        Photographer photographer = new Photographer(id, vorname, nachname, geburtsdatum, notizen);
        return photographer;
    }

    public String getId()
    {
        if (id == 0) {String x = ""; return x;};
        return String.valueOf(id);
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

    public void setVorname(String input) {vorname = input;}

    public void setNachname(String input) {nachname = input;}

    public void setGeburtsdatum(String input) {geburtsdatum = input;}

    public void setNotizen(String notes) {
        notizen = notes;
    }

}