import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ToMo {
    public static void main(String[] args) {
        System.out.println("What's up, it's ToMo here!");
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.println("How can I help you?");
            String cmd = sc.nextLine();
            if (cmd.equals("bye")) {
                break;
            } else if (cmd.equals("list")) {
                System.out.println("Your tasks list as follow:");
                for (int i = 0; i < tasks.size(); ++i) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                String[] words = cmd.split(" ");
                if (words[0].equals("mark")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    Task newTask = tasks.get(i);
                    newTask.mark();
                    tasks.set(i, newTask);
                    System.out.println("Your task is marked");
                    System.out.println(newTask);
                } else if (words[0].equals("unmark")) {
                    int i = Integer.parseInt(words[1]) - 1;
                    Task newTask = tasks.get(i);
                    newTask.unmark();
                    tasks.set(i, newTask);
                    System.out.println("Your task is unmarked");
                    System.out.println(newTask);
                } else {
                    Task newTask;
                    if (words[0].equals("todo")) {
                        String description = cmd.substring(5); // remove "todo "
                        newTask = new ToDo(description);
                    } else if (words[0].equals("deadline")) {
                        words = cmd.split("/");
                        String description = words[0].substring(9); // remove "deadline "
                        String deadline = words[1].substring(3); // remove "by "
                        newTask = new Deadline(description, deadline);
                    } else {
                        words = cmd.split("/");
                        String description = words[0].substring(6); // remove "event "
                        String start = words[1].substring(5); // remove "from "
                        String end = words[2].substring(3); // remove "to "
                        newTask = new Event(description, start, end);
                    }
                    tasks.add(newTask);
                    System.out.println("A new task is added");
                    System.out.println(newTask);
                }
            }
        }
        sc.close();
        System.out.println("Bye, see you later!");
    }
}