package phenex;

import phenex.task.TaskList;
import phenex.ui.Ui;
import phenex.storage.Storage;
import phenex.util.Parser;
import phenex.exception.PhenexException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;


public class Phenex {
    /** Encapsulates the Ui of Phenex */
    private Ui ui;
    /** Encapsulates the task list of Phenex */
    private TaskList tasks;
    /** Encapsulates the storage of Phenex */
    private Storage storage;
    /** Encapsulates the parser of Phenex */
    private Parser parser;

    public enum CommandType {
        COMMAND_MARK, COMMAND_UNMARK, COMMAND_DELETE;
    }

    public Phenex(Path filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage);
        this.ui = new Ui();
        this.parser = new Parser();

    }

    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        Path filePath = Paths.get(home, "Downloads", "CS2103T_AY2425", "iP", "data", "phenex.txt");
        Phenex p = new Phenex(filePath);

        p.ui.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        // regex's for commands which tell phenex.Phenex to do actions
        String terminatingRegex = "(?i)bye\\s*$";
        String listRegex = "(?i)list\\s*$";
        String markRegex = "^mark \\d+\\s*$";
        String unmarkRegex = "^unmark \\d+\\s*$";
        String deleteRegex = "^delete \\d+\\s*$";
        String dateCheckRegex = "^missions on (.+)$";

        // regex's for commands which tell phenex.Phenex to add Task.Task
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
                    // mark phenex.task as done
                    int idx = p.parser.getIndexOfTask(markMatcher, CommandType.COMMAND_MARK);
                    p.tasks.markTaskCompleted(idx);
                } else if (unmarkMatcher.find()) {
                    // unmark phenex.task as done
                    int idx = p.parser.getIndexOfTask(unmarkMatcher, CommandType.COMMAND_UNMARK);
                    p.tasks.markTaskIncomplete(idx);
                } else if (todoMatcher.matches()) {
                    p.tasks.addTask(todoMatcher, TaskList.TaskType.TASK_TODO);
                } else if (deadlineMatcher.matches()) {
                    p.tasks.addTask(deadlineMatcher, TaskList.TaskType.TASK_DEADLINE);
                } else if (eventMatcher.matches()) {
                    p.tasks.addTask(eventMatcher, TaskList.TaskType.TASK_EVENT);
                } else if (deleteMatcher.matches()) {
                    int idx = p.parser.getIndexOfTask(deleteMatcher, CommandType.COMMAND_DELETE);
                    p.tasks.deleteTask(idx);
                } else if (dateCheckMatcher.matches()) {
                    p.ui.printAllTasksOn(dateCheckMatcher, p.tasks);
                } else {
                    Ui.printInvalidInputMessage();
                }
            } catch (PhenexException e) {
                Ui.printExceptionMessage(e);
            }

            p.ui.printLine();
        }

        p.storage.storeTasksToMemory(p.tasks);
        scanner.close();
        p.ui.sayFarewell();
    }
}
