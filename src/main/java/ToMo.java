import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ToMo {
    public static class Task {
        private String description;
        private boolean done;

        Task(String description) {
            this.description = description;
            this.done = false;
        }
        
        public void mark() {
            this.done = true;
        }

        public void unmark() {
            this.done = false;
        }

        @Override
        public String toString() {
            String pref = done ? "[X] " : "[ ] ";
            return pref + description;
        }
    }
    
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
                    Task newTask = new Task(cmd);
                    tasks.add(newTask);
                    System.out.println("Your task is added");
                    System.out.println(newTask);
                }
            }
        }
        sc.close();
        System.out.println("Bye, see you later!");
    }
}