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

                //check if string has date
                Task newTask;
                if (ui.hasDateTime(str[1])) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime date = LocalDateTime.parse(str[1], formatter);
                    newTask = new Deadline(str[0], false, date);
                } else if (ui.hasDate(str[1])) {
                    LocalDate date = LocalDate.parse(str[1]);
                    newTask = new Deadline(str[0], false, date);
                } else {
                    newTask = new Deadline(str[0], false, str[1]);
                }

                tasks.addTask(newTask);
                tasks.printabstraction();

                storage.writeToFile(newTask + System.lineSeparator(), true);
                break;

            case EVENT:
                String[] str3 = task.split("/");
                if (!(str3.length == 3 && str3[1].contains("from") && str3[2].contains("to"))) {
                    ui.showLoadingError(new BossException("Wrong input! You must specify a description, start and end date for an event!"));
                }
                String[] description = str3[0].split(" ");
                String[] fromArr = str3[1].split("from ");
                String[] toArr = str3[2].split("to ");

                if (description.length <= 1 || fromArr.length <= 1 || toArr.length <= 1
                        || fromArr[1].equals("") || toArr[1].equals("")) {
                    ui.showLoadingError(new BossException("Invalid input!"));
                }

                String from = fromArr[1];
                String to = toArr[1];

                if (ui.hasDateTime(from)) {
                    from = from.trim();
                    from = ui.formatDateTime(from, true);
                } else if (ui.hasDate(from)) {
                    from = from.replace(" ", "");
                    from = ui.formatDateTime(from, false);
                }

                if (ui.hasDateTime(to)) {
                    to = ui.formatDateTime(to, true);
                } else if (ui.hasDate(to)) {
                    to = ui.formatDateTime(to, false);
                }

                Task newItem = new Event(str3[0], from, to, false);

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
