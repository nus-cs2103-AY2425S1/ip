package BonnieGUI;

import java.util.ArrayList;
import Exceptions.*;
import Tasks.*;


public class GUIParser {

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param input The user input to be parsed.
     * @throws DeadlineFormatException   If the format of the deadline task is incorrect.
     * @throws EmptyTodoException        If the body of the todo task is empty.
     * @throws UnknownCommandException   If the command is unknown.
     */
    public static String parseInput(String input) throws DeadlineFormatException, EmptyTodoException, UnknownCommandException {
        String output = "";
        if (input.equals("list")) {
            String list = "Your current tasks are\n";
            for (int i = 1; i <= GUITaskList.getSize(); i++) {
                list += String.format("%d. %s\n", i, GUITaskList.getTasks().get(i - 1));
            }
            output = list;
        } else if (checkMarkCommand(input)) {
            String[] arr = input.split(" ", 2);
            Integer taskNum = Integer.valueOf(arr[1]);
            if (arr[0].equals("mark")) {
                // Mark task "taskNum" as done
                GUITaskList.markTaskAsDone(taskNum);
                output = String.format("Task %d has been successfully marked as done!", taskNum);
            } else if (arr[0].equals("unmark")) {
                // Mark task "taskNum" as not done
                GUITaskList.unmarkTaskAsDone(taskNum);
                output = String.format("Task %d has been successfully unmarked as done!", taskNum);
            }
        } else if (checkDeleteCommand(input)) {
            String[] arr = input.split(" ", 2);
            Integer taskNum = Integer.valueOf(arr[1]);
            GUITaskList.removeTask(taskNum);
            output = String.format("Task %d has been successfully deleted!", taskNum);
        } else if (checkFindCommand(input)) {
            String[] arr = input.split(" ", 2);
            String stringToFindBy = arr[1];
            ArrayList<Task> foundTasks = GUITaskList.findTasks(stringToFindBy);
            // Does not use the TaskList, we are manipulating a new ArrayList of tasks here
            String list = "Bonnie has found the matching tasks!\n";
            for (int i = 0; i < foundTasks.size(); i++) {
                list += String.format("%d. %s\n", i+1, foundTasks.get(i));
            }
            output = list;
        }
        else {
            // Want to parse and add task into task list
            String successMessage = parseTaskAddition(input);
            output = successMessage;
        }

        // Want to update file of tasks after every new command (which could possibly change the tasks involved)
        GUIStorage.updateFile();
        return output;
    }

    private static boolean checkMarkCommand(String targetString) {
        String[] arr = targetString.split(" ");
        if ((arr[0].equals("mark") || arr[0].equals("unmark")) && arr.length == 2) {
            try {
                Integer taskNum = Integer.valueOf(arr[1]);
                if (taskNum <=GUITaskList.getSize() && taskNum >= 1) {
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
                if (taskNum <= GUITaskList.getSize() && taskNum >= 1) {
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

    private static String parseTaskAddition(String input) throws EmptyTodoException, UnknownCommandException, DeadlineFormatException {
        // Want to split the string according to spaces 1st
        String[] splitString = input.split(" ", 2);
        String name;
        if (splitString[0].equals("todo")) {
            if (splitString.length == 1) {
                throw new EmptyTodoException();
            }
            String taskName = splitString[1];
            GUITaskList.addTask(new Todo(taskName));
            name = taskName;
        } else if (splitString[0].equals("deadline")) {
            String[] components = splitString[1].split(" /by ", 2);
            if (components.length < 2) {
                throw new DeadlineFormatException();
            }
            GUITaskList.addTask(new Deadline(components[0], components[1]));
            name = components[0];
        } else if (splitString[0].equals("event")) {
            // Idea is that original string is in the form {event_name} /from {start} /to {end}
            // Hence first split will get event name and the {start} /to {end}
            // Second split will split the {start} /to {end} to get the actual start and end
            String[] component1 = splitString[1].split(" /from ", 2);
            String[] component2 = component1[1].split(" /to ", 2);
            GUITaskList.addTask(new Event(component1[0], component2[0], component2[1]));
            name = component1[0];
        } else {
            throw new UnknownCommandException(input);
        }

        return String.format("Hey %s, I have added \"%s\" into your task list!\n" +
                "You now have %d tasks to complete!\n", MainWindow.username, name, GUITaskList.getSize());
    }
}