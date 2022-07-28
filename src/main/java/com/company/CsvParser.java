package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvParser {

    public CsvParser(String chosenFile) throws IOException {
        Path path = Path.of(chosenFile);

        Map<Integer, Map<String, List<Car>>> mapOfCarsByYearAndMake = Files.lines(path)
                .map(CsvParser::getCar)
                .collect(Collectors.groupingBy(Car::getYear, Collectors.groupingBy(Car::getMake)));

        for (Integer year: mapOfCarsByYearAndMake.keySet()) {
            System.out.println(year.intValue());
            Map<String, List<Car>> mapOfCarMake = mapOfCarsByYearAndMake.get(year);
            for (String make: mapOfCarMake.keySet()) {
                System.out.println("Make: " + make);
                Map<String, List<Car>> mapOfCarModel = mapOfCarMake.get(make).stream().collect(Collectors.groupingBy(Car::getModel));
                for (String model: mapOfCarModel.keySet()) {
                    System.out.println("Model: " + model);
                    Map<Integer, List<Car>> mapOfCarMsrp = mapOfCarModel.get(model).stream().collect(Collectors.groupingBy(Car::getMsrp));
                    for(Integer msrp: mapOfCarMsrp.keySet()) {
                        System.out.println("MSRP: " + msrp + "\n");
                    }
                }
            }
        }
    }


    //oreach a : b.getKeys()
    //  c = b.get(a) // instead of getting it from the file again
    //  foreach d : c.getKeys()
    //  e = c.get(d)

    //for (Integer year: mapOfXYZ.keys())
    //  Map<stuff> mapOfABC =  mapOfXYZ.get(year)
    //  for (String make : mapOfABC.keys())
    //      List<Car> cars = mapOfABC.get(make)
    //       for(Car car : cars)
    //          Do whatever you want

    private static Car getCar(String line) {
        String[] fields = line.split(",");
        return new Car(Integer.parseInt(fields[0]),fields[1], fields[2], Integer.parseInt(fields[3]));
    }
}