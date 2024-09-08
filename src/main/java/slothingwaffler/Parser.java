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
     * @param storage The Storage object used to save tasks to persistent storage.
     * @return        True if the command is "bye" and the program should exit; otherwise, false.
     * @throws SlothingWafflerException If the input command is invalid or not recognized.
     */
    public static String parse(String input, TaskList tasks, Storage storage) throws SlothingWafflerException {

        String[] split = input.split(" ", 2);
        if (split[0].strip().equals("bye")) {
            storage.save(tasks.getTasks());
            return "See you next time! Remember to get a waffle!";
        }
        switch (split[0].strip()) {
        case "list":
            return tasks.displayTaskList();
        case "mark":
            return tasks.markTask(Integer.parseInt(split[1]));
        case "delete":
            return tasks.deleteTask(Integer.parseInt(split[1]));
        case "todo":
            return tasks.addTodoTask(split);
        case "deadline":
            return tasks.addDeadlineTask(split);
        case "event":
            return tasks.addEventTask(split);
        case "find":
            return tasks.findMatchingTasks(split);
        case "priority":
            return tasks.prioritiseTask(Integer.parseInt(split[1]));
        default:
            throw new SlothingWafflerException("Please give instructions that the Slothing Waffler can understand :(");
        }
    }

}
