import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;


public class Phenex {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    public Phenex() {
        this.storage = new Storage();
        this.tasks = new TaskList(this.storage);
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Phenex p = new Phenex();
        p.ui.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        // regex's for commands which tell Phenex to do actions
        String terminatingRegex = "(?i)bye\\s*$";
        String listRegex = "(?i)list\\s*$";
        String markRegex = "^mark \\d+\\s*$";
        String unmarkRegex = "^unmark \\d+\\s*$";
        String deleteRegex = "^delete \\d+\\s*$";
        String dateCheckRegex = "^missions on (.+)$";

        // regex's for commands which tell Phenex to add Task
        String todoRegex = "^(?i)todo (.+)";
        String deadlineRegex = "^(?i)deadline (.+) /by (.+)";
        String eventRegex = "^(?i)event (.+) /from (.+) /to (.+)$";

        // Patterns and Matchers for each regex
        Pattern terminatingPattern = Pattern.compile(terminatingRegex);
        Pattern listPattern = Pattern.compile(listRegex);
        Pattern markPattern = Pattern.compile(markRegex);
        Pattern unmarkPattern = Pattern.compile(unmarkRegex);
        Pattern todoPattern = Pattern.compile(todoRegex);
        Pattern deadlinePattern = Pattern.compile(deadlineRegex);
        Pattern eventPattern = Pattern.compile(eventRegex);
        Pattern deletePattern = Pattern.compile(deleteRegex);
        Pattern dateCheckPattern = Pattern.compile(dateCheckRegex);

        Matcher terminatingMatcher;
        Matcher listMatcher;
        Matcher markMatcher;
        Matcher unmarkMatcher;
        Matcher todoMatcher;
        Matcher deadlineMatcher;
        Matcher eventMatcher;
        Matcher deleteMatcher;
        Matcher dateCheckMatcher;

        while (true) {
            // scan inputs
            userInput = scanner.nextLine();
            terminatingMatcher = terminatingPattern.matcher(userInput);

            // detect terminating string
            if (terminatingMatcher.find()) {
                break;
            }

            // Matchers to detect which input
            listMatcher = listPattern.matcher(userInput);
            markMatcher = markPattern.matcher(userInput);
            unmarkMatcher = unmarkPattern.matcher(userInput);
            todoMatcher = todoPattern.matcher(userInput);
            deadlineMatcher = deadlinePattern.matcher(userInput);
            eventMatcher = eventPattern.matcher(userInput);
            deleteMatcher = deletePattern.matcher(userInput);
            dateCheckMatcher = dateCheckPattern.matcher(userInput);

            p.ui.printLine();

            try {
                if (listMatcher.find()) {
                    p.ui.printTaskList(p.tasks);
                } else if (markMatcher.find()) {
                    // mark task as done
                    int taskNumber = Integer.parseInt(markMatcher.group().substring(5));
                    int idx = taskNumber - 1;
                    p.tasks.markTaskCompleted(idx);
                } else if (unmarkMatcher.find()) {
                    // unmark task as done
                    int taskNumber = Integer.parseInt(unmarkMatcher.group().substring(7));
                    int idx = taskNumber - 1;
                    p.tasks.markTaskIncomplete(idx);
                } else if (todoMatcher.matches()) {
                    p.tasks.addTask(todoMatcher, TaskList.TaskType.TASK_TODO);
                } else if (deadlineMatcher.matches()) {
                    p.tasks.addTask(deadlineMatcher, TaskList.TaskType.TASK_DEADLINE);
                } else if (eventMatcher.matches()) {
                    p.tasks.addTask(eventMatcher, TaskList.TaskType.TASK_EVENT);
                } else if (deleteMatcher.matches()) {
                    int idx = Integer.parseInt(deleteMatcher.group().substring(7)) - 1;
                    p.tasks.deleteTask(idx);
                } else if (dateCheckMatcher.matches()) {
                    p.ui.printAllTasksOn(dateCheckMatcher, p.tasks);
                } else {
                    System.out.println("\tError, invalid input.");
                }
            } catch (PhenexException e) {
                System.out.println("WARNING! SYSTEM OVERLOAD " + e.getMessage());
            }


            p.ui.printLine();
        }

        p.storage.storeTasksToMemory(p.tasks);
        scanner.close();
        p.ui.sayFarewell();
    }
}
