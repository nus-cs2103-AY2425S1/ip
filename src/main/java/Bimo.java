import java.util.Scanner;
public class Bimo {
    public static String name = "Bimo";
    public static String line = "    " + "----------------------------------";
    public static Scanner scanner = new Scanner(System.in);
    public static int length = 0;
    public static Task[] list = new Task[100];
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("    " + String.format("Hello! I'm %s", name));
        System.out.println("    " + "What can I do for you?");
        System.out.println(line);
        String command = scanner.nextLine();
        while (!command.toLowerCase().equals("bye")) {
            System.out.println(line);
            if (command.toLowerCase().equals("list") && length > 0 ) {
                for (int i = 0; i < length; i++) {
                    String message = String.format("    %d. %s", i + 1, list[i].toString());
                    System.out.println(message);
                }
            } else if (command.toLowerCase().equals("list")) {
                System.out.println("    No tasks added");
            } else  {
                Task task = new Task(command);
                Bimo.list[length] = task;
                length += 1;
                System.out.println("    added: " + task.toString());
            }
            System.out.println(line);
            command = scanner.nextLine();
        }
        System.out.println("    " +"Bye. Hope to see you again soon!");
        System.out.println(line);
    }


}
