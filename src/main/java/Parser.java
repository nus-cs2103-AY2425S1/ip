import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private final Scanner scanner;

    public Parser(){
        this.scanner = new Scanner(System.in);
    }

    public String getInput(){
        return this.scanner.nextLine();
    }

    public static LocalDate parseStringToDate(String dateTimeString){
        try {
            return LocalDate.parse(dateTimeString);
        } catch (DateTimeParseException  e) {
            System.out.println("Please use the format yyyy-MM-dd for datetime inputs");
            return null;
        }
    }
}
