package chatbot.command;

import java.time.format.DateTimeParseException;
import java.util.Objects;

import chatbot.bot.Storage;
import chatbot.bot.TaskList;
import chatbot.bot.Ui;
import chatbot.exception.EmptyDescException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDoTask;

/** AddCommand is a subclass of Command to add tasks */
public class AddCommand extends Command {
    private final String action;
    private final String desc;

    /** Constructor */
    public AddCommand(String action, String desc) {
        this.action = action;
        this.desc = desc.stripLeading();
    }

    /** Adds Tasks to taskList, if fails catch exceptions and print them */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String output = "";
        try {
            Task t = this.getTask();
            assert t != null : "Task is null, code is trying to run still";
            taskList.addTask(t);
            output = "added:\n" + t + "\n"
                    + "You have " + taskList.size() + " tasks in list";
            System.out.println(output);
        } catch (ArrayIndexOutOfBoundsException e) {
            output = "missing '/by', '/from', or '/to'";
            System.out.println(output);
            return output;
        } catch (DateTimeParseException e) {
            output = "Invalid date: please follow this format yyyy-MM-dd, eg. 2019-12-01";
            System.out.println(output);
            return output;
        } catch (Exception e) { // EmptyDescException message is already written nicely
            System.out.println(e.getMessage());
            output = e.getMessage();
            return output;
        }
        return output;
    }

    private Task getTask() throws EmptyDescException {
        Task t = null;
        if (this.action.equals("todo")) {
            t = new ToDoTask(desc); // throws EmptyDescException
        } else if (this.action.equals("deadline")) {
            String[] tokens = desc.split("/by");
            t = new Deadline(tokens[0].strip(), tokens[1].strip());
        } else if (this.action.equals("event")) {
            String[] tokens = desc.split("/from");
            String[] tokens2 = tokens[1].split("/to");
            t = new Event(tokens[0].strip(), tokens2[0].strip(), tokens2[1].strip());
        }
        return t;
    }
}
