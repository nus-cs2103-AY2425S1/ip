package lemon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import lemon.exception.DescriptionException;
import lemon.exception.InvalidCommandException;
import lemon.exception.InvalidFormatException;
import lemon.task.Deadline;
import lemon.task.Event;
import lemon.task.Task;
import lemon.task.Todo;

/**
 * Lemon chatbot that can be used to track tasks given to it
 * @author He Yiheng
 */
public class Lemon {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList tasks = new TaskList();
    private boolean isInitialised = false;
    private Scanner systemScanner;

    /**
     * Constructor for lemon
     */
    public Lemon() {
        systemScanner = new Scanner(System.in);
        tasks = new TaskList();
        isInitialised = storage.loadTasks(tasks);
    }

    /**
     * Execute lemon once initialization is done
     */
    public void runLemon() {
        if (!isInitialised) {
            System.out.print(" Ouhiiee, my head hurrrtt, i dont think im initialized properly ;-;\n"
                    + " Please try again...");
            return;
        }

        boolean isRunning = true;

        ui.printIntroMsg();

        while (isRunning) {
            ui.printBarMsg();

            String input = systemScanner.next().toUpperCase();
            ui.printBarMsg();
            try {
                Parser.Commands command = Parser.Commands.valueOf(input);
                switch (command) {
                case BYE: {
                    boolean isAbleToSave = storage.saveTasks(tasks);
                    if (isAbleToSave) {
                        isRunning = false;
                        ui.printEndingMsg();
                    }
                    break;
                }
                case LIST:
                    if (tasks.size() == 0) {
                        ui.printEmptyListMsg();
                    } else {
                        ui.printListMsg(tasks.toString());
                    }
                    break;
                case MARK: {
                    int next = systemScanner.nextInt();
                    if (next > tasks.size() || next <= 0) {
                        throw new InvalidCommandException(" OOPS!!! Please select a valid task");
                    }

                    ui.printMarkMsg();
                    tasks.get(next - 1).markDone();

                    System.out.println("   " + tasks.get(next - 1).toString());
                    break;
                }
                case UNMARK: {
                    int next = systemScanner.nextInt();
                    if (next > tasks.size() || next <= 0) {
                        throw new InvalidCommandException(" OOPS!!! Please select a valid task");
                    }

                    ui.printUnmarkMsg();
                    tasks.get(next - 1).unmarkDone();

                    System.out.println("   " + tasks.get(next - 1).toString());
                    break;
                }
                case TODO: {
                    String next = systemScanner.nextLine();

                    Task todo = new Todo(next, false);
                    tasks.addNewTask(todo);
                    ui.printAddTaskMsg(todo.toString(), tasks.size());
                    break;
                }
                case DEADLINE: {
                    String[] next = systemScanner.nextLine().split("/by ");
                    if (next.length < 2) {
                        throw new InvalidFormatException(" Missing/incorrect date format \n"
                                + " Use /by and format date as dd-mm-yyyy");
                    }

                    LocalDate by = LocalDate.parse(next[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    Task deadline = new Deadline(next[0], by, false);
                    tasks.addNewTask(deadline);
                    ui.printAddTaskMsg(deadline.toString(), tasks.size());
                    break;
                }
                case EVENT: {
                    String[] next = systemScanner.nextLine().split("/from | /to ");

                    if (next.length < 3) {
                        throw new InvalidFormatException(" Missing/incorrect date format \n"
                                + " Use /by and format date as dd-mm-yyyy");
                    }

                    LocalDate from = LocalDate.parse(next[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    LocalDate to = LocalDate.parse(next[1], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                    Task event = new Event(next[0], from, to, false);
                    tasks.addNewTask(event);
                    ui.printAddTaskMsg(event.toString(), tasks.size());
                    break;
                }
                case DELETE: {
                    int next = systemScanner.nextInt();
                    if (next > tasks.size() || next <= 0) {
                        throw new InvalidCommandException(" OOPS!!! Please select a valid task");
                    }

                    Task t = tasks.deleteTask(next);
                    ui.printDeleteTaskMsg(t.toString(), tasks.size());
                    break;
                }
                case FIND: {
                    String next = systemScanner.nextLine();
                    if (next.isEmpty() || next.equals(" ")) {
                        throw new InvalidFormatException(" Please specify what you need me to find ^-^");
                    }

                    TaskList matchingTasks = tasks.findTasks(next);
                    if (matchingTasks.size() > 0) {
                        ui.printMatchingTaskMsg(matchingTasks.toString());
                    } else {
                        ui.printNoMatchingMsg();
                    }
                    break;
                }
                default:
                    throw new IllegalArgumentException();
                }
            } catch (InvalidCommandException | DescriptionException e) {
                System.out.println(e.getMessage());
                systemScanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(" OOPS!!! I cant understand your mark/unmark, please select using numbers xd");
                systemScanner.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println(" OOPS!!! I'm sowwy, but I don't know what that means :-(\n\n"
                        + " I can help you add tasks with \"todo\", \"deadline\", \"event\"\n"
                        + " I can also keep track of all your tasks with \"list\"\n"
                        + " If you wanna update certain tasks, use \"mark\" or \"unmark\" and then its number");
                systemScanner.nextLine();
            } catch (DateTimeParseException e) {
                System.out.println(" Incorrect date format, make sure the format is dd-MM-yyyy");
            } catch (InvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Lemon lemon = new Lemon();
        lemon.runLemon();
    }
}
