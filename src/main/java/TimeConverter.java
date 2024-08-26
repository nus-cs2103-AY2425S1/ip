import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeConverter {
    protected static LocalDateTime timeConverter(String date) {
        String[] arr = date.split(" ");
        LocalDate res = null;
        String[] dateArr = arr[0].split("/");
        for(String item: dateArr) {
            System.out.println(item);
        }
        if (dateArr.length == 1) {
            res = LocalDate.parse(dateArr[0]);
        } else {
            res = LocalDate.of(Integer.parseInt(dateArr[2].trim()), Integer.parseInt(dateArr[1].trim()),
                    Integer.parseInt(dateArr[0].trim()));
        }
        LocalTime time = null;
        if(arr.length == 2) {
            time = LocalTime.of(Integer.parseInt(arr[1].substring(0,2)),Integer.parseInt(arr[1].substring(2)));
        } else {
            time = LocalTime.of(0,0);
        }
        LocalDateTime dt = LocalDateTime.of(res,time);
        return dt;
    }
}
