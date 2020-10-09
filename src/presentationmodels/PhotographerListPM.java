package presentationmodels;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhotographerListPM {
    List<PhotographerPM> photographers = new ArrayList<>();

    public PhotographerListPM(List<PhotographerPM> list) {
        photographers = list;
    }

    public List<PhotographerPM> getPhotographers() {
        return photographers;
    }

    public List<String> getNames() {
        return photographers.stream().map(i -> (i.getId()+" "+i.getVorname()+" "+i.getNachname())).collect(Collectors.toList());
    }

}
