package chatbot.command;

import java.time.format.DateTimeParseException;

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
        if (this.desc == "") {
            try {
                throw new EmptyDescException();
            } catch (EmptyDescException e) {
                System.out.println(e.getMessage());
                output = e.getMessage();
                return output;
            }
        }
        assert !this.desc.isEmpty() : "task description is empty, but code still running";
        if (action.equals("todo")) {
            try {
                Task t = new ToDoTask(desc);
                taskList.addTask(t);
                output = "added:\n" + t + "\n"
                        + "You have " + taskList.size() + " tasks in list";
                System.out.println(output);
            } catch (Exception e) {
                System.out.println(e);
                output = e.getMessage();
            }
        } else if (action.equals("deadline")) {
            try {
                String[] arr = desc.split("/by");
                Task t = null;
                try {
                    t = new Deadline(arr[0].strip(), arr[1].strip());
                } catch (ArrayIndexOutOfBoundsException e) {
                    output = "missing /by";
                    System.out.println(output);
                    return output;
                } catch (DateTimeParseException e) {
                    output = "Invalid date: please follow this format yyyy-MM-dd, eg. 2019-12-01";
                    System.out.println(output);
                    return output;
                }
                taskList.addTask(t);
                output = "added:\n" + t + "\n"
                        + "You have " + taskList.size() + " tasks in list";
                System.out.println(output);
            } catch (EmptyDescException e) {
                output = e.getMessage();
                System.out.println(output);
            }
        } else if (action.equals("event")) {
            String[] arr = desc.split("/from");
            String[] arr2;
            try {
                arr2 = arr[1].split("/to");
            } catch (ArrayIndexOutOfBoundsException e) {
                output = "missing /from";
                System.out.println(output);
                return output;
            }
            Task t = null;
            try {
                try {
                    t = new Event(arr[0].strip(), arr2[0].strip(), arr2[1].strip());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    output = "missing /to";
                    System.out.println(output);
                    return output;
                } catch (DateTimeParseException e) {
                    output = "Invalid date: please follow this format yyyy-MM-dd, eg. 2019-12-01";
                    System.out.println(output);
                    return output;
                }
                taskList.addTask(t);
                output = "added:\n" + t + "\n"
                        + "You have " + taskList.size() + " tasks in list";
                System.out.println(output);
            } catch (Exception e) {
                output = e.getMessage();
                System.out.println(output);
            }
        }
        assert !output.isEmpty() : "output is empty, something went wrong in AddCommand";
        if (output.isEmpty()) {
            output = "something went wrong in AddCommand";
        }
        return output;
    }
}
