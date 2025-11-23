package utils;

import java.time.LocalDate;

public class ValidationUtil {

    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (Exception e) {
            System.out.println("Invalid date! Setting today's date.");
            return LocalDate.now();
        }
    }
}

