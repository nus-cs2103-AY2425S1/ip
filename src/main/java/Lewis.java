import java.util.Scanner;
public class Lewis {
    private static boolean exit = false;
    private static void printLine() {
        System.out.println("-----------------------------------------------\n");
    }
    private static void echo(String input) {
        if (input.equals("Bye"))  {
            System.out.println("\n");
            printLine();
            System.out.println("Bye. Hope to see you again soon!\n");
            exit = true;
        } else if (input.equals("Beep")) {
            System.out.println("\n");
            printLine();
            System.out.println("Beep Boop!\n");
        }
        else {
            printLine();
            StringBuilder str = new StringBuilder();
            str.append("I'm sorry. That is not a command I know yet, so I'll just repeat it\n\n");
            str.append(input);
            str.append("\n");
            System.out.println(str);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello, I'm Lewis. \nWhat can I do for you?\n");
        printLine();
        while(!exit) {
            System.out.println("Hello, I'm Lewis. \nWhat can I do for you?\n");
            String command = scanner.nextLine();
            echo(command);
            printLine();
        }
        System.exit(0);
    }
}
