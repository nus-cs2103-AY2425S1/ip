import java.util.Scanner;

public class CancelGPT {
    private final String CHATBOT_NAME;

    public CancelGPT() {
        this.CHATBOT_NAME = "CancelGPT";
    }
    public static void main(String[] args) {
        CancelGPT cancelGPT = new CancelGPT();
        cancelGPT.run();
    }

    public void run() {
        String horizontalLine = "____________________________________________________________";
        Scanner sc = new Scanner(System.in);

        System.out.println(horizontalLine);
        greet();
        System.out.println(horizontalLine);

        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(horizontalLine);
            System.out.println(command);
            System.out.println(horizontalLine);
            command = sc.nextLine();
        }

        sc.close();
        System.out.println(horizontalLine);
        exit();
        System.out.println(horizontalLine);
    }

    public void greet() {
        System.out.println("Hello! I am " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Good bye. Hope to see you again soon!");
    }
}
