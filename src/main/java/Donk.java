import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Donk {
    public static void main(String[] args) {
        String greeting = " ____________________________________________________________\n" +
                " Hello! I'm Donk, the super intelligent chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMsg = "    Bye bro, catch 'ya later\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);

        List<String> tasks = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(byeMsg);
                break;
            } else if (userInput.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println("    " + i + ": " + tasks.get(i-1));
                }
            } else {
                tasks.add(userInput);
                System.out.println("    added: " + userInput);
            }
        }

    }
}
