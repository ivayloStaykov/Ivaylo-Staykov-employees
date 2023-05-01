package validators;

import common.CONSTANTS;

import java.time.LocalDateTime;

public class ValidateEmployee {
    public static String validateDate(String date) {
        if (date.equalsIgnoreCase("null") || date.isEmpty()) {
            LocalDateTime now = LocalDateTime.now();
            return CONSTANTS.DF.format(now);
        } else {
            return date;
        }
    }
}
