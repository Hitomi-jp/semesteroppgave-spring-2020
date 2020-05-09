package file;

import java.io.*;

public class JobjFileOperation implements FileOpen,FileSave {
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
    public void save(String fileName, String fileContents) throws IOException {
        FileOutputStream fos = new FileOutputStream("biOutput.dat");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(fileContents);

    }
}
