package exception;

import carRegister.Components;
import javafx.scene.control.Alert;
import javafx.util.StringConverter;

import java.awt.*;


public class PriceStringConverter{

    public static class DoubleStringConverter extends javafx.util.converter.DoubleStringConverter {
        private boolean conversionSuccessful;

        @Override
        public Double fromString(String s) {
            try {
                Double result = super.fromString(s);
                conversionSuccessful = true;
                return result;
            } catch (NumberFormatException e) {
                Popups.showErrorDialog("Du må skrive inn et gyldig tall.");
                conversionSuccessful = false;
                return 0.0;
            }
        }

        public boolean wasSuccessful() {
            return conversionSuccessful;
        }
    }
    }
