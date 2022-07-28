package com.company;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CreatePdf {
    //pdfGenerator accepts chosenFile paramater which is set in ChooseFile.java
    public void pdfGenerator(String chosenFile) throws IOException, CsvException, DocumentException {

        String filename = chosenFile;
        // create a new document pdf instance
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("vehicleinfo.pdf"));

        // open document to begin formatting
        document.open();
        // execute CSV reader and read the csv file provided
        CSVReader reader = new CSVReader(new FileReader(filename));
        List<String[]> r = reader.readAll();

        //loop over each entity in the csv file
        int listIndex = 0;
        for (String[] arrays : r) {
            if (listIndex == 0) {
                listIndex++;
                continue;
            }
            // save the title for each vehicle to the pdf file
            document.add(new Paragraph("\nVehicle " + listIndex++ + " : " + Arrays.toString(arrays)));

            int index = 0;
            // loop over each entity's cells and parse them out to be added as separate categories
            for (String array : arrays) {
                if (index == 0) {
                    document.add(new Paragraph("Year" + " : " + array));
                    index++;
                } else if (index == 1) {
                    document.add(new Paragraph("Make" + " : " + array));
                    index++;
                } else if (index == 2) {
                    document.add(new Paragraph("Model" + " : " + array));
                    index++;
                } else if (index == 3) {
                    document.add(new Paragraph("Price" + " : " + array));
                    index++;
                //example csv only had 4 categories, if a csv has more categories this will
                    // ensure they are printed with a simple
                } else {
                    document.add(new Paragraph(index++ + " : " + array));
                }
            }
        }
            // close reader and document after for loop completes
            document.close();
            reader.close();

    }
}

