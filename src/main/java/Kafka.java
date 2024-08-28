import java.util.ArrayList;
import java.util.Scanner;

public class Kafka {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public void greet() {
        String message = """
              Hello. Kafka here.
              I have been waiting for your arrival.
            """;
        System.out.println(message);
    }

    public void goodbye() {
        String message = "  Farewell. I look forward to our next meeting, wherever destiny may lead us. ";
        System.out.println(message);
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("  added: " + task.description);
    }

    public void createList() {
        for (int i = 0; i < tasks.size(); i++) {
            String listMessage = "  " + (i + 1) + ". " + tasks.get(i).description;
            System.out.println(listMessage);
        }
    }
    public static void main(String[] args) {
        String logo = """
                   __  __            __     _
                  |  |/  /  ____   _/  /_  | |      ____
                  |     /  / _  | |_    _| | |/ /  / _  |
                  |     \\ | |_| |   |  |   |   <  | |_| |
                  |__|\\__\\ \\____|   |__|   |_|\\ \\  \\____|
                """;
        Scanner scanner = new Scanner(System.in);
        Kafka kafka = new Kafka();
        boolean exitChat = false;

        System.out.println("  Hello from\n" + logo);
        kafka.greet();
        System.out.println("  What do you need me for?");
        while (!exitChat) {
            String description = scanner.nextLine();
            Task task =  new Task(description);
            if (task.description.trim().equalsIgnoreCase("Bye")) {
                exitChat = true;
            } else if (task.description.trim().equalsIgnoreCase("list")) {
                kafka.createList();
            } else {
                kafka.addTask(task);
            }
        }
        kafka.goodbye();
    }
}
