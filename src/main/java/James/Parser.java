package james;

import java.time.LocalDateTime;
import java.lang.NumberFormatException;
import java.lang.IndexOutOfBoundsException;

/**
 * Handles parsing and executing user commands.
 * <p>
 * The Parser class interprets user input, performs the necessary actions
 * on the TaskList and Storage, and interacts with the Ui
 * to provide feedback to the user.
 * </p>
 */
class Parser {
    private Ui ui;
    private Storage storage;

    /**
     * Creates a new Parser instance.
     *
     * @param ui The Ui instance used for user interactions
     * @param storage The Storage instance used for loading and saving tasks
     */
    public Parser(Ui ui, Storage storage) {
        assert ui != null : "UI failed to load into parser";
        assert storage != null : "storage failed to load into parser";
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Parses the user command and executes the appropriate action.
     *
     * @param command The user command to be parsed and executed
     * @param taskList The TaskList instance to modify based on the command
     * @return A string response based on the executed command, such as task details, confirmation messages,
     *         or the exit message when the command is "bye".
     * @throws JamesException If an error occurs during command execution
     */
    public String parseAndExecute(String command, TaskList taskList) throws JamesException {
        assert command != null : "empty input";
        assert taskList != null : "failed to pass in task list to parser";
        String[] words = command.split(" ");
        String action = words[0].toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();

        switch (action) {
        case "bye":
            return ui.showExitMessage();

        case "list":
            return taskList.printTasks();

        case "mark":
            if (words.length < 2) {
                throw new IncorrectIdentifierException("Oops! Looks like you missed out the task number. " +
                        "Please enter the task number after the command");
            }
            try {
                int markTaskNum = Integer.parseInt(words[1]);
                taskList.markTask(markTaskNum - 1);
                stringBuilder.append("Task marked as done:\n");
                stringBuilder.append(taskList.getTask(markTaskNum - 1).printTask());
                storage.saveTasks(taskList.getTasks());
                return stringBuilder.toString();
            } catch (NumberFormatException e) {
                throw new IncorrectIdentifierException("Oops! Please enter a number after the command");
            } catch (IndexOutOfBoundsException e) {
                throw new IncorrectIdentifierException("Oops! Please enter a valid task number after the command");
            }


        case "unmark":
            if (words.length < 2) {
                throw new IncorrectIdentifierException("Oops! Looks like you missed out the task number. " +
                        "Please enter the task number after the command");
            }
            try {
                int unmarkTaskNum = Integer.parseInt(words[1]);
                taskList.unmarkTask(unmarkTaskNum - 1);
                stringBuilder.append("Task marked as not done:\n");
                stringBuilder.append(taskList.getTask(unmarkTaskNum - 1).printTask());
                storage.saveTasks(taskList.getTasks());
                return stringBuilder.toString();
            } catch (NumberFormatException e) {
                throw new IncorrectIdentifierException("Oops! Please enter a number after the command");
            } catch (IndexOutOfBoundsException e) {
                throw new IncorrectIdentifierException("Oops! Please enter a valid task number after the command");
            }

        case "todo":
            String todoDescription = command.substring(4).trim();
            Task todoTask = new Todo(todoDescription, false);
            taskList.addTask(todoTask);
            stringBuilder.append("Task added:\n" + todoTask.printTask() + "\n");
            stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();

        case "deadline":
            String deadlineDescription = command.substring(8, command.lastIndexOf("/by")).trim();
            String deadline = command.substring(command.lastIndexOf("/by") + 4).trim();
            Task deadlineTask = new Deadline(deadlineDescription, false, LocalDateTime.parse(deadline));
            taskList.addTask(deadlineTask);
            stringBuilder.append("Task added:\n" + deadlineTask.printTask() + "\n");
            stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();

        case "event":
            String eventDescription = command.substring(5, command.lastIndexOf("/from")).trim();
            String start = command.substring(command.lastIndexOf("/from") + 6,
                    command.lastIndexOf("/to")).trim();
            String end = command.substring(command.lastIndexOf("/to") + 4).trim();
            Task eventTask = new Event(eventDescription, false, LocalDateTime.parse(start),
                    LocalDateTime.parse(end));
            taskList.addTask(eventTask);
            stringBuilder.append("Task added:\n" + eventTask.printTask() + "\n");
            stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
            storage.saveTasks(taskList.getTasks());
            return stringBuilder.toString();

        case "delete":
            if (words.length < 2) {
                throw new IncorrectIdentifierException("Oops! Looks like you missed out the task number. " +
                        "Please enter the task number after the command");
            }
            try {
                int deleteTaskNum = Integer.parseInt(words[1]);
                stringBuilder.append("Task removed:\n" + taskList.getTask(deleteTaskNum - 1).printTask() + "\n");
                taskList.deleteTask(deleteTaskNum - 1);
                stringBuilder.append(String.format("Now you have %d tasks in the list.", taskList.size()));
                storage.saveTasks(taskList.getTasks());
                return stringBuilder.toString();
            } catch (NumberFormatException e) {
                throw new IncorrectIdentifierException("Oops! Please enter a number after the command");
            } catch (IndexOutOfBoundsException e) {
                throw new IncorrectIdentifierException("Oops! Please enter a valid task number after the command");
            }

        case "find":
            String keyWord = words[1];
            stringBuilder.append("Here are the matching tasks in your list:\n");
            stringBuilder.append(taskList.printMatchingTasks(keyWord));
            return stringBuilder.toString();

        default:
            throw new CommandNotFoundException("Sorry! I don't understand what you mean by (" + command + "). " +
                    "Please try a different command!");
        }
    }
}
