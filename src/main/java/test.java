import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args) {
        String a = "29/9/2024 10pm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy ha");
        LocalDateTime date = LocalDateTime.parse(a, formatter);
        System.out.println("hello");
    }
}
