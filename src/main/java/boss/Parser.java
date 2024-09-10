package boss;

import boss.exceptions.BossException;
import boss.exceptions.InvalidInputException;
import boss.tasks.Deadline;
import boss.tasks.Event;
import boss.tasks.Task;
import boss.tasks.Todo;

import java.io.IOException;

/**
 * Represents the class that deals with
 * making sense of the user command
 */

public class Parser {
    private final Storage storage;
    private final TaskList tasks;


    /**
     * Creates a Parser object!
     *
     * @param storage deals with loading tasks and saving tasks from file
     * @param tasks contains the task list
     */
    public Parser(Storage storage, TaskList tasks) {
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Handles the user command for the text-based UI
     *
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
                    throw new InvalidInputException("todo");
                }
                Task item = new Todo(task);
                tasks.addTask(item);
                tasks.printTextAbstraction();

                storage.writeToFile(item + System.lineSeparator(), true);
                break;

            case DEADLINE:
                String[] str = task.split("/by ");
                if (str.length == 1) {
                    throw new InvalidInputException("deadline");
                }

                Task newTask = new Deadline(str[0], false, str[1]);
                tasks.addTask(newTask);
                tasks.printTextAbstraction();

                storage.writeToFile(newTask + System.lineSeparator(), true);
                break;

            case EVENT:
                String[] strArr = task.split("/");
                if (!(strArr.length == 3 && strArr[1].contains("from") && strArr[2].contains("to"))) {
                    throw new InvalidInputException("event");
                }
                String[] description = strArr[0].split(" ");
                String[] fromArr = strArr[1].split("from ");
                String[] toArr = strArr[2].split("to ");

                if (description.length <= 1 || fromArr.length <= 1 || toArr.length <= 1
                        || fromArr[1].isEmpty() || toArr[1].isEmpty()) {
                    throw new InvalidInputException("event");
                }

                String from = fromArr[1];
                String to = toArr[1];

                Task newItem = new Event(strArr[0], from, to, false);

                tasks.addTask(newItem);
                tasks.printTextAbstraction();

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


    /**
     * Handles the user command for the GUI.
     *
     * @param task user input that contains the command.
     * @return String containing response from the Chatbot.
     * @throws BossException throws an exception if user input is invalid.
     * @throws IOException throws an exception if writing to data fails.
     */
    public String getResponse(String task) throws BossException, IOException {
        if (task.equals("list")) {
            return storage.printTasks();
        } else if (task.equals("Yashvan is the best")) {
            return "THATS RIGHT! HE IS THE GOAT OF GOATS";
        } else if (task.startsWith("mark")) {
            String newFileData = tasks.mark(task);
            storage.writeToFile(newFileData, false);
            return "Nice! I have marked this task as done!";
        } else if (task.startsWith("unmark")) {
            String newFileData = tasks.unmark(task);
            storage.writeToFile(newFileData, false);
            return "Nice! I have marked this task as undone!";
        } else if (task.startsWith("delete")) {
            String newFileData = tasks.delete(task);
            storage.writeToFile(newFileData, false);
            return "Ok. This task has been removed!";
        } else if (task.startsWith("find")) {
            String description = task.split("find ")[1];
            return tasks.findTask(description);
        } else if (task.startsWith("remind")) {
            return tasks.remind();
        }


        else if (task.startsWith("bye")) {
            return "Bye! Have a wonderful day legend!";
        }
        else {
            if (task.startsWith("todo")) {
                String[] string = task.split(" ");
                if (string.length == 1) {
                    throw new InvalidInputException("todo");
                }
                Task item = new Todo(task);
                tasks.addTask(item);
                storage.writeToFile(item + System.lineSeparator(), true);

                return tasks.printAbstraction();

            } else if (task.startsWith("deadline")) {
                String[] str = task.split("/by ");
                if (str.length == 1) {
                    throw new InvalidInputException("deadline");
                }
                Task newTask = new Deadline(str[0], false, str[1]);
                tasks.addTask(newTask);
                storage.writeToFile(newTask + System.lineSeparator(), true);

                return tasks.printAbstraction();
            } else if (task.startsWith("event")) {
                String[] strArr = task.split("/");
                if (!(strArr.length == 3 && strArr[1].contains("from") && strArr[2].contains("to"))) {
                    throw new InvalidInputException("event");
                }
                String[] description = strArr[0].split(" ");
                String[] fromArr = strArr[1].split("from ");
                String[] toArr = strArr[2].split("to ");

                if (description.length <= 1 || fromArr.length <= 1 || toArr.length <= 1
                        || fromArr[1].isEmpty() || toArr[1].isEmpty()) {
                    throw new InvalidInputException("event");
                }

                String from = fromArr[1];
                String to = toArr[1];

                Task newItem = new Event(strArr[0], from, to, false);

                tasks.addTask(newItem);

                storage.writeToFile(newItem + System.lineSeparator(), true);
                return tasks.printAbstraction();
            } else {
                Task taskItem = new Task(task);
                tasks.addTask(taskItem);

                storage.writeToFile(taskItem + System.lineSeparator(), true);
                return "added: " + task;
            }
        }
    }
}
