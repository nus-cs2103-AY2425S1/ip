import java.util.ArrayList;
import java.util.Scanner;

public class LeBron {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList list = new ArrayList();

        String name = "LeBron";
        System.out.println("------------------------");
        System.out.println(String.format("Hello! I'm %s", name));
        System.out.println("What can I do for you?");
        System.out.println("------------------------");

        while (true) {
            input = scanner.nextLine();
            input = input.trim();

            String[] parts = input.split(" ");

            if (input.equals("bye")) {
                System.out.println(String.format("%s: Bye! I'm leaving now.", name));
                System.out.println("------------------------");
                break;
            } else if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    Task task = (Task) list.get(i - 1);
                    System.out.println(String.format("%d.[%s] %s", i, task.getStatusIcons(), task.description));
                }
                System.out.println("------------------------");
            } else if (parts[0].equals("mark")) {
                if (parts.length > 1 && isNumeric(parts[1])) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (list.size() >= taskNumber) {
                        Task task = (Task) list.get(taskNumber - 1);
                        task.markAsDone();
                        System.out.println("Alright bro, great work");
                        System.out.println(String.format("[%s] %s", task.getStatusIcons(), task.description));
                        System.out.println("------------------------");
                    }
                }
            } else if (parts[0].equals("unmark")) {
                if (parts.length > 1 && isNumeric(parts[1])) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (list.size() >= taskNumber) {
                        Task task = (Task) list.get(task