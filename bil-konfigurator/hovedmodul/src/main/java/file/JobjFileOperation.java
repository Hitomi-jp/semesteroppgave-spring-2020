package file;

import carRegister.Component;
import carRegister.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JobjFileOperation {

    public LoadedResources load(String filename) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            LoadedResources result = new LoadedResources();

            int compCount = ois.readInt();
            for (int i = 0; i < compCount; i++) {
                result.compList.add((Component) ois.readObject());
            }

            int modelCount = ois.readInt();
            for (int i = 0; i < modelCount; i++) {
                result.modelList.add((Model) ois.readObject());
            }

            return result;
        } catch (EOFException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class LoadedResources {
        public List<Component> compList = new ArrayList<>();
        public List<Model> modelList = new ArrayList<>();
    }

    public void save(List<Component> componentList, List<Model> modelList, String filename) throws IOException {
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeInt(componentList.size());
        for (Component c : componentList) {
            oos.writeObject(c);
        }
        oos.writeInt(modelList.size());
        for (Model m : modelList) {
            oos.writeObject(m);
        }
        oos.close();
    }
}
