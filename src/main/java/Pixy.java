import java.util.Scanner;

public class Pixy {

    public static String inputCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }
    public static void main(String[] args) {
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Pixy \n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");
        String command = inputCommand();
        while (!command.equalsIgnoreCase("Bye")) {
            System.out.println("____________________________________________________________\n" + command + "\n" +
                    "____________________________________________________________\n");
            command = inputCommand();
        }
        System.out.println("____________________________________________________________\n" + "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n");
    }
}
