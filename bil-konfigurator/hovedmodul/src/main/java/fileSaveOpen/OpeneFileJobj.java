package fileSaveOpen;


import carRegister.ComponentsRegister;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class OpeneFileJobj implements OpenFile {
    @Override
    public void open(ComponentsRegister componentsRegister, Path filePath) throws IOException {
        try (InputStream fileInput = Files.newInputStream(filePath);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput))
        {
            ComponentsRegister register = (ComponentsRegister) objectInput.readObject();
            componentsRegister.removeAll();
            register.all_components().forEach(componentsRegister::addComponent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new IOException("Something is wrong with the implementation. See debug information");
        }
    }

}
