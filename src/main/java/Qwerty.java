import java.util.Scanner;

/**
 * The chatbot class.
 */
public class Qwerty {

    /** True if the bot is currently chatting and accepting input */
    private boolean isChatting;

    public Qwerty() {
        this.isChatting = true;
    }

    /**
     * Prints a greeting message.
     */
    public void greet() {
        System.out.println("""
                
                Hello! I'm Qwerty.
                What can I do for you?
                """);
    }

    /**
     * Prints a goodbye message.
     */
    public void say_goodbye() {
        System.out.println("""
                
                Bye. Hope to see you again soon!
                """);
    }

    /**
     * Starts the chatbot.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        isChatting = true;
        greet();

        while (isChatting) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                isChatting = false;
                say_goodbye();
            } else {
                System.out.println("\n" + input + "\n");
            }

        }
    }

    public static void main(String[] args) {
        Qwerty chatBot = new Qwerty();
        chatBot.start();
    }
}
