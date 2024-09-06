package diomon;

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
            throw new RuntimeException("Error loading Diomon.Task");
        }
    }

    public static Task parseStoredTodo(String[] dataArr) {
        if (checkStoredStatus(dataArr[1])) {
            throw new RuntimeException("CompletionStatus seem to be wrong");
        }
        if (dataArr.length == 3) {
            return new Todo(dataArr[1].equals(Task.COMPLETEICON), dataArr[2]);
        }
        throw new RuntimeException("Error loading todo task, data stored is wrong");
    }
    public static Task parseStoredDeadline(String[] dataArr) {
        if (checkStoredStatus(dataArr[1])) {
            throw new RuntimeException("CompletionStatus seem to be wrong");
        }
        if (dataArr.length == 4) {
            return new Deadline(dataArr[1].equals(Task.COMPLETEICON), dataArr[2], LocalDate.parse(dataArr[3], DATEFORMATTER));
        } else {
            throw new RuntimeException("Error loading deadline task, data stored is wrong");
        }
    }

    public static Task parseStoredEvent(String[] dataArr) {
        if (checkStoredStatus(dataArr[1])) {
            throw new RuntimeException("CompletionStatus seem to be wrong");
        }
        if (dataArr.length == 5) {
            return new Event(dataArr[1].equals(Task.COMPLETEICON),
                    dataArr[2],
                    LocalDate.parse(dataArr[3], DATEFORMATTER),
                    LocalDate.parse(dataArr[4], DATEFORMATTER));
        } else {
            throw new RuntimeException("Error loading event task, data stored is wrong");
        }
    }

    public static boolean checkStoredStatus(String status) {
        return !(status.equals(Task.COMPLETEICON) || status.equals(Task.INCOMPLETEICON));
    }
}
