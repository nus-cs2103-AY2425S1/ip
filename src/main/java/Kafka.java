import java.util.Scanner;

public class Kafka {

    public static String[] list = new String[100];
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

    public void notes(String task, int taskNumber) {
        list[taskNumber] = task;
        System.out.println("  added: " + task);
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
        int taskNumber = 0;

        System.out.println("  Hello from\n" + logo);
        kafka.greet();
        System.out.println("  What do you need me for?");
        while (!exitChat) {
            String task = scanner.nextLine();
            if (task.trim().equalsIgnoreCase("Bye")) {
                exitChat = true;
            } else if (task.trim().equalsIgnoreCase("list")) {
                for (int i = 1; i <= taskNumber; i++) {
                    String listMessage = "  " + i + ". " + list[i];
                    System.out.println(listMessage);
                }
            } else {
                taskNumber++;
                kafka.notes(task, taskNumber);
            }
        }
        kafka.goodbye();
    }
}
