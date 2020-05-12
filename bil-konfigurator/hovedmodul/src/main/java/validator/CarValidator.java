package validator;


import carRegister.Component;
import carRegister.EngineType;
import carRegister.Model;
import exception.InvalidDataException;
import exception.InvalidNameException;
import exception.InvalidPriceException;
import forms.ComponentForm;
import forms.ModelForm;

import java.util.regex.Pattern;

public class CarValidator {

    public static boolean name(String name) {
        return Pattern.matches("^[A-Z].*", name);
    }

    public static boolean price(double price) {
        return price >= 0.0;
    }

    public static boolean check(EngineType engineType) {
        return engineType != null;
    }

    public static boolean check(double dbl) {
        return dbl >= 0.0;
    }

    public static boolean check(String str) {
        return Pattern.matches("^[A-Z].*", str);
    }

    public static void validate(ComponentForm componentForm) throws InvalidDataException {
        if (!CarValidator.name(componentForm.getName())) {
            throw new InvalidDataException("Invalid component name: " + componentForm.getName());
        }

        double componentPrice;
        try {
            componentPrice = Double.parseDouble(componentForm.getPrice());
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Invalid component price: " + componentForm.getPrice());
        }

        if (!CarValidator.price(componentPrice)) {
            throw new InvalidDataException("Invalid component price: " + componentForm.getPrice());
        }

    }

    public static void validate(ModelForm modelForm) throws InvalidDataException {
        if (!check(modelForm.getEngineType())) {
            throw new InvalidDataException("Invalid engineType: " + modelForm.getEngineType());
        }
        if (!check(modelForm.getBrand())) {
            throw new InvalidDataException("Invalid brand: " + modelForm.getBrand() + ". Try capital first letter.");
        }

        double modelPrice;
        try {
            modelPrice = Double.parseDouble(modelForm.getPrice());
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Invalid price: " + modelForm.getPrice());
        }
        if (!check(modelPrice)) {
            throw new InvalidDataException("Invalid price: " + modelForm.getPrice());
        }
    }

    public static void validate(Component component) throws InvalidDataException {
        if (!CarValidator.name(component.getComponentName())) {
            throw new InvalidDataException("Invalid component name: " + component.getComponentName() + ". Try capital first letter.");
        }

        if (!CarValidator.price(component.getComponentPrice())) {
            throw new InvalidDataException("Invalid component price: " + component.getComponentPrice());
        }

    }

    public static void validate(Model model) throws InvalidDataException {
        if (!check(model.getEngineType())) {
            throw new InvalidDataException("Invalid model: " + model.getEngineType());
        }


        if (!check(model.getPrice())) {
            throw new InvalidDataException("Invalid price: " + model.getPrice());
        }
    }
}