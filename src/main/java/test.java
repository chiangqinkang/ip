import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class test   {
    public static void main(String[] args) {
        String testDate = "Jun 6 2024 12:00 PM";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");

        try {
            LocalDateTime parsedDate = LocalDateTime.parse(testDate, formatter);
            System.out.println("Parsed LocalDateTime: " + parsedDate);
        } catch (DateTimeParseException e) {
            System.out.println("Parsing failed: " + e.getMessage());
        }
    }


}
