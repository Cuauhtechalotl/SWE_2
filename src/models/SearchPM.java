package models;

import java.util.ArrayList;
import java.util.List;

public class SearchPM {

    List<String> picturePaths;

    public void clear(){
        picturePaths.clear();
    }

    public SearchPM(){
        picturePaths = new ArrayList<>();
    }

    public void addPicPaths(List<String> path) {
        if (path!=null) {
            picturePaths.addAll(path);
        }
    }

    public List<String> getPicturePaths() {
        return picturePaths;
    }
}

