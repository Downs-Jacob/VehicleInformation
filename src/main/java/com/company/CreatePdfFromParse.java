package com.company;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CreatePdfFromParse {
    //pdfGenerator accepts chosenFile paramater which is set in ChooseFile.java
    public void pdfGenerator(String chosenFile) throws IOException, CsvException, DocumentException {

        String filename = chosenFile;
        // create a new document pdf instance
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("vehicleinfo.pdf"));

        Path path = Path.of(chosenFile);

        Map<Integer, Map<String, List<Car>>> mapOfCarsByYearAndMake = Files.lines(path)
                .map(CsvParser::getCar)
                .collect(Collectors.groupingBy(Car::getYear, Collectors.groupingBy(Car::getMake)));

        // open document to begin formatting
        document.open();

        for (Integer year: mapOfCarsByYearAndMake.keySet()) {
            document.add(new Paragraph("All Vehicles of " + year.intValue()));
            Map<String, List<Car>> mapOfCarMake = mapOfCarsByYearAndMake.get(year);
            for (String make: mapOfCarMake.keySet()) {
                document.add(new Paragraph("    Make: " + make));
                Map<String, List<Car>> mapOfCarModel = mapOfCarMake.get(make).stream().collect(Collectors.groupingBy(Car::getModel));
                for (String model: mapOfCarModel.keySet()) {
                    document.add(new Paragraph("      Model: " + model));
                    Map<Integer, List<Car>> mapOfCarMsrp = mapOfCarModel.get(model).stream().collect(Collectors.groupingBy(Car::getMsrp));
                    for(Integer msrp: mapOfCarMsrp.keySet()) {
                        document.add(new Paragraph("      MSRP: $" + msrp));
                        document.add( Chunk.NEWLINE );
                    }
                }
            }
        }


        // close reader and document after for loop completes
        document.close();
    }
}
