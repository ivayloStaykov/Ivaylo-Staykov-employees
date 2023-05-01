package services;

import models.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {
    public static List<Employee> readEmployeesFromCSV(String fileName) {
        List<Employee> employees = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {

                // use string.split to load a string array with the values from
                // each line of
                // the file, using a comma as the delimiter
                String[] attributes = line.split(",");

                Employee employee = createEmployee(attributes);

                // adding book into ArrayList
                employees.add(employee);

                // read next line before looping
                // if end of file reached, line would be null
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return employees;
    }

    private static Employee createEmployee(String[] metadata) {
        int id = Integer.parseInt(metadata[0]);
        int projectId = Integer.parseInt(metadata[1]);
        String from = metadata[2];
        String to = metadata[3];

        // create and return employee of this metadata
        return new Employee(id, projectId, from, to);
    }
}
