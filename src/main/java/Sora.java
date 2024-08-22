import java.util.Scanner;

public class Sora {
    public static void main(String[] args) {
        final String horizontalLine = "\t------------------------------";
        String greeting = "\tHello! I'm Sora!\n\tWhat can I do for you?\n" + horizontalLine;
        System.out.println(horizontalLine + "\n" + greeting);

        Scanner commandScanner = new Scanner(System.in);
        while (true) {
            String command = commandScanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println("\t" + command + "\n" + horizontalLine);
        }

        String exit = "\tBye. Hope to see you again soon!";
        System.out.println(exit + "\n" + horizontalLine);
    }
}
