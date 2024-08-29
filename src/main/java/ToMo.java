import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ToMo {
    public static void main(String[] args) {
        System.out.println("What's up, it's ToMo here!");
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();

        while (true) {
            System.out.println("How can I help you?");
            String cmd = sc.nextLine();

            try {
                String[] words = cmd.split(" ");
                if (words.length == 0) continue;
                if (words[0].equals("bye")) {
                    if (words.length != 1) {
                        throw new Exception("Wrong number of arguments in bye command: expected 1, got " + words.length);
                    }
                    break;
                } else if (words[0].equals("list")) {
                    if (words.length != 1) {
                        throw new Exception("Wrong number of arguments in list command: expected 1, got " + words.length);
                    }

                    System.out.println("The tasks list as follow:");
                    for (int i = 0; i < tasks.size(); ++i) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (words[0].equals("mark")) {
                    if (words.length != 2) {
                        throw new Exception("Wrong number of arguments in mark command: expected 2, got " + words.length);
                    }
                    int i = Integer.parseInt(words[1]) - 1;
                    if (i >= 0 && i < tasks.size()) {
                        Task newTask = tasks.get(i);
                        newTask.mark();
                        tasks.set(i, newTask);
                        System.out.println("A task is marked");
                        System.out.println(tasks.get(i));
                    } else {
                        throw new Exception("Index out of range for command mark");
                    }
                } else if (words[0].equals("unmark")) {
                    if (words.length != 2) {
                        throw new Exception("Wrong number of arguments in unmark command: expected 2, got " + words.length);
                    }
                    int i = Integer.parseInt(words[1]) - 1;
                    if (i >= 0 && i < tasks.size()) {
                        Task newTask = tasks.get(i);
                        newTask.unmark();
                        tasks.set(i, newTask);
                        System.out.println("A task is unmarked");
                        System.out.println(newTask);
                    } else {
                        throw new Exception("Index out of range for command unmark");
                    }
                } else if (words[0].equals("todo")) {
                    if (cmd.length() <= 5) {
                        throw new Exception("Empty description for new ToDo task");
                    }
                    String description = cmd.substring(5);
                    Task task = new ToDo(description);
                    tasks.add(task);
                    System.out.println("A new task is added");
                    System.out.println(task);
                } else if (words[0].equals("deadline")) {
                    words = cmd.split(" /by ");
                    if (words.length != 2) {
                        throw new Exception("Wrong number of arguments for new Deadline task: expected 2, got " + words.length);
                    }
                    if (words[0].length() <= 9) {
                        throw new Exception("Empty description for Deadline task");
                    }
                    String description = words[0].substring(9);
                    String deadline = words[1];
                    Task task = new Deadline(description, deadline);
                    tasks.add(task);
                    System.out.println("A new task is added");
                    System.out.println(task);
                } else if (words[0].equals("event")) {
                    words = cmd.split(" /from | /to ");
                    if (words.length != 3) {
                        throw new Exception("Wrong number of arguments for new Event task: expected 3, got " + words.length);
                    }
                    if (words[0].length() <= 6) {
                        throw new Exception("Empty description for Event task");
                    }
                    String description = words[0].substring(6);
                    String start = words[1];
                    String end = words[2];
                    Task task = new Event(description, start, end);
                    tasks.add(task);
                    System.out.println("A new task is added");
                    System.out.println(task);
                } else if (words[0].equals("delete")) {
                    if (words.length != 2) {
                        throw new Exception("Wrong number of arguments in delete command: expected 2, got " + words.length);
                    }
                    int i = Integer.parseInt(words[1]) - 1;
                    if (i >= 0 && i < tasks.size()) {
                        Task task = tasks.get(i);
                        tasks.remove(i);
                        System.out.println("A task is deleted");
                        System.out.println(task);
                    } else {
                        throw new Exception("Index out of range for command delete");
                    }  
                } else {
                    throw new Exception("Unknown command");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        System.out.println("Bye, see you later!");
    }
}