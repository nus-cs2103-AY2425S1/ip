import java.util.Scanner;
import java.util.ArrayList;
public class ChattyBuddy {
    public static void main(String[] args) {
        ArrayList<String> inputList = new ArrayList<>();
        String break_line = "——————————————————————————";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(break_line);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm ChattyBuddy");
        System.out.println("What can I do for you?");
        System.out.println(break_line);
        Scanner userInput = new Scanner(System.in);
        String response = userInput.nextLine();
        while (!response.equals("bye")) {
            if (response.equals("list")) {
                System.out.println(break_line);
                for (int i = 0; i < inputList.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, inputList.get(i));
                }
                System.out.println(break_line);
                response = userInput.nextLine();
            } else {
                inputList.add(inputList.size(), response);
                System.out.println(break_line);
                System.out.printf("added: %s%n", response);
                System.out.println(break_line);
                response = userInput.nextLine();
            }
        }
        System.out.println(break_line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(break_line);
    }
}
