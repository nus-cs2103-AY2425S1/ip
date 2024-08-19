import java.util.Scanner;

public class Kita {
    private static void printLine() {
        System.out.println("____________________________________________________________\n");
    }

    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in);

        printLine();
        System.out.println(" Hello! I'm Kita");
        System.out.println(" What can I do for you?");
        printLine();
        while (true) {
            String command = getInput.nextLine();
            if (command.equals("bye")) {
                printLine();
                System.out.println(" Bye. Hope to see you again soon!\n");
                printLine();
                break;
            }
            printLine();
            System.out.println(command);
            printLine();
        }
    }
}
