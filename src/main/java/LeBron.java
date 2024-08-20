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

            String[] parts = input.split(" ", 2);

            if (input.equals("bye")) {
                System.out.println(String.format("%s: Bye! I'm leaving now.", name));
                System.out.println("------------------------");
                break;
            } else if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    Task task = (Task) list.get(i - 1);
                    System.out.println(String.format("%d. %s", i, task.toString()));
                }
                System.out.println("------------------------");
            } else if (parts[0].equals("mark")) {
                if (parts.length > 1 && isNumeric(parts[1])) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (list.size() >= taskNumber) {
                        Task task = (Task) list.get(taskNumber - 1);
                        task.markAsDone();
                        System.out.println("Alright bro, great work");
                        System.out.println(task.toString());
                        System.out.println("------------------------");
                    }
                }
            } else if (parts[0].equals("unmark")) {
                if (parts.length > 1 && isNumeric(parts[1])) {
                    int taskNumber = Integer.parseInt(parts[1]);
                    if (list.size() >= taskNumber) {
                        Task task = (Task) list.get(taskNumber - 1);
                        task.markAsUndone();
                        System.out.println("Let's get it done soon!");
                        System.out.println(task.toString());
                        System.out.println("------------------------");
                    }
                }
            } else if (parts[0].equals("todo")) {
                if (parts.length > 1) {
                    ToDos todo = new ToDos(parts[1]);
                    list.add(todo);
                    System.out.println("Gotchu, I added the task: ");
                    System.out.println(todo.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                    System.out.println("------------------------");
                }
            } else if (parts[0].equals("deadline")) {
                if (parts.length > 1) {
                    String[] splStr = parts[1].split("/by ", 2);
                    Deadlines deadline = new Deadlines(splStr[0], splStr[1]);
                    list.add(deadline);
                    System.out.println("Gotchu, I added the task: ");
                    System.out.println(deadline.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                    System.out.println("------------------------");
                }
            } else if (parts[0].equals("event")) {
                if (parts.length > 1) {
                    if (parts[1].contains("/from")) {
                        String[] splStrings = parts[1].split("/from", 2);
                        System.out.println(splStrings[0]);
                        String taskDescription = splStrings[0].trim();
                        String start = "";
                        String end = "";
                        if (splStrings[1].contains("/to")) {
                            String[] timeParts = splStrings[1].split("/to", 2);
                            start = timeParts[0];
                            end = timeParts[1];
                        }
                        Event event = new Event(taskDescription, start, end);
                        list.add(event);
                        System.out.println("Gotchu, I added the task: ");
                        System.out.println(event.toString());
                        System.out.println(String.format("Now you have %d tasks in the list", list.size()));
                        System.out.println("------------------------");
                    }
                }
            } else {
                Task task = new Task(input);
                list.add(task);
                System.out.println(String.format("added: %s", input));
                System.out.println("------------------------");
            }
        }

    }

    private static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
