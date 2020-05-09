package file;

import carRegister.Component;

import java.io.IOException;
import java.util.List;

public interface FileLoad {
    List<Component> load(String filename) throws IOException;
}
