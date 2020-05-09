package file;

import carRegister.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JobjFileOperation {

    public List<Component> load(String filename) throws IOException {
        System.out.println("LOADFILE: " + filename);

        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Component> list = new ArrayList<>();
            int compCount = ois.readInt();
            for (int i = 0; i < compCount; i++) {
                list.add((Component) ois.readObject());
            }
            return list;
        } catch (EOFException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(List<Component> componentList, String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeInt(componentList.size());
        for (Component c : componentList) {
            oos.writeObject(c);
        }
        oos.close();
    }
}
