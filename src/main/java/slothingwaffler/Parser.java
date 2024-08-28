package slothingwaffler;

/**
 * Parses user input and executes the corresponding command.
 */
public class Parser {

    /**
     * Parses the input string and performs the corresponding action based on the command.
     *
     * @param input   The user input string.
     * @param tasks   The TaskList object that manages tasks.
     * @param ui      The Ui object used to display messages to the user.
     * @param storage The Storage object used to save tasks to persistent storage.
     * @return        True if the command is "bye" and the program should exit; otherwise, false.
     * @throws SlothingWafflerException If the input command is invalid or not recognized.
     */
    public static boolean parse(String input, TaskList tasks, Ui ui, Storage storage) throws SlothingWafflerException {

        String[] split = input.split(" ", 2);
        if (split[0].strip().equals("bye")) {
            ui.exit();
            storage.save(tasks.getTasks());
            return true;
        }
        switch (split[0].strip()) {
        case "list":
            tasks.displayTaskList();
            break;
        case "mark":
            tasks.markTask(Integer.parseInt(split[1]) - 1);
            ui.markTaskMessage(tasks, Integer.parseInt(split[1]) - 1);
            break;
        case "delete":
            ui.deleteTaskMessage(tasks, Integer.parseInt(split[1]) - 1);
            tasks.deleteTask(Integer.parseInt(split[1]) - 1);
            break;
        case "todo":
            tasks.addTodoTask(split);
            ui.addTaskMessage(tasks);
            break;
        case "deadline":
            tasks.addDeadlineTask(split);
            ui.addTaskMessage(tasks);
            break;
        case "event":
            tasks.addEventTask(split);
            ui.addTaskMessage(tasks);
            break;
        case "find":
            tasks.findMatchingTasks(split);
            break;
        default:
            throw new SlothingWafflerException("Please give instructions that the Slothing Waffler can understand :(");
        }
        return false;
    }

}
