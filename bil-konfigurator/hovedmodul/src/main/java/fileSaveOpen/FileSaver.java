package fileSaveOpen;



import carRegister.ComponentsRegister;

import java.io.IOException;
import java.nio.file.Path;

public interface FileSaver {

    void save(ComponentsRegister myRegistry, Path filePath) throws IOException;

}
