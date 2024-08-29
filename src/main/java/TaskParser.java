import task.Deadline;
import task.Task;
import task.Event;
import task.Todo;
import java.util.ArrayList;

public class TaskParser {
    public TaskParser() {
        // nothing
    }
    
    public static void parseTask(String str, ArrayList<Task> list) {
        String[] input = str.split(" / ");
        
        switch (input[0]) {
        case ("T"):
            Todo todo = new Todo(input[2]);
            list.add(todo);
            break;
        case ("D"):
            Deadline deadline = new Deadline(input[2], input[3]);
            list.add(deadline);
            break;
        case ("E"):
            Event event = new Event(input[2], input[3], input[4]);
            list.add(event);
            break;
        }
    }
}
