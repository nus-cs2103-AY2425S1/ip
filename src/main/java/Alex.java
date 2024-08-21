import java.util.Scanner;

public class Alex {

    public static final String byeMessage = "Bye. Hope to see you again soon!";
    public static final String LINE = "----------------------------------------------------";

    public void scan() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("bye")) {
            System.out.println(LINE);
            System.out.println(byeMessage);
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println(userInput);
            System.out.println(LINE);
            scan();
        }
    }
    public static void main(String[] args) {

        Alex alex = new Alex();

        System.out.println(LINE);
        System.out.println("Hello! I'm Alex 👋🏼🤖 \n" +
                "What can I do for you? ");
        System.out.println(LINE);

        alex.scan();
    }
}
