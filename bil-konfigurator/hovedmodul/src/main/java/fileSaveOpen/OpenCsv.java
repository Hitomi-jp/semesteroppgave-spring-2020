package fileSaveOpen;



import carRegister.Components;
import carRegister.ComponentsRegister;
import exception.PriceStringConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class OpenCsv implements OpenFile {
    @Override
    public void open(ComponentsRegister myRegister, Path filePath) throws IOException {
        myRegister.removeAll();
        try (BufferedReader bufferedReader = Files.newBufferedReader(filePath)) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                myRegister.addComponent(parseComponent(line));
            }
        }
    }

    private Components parseComponent(String line) throws IOException {

        String[] split = line.split(";");
        if (split.length != 2) {
            throw new IOException("You must use ; for separating data field");
        }

        String componentsName = split[0];
        double componentsPrice = parseComponentsPrice(split[1], "Price must be numbers");

        return new Components(componentsName, componentsPrice);
    }

        private double parseComponentsPrice(String str, String errorMessage) throws IllegalArgumentException {
            double number;
            try {
                number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(errorMessage);
            }

            return number;
        }
    }

