package validator;


import java.util.regex.Pattern;

public class CarValidator {

    public static boolean name(String name){
        return Pattern.matches("^[A-Z].*",name);
    }
    public static boolean price(double price) {
        return price >= 0;
    }


}