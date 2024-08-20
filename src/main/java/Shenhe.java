import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shenhe {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello, Ying. I'm Shenhe. The assumption that every person has somewhere to call home is \n" +
                "naive. I got used to living in the mountains alongside the birds and beasts a long time ago. \n" +
                "You, are not the only traveller, but the most interesting one.");
        System.out.println("What do you want today?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        String userInput;

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            } else if (userInput.startsWith("mark")) {
                int length = userInput.length();
                char lastChar = userInput.charAt(length - 1);
                int lastDigit = -1;
                if (Character.isDigit(lastChar)) {
                    lastDigit = lastChar - '0';
                }
                if (lastDigit >= 1 && lastDigit <= length) {
                    tasks.get(lastDigit - 1).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(lastDigit - 1).toString());
                    System.out.println("____________________________________________________________");
                }
            } else if (userInput.startsWith("unmark")) {
                int length = userInput.length();
                char lastChar = userInput.charAt(length - 1);
                int lastDigit = -1;
                if (Character.isDigit(lastChar)) {
                    lastDigit = lastChar - '0';
                }
                if (lastDigit >= 1 && lastDigit <= length) {
                    tasks.get(lastDigit - 1).markAsUndone();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(lastDigit - 1).toString());
                    System.out.println("____________________________________________________________");
                }
            } else if (!userInput.equals("list")) {
                tasks.add(new Task(userInput));
                System.out.println("____________________________________________________________");
                System.out.println("added: " + userInput);
                System.out.println("____________________________________________________________");
            } else {
                int length = tasks.size();
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < length; i++) {
                    System.out.println(i + 1 + "." + tasks.get(i).toString());
                }
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye travaller. Hope to see you again soon.");
        System.out.println("____________________________________________________________");
    }
}
