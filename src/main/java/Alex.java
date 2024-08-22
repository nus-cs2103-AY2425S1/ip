import java.util.ArrayList;
import java.util.Scanner;

public class Alex {

    public static final String byeMessage = "Bye. Hope to see you again soon!";
    public static final String LINE = "----------------------------------------------------";
    ArrayList<String> list = new ArrayList<>();

    public void scan() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        if (userInput.equalsIgnoreCase("bye")) {
            System.out.println(LINE);
            System.out.println(byeMessage);
            System.out.println(LINE);
        }
        else if (userInput.equalsIgnoreCase("list")) {
            System.out.println(LINE);
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
            System.out.println(LINE);
            scan();
        } else {
            System.out.println(LINE);
            System.out.println("added: " + userInput);
            list.add(userInput);
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
