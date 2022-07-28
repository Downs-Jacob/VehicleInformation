package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvParser {

    public CsvParser(String chosenFile) throws IOException {
        //set the path of the file for the parser
        Path path = Path.of(chosenFile);
        //parse the chosenFile to Car.java object and group entries by year and make
        Map<Integer, Map<String, List<Car>>> mapOfCarsByYearAndMake = Files.lines(path)
                .map(CsvParser::getCar)
                .collect(Collectors.groupingBy(Car::getYear, Collectors.groupingBy(Car::getMake)));
        //A nest set of for loops that will iterate over sets and print them out by year, make, model, and msrp
        for (Integer year: mapOfCarsByYearAndMake.keySet()) {//for each year in the map of Years
            System.out.println("All Vehicles of " + year.intValue());//print year
            //create a list from previous that will display make
            Map<String, List<Car>> mapOfCarMake = mapOfCarsByYearAndMake.get(year);
            for (String make: mapOfCarMake.keySet()) {//for each make in the set
                System.out.println("Make: " + make);//print make
                //create list of models
                Map<String, List<Car>> mapOfCarModel = mapOfCarMake.get(make).stream().collect(Collectors.groupingBy(Car::getModel));
                for (String model: mapOfCarModel.keySet()) {//for each model in list
                    System.out.println("Model: " + model);//print model
                    //create a list of msrp
                    Map<Integer, List<Car>> mapOfCarMsrp = mapOfCarModel.get(model).stream().collect(Collectors.groupingBy(Car::getMsrp));
                    for(Integer msrp: mapOfCarMsrp.keySet()) { //for each msrp
                        System.out.println("MSRP: " + msrp + "\n"); //print msrp
                    }
                }
            }
        }
    }
    //parse list into 4 fields
    static Car getCar(String line) {
        String[] fields = line.split(",");
        return new Car(Integer.parseInt(fields[0]),fields[1], fields[2], Integer.parseInt(fields[3]));
    }
}
