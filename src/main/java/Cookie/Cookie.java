package Cookie;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Cookie {
    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private TaskList taskList;
    private Parser parser = new Parser();

    private void handlePrintTasks() {
        ui.printBeforeListResult();
        ui.printTasks(taskList.getTaskArrayList());
    }

    private void handleDelete(String string) throws CookieException {
        if (string.isEmpty()) {
            throw new CookieException("Cookie does not know which task to delete.\n " +
                    "(Please enter an integer after \"delete\")");
        }

        int deleteIndex = parser.parseInteger(string);

        if (deleteIndex <= 0 || deleteIndex > taskList.getSize()) {
            throw  new CookieException("The task you want to delete does not exist");
        }
        ui.printDeleteTask(this.taskList.getTask(deleteIndex));
        this.taskList.delete(deleteIndex);
        ui.printNoTasksInList(this.taskList.getTaskArrayList());
    }

    private void handleMark(String string) throws CookieException {
        if (string.isEmpty()) {
            throw new CookieException("Cookie does not know which task to mark.\n " +
                    "(Please enter an integer after \"mark\")");
        }
        int markIndex = parser.parseInteger(string);
        if (markIndex <= 0 || markIndex > taskList.getSize()) {
            throw  new CookieException("The task you want to mark does not exist");
        }
        this.taskList.markDone(markIndex);
        ui.printMarkTask(this.taskList.getTask(markIndex));
    }

    private void handleUnmark(String string) throws CookieException {
        if (string.isEmpty()) {
            throw new CookieException("Cookie does not know which task to mark.\n " +
                    "(Please enter an integer after \"mark\")");
        }

        int unmarkIndex = parser.parseInteger(string);
        if (unmarkIndex <= 0 || unmarkIndex > taskList.getSize()) {
            throw  new CookieException("The task you want to mark does not exist");
        }
        this.taskList.unmarkDone(unmarkIndex);
        ui.printUnmarkTask(this.taskList.getTask(unmarkIndex));
    }

    private void handleTodo(String string) throws CookieException {
        if (string.isEmpty()) {
            throw new CookieException("Please enter a task for you to do.");
        }
        ToDo newTodoTask = new ToDo(string);
        this.taskList.addTask(newTodoTask);
        ui.printLatestTask(newTodoTask);
        ui.printNoTasksInList(this.taskList.getTaskArrayList());
    }

    private void handleDeadline(String string) throws CookieException {
        String[] deadlineDetails = parser.parseDeadline(string);
        if (deadlineDetails.length < 2 || deadlineDetails[0].isEmpty() || deadlineDetails[1].isEmpty()) {
            throw new CookieException("Deadlines must include a task todo and a due date \n" +
                    "[task] /by [deadline]");
        }
        Deadline newDeadlineTask = new Deadline(deadlineDetails[0], deadlineDetails[1]);
        taskList.addTask(newDeadlineTask);
        ui.printLatestTask(newDeadlineTask);
        ui.printNoTasksInList(this.taskList.getTaskArrayList());
    }

    private void handleEvent(String string) throws CookieException {
        String[] eventDetails = parser.parseEvent(string);
        if (eventDetails.length < 2 || eventDetails[0].isEmpty() ||
                eventDetails[1].isEmpty() || eventDetails[2].isEmpty()) {
            throw new CookieException("Events must include a task, a start time and an end time \n" +
                    "[task] /from [start] /to [end]");
        }
        Event newEventTask = new Event(eventDetails[0], eventDetails[1], eventDetails[2]);
        taskList.addTask(new Event(eventDetails[0], eventDetails[1], eventDetails[2]));
        ui.printLatestTask(newEventTask);
        ui.printNoTasksInList(this.taskList.getTaskArrayList());
    }

    private void handleFind(String string) {
        ArrayList<Task> arrayMatchKeyword = taskList.findByKeyword(string);
        ui.printBeforeFindResult();
        ui.printTasks(arrayMatchKeyword);
    }

    /**
     * Boots up Cookie bot.
     */
    public void run() {
        try {
            taskList = new TaskList(storage.loadFile(storage.fetchFile()));
        } catch (FileNotFoundException | CookieException e) {
            System.out.println(e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            try {
                String command = parser.parseCommand(input);
                String description = parser.parseDescription(input);

                switch (command) {
                    case "list":
                        handlePrintTasks();
                        break;
                    case "find":
                        handleFind(description);
                        break;
                    case "delete":
                        handleDelete(description);
                        break;
                    case "mark":
                        handleMark(description);
                        break;
                    case "unmark":
                        handleUnmark(description);
                        break;
                    case "todo":
                        handleTodo(description);
                        break;
                    case "deadline":
                        handleDeadline(description);
                        break;
                    case "event":
                        handleEvent(description);
                        break;
                    default:
                        throw new CookieException("Cookie does not understand this command. I'm sorry!!");

                }
            } catch (CookieException e) {
                System.out.println(e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }
        try {
            storage.saveFile(taskList.getTaskArrayList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.printQuit();
        scanner.close();
    }
    public static void main(String[] args) {
        Cookie cookie = new Cookie();
        cookie.ui.printLogo();
        cookie.ui.printGreet();
        cookie.run();
    }
}
