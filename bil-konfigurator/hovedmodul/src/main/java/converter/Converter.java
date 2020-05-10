package converter;

import exception.Popups;

import java.text.ParseException;

public class Converter {


   public static class Price extends javafx.util.converter.NumberStringConverter {
        private boolean conversionSuccessful;

        @Override
        public Number fromString(String s) {
            try {
                Number result = super.fromString(s);
                if (result == null)
                    return 0.00;
                return result;
            } catch (RuntimeException pe) {
                Popups.showErrorDialog("'" + s +"' is invalid. Only number allowed.");
                return 0.0;
            }
        }
    }
}
