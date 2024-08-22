import java.util.Scanner;

public class Sora {
    public static void main(String[] args) {
        final String horizontalLine = "------------------------------";
        String greeting = "Hello! I'm Sora!\nWhat can I do for you?\n" + horizontalLine;
        System.out.println(horizontalLine + "\n" + greeting);

        Scanner commandScanner = new Scanner(System.in);
        while (true) {
            String command = commandScanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(command + "\n" + horizontalLine);
        }

        String exit = "Bye. Hope to see you again soon!";
        System.out.println(exit + "\n" + horizontalLine);
    }
}
