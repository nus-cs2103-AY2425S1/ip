import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Parser {
    public static final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static Task loadTask(String data) {
        String[] dataArr = data.split("\\|");
        boolean completed = false;
        if (dataArr[1].equals("X")) {
            completed = true;
        }
        switch (dataArr[0]) {
        case Todo.TYPEICON:
            return parseStoredTodo(dataArr);
        case Event.TYPEICON:
            return parseStoredEvent(dataArr);
        case Deadline.TYPEICON:
            return parseStoredDeadline(dataArr);
        default:
            throw new RuntimeException("Error loading Task");
        }
    }

    public static Task parseStoredTodo(String[] dataArr) {
        if (dataArr.length == 3) {
            if (dataArr[1].equals(Task.COMPLETEICON)){
                return new Todo(true, dataArr[2]);
            }
            return new Todo(false, dataArr[2]);
        }
        throw new RuntimeException("Error loading task, data given is wrong");
    }
    public static Task parseStoredDeadline(String[] dataArr) {
        try {
            if (dataArr.length == 4) {
                if (dataArr[1].equals(Task.COMPLETEICON)){
                    return new Deadline(true, dataArr[2], LocalDate.parse(dataArr[3], DATEFORMATTER));
                }
                return new Deadline(false, dataArr[2], LocalDate.parse(dataArr[3], DATEFORMATTER));
            }
            throw new RuntimeException("Error loading task, data given is wrong");
        } catch (RuntimeException e) {
            throw new RuntimeException("Fk");
        }

    }

    public static Task parseStoredEvent(String[] dataArr) {
        if (dataArr.length == 5) {
            if (dataArr[1].equals(Task.COMPLETEICON)){
                return new Event(true,
                        dataArr[2],
                        LocalDate.parse(dataArr[3], DATEFORMATTER),
                        LocalDate.parse(dataArr[4], DATEFORMATTER));
            }
            return new Event(false,
                    dataArr[2],
                    LocalDate.parse(dataArr[3], DATEFORMATTER),
                    LocalDate.parse(dataArr[4], DATEFORMATTER));
        }
        throw new RuntimeException("Error loading task, data given is wrong");
    }
}
