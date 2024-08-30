import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alvis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tracker = new ArrayList<>();
        System.out.println("So you require my assistance?");

        while (true) {
            String[] userInput = sc.nextLine().toLowerCase().split(" ");
            switch (userInput[0]) {
            case "bye":
                System.out.println("Understood.");
                break;
            case "list":
                System.out.println("Let me help you to remember what you need to do ...");
                for (int i = 0; i < tracker.size(); ++i) {
                        Task task = tracker.get(i);
                        String statusIcon = task.getStatus();
                        String desc = task.getDesc();
                        System.out.println("" + (i+1) + ". " + statusIcon + " " + desc);
                    }
                break;
            case "toggle":
                try {
                    int index = Integer.parseInt(userInput[1]) - 1;
                    tracker.get(index).toggleStatus();
                    System.out.println("As requested, I have changed the status of your task");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("I cannot do that, it is not a valid index");
                }
                break;
            default:
                Task task = new Task(userInput[0]);
                tracker.add(task);
                System.out.println("Added: " + task.getDesc());
            }
        }
    }
}
