package bonnieGUI;

import java.time.LocalDate;
import java.util.ArrayList;
import Exceptions.*;
import Tasks.*;
import java.time.temporal.ChronoUnit;


public class GUIParser {

    /**
     * Parses the user input and performs the corresponding action.
     *
     * @param input The user input to be parsed.
     * @throws DeadlineFormatException   If the format of the deadline task is incorrect.
     * @throws EmptyTodoException        If the body of the todo task is empty.
     */
    public static String parseInput(String input) throws DeadlineFormatException, EmptyTodoException {
        assert input != "";
        if (input.equals("list")) {
            return returnList();
        } else if (checkMarkCommand(input)) {
            return resolveTask(input);
        } else if (checkDeleteCommand(input)) {
            String[] arr = input.split(" ", 2);
            Integer taskNum = Integer.valueOf(arr[1]);
            GUITaskList.removeTask(taskNum);
            GUIStorage.updateFile();
            return String.format("Task %d has been successfully deleted!", taskNum);
        } else if (checkFindCommand(input)) {
            return findTasks(input);
        } else if (checkRemindCommand(input)) {
            return remindAboutDeadlineTasks(input);
        } else if (checkSortCommand(input)) {
            return sort();
        } else {
            // Want to parse and add task into task list
            String successMessage = parseTaskAddition(input);
            String output = successMessage;
            GUIStorage.updateFile();
            return output;
        }
    }

    private static String returnList() {
        String list = "Your current tasks are\n";
        for (int i = 1; i <= GUITaskList.getSize(); i++) {
            Task currTask = GUITaskList.getTasks().get(i - 1);
            if (currTask instanceof Deadline) {
                Deadline d = (Deadline) currTask;
                list += String.format("%d. %s\n", i, d.toStringFormatted());
            } else {
                list += String.format("%d. %s\n", i, currTask.toString());
            }

        }
        GUIStorage.updateFile();
        return list;
    }

    private static String sort() {
        GUITaskList.sort();
        GUIStorage.updateFile();
        return "Your task list has been sorted successfully! \n" + returnList();
    }

    private static String resolveTask(String input) {
        String[] arr = input.split(" ", 2);
        Integer taskNum = Integer.valueOf(arr[1]);
        String output = "";
        if (arr[0].equals("mark")) {
            // Mark task "taskNum" as done
            GUITaskList.markTaskAsDone(taskNum);
            output = String.format("Task %d has been successfully marked as done!", taskNum);
        } else if (arr[0].equals("unmark")) {
            // Mark task "taskNum" as not done
            GUITaskList.unmarkTaskAsDone(taskNum);
            output = String.format("Task %d has been successfully unmarked as done!", taskNum);
        }
        GUIStorage.updateFile();
        return output;
    }

    private static String findTasks(String input) {
        String[] arr = input.split(" ", 2);
        assert arr.length == 2; // Ensure the find command has find keyword and another string
        String stringToFindBy = arr[1];
        ArrayList<Task> foundTasks = GUITaskList.findTasks(stringToFindBy);
        // Does not use the TaskList, we are manipulating a new ArrayList of tasks here
        String list = "Bonnie has found the matching tasks!\n";
        for (int i = 0; i < foundTasks.size(); i++) {
            list += String.format("%d. %s\n", i+1, foundTasks.get(i));
        }
        GUIStorage.updateFile();
        return list;
    }

    private static String remindAboutDeadlineTasks(String input) {
        String[] arr = input.split(" ", 2);
        int withinDays = Integer.parseInt(arr[1]);
        String list = String.format("These deadlines are due within the next %d days\n", withinDays);
        ArrayList<Deadline> deadlines = GUITaskList.getSortedDeadlineTasks(GUITaskList.getTasks());

        int index = 1;
        for (Deadline d : deadlines) {
            if (ChronoUnit.DAYS.between(LocalDate.now(), d.getDeadline()) <= withinDays) {
                list += String.format("%d. %s\n", index, d.toStringFormatted());
                index++;
            }
        }
        GUIStorage.updateFile();
        return list;
    }

    private static boolean checkMarkCommand(String targetString) {
        String[] arr = targetString.split(" ");
        if ((arr[0].equals("mark") || arr[0].equals("unmark")) && arr.length == 2) {
            try {
                Integer taskNum = Integer.valueOf(arr[1]);
                assert taskNum >= 1 && taskNum <= GUITaskList.getSize();
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private static boolean checkSortCommand(String targetString) {
        String[] arr = targetString.split(" ");
        return arr[0].equals("sort") && arr.length == 1;
    }

    private static boolean checkDeleteCommand(String targetString) {
        String[] arr = targetString.split(" ");
        if (arr[0].equals("delete") && arr.length == 2) {
            try {
                Integer taskNum = Integer.valueOf(arr[1]);
                assert taskNum >= 1 && taskNum <= GUITaskList.getSize();
                return true;
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

    private static String parseTaskAddition(String input) throws EmptyTodoException, DeadlineFormatException {
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
            String[] component1 = splitString[1].split(" /from ", 2);
            String[] component2 = component1[1].split(" /to ", 2);
            GUITaskList.addTask(new Event(component1[0], component2[0], component2[1]));
            name = component1[0];
        } else {
            return String.format("Hey %s, I do not understand what you mean by %s", MainWindow.username, input);
        }

        return String.format("Hey %s, I have added \"%s\" into your task list!\n" +
                "You now have %d tasks to complete!\n", MainWindow.username, name, GUITaskList.getSize());
    }

    private static boolean checkRemindCommand(String targetString) {
        String[] arr = targetString.split(" ", 2);
        if (arr[0].equals("remind") && arr.length == 2) {
            try {
                Integer remindLimit = Integer.valueOf(arr[1]);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }
}