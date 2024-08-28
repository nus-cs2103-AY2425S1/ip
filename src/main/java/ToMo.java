import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ToMo {
    public static class Task {
        private String description;
    
        Task(String description) {
            this.description = description;
        }
    
        @Override
        public String toString() {
            return description;
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
                for (int i = 0; i < tasks.size(); ++i) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
            } else {
                tasks.add(new Task(cmd));
                System.out.println("added: " + cmd);
            }
        }
        sc.close();
        System.out.println("Bye, see you later!");
    }
}