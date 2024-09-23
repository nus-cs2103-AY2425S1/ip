package bonnie;

import java.util.ArrayList;

public class Parser {

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param input The user input to be parsed.
     * @throws DeadlineFormatException   If the format of the deadline task is incorrect.
     * @throws EmptyTodoException        If the body of the todo task is empty.
     * @throws UnknownCommandException   If the command is unknown.
     */
    public static void parseInput(String input) throws DeadlineFormatException, EmptyTodoException, UnknownCommandException {
        if (input.equals("bye")) {
            System.out.println("Bye " + Ui.username + "\n");
            return;
        } else if (input.equals("list")) {
            String list = "Your current tasks are\n";
            for (int i = 1; i <= TaskList.getSize(); i++) {
                list += String.format("%d. %s\n", i, TaskList.getTasks().get(i - 1));
            }
            System.out.println(list);
        } else if (checkMarkCommand(input)) {
            String[] arr = input.split(" ", 2);
            Integer taskNum = Integer.valueOf(arr[1]);
            if (arr[0].equals("mark")) {
                // Mark task "taskNum" as done
                TaskList.markTaskAsDone(taskNum);
            } else if (arr[0].equals("unmark")) {
                // Mark task "taskNum" as not done
                TaskList.unmarkTaskAsDone(taskNum);
            }
        } else if (checkDeleteCommand(input)) {
            String[] arr = input.split(" ", 2);
            Integer taskNum = Integer.valueOf(arr[1]);
            TaskList.removeTask(taskNum);
        } else if (checkFindCommand(input)) {
            String[] arr = input.split(" ", 2);
            String stringToFindBy = arr[1];
            ArrayList<Task> foundTasks = TaskList.findTasks(stringToFindBy);
            // Does not use the TaskList, we are manipulating a new ArrayList of tasks here
            String list = "Bonnie has found the matching tasks!\n";
            for (int i = 0; i < foundTasks.size(); i++) {
                list += String.format("%d. %s\n", i+1, foundTasks.get(i));
            }
            System.out.println(list);
        } else {
            // Want to parse and add task into task list
            parseTaskAddition(input);
        }

        // Want to update file of tasks after every new command (which could possibly change the tasks involved)
        Storage.updateFile();
    }

    private static boolean checkMarkCommand(String targetString) {
        String[] arr = targetString.split(" ");
        if ((arr[0].equals("mark") || arr[0].equals("unmark")) && arr.length == 2) {
            try {
                Integer taskNum = Integer.valueOf(arr[1]);
                if (taskNum <= TaskList.getSize() && taskNum >= 1) {
                    return true;
                } else {
                    System.out.println("You might have tried to mark or unmark a task that does not exist.\n" +
                            "If so, please delete this wrongly added task using the delete command.\n");
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean checkDeleteCommand(String targetString) {
        String[] arr = targetString.split(" ");
        if (arr[0].equals("delete") && arr.length == 2) {
            try {
                Integer taskNum = Integer.valueOf(arr[1]);
                if (taskNum <= TaskList.getSize() && taskNum >= 1) {
                    return true;
                } else {
                    System.out.println("You might have tried to delete a task that does not exist.\n");
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean checkFindCommand(String targetString) {
        String[] arr = targetString.split(" ", 2);
        if (arr[0].equals("find")) {
            return true;
        } else {
            return false;
        }
    }

    private static void parseTaskAddition(String input) throws EmptyTodoException, UnknownCommandException, DeadlineFormatException {
        String[] splitString = input.split(" ", 2);
        String name;
        if (splitString[0].equals("todo")) {
            if (splitString.length == 1) {
                throw new EmptyTodoException();
            }
            String taskName = splitString[1];
            TaskList.addTask(new Todo(taskName));
            name = taskName;
        } else if (splitString[0].equals("deadline")) {
            String[] components = splitString[1].split(" /by ", 2);
            if (components.length < 2) {
                throw new DeadlineFormatException();
            }
            TaskList.addTask(new Deadline(components[0], components[1]));
            name = components[0];
        } else if (splitString[0].equals("event")) {
            String[] component1 = splitString[1].split(" /from ", 2);
            String[] component2 = component1[1].split(" /to ", 2);
            TaskList.addTask(new Event(component1[0], component2[0], component2[1]));
            name = component1[0];
        } else {
            throw new UnknownCommandException(input);
        }

        Ui.generateMessage(String.format("Hey %s, I have added \"%s\" into your task list!\n" +
                "You now have %d tasks to complete!\n", Ui.username, name, TaskList.getSize()));
    }
}
