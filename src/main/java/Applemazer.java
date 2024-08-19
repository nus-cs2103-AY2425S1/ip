import java.util.Scanner;

public class Applemazer {

    private void greeting() {
        String greeting = "Hello! I'm Applemazer.\nWhat can I do for you?\n";
        System.out.println(greeting);
    }

    private void farewell() {
        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    public static void main(String[] args) {
        Applemazer chatBot = new Applemazer();
        chatBot.greeting();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(command + "\n");
            command = sc.nextLine();
        }

        chatBot.farewell();
    }
}
