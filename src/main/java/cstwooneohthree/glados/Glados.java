package cstwooneohthree.glados;

import java.util.ArrayList;
import java.util.Scanner;

import cstwooneohthree.glados.enums.TaskType;
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

    /**
     * Constructs Glados object by initializing TaskList.
     * TaskList object is created with loading tasks.
     */
    public Glados() {
        this.taskList = new TaskList(true);
    }

    private void run() {
        // Greet the user
        Ui.greet();

        // Initialise a scanner for detecting input
        Scanner sc = new Scanner(System.in);

        // Run a loop indefinitely to execute commands until program exits through bye
        while (true) {
            String input = sc.nextLine().toLowerCase();
            if (Parser.parseCommand(input).equals("bye")) {
                Ui.exit();
                break;
            } else {
                try {
                    String query = Parser.parseCommand(input);
                    switch (query) {
                    case "echo":
                        String echoArgs = Parser.parseArguments(input);
                        Ui.echo(echoArgs);
                        break;
                    case "todo":
                        String todoArgs = Parser.parseArguments(input);
                        add(TaskType.TODO, todoArgs);
                        break;
                    case "deadline":
                        String deadlineArgs = Parser.parseArguments(input);
                        add(TaskType.DEADLINE, deadlineArgs);
                        break;
                    case "event":
                        String eventArgs = Parser.parseArguments(input);
                        add(TaskType.EVENT, eventArgs);
                        break;
                    case "list":
                        list();
                        break;
                    case "mark":
                        String markArgs = Parser.parseArguments(input);
                        mark(Integer.valueOf(markArgs));
                        break;
                    case "unmark":
                        String unmarkArgs = Parser.parseArguments(input);
                        unmark(Integer.valueOf(unmarkArgs));
                        break;
                    case "delete":
                        String deleteArgs = Parser.parseArguments(input);
                        delete(Integer.valueOf(deleteArgs));
                        break;
                    case "find":
                        String findArgs = Parser.parseArguments(input);
                        find(findArgs);
                        break;
                    default:
                        throw new CommandNotFoundException();
                    }
                } catch (GladosException e) {
                    Ui.error(e);
                }
            }
        }
        sc.close();
    }

    private void add(TaskType taskType, String input) throws GladosException {
        String[] res = taskList.add(taskType, input);
        Ui.add(res[0], res[1]);
    }

    private void delete(int index) throws TaskNotFoundException {
        String[] res = taskList.delete(index);
        Ui.delete(res[0], res[1]);
    }

    private void list() {
        ArrayList<Task> res = taskList.list();
        Ui.list(res, false);
    }

    private void mark(int index) throws TaskNotFoundException {
        String res = taskList.mark(index);
        Ui.mark(res);
    }

    private void unmark(int index) throws TaskNotFoundException {
        String res = taskList.unmark(index);
        Ui.unmark(res);
    }

    private void find(String input) {
        ArrayList<Task> res = taskList.find(input);
        Ui.list(res, true);
    }

    /**
     * Initializes a new Glados object and invokes its run method.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Glados().run();
    }
}
