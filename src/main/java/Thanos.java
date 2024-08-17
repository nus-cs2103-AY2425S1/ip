import java.util.ArrayList;
import java.util.Scanner;

public class Thanos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Thanos!\nWhat can I do for you?");

        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    System.out.printf("%d.%s\n", i + 1, task);
                }
            } else if (userInput.startsWith("mark")) {
                String[] inputArr = userInput.split(" ");
                if (inputArr.length > 1) {
                    int index = Integer.parseInt(inputArr[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        Task task = tasks.get(index);
                        task.markDone();
                        System.out.printf("Nice! I've marked this task as done:\n%s\n", task);
                    }
                }
            } else if (userInput.startsWith("unmark")) {
                String[] inputArr = userInput.split(" ");
                if (inputArr.length > 1) {
                    int index = Integer.parseInt(inputArr[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        Task task = tasks.get(index);
                        task.unmarkDone();
                        System.out.printf("OK, I've marked this task as not done yet:\n%s\n", task);
                    }
                }
            } else {
                tasks.add(new Task(userInput));
                System.out.println("added: " + userInput);
            }
        }
    }
}
