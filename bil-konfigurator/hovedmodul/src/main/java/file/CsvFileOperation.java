package file;

import carRegister.Car;
import carRegister.Component;
import carRegister.Customer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvFileOperation {

    public List<Component> load() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(""));
        String row = csvReader.readLine();
        while (row != null) {
            String[] data = row.split(",");
            // do something with the data
        }
        csvReader.close();
        return null;
    }

    private static final String CSV_SEPARATOR = ";";

    public void save(List<Customer> customerList, String filename) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), StandardCharsets.UTF_8));
        for (Customer customer : customerList) {
            StringBuffer line = new StringBuffer();
            line.append(customer.toCsv()).append(CSV_SEPARATOR);
            System.out.println(line);
//            bw.write(line.toString());
//            bw.newLine();
        }
//        bw.flush();
//        bw.close();

    }
//csvReader.close();
}
