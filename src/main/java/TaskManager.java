import mendel.dbmanager.DBManager;
import mendel.metacognition.TaskStorage;
import mendel.metacognition.Leave;
import mendel.mendelexception.MendelException;

import mendel.discretetask.Todo;
import mendel.discretetask.Event;
import mendel.discretetask.Deadline;

import mendel.mendelexception.ConditionalExceptionHandler;

public class TaskManager {
    private final TaskStorage taskStorage;
    private final DBManager dbContoller;

    public TaskManager() {
        this.taskStorage = new TaskStorage();
        this.dbContoller = new DBManager("data/dbTaskList.txt");
        dbContoller.loadInto(taskStorage);
    }

    public void manage(String currAction) throws MendelException {
        if (currAction.equals("bye")) {
            new Leave().speak();
        } else if (currAction.equals("list")) {
            this.taskStorage.speak();
        } else {
            String[] segments = currAction.split(" ");
            if (segments[0].equals("mark") || segments[0].equals("unmark") || segments[0].equals("delete")) {
                ConditionalExceptionHandler.of()
                        .conditionTriggerException(segments.length < 2, "OOPS! No serial given.\nSpecify serial.")
                        .conditionTriggerException(segments.length > 2, "OOPS! Too much stuff.\nSpecify only serial.");
                try {
                    Integer.parseInt(segments[1]);
                } catch (NumberFormatException e) {
                    throw new MendelException("OOPS! That is invalid serial\nSpecify integer serial");
                }
            }

            if (segments[0].equals("mark")) {
                taskStorage.marker(Integer.parseInt(segments[1]) - 1);
            } else if(segments[0].equals("unmark")) {
                taskStorage.unMarker(Integer.parseInt(segments[1]) - 1);
            } else if(segments[0].equals("delete")) {
                taskStorage.delete(Integer.parseInt(segments[1]) - 1);
            } else if(segments[0].equals("todo")) {
                taskStorage.add(Todo.of(currAction));
            } else if(segments[0].equals("deadline")) {
                taskStorage.add(Deadline.of(currAction));
            } else if(segments[0].equals("event")) {
                taskStorage.add(Event.of(currAction));
            } else {
                throw new MendelException("OOPS! I cannot understand command\nCheck the first word.");
            }
        }
    }
}
