import mendel.dbmanager.DBManager;
import mendel.metacognition.LeaveCommand;
import mendel.metacognition.TaskStorage;
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

    public String manage(String currAction) throws MendelException {
        if (currAction.equals("bye")) {
            return new LeaveCommand().speak();
        } else if (currAction.equals("list")) {
            return this.taskStorage.speak();
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

            String message;
            if (segments[0].equals("mark")) {
                message = taskStorage.marker(Integer.parseInt(segments[1]) - 1);
                this.dbContoller.update(this.taskStorage);
            } else if(segments[0].equals("unmark")) {
                message = taskStorage.unMarker(Integer.parseInt(segments[1]) - 1);
                this.dbContoller.update(this.taskStorage);
            } else if(segments[0].equals("delete")) {
                message = taskStorage.delete(Integer.parseInt(segments[1]) - 1);
                this.dbContoller.update(this.taskStorage);
            } else if(segments[0].equals("todo")) {
                Todo task = Todo.of(currAction);
                message = taskStorage.add(task);
                this.dbContoller.create(task, taskStorage.isFirstTask());
            } else if(segments[0].equals("deadline")) {
                Deadline task = Deadline.of(currAction);
                message = taskStorage.add(task);
                this.dbContoller.create(task, taskStorage.isFirstTask());
            } else if(segments[0].equals("event")) {
                Event task = Event.of(currAction);
                message = taskStorage.add(task);
                this.dbContoller.create(task, taskStorage.isFirstTask());
            } else if(segments[0].equals("findeventon")) {
                message = taskStorage.find(segments[1]);
            } else {
                throw new MendelException("OOPS! I cannot understand command\nCheck the first word.");
            }
            return message;
        }
    }
}
