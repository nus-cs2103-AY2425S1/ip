package chatBot.command;

import chatBot.bot.Storage;
import chatBot.bot.TaskList;
import chatBot.bot.Ui;
import chatBot.exception.EmptyDescException;
import chatBot.task.Deadline;
import chatBot.task.Event;
import chatBot.task.Task;
import chatBot.task.ToDoTask;

import java.time.format.DateTimeParseException;

/** AddCommand is a subclass of Command to add tasks */
public class AddCommand extends Command {
    private final String action;
    private final String desc;

    public AddCommand(String action, String desc) {
        this.action = action;
        this.desc = desc.stripLeading();
    }

    /** Adds Tasks to taskList, if fails catch exceptions and print them */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.desc == "") {
            try {
                throw new EmptyDescException();
            } catch (EmptyDescException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        if (action.equals("todo")) {
            try {
                Task t = new ToDoTask(desc);
                taskList.addTask(t);
                System.out.println("added:\n" + t);
                System.out.println("You have " + taskList.size() + " tasks in list");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (action.equals("deadline")) {
            try {
                String[] arr = desc.split("/by");
                Task t;
                try {
                    t = new Deadline(arr[0].strip(), arr[1].strip());
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("missing /by");
                    return;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date: please follow this format yyyy-MM-dd, eg. 2019-12-01");
                    return;
                }
                taskList.addTask(t);
                System.out.println("added:\n" + t);
                System.out.println("You have " + taskList.size() + " tasks in list");
            } catch (EmptyDescException e) {
                System.out.println(e);
            }
        } else if (action.equals("event")) {
            String[] arr = desc.split("/from");
            String[] arr2;
            try {
                arr2 = arr[1].split("/to");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("missing /from");
                return;
            }
            Task t = null;
            try {
                try {
                    t = new Event(arr[0].strip(), arr2[0].strip(), arr2[1].strip());
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("missing /to");
                    return;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date: please follow this format yyyy-MM-dd, eg. 2019-12-01");
                    return;
                }
                taskList.addTask(t);
                System.out.println("added:\n" + t);
                System.out.println("You have " + taskList.size() + " tasks in list");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
