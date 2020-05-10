package file;

import carRegister.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LoadCsvH {

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

    public void save(Object o) throws IOException {

    }
//csvReader.close();
}
