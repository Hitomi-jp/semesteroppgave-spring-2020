package file;

import carRegister.Component;

import java.io.IOException;
import java.util.List;

public interface FileSave {
    void save(List<Component> componentList) throws IOException;
}
