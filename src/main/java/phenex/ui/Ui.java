package phenex.ui;

import phenex.exception.PhenexException;
import phenex.task.Task;
import phenex.task.TaskList;
import phenex.task.TaskWithDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

public class Ui {

    private final String LINE = "\t____________________________________________________________\n";
    private final String LOGO = "  _____    _    _   ______   _   _   ______  __   __\n"
            + " |  __ \\  | |  | | |  ____| | \\ | | |  ____| \\ \\ / /\n"
            + " | |__) | | |__| | | |__    |  \\| | | |__     \\ V /\n"
            + " | |      | |  | | | |____  | |\\  | | |____   / . \\\n"
            + " |_|      |_|  |_| |______| |_| \\_| |______| /_/ \\_\\\n";
    private final String MSG_GREET = "Hello! I'm\n"
            + LOGO
            + "Your favourite solid gold mobile suit!\n"
            + LINE
            + "\tWhat can I do for you?\n"
            + LINE;
    private final String MSG_FAREWELL = "\t Goodbye. Extinguish the Zeon forces on your way out!\n" + LINE;

    public void greet() {
        System.out.print(MSG_GREET);
    }

    public void sayFarewell() {
        System.out.println(MSG_FAREWELL);
    }

    public void printLine() {
        System.out.print(LINE);
    }

    public void printTaskList(TaskList taskList) {
        int size = taskList.getTasks().size();
        if (size == 0) {
            System.out.println("\t No scheduled missions. Rest up for the next battle, soldier!");
            return;
        }

        System.out.println("\t Outstanding missions:");
        for (int i = 0; i < size; i++) {
            String row = "\t "
                    + (i + 1)
                    + "."
                    + taskList.getTaskByIdx(i);
            System.out.println(row);
        }
    }

    public void printAllTasksOn(Matcher matcher, TaskList taskList) throws PhenexException {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(matcher.group().substring(12));
        } catch (DateTimeParseException e) {
            throw new PhenexException(e.getMessage());
        }

        System.out.println("\tMissions on " + localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " :\n");
        for (Task task : taskList.getTasks()) {
            if (task instanceof TaskWithDate) {
                // ok to cast here due to type checking during run time
                TaskWithDate taskWithDate = (TaskWithDate) task;
                if (taskWithDate.overlapsWith(localDate)) {
                    System.out.println("\t" + taskWithDate);
                }
            }
        }
    }

    public static void printInvalidInputMessage() {
        System.out.println("\tUnknown input!");
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printTaskMarkedCompleteMessage(Task taskMarked) {
        System.out.println("\t Mission marked as complete. Good job, soldier!");
        System.out.println("\t\t" + taskMarked);
    }

    public static void printTaskMarkedIncompleteMessage(Task taskMarked) {
        System.out.println("\t Mission marked as incomplete.");
        System.out.println("\t\t" + taskMarked);
    }

    public static void printTaskDeletedMessage(Task taskDeleted, int taskListSize) {
        System.out.println("\t OK. Mission aborted, retreat!");
        System.out.println("\t  " + taskDeleted);
        System.out.println("\t " + taskListSize + " missions remaining. Destroy the enemy!");
    }

    public static void printTaskAddedMessage(Task taskAdded, int taskListSize) {
        System.out.println("\t Mission " + taskAdded.getName() + " added:");
        System.out.println("\t   " + taskAdded);
        System.out.println("\t Total upcoming missions: " + taskListSize);
    }

    public static void printMemoryInitialisedMessage() {
        System.out.println("\tMemory successful initialised.");
    }

    public static void printMemoryInitialisingFailureMessage() {
        System.out.println("\tCorrupted memory detected. Aborting sequence!");
    }
}
