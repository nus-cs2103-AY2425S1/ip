import java.util.Scanner;

public class Sora {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Sora!\nWhat can I do for you?\n";
        System.out.println(greeting);

        Scanner commandScanner = new Scanner(System.in);
        while (true) {
            String command = commandScanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(command);
        }

        String exit = "Bye. Hope to see you again soon!\n";
        System.out.println(exit);
    }
}
