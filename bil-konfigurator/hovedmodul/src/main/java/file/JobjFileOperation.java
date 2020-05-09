package file;

import java.io.*;

public class JobjFileOperation implements FileOpen,FileSave {
    private final String FILENAME_REGISTRY = "Registry.dat";

    @Override
    public void open(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            ObjectInputStream ostream = new ObjectInputStream(fis);
            Object obj = "";
            try {
                obj = ostream.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            //return (String) obj;
        }
    }

    @Override
    public void save(Object o) throws IOException {
        FileOutputStream fos = new FileOutputStream(FILENAME_REGISTRY);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(o);
    }
}
