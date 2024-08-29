import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hi {

    public static void main(String[] args) {
        DateTimeFormatter a = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime d1 = LocalDateTime.parse("2019-12-01 1800", a);
        System.out.println(d1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
    }
}



