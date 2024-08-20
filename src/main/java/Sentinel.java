import java.util.Scanner;

/**
 * Sentinel chatbot system.
 */
public class Sentinel {
    private TaskList taskList;

    public Sentinel() {
        this.taskList = new TaskList();
    }

    /**
     * Makes Sentinel say a greeting message.
     */
    public void greet() {
        String greetMessage = "Greetings! I'm Sentinel. \n" + "What can I do for you?";
        say(greetMessage);
    }

    /**
     * Makes Sentinel say a goodbye message.
     */
    public void goodbye() {
        String goodbyeMessage = "It was a pleasure conversing with you. Goodbye!";
        say(goodbyeMessage);
    }

    /**
     * Makes Sentinel say a message.
     *
     * @param message Message for Sentinel to say.
     */
    public void say(String message) {
        System.out.println(message);
    }

    /**
     * Makes Sentinel echo a message until a bye command is received.
     */
    public void echo() {
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            say(userInput);
            userInput = scanner.nextLine();
        }
    }

    /**
     * Makes Sentinel listen for tasks.
     */
    public void listen() {
        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                say(this.taskList.toString());
            } else {
                this.taskList.add(userInput);
                say(String.format("Added: %s", userInput));
            }

            userInput = scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        Sentinel mySentinel = new Sentinel();
        mySentinel.greet();
        mySentinel.listen();
        mySentinel.goodbye();
    }
}
