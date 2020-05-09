package file;

import java.io.IOException;

public interface FileSave {
    void save(String fileName, String fileContents) throws IOException;
}
