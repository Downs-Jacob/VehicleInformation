package com.company;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class CsvReader {

    public CsvReader() throws IOException, CsvException {
        //designate the location of the csv file
        String fileName = "/Users/downs/Desktop/VehicleInformation/src/main/vehicleinfo.csv";
        Path path = Path.of("src", "main","vehicleinfo.csv");

        //creates a new csvreader instance and looks for the designated .csv file
        try (
                CSVReader reader = new CSVReader(new FileReader(fileName))) {
            List<String[]> r = reader.readAll();

            int listIndex = 0;

            //for loop to parse each entry in the csv and create an output table
            for (String[] arrays : r) {
                if (listIndex == 0) {
                    listIndex++;
                    continue;
                }
                System.out.println("\nVehicle " + listIndex++ + " : " + Arrays.toString(arrays));

                int index = 0;
                //for loop to generate a
                for (String array : arrays) {
                    if (index == 0) {
                        System.out.println("Year" + " : " + array);
                        index++;
                    } else if (index == 1) {
                        System.out.println("Make" + " : " + array);
                        index++;
                    } else if (index == 2) {
                        System.out.println("Model" + " : " + array);
                        index++;
                    } else if (index == 3) {
                        System.out.println("Price" + " : " + array);
                        index++;
                    } else {
                        System.out.println(index++ + " : " + array);
                    }

                }
            }
        }
    }

}
