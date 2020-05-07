package fileSaveOpen;

import programutvikling.personregister.PersonRegister;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSaverTxt implements programutvikling.personregister.io.FileSaver {
    @Override
    public void save(PersonRegister registry, Path filePath) throws IOException {
        Files.write(filePath, registry.toString().getBytes());
    }

}
