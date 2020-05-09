package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenCsvH implements FileOpen, FileSave{

    @Override
    public void open(File file) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(file));
        String row = csvReader.readLine();
        while (row != null) {
            String[] data = row.split(",");
            // do something with the data
        }
        csvReader.close();
    }

    @Override
    public void save(Object o) throws IOException {

    }
//csvReader.close();
}
