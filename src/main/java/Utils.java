import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    static void fileChecker(String filePath) {
        File file = new File(filePath);
        File directory = new File(file.getParent());

        try{
            if(!directory.exists()){
                boolean isCreated = directory.mkdir();
                if (!isCreated){
                    System.out.println("Issue creating Directory");
                }
            }
            if(!file.exists()){
                if(!file.createNewFile()){
                    System.out.println("Issue creating File.");
                }
            }
        }catch (IOException e) {
            System.out.println("Error in file creation.");
        }
    }

    static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date and time format. Please use the format 'd/M/yyyy HHmm'.");
            return null;
        }
    }
}
