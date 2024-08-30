import java.time.LocalDate;

public class DateTime {
    LocalDate dateTime;

    public DateTime(String dateTime) {
        this.dateTime = LocalDate.parse(dateTime);
    }


}
