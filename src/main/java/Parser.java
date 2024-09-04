import java.util.Arrays;

public class Parser {
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
        if (dataArr.length == 4) {
            if (dataArr[1].equals(Task.COMPLETEICON)){
                return new Deadline(true, dataArr[2], dataArr[3]);
            }
            return new Deadline(false, dataArr[2], dataArr[3]);
        }
        throw new RuntimeException("Error loading task, data given is wrong");
    }

    public static Task parseStoredEvent(String[] dataArr) {
        if (dataArr.length == 5) {
            if (dataArr[1].equals(Task.COMPLETEICON)){
                return new Event(true, dataArr[2], dataArr[3],dataArr[4]);
            }
            return new Event(false, dataArr[2], dataArr[3],dataArr[4]);
        }
        throw new RuntimeException("Error loading task, data given is wrong");
    }
}
