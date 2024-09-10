package lumina.main;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lumina.exception.LuminaException;
import lumina.parser.Parser;
import lumina.storage.Storage;
import lumina.task.TaskList;
import lumina.ui.Ui;

/**
 * Represents a simple chatbot.
 * It uses predefined set of responses and can be extended for more complex behaviour
 */
public class Lumina {

    // counter for instances
    private static int luminaCount = 0;

    // commands
    private static final String ECHO_EXIT_STRING = "bye";
    private static final String ECHO_LIST_STRING = "list";
    private static final String ECHO_MARK_TASK_STRING = "mark";
    private static final String ECHO_UNMARK_TASK_STRING = "unmark";
    private static final String ECHO_TODO_TASK = "todo";
    private static final String ECHO_DEADLINE_TASK = "deadline";
    private static final String ECHO_EVENT_TASK = "event";
    private static final String ECHO_DELETE_TASK = "delete";
    private static final String ECHO_FIND_TASK = "find";

    // composed objects
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList;

    /**
     * Constructor for the chatbot
     *
     * @throws IllegalStateException If already instantiated
     */
    public Lumina() {
        if (luminaCount > 0) {
            // since for now we only have one data file, we will prevent more instantiation
            // of more than once Lumina instance, to prevent data from being corrupted
            throw new IllegalStateException("Lumina has already been instantiated!");
        }
        luminaCount++;
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(parser);
        taskList = new TaskList(ui, parser);

        storage.loadData(taskList);
    }

    private void echo(Scanner sc) {
        ui.greet();
        String msg;
        while (true) {
            System.out.println();
            msg = sc.nextLine();
            String command = msg.split(" ")[0].trim();

            if (command.equals(Lumina.ECHO_EXIT_STRING)) {
                storage.saveData(taskList);
                ui.exit();
                break;
            }
            if (command.equals(Lumina.ECHO_LIST_STRING)) {
                taskList.listTasks();
                continue;
            }
            // first check unmark since mark contains unmark
            if (command.equals(Lumina.ECHO_UNMARK_TASK_STRING)) {
                try {
                    String[] msgSplit = msg.split(" ");
                    if (msgSplit.length == 2) {
                        int taskIndex = Integer.parseInt(msgSplit[1]) - 1; // 0 indexed
                        taskList.markTaskNotDone(taskIndex);
                    } else {
                        throw new LuminaException("Oh no! Lumina detected "
                                + "unexpected number of parameters in your command! "
                                + "Please try again");
                    }
                } catch (LuminaException e) {
                    ui.printMessage(e.getMessage());
                }
                continue;
            }
            if (command.equals(Lumina.ECHO_MARK_TASK_STRING)) {
                try {
                    String[] msgSplit = msg.split(" ");
                    if (msgSplit.length == 2) {
                        int taskIndex = Integer.parseInt(msgSplit[1]) - 1; // 0 indexed
                        taskList.markTaskDone(taskIndex);
                    } else {
                        throw new LuminaException("Oh no! Lumina detected "
                                + "unexpected number of parameters in your command! "
                                + "Please try again");
                    }
                } catch (LuminaException e) {
                    ui.printMessage(e.getMessage());
                }
                continue;
            }
            if (command.equals(Lumina.ECHO_TODO_TASK)) {
                try {
                    taskList.handleTodoTask(msg);
                } catch (LuminaException e) {
                    ui.printMessage(e.getMessage());
                }
                continue;
            }
            if (command.equals(Lumina.ECHO_EVENT_TASK)) {
                try {
                    taskList.handleEventTask(msg);
                } catch (LuminaException e) {
                    ui.printMessage(e.getMessage());
                }
                continue;
            }
            if (command.equals(Lumina.ECHO_DEADLINE_TASK)) {
                try {
                    taskList.handleDeadlineTask(msg);
                } catch (LuminaException e) {
                    ui.printMessage(e.getMessage());
                }
                continue;
            }
            if (command.equals(Lumina.ECHO_DELETE_TASK)) {
                try {
                    String[] msgSplit = msg.split(" ");
                    if (msgSplit.length == 2) {
                        int taskIndex = Integer.parseInt(msgSplit[1]) - 1; // 0 indexed
                        taskList.deleteTask(taskIndex);
                    } else {
                        throw new LuminaException("Oh no! Lumina detected "
                                + "unexpected number of parameters in your command! "
                                + "Please try again");
                    }
                } catch (LuminaException e) {
                    ui.printMessage(e.getMessage());
                }
                continue;
            }
            if (command.equals(Lumina.ECHO_FIND_TASK)) {

                try {
                    // Define the regex pattern to match the first word followed by any whitespace
                    Pattern pattern = Pattern.compile("^\\S+\\s*(.*)$");
                    Matcher matcher = pattern.matcher(msg);

                    // Check if the pattern matches
                    if (matcher.find()) {
                        taskList.findTasks(matcher.group(1));
                    } else {
                        // if it doesn't throw exception
                        throw new LuminaException("Oh no! invalid find command! Please try again");
                    }
                } catch (LuminaException e) {
                    ui.printMessage(e.getMessage());
                }
                continue;
            }

            // exception
            ui.printMessage("Oh no! I don't know what to do with this command! "
                    + "Please try again");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Lumina lumina = new Lumina();
        lumina.echo(sc);
    }
}
