import mendel.metacognition.TaskStorage;
import mendel.metacognition.Leave;
import mendel.mendelexception.MendelException;

import mendel.discretetask.Todo;
import mendel.discretetask.Event;
import mendel.discretetask.Deadline;

public class TaskManager {
    private final TaskStorage taskStorage;

    public TaskManager() {
        this.taskStorage = new TaskStorage();
    }

    public void manage(String currAction) {
        if (currAction.equals("bye")) {
            new Leave().speak();
        } else if (currAction.equals("list")) {
            this.taskStorage.speak();
        } else {
            String[] segments = currAction.split(" ");
            if(segments[0].equals("mark")) {
                if (segments.length != 2) {
                    throw new MendelException("OOPS! No serial given.\nSpecify serial.");
                }
                taskStorage.marker(Integer.parseInt(segments[1]) - 1);
            } else if(segments[0].equals("unmark")) {
                if (segments.length != 2) {
                    throw new MendelException("OOPS! No serial given.\nSpecify serial.");
                }
                taskStorage.unMarker(Integer.parseInt(segments[1]) - 1);
            } else if(segments[0].equals("delete")) {
                if (segments.length != 2) {
                    throw new MendelException("OOPS! No serial given.\nSpecify serial.");
                }
                taskStorage.delete(Integer.parseInt(segments[1]) - 1);
            } else if(segments[0].equals("todo")) {
                taskStorage.add(new Todo(currAction));
            } else if(segments[0].equals("deadline")) {
                taskStorage.add(new Deadline(currAction));
            } else if(segments[0].equals("event")) {
                taskStorage.add(new Event(currAction));
            } else {
                throw new MendelException("OOPS! I cannot understand command\nCheck the first word.");
            }
        }
    }
}
