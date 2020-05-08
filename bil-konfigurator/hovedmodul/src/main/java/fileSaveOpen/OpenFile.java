package fileSaveOpen;

import carRegister.ComponentsRegister;


import java.io.IOException;
import java.nio.file.Path;

public interface OpenFile {

    void open(ComponentsRegister componentsRegister, Path filePath) throws IOException;

}
