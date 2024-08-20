import java.util.Scanner;

public class Elara {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] list = new Task[100];
        int count = 0;

        System.out.println("Hello! I'm Elara");
        System.out.println("What can I do for you?");

        while (true) {
            String text = scanner.nextLine().strip();
            if (text.equalsIgnoreCase("bye")) {
                break;
            } else if (text.equalsIgnoreCase("list")) {
                for (int i = 0; i < count; i++) {
                    if (list[i] == null) {
                        break;
                    }
                    int j = i + 1;
                    System.out.println(j + ". " + list[i]);
                }
            } else if (text.toLowerCase().startsWith("mark")) {
                int i = Integer.parseInt(text.substring(5)) - 1;
                if (i >= 0 && i < count) {
                    list[i].mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list[i]);
                }
            } else if (text.toLowerCase().startsWith("unmark")) {
                int i = Integer.parseInt(text.substring(7)) - 1;
                if (i >= 0 && i < count) {
                    list[i].unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list[i]);
                }
            } else {
                Task newTask = new Task(text);
                System.out.println("added: " + newTask.description);
                list[count] = newTask;
                count++;
            }
        }

        System.out.println("Bye. Hope to see you again!");
    }
}
