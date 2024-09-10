package cstwooneohthree.glados;

import java.util.ArrayList;
import java.util.Scanner;

import cstwooneohthree.glados.enums.TaskType;
import cstwooneohthree.glados.enums.UiType;
import cstwooneohthree.glados.exceptions.CommandNotFoundException;
import cstwooneohthree.glados.exceptions.GladosException;
import cstwooneohthree.glados.exceptions.TaskNotFoundException;
import cstwooneohthree.glados.tasks.Task;
import cstwooneohthree.glados.tasks.TaskList;
import cstwooneohthree.glados.utils.Parser;
import cstwooneohthree.glados.utils.Ui;

/**
 * Glados class to initiate and run CLI.
 *
 * @author jayjay19630
 */
public class Glados {
    /* TaskList object to manage tasks */
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs Glados object by initializing TaskList.
     * TaskList object is created with loading tasks.
     */
    public Glados(UiType uiType) {
        this.taskList = new TaskList(true);
        this.ui = new Ui(uiType);
    }

    private void runCommandLineInterface() {
        // Greet the user
        greet();

        // Initialise a scanner for detecting input
        Scanner sc = new Scanner(System.in);

        // Run a loop indefinitely to execute commands until program exits through bye
        while (true) {
            String input = sc.nextLine().toLowerCase();
            if (Parser.parseCommand(input).equals("bye")) {
                ui.exit();
                break;
            } else {
                executeCommand(input);
            }
        }
        sc.close();
    }

    /**
     * Executes a command given by user.
     *
     * @param input Command to be executed.
     * @return String to be shown in the UI.
     */
    public String executeCommand(String input) {
        try {
            String query = Parser.parseCommand(input);
            switch (query) {
            case "echo":
                String echoArgs = Parser.parseArguments(input);
                return ui.echo(echoArgs);
            case "todo":
                String todoArgs = Parser.parseArguments(input);
                return add(TaskType.TODO, todoArgs);
            case "deadline":
                String deadlineArgs = Parser.parseArguments(input);
                return add(TaskType.DEADLINE, deadlineArgs);
            case "event":
                String eventArgs = Parser.parseArguments(input);
                return add(TaskType.EVENT, eventArgs);
            case "list":
                return list();
            case "mark":
                String markArgs = Parser.parseArguments(input);
                return mark(Integer.valueOf(markArgs));
            case "unmark":
                String unmarkArgs = Parser.parseArguments(input);
                return unmark(Integer.valueOf(unmarkArgs));
            case "delete":
                String deleteArgs = Parser.parseArguments(input);
                return delete(Integer.valueOf(deleteArgs));
            case "find":
                String findArgs = Parser.parseArguments(input);
                return find(findArgs);
            default:
                throw new CommandNotFoundException();
            }
        } catch (GladosException e) {
            return ui.printError(e);
        }
    }

    public String greet() {
        return ui.greet();
    }

    private String add(TaskType taskType, String input) throws GladosException {
        String[] res = taskList.add(taskType, input);
        return ui.add(res[0], res[1]);
    }

    private String delete(int index) throws TaskNotFoundException {
        String[] res = taskList.delete(index);
        return ui.delete(res[0], res[1]);
    }

    private String list() {
        ArrayList<Task> res = taskList.list();
        return ui.list(res, false);
    }

    private String mark(int index) throws TaskNotFoundException {
        String res = taskList.mark(index);
        return ui.mark(res);
    }

    private String unmark(int index) throws TaskNotFoundException {
        String res = taskList.unmark(index);
        return ui.unmark(res);
    }

    private String find(String input) {
        ArrayList<Task> res = taskList.find(input);
        return ui.list(res, true);
    }

    /**
     * Initializes a new Glados object and invokes its run method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Glados(UiType.COMMAND_LINE_INTERFACE).runCommandLineInterface();
    }
}
