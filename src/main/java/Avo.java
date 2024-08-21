import Tasks.*;
import Exceptions.AvoException;

import java.util.Objects;
import java.util.Scanner;

public class Avo {
    private final TaskManager manager;
    private Avo() {
        manager = new TaskManager();
    }
    private void start() {
        String greetingMessage = "Hello, I am Avo.\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    private void stop() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    private void run() {
        try {
            listen();
        } catch (AvoException e) {
            System.out.println(e.getMessage());
            run();
        }
    }
    private void listTasks() {
        manager.listTasks();
    }
    private void markTask(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < 2) {
            throw new AvoException("OOPS!!! The task number cannot be empty.");
        }
        manager.completeTask(Integer.parseInt(inputs[1]) - 1);
    }
    private void unMarkTask(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < 2) {
            throw new AvoException("OOPS!!! The task number cannot be empty.");
        }
        manager.unCompleteTask(Integer.parseInt(inputs[1]) - 1);
    }
    private void deleteTask(String userInput) throws AvoException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < 2) {
            throw new AvoException("OOPS!!! The task number cannot be empty.");
        }
        manager.deleteTask(Integer.parseInt(inputs[1]) - 1);
    }
    private void addToDo(String userInput) throws AvoException {
        String[] inputs = userInput.split("todo ");
        if (inputs.length < 2) {
            throw new AvoException("OOPS!!! The description of a todo cannot be empty.");
        }
        manager.addTask(new ToDo(inputs[1]));
    }
    private void addDeadline(String userInput) throws AvoException {
        String[] inputs = userInput.split("deadline |/by ");
        if (inputs.length < 3) {
            throw new AvoException("OOPS!!! The description of a deadline cannot be empty.");
        }
        manager.addTask(new Deadline(inputs[1], inputs[2]));
    }
    private void addEvent(String userInput) throws AvoException {
        String[] inputs = userInput.split("event |/from |/to ");
        if (inputs.length < 4) {
            throw new AvoException("OOPS!!! The description of an event cannot be empty.");
        }
        manager.addTask(new Event(inputs[1], inputs[2], inputs[3]));
    }
    @SuppressWarnings("InfiniteLoopStatement")
    private void listen() throws AvoException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            String command = userInput.split(" ")[0];
            switch (command) {
                case "exit":
                    break;
                case "list":
                    listTasks();
                    break;
                case "todo":
                    addToDo(userInput);
                    break;
                case "deadline":
                    addDeadline(userInput);
                    break;
                case "event":
                    addEvent(userInput);
                    break;
                case "mark":
                    markTask(userInput);
                    break;
                case "unmark":
                    unMarkTask(userInput);
                    break;
                case "delete":
                    deleteTask(userInput);
                    break;
                default:
                    throw new AvoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
        Avo chatBot = new Avo();
        chatBot.start();
        chatBot.run();
        chatBot.stop();
    }
}
