package boss;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the class that deals with
 * making sense of the user command
 */

public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Creates a Parser object!
     * @param storage deals with loading tasks and saving tasks from file
     * @param tasks contains the task list
     */
    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.storage = storage;
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Handles the user command
     * @param task the user command
     * @throws IOException throws an exception if
     *                     writing to file causes an error.
     */

    public void handleCommand(String task) throws IOException, BossException {
        if (task.equals("list")) {
            storage.printFileContents();
        } else if (task.startsWith("unmark")) {
            String newFileData = tasks.unmark(task);
            storage.writeToFile(newFileData, false);
        } else if (task.startsWith("mark")) {
            String newFileData = tasks.mark(task);
            storage.writeToFile(newFileData, false);
        } else if (task.startsWith("delete")) {
            String newFileData = tasks.delete(task);
            storage.writeToFile(newFileData, false);
        } else if (task.startsWith("find")) {
            String description = task.split("find ")[1];
            tasks.find(description);
        }
        // it must be a command to create a task (of some type)
        else {
            Boss.Types taskType;
            if (task.startsWith("todo")) {
                taskType = Boss.Types.TODO;
            } else if (task.startsWith("deadline")) {
                taskType = Boss.Types.DEADLINE;
            } else if (task.startsWith("event")) {
                taskType = Boss.Types.EVENT;
            } else {
                taskType = Boss.Types.NONE;
            }

            switch (taskType) {
            case TODO:
                String[] string = task.split(" ");
                if (string.length == 1) {
                    ui.showLoadingError(new BossException("The description of todo cannot be empty!"));
                }
                Task item = new Todo(task);
                tasks.addTask(item);
                tasks.printabstraction();

                storage.writeToFile(item + System.lineSeparator(), true);
                break;

            case DEADLINE:
                String[] str = task.split("/by ");
                if (str.length == 1) {
                    ui.showLoadingError(new BossException("Please specify a deadline date!"));
                }

                Task newTask = new Deadline(str[0], false, str[1]);

                tasks.addTask(newTask);
                tasks.printabstraction();

                storage.writeToFile(newTask + System.lineSeparator(), true);
                break;

            case EVENT:
                String[] strArr = task.split("/");
                if (!(strArr.length == 3 && strArr[1].contains("from") && strArr[2].contains("to"))) {
                    ui.showLoadingError(new BossException("Wrong input! You must specify a description, start and end date for an event!"));
                }
                String[] description = strArr[0].split(" ");
                String[] fromArr = strArr[1].split("from ");
                String[] toArr = strArr[2].split("to ");

                if (description.length <= 1 || fromArr.length <= 1 || toArr.length <= 1
                        || fromArr[1].equals("") || toArr[1].equals("")) {
                    ui.showLoadingError(new BossException("Invalid input!"));
                }

                String from = fromArr[1];
                String to = toArr[1];

                Task newItem = new Event(strArr[0], from, to, false);

                tasks.addTask(newItem);
                tasks.printabstraction();

                storage.writeToFile(newItem + System.lineSeparator(), true);
                break;

            default:
                System.out.println("added: " + task);
                Task taskItem = new Task(task);
                tasks.addTask(taskItem);

                storage.writeToFile(taskItem + System.lineSeparator(), true);
                break;
            }
        }
    }

}
