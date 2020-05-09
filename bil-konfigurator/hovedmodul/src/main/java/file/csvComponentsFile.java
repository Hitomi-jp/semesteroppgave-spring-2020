package file;

import java.io.File;
import java.io.IOException;

public class csvComponentsFile implements FileOpen, FileSave {
    private static final String COMPONENTS_CSV_FILE = "./sample.csv";
    @Override
    public void open(File file) throws IOException {
        //return null;
    }

    @Override
    public void save(String fileName, String fileContents) throws IOException {

    }
}
