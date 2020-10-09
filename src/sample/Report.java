package sample;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import presentationmodels.PicturePM;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.util.List;

public class Report {

    private static String destination = null;

    private String documentName;

    public Report(){

    }

    public void createPdf(String destination, PicturePM foto) throws FileNotFoundException {
        
        File file = new File(destination);
        file.getParentFile().mkdirs();

        // init pdfwriter
        FileOutputStream fos = new FileOutputStream(destination);
        PdfWriter writer = new PdfWriter(fos);

        //init pdf document
        PdfDocument pdfDocument = new PdfDocument(writer);

        //init document
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Bild Report\n\n"));
        document.add(new Paragraph("Fotograf"));
        document.add(new Paragraph(foto.getPhotographer().getName()));


        // Creating an ImageData object
        String imFile = foto.getPath();
        ImageData data = null;
        try {
            data = ImageDataFactory.create(imFile);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Creating an Image object
        Image image = new Image(data);

        // Adding image to the document
        document.add(image);


        // Creating a table
        float [] pointColumnWidths = {200F, 200F};
        

        // EXIF HERE

        document.add(new Paragraph("EXIF"));

        Table table = new Table(pointColumnWidths);
        
        // Populating row 1 and adding it to the table
        Cell c1 = new Cell();                        // Creating cell 1
        c1.add("Belichtung = " + foto.getExif().getBelichtung());                              // Adding name to cell 1
        c1.setBackgroundColor(Color.GRAY);      // Setting background color
        c1.setBorder(Border.NO_BORDER);              // Setting border
        c1.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment
        table.addCell(c1);                           // Adding cell 1 to the table

        // Populating row 1 and adding it to the table
        Cell c2 = new Cell();                        // Creating cell 1
        c2.add("ISO = " + foto.getExif().getIso());                              // Adding name to cell 1
        c2.setBackgroundColor(Color.GRAY);      // Setting background color
        c2.setBorder(Border.NO_BORDER);              // Setting border
        c2.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment
        table.addCell(c2);                           // Adding cell 1 to the table

        // Populating row 1 and adding it to the table
        Cell c3 = new Cell();                       // Creating cell 1
        c3.add("Blende = " + foto.getExif().getBlende());                              // Adding name to cell 1
        c3.setBackgroundColor(Color.GRAY);      // Setting background color
        c3.setBorder(Border.NO_BORDER);              // Setting border
        c3.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment
        table.addCell(c3);                           // Adding cell 1 to the table


        // Adding Table to document
        document.add(table);


        // IPTC HERE 

        document.add(new Paragraph("IPTC"));

        Table table2 = new Table(pointColumnWidths);

        // Populating row 1 and adding it to the table
        Cell c4 = new Cell();                       // Creating cell 1
        c4.add("Überschrift = " + foto.getIptc().getUeberschrift());                              // Adding name to cell 1
        c4.setBackgroundColor(Color.LIGHT_GRAY);      // Setting background color
        c4.setBorder(Border.NO_BORDER);              // Setting border
        c4.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment
        table2.addCell(c4);                           // Adding cell 1 to the table

        // Populating row 1 and adding it to the table
        Cell c5 = new Cell();                        // Creating cell 1
        c5.add("Datum = " + foto.getIptc().getDatum());                              // Adding name to cell 1
        c5.setBackgroundColor(Color.LIGHT_GRAY);      // Setting background color
        c5.setBorder(Border.NO_BORDER);              // Setting border
        c5.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment
        table2.addCell(c5);                           // Adding cell 1 to the table

        // Populating row 1 and adding it to the table
        Cell c6 = new Cell();                        // Creating cell 1
        c6.add("Ort = " + foto.getIptc().getOrt());                              // Adding name to cell 1
        c6.setBackgroundColor(Color.LIGHT_GRAY);      // Setting background color
        c6.setBorder(Border.NO_BORDER);              // Setting border
        c6.setTextAlignment(TextAlignment.CENTER);   // Setting text alignment
        table2.addCell(c6);                           // Adding cell 1 to the table

        document.add(table2);


        //close document
        document.close();

    }

    public void setDestination(String destination){
        this.destination = destination;
    }

}
