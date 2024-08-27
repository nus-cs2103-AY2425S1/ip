import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeConverter {
    protected static LocalDateTime timeConverter(String date) {
        String[] arr = date.split(" ");
        LocalDate res = LocalDate.parse(arr[0]);
        LocalTime time;
        if(arr.length == 2) {
            time = LocalTime.of(Integer.parseInt(arr[1].substring(0,2)),Integer.parseInt(arr[1].substring(2)));
        } else {
            time = LocalTime.of(0,0);
        }
        return LocalDateTime.of(res,time);
    }
}
