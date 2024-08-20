import Tasks.*;
import Exceptions.AvoException;

import java.util.Objects;
import java.util.Scanner;

public class Avo {
    private final TaskManager manager;
    public Avo() {
        manager = new TaskManager();
    }
    public void start() {
        String greetingMessage = "Hello, I am Avo.\nWhat can I do for you?";
        System.out.println(greetingMessage);
    }
    public void stop() {
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }
    public void listen() throws AvoException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            if (Objects.equals(userInput, "exit")) {
                break;
            }
            if (userInput.equals("list")) {
                manager.listTasks();
            } else if (userInput.startsWith("todo")) {
                String[] inputs = userInput.split("todo ");
                if (inputs.length < 2) {
                    throw new AvoException("OOPS!!! The description of a todo cannot be empty.");
                }
                manager.addTask(new ToDo(inputs[1]));
            } else if (userInput.startsWith("deadline")) {
                String[] inputs = userInput.split("deadline |/by ");
                if (inputs.length < 3) {
                    throw new AvoException("OOPS!!! The description of a deadline cannot be empty.");
                }
                manager.addTask(new Deadline(inputs[1], inputs[2]));
            } else if (userInput.startsWith("event")) {
                String[] inputs = userInput.split("event |/from |/to ");
                if (inputs.length < 4) {
                    throw new AvoException("OOPS!!! The description of an event cannot be empty.");
                }
                manager.addTask(new Event(inputs[1], inputs[2], inputs[3]));
            } else if (userInput.startsWith("mark")) {
                String[] inputs = userInput.split(" ");
                if (inputs.length < 2) {
                    throw new AvoException("OOPS!!! The task number cannot be empty.");
                }
                manager.completeTask(Integer.parseInt(inputs[1]) - 1);
            } else if (userInput.startsWith("unmark")) {
                String[] inputs = userInput.split(" ");
                if (inputs.length < 2) {
                    throw new AvoException("OOPS!!! The task number cannot be empty.");
                }
                manager.unCompleteTask(Integer.parseInt(inputs[1]) - 1);
            } else {
                throw new AvoException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
    private void run() {
        try {
            listen();
        } catch (AvoException e) {
            System.out.println(e.getMessage());
            run();
        }
    }
    public static void main(String[] args) {
        Avo chatBot = new Avo();
        chatBot.start();
        chatBot.run();
        chatBot.stop();
    }
}
