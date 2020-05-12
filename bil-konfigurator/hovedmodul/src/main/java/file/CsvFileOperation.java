package file;

import carRegister.*;
import exception.InvalidDataException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CsvFileOperation {

    public ObservableList<Customer> load(String filename) throws IOException {
        HashMap<String, Customer> customerMap = new HashMap<>();

        try {
            // Per;
            BufferedReader customerReader = new BufferedReader(new FileReader("Customer" + filename));
            String row = customerReader.readLine();
            while (row != null) {
                String[] data = row.split(";");
                customerMap.put(data[0], new Customer(data[0], FXCollections.observableArrayList()));
                row = customerReader.readLine();
            }
            customerReader.close();

            // Maja;GASOLINE;Mercedes SLK;260000.0;264500.0;
            BufferedReader carReader = new BufferedReader(new FileReader("Car" + filename));
            String carRow = carReader.readLine();
            while (carRow != null) {
                String[] data = carRow.split(";");
                customerMap.get(data[0]).getCarList().add(
                        new Car(new Model(EngineType.valueOf(data[1]), data[2], Double.parseDouble(data[3])))
                );

                carRow = carReader.readLine();
            }
            carReader.close();

            // Maja;Mercedes SLK;Blue color;4500.0;
            BufferedReader compReader = new BufferedReader(new FileReader("Component" + filename));
            String compRow = compReader.readLine();
            while (compRow != null) {
                String[] data = compRow.split(";");
                Customer cust = customerMap.get(data[0]);
                for (Car car : cust.getCarList()) {
                    if (car.getModel().getBrand().equals(data[1])) {
                        car.addComponent(new Component(data[2], Double.parseDouble(data[3])));
                    }
                }
                compRow = compReader.readLine();
            }
            compReader.close();
        } catch (IllegalArgumentException e) {
            throw new InvalidDataException("Error reading CSV " + e.getMessage());
        }


        return FXCollections.observableArrayList(customerMap.values());
    }

    private static final String CSV_SEPARATOR = ";";


    public void save(List<Customer> customerList, String filename) throws IOException {
//        Per;
        StringBuilder customerCsv = new StringBuilder();
        StringBuilder carCsv = new StringBuilder();
        StringBuilder componentCsv = new StringBuilder();
        for (Customer customer : customerList) {
            customerCsv.append(customer.getName()).append(";");
            customerCsv.append("\n");

            for (Car car : customer.getCarList()) {
                carCsv.append(customer.getName()).append(";");
                carCsv.append(car.getModel().getEngineType()).append(";");
                carCsv.append(car.getModel().getBrand()).append(";");
                carCsv.append(car.getModel().getPrice()).append(";");
                carCsv.append(car.getTotalPrice()).append(";");
                carCsv.append("\n");

                for (Component comp : car.getComponentList()) {
                    componentCsv.append(customer.getName()).append(";");
                    componentCsv.append(car.getModel().getBrand()).append(";");
                    componentCsv.append(comp.getComponentName()).append(";");
                    componentCsv.append(comp.getComponentPrice()).append(";");
                    componentCsv.append("\n");
                }
            }
        }

        BufferedWriter bwCustomer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Customer" + filename), StandardCharsets.UTF_8));
        bwCustomer.write(customerCsv.toString());
        bwCustomer.flush();
        bwCustomer.close();

        BufferedWriter bwCar = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Car" + filename), StandardCharsets.UTF_8));
        bwCar.write(carCsv.toString());
        bwCar.flush();
        bwCar.close();

        BufferedWriter bwComponent = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Component" + filename), StandardCharsets.UTF_8));
        bwComponent.write(componentCsv.toString());
        bwComponent.flush();
        bwComponent.close();
    }
}
