package fileSaveOpen;

import carRegister.ComponentsRegister;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SaveCsv implements FileSaver{
    @Override
    public void save(ComponentsRegister registry, Path filePath) throws IOException {
        Files.write(filePath, registry.toString().getBytes());
    }

}
