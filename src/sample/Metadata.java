package sample;


import java.io.*;
import java.util.ArrayList;
import java.util.List;




class Metadata{

    private static Metadata instance = null;

    public static Metadata getInstance() {
        if (instance == null)
            instance = new Metadata();
        return instance;
    }

    public String[] listTags(String imagePath) throws IOException {
        //this function is supposed to select/filter the tags to be shown

        List<String> metadataTags = read(imagePath);
        String[] tagArray = new String[6];
//
//
//        //TODO: FIND NEW SOLUTION TAG INDIECES ARE DIFFERENT FOR EACH PICTURE(MISSING TAGS, MORE TAGS)
//        //
//        //* notes:    dbName            tagName      TODO:                         index          outputExample
//        //*           ISO               [Exif SubIFD] ISO Speed Ratings       19             [Exif SubIFD] Exposure Time - 0.01 sec
//        //*           Blende            [Exif SubIFD] F-Number                18             [Exif SubIFD] F-Number - f/5.0
//        //*           Belichtungszeit   [Exif SubIFD] Exposure Time           17             [Exif SubIFD] ISO Speed Ratings - 800 0 (unable to formulate description)
//        //
//        //small loop for debugging, uncomment to find out index of tags
//        int i = 0;
//        for(String tags: metadataTags){
//            System.out.println(i);
//            System.out.println(tags);
//            i++;
//        }
//        //EXIF
//        tagArray[0] = metadataTags.get(19);
//        tagArray[1] = metadataTags.get(18);
//        tagArray[2] = metadataTags.get(17);
//
//        //IPTC
//        tagArray[3] = metadataTags.get(19);
//        tagArray[4] = metadataTags.get(18);
//        tagArray[5] = metadataTags.get(17);
//
//        //need to add conditions to check the incoming tags for correct values!!!
//
        return tagArray;
    }

    public List<String> read(String imagePath) throws IOException {
        /*
        * this function uses the metadataextractor library to extract all metadata as shown on the
        * get-started page of the library in git
        *
        * https://github.com/drewnoakes/metadata-extractor/wiki/Getting-Started-(Java)
        *
        * returns a list of strings with all tags
        */

        List<String> metadataTags = new ArrayList<String>();
//
//        File pictureFile = new File(imagePath);
//        com.drew.metadata.Metadata metadata = ImageMetadataReader.readMetadata(pictureFile);
//
//        System.out.println("read metadata succesful!");
//
//        for (Directory directory : metadata.getDirectories()){
//            for(Tag tag : directory.getTags()){
//                metadataTags.add(tag.toString());
//            }
//        }
//
        return metadataTags;
    }
}
