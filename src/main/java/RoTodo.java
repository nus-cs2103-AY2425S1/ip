import java.util.Scanner;

import rotodo.tasklist.TaskList;
import rotodo.exception.IncompleteInputException;
import rotodo.exception.InvalidInputException;;

/**
 * __________       __________            __   _____
 * \______   \  ____\__   ___/____    ___|  | /  _  \   ____    
 *  |       _/ /  _ \ |   |  /  _ \  /  _   ||  / \  | /  _ \   ___
 *  |    |   \(  <_> ||   | (  <_> |(  <_>  ||  \_/  |(  <_> | / o \  _
 *  |____|_  / \____/ |___|  \____/  \_____/  \_____/  \____/  \___/ (_) O o . 
 *         \/
 * This class implements the CLI of RoTodo.
 * 
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class RoTodo {
    /**
     * Different types of tasks
     */
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    /**
     * Different types of commands
     */
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String HELP = "help";
    private static final String LIST = "list";
    private static final String EXIT = "bye";

    /**
     * Wraps input string x with line text above and below.
     * 
     * @param x
     */
    public static void print(String x) {
        System.out.println("  " + new String(new char[100]).replace("\0", "-"));
        System.out.println(x);
        System.out.println("  " + new String(new char[100]).replace("\0", "-") + "\n");
    }

    /**
     * Prints chatbot banner.
     */
    public static void banner() {
        // Declaring ANSI_COLOR 
        String ansiReset = "\u001B[0m"; 
        String ansiRed = "\u001B[31m"; 
        
        RoTodo.print("    Hello! I'm \n" 
            + ("    R__________E       __________            __   _____\n"
            + "    R\\______   \\E  ____\\__   ___/____    ___|  | /  _  \\   ____\n"
            + "     R|       _/E /  _ \\ |   |  /  _ \\  /  _   ||  / \\  | /  _ \\   ___\n"
            + "     R|    |   \\E(  <_> ||   | (  <_> |(  <_>  ||  \\_/  |(  <_> | / o \\  _\n"
            + "     R|____|_  /E \\____/ |___|  \\____/  \\_____/  \\_____/  \\____/  \\___/ (_) O o .\n"
            + "            R\\/E\n").replace("R", ansiRed).replace("E", ansiReset)
            + "    Your very own Robot Todo List!\n"
            + "    How can I help you help yourself?");
    }

    public static void help() {
        RoTodo.print(
            "    Options:\n"
            + "      help          list all available options\n"
            + "      list          Prints all tasks on tasklist\n"
            + "      mark INDEX    Mark task as done\n"
            + "      unmark INDEX  Mark task as undone\n"
            + "      bye           Exit program\n\n"
            + "      Supported Task commands:\n"
            + "        todo TASK_DESCRIPTION\n"
            + "                    Add new ToDo task to tasklist\n"
            + "        deadline TASK_DESCRIPTION /by DEADLINE\n"
            + "                    Add new Deadline task to tasklist,\n"
            + "                    with due by date/time\n"
            + "        event TASK_DESCRIPTION /from START /to END\n"
            + "                    Add new Event task to tasklist,\n"
            + "                    with start and end date/time\n\n");
    }

    /**
     * Process user inputs
     * 
     * @param taskList
     * @param input
     * @throws InvalidInputException
     */
    public static void process(TaskList taskList, String input) throws InvalidInputException {
        String[] token = input.split(" ", 2);
        boolean mark = false;
        switch (token[0]) {
            case LIST:
                RoTodo.print(taskList.toString());
                break;

            case EXIT:
                RoTodo.exit();
                break;

            case MARK:
                mark = true;
            case UNMARK:
                if (token.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo need task number");
                }

                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(token[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new InvalidInputException(String.format("'%s' not a "
                        + "number RoTodo knows (and RoTodo know much numbers, "
                        + "like 1s and 0s)", token[1]));
                }
                
                if (mark) RoTodo.print(taskList.markDone(taskNumber));
                else RoTodo.print(taskList.unmarkDone(taskNumber));
                break;

            case TODO:
                if (token.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n      "
                        + "RoTodo needs a task description");
                }
                RoTodo.print(taskList.addTask(token[1]));
                break;
            
            case DEADLINE:
                if (token.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n      "
                        + "RoTodo needs a task description and deadline");
                }
                token = token[1].split(" /by ", 2);
                if (token.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n      "
                        + "RoTodo needs a task description and deadline");
                }
                RoTodo.print(taskList.addTask(token[0], token[1]));
                break;

            case EVENT:
                if (token.length < 2) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n      "
                        + "RoTodo needs a task description, from and to date/time");
                }
                token = token[1].split(" /from | /to ", 3);
                if (token.length < 3) {
                    throw new IncompleteInputException(
                        "RoTodo can't read you mind, otherwise "
                        + "RoTodo's creator would be rich!\n      "
                        + "RoTodo needs a task description, from and to date/time");
                }
                boolean fromBeforeTo = input.indexOf("/from") < input.indexOf("/to");
                if (fromBeforeTo) {
                    RoTodo.print(taskList.addTask(token[0], token[1], token[2]));
                } else {
                    RoTodo.print(taskList.addTask(token[0], token[2], token[1]));
                }
                break;
            
            case HELP:
                RoTodo.help();
        
            default:
                throw new InvalidInputException(
                    "Reep Roop... RoTodo Read No Understand?");
        }
        InvalidInputException.noError();
    }

    /**
     * Exits program after printing goodbye text.
     */
    public static void exit() {
        RoTodo.print("    Bye, RoTodo wish you all the best. Remember to finish all your tasks!");
        System.exit(0);
    }

    public static void main(String[] args) {
        RoTodo.banner();
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        TaskList taskList = new TaskList();
        while (true) {
            try {
                RoTodo.process(taskList, sc.nextLine());
            } catch (InvalidInputException e) {
                RoTodo.print(e.toString());
            }
        }
    }
}
