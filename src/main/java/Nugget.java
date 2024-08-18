import java.util.ArrayList;
import java.util.Scanner;
public class Nugget {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("________________________________________");
        System.out.println("Hello! I'm Nugget");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");
        while (true) {
            String text = scanner.nextLine().trim();
            String[] splitText = text.split(" ");
            if (text.equals("bye")) {
                System.out.println("________________________________________");
                break;
            } else if (text.equals("list")) {
                int len = tasks.size();
                for (int i = 0; i < len; i++) {
                    String formattedMessage = String.format("%d.%s", i + 1, tasks.get(i));
                    System.out.println(formattedMessage);
                }
                System.out.println("________________________________________");
            } else if (splitText[0].equals("mark")) {
                tasks.get(Integer.parseInt(splitText[1]) - 1).markTask();
                System.out.println("________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(Integer.parseInt(splitText[1]) - 1));
                System.out.println("________________________________________");
            } else if (splitText[0].equals("unmark")) {
                tasks.get(Integer.parseInt(splitText[1]) - 1).unmarkTask();
                System.out.println("________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(Integer.parseInt(splitText[1]) - 1));
                System.out.println("________________________________________");
            } else {
                System.out.println("________________________________________");
                System.out.println("added: " + text);
                System.out.println("________________________________________");
                Task newTask = new Task(text);
                tasks.add(newTask);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________");
    }
}
