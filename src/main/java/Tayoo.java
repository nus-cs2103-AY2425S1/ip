import java.util.Scanner;

public class Tayoo {
    public static void main(String[] args) {
        String name = "Tayoo";
        Scanner scanner = new Scanner(System.in);

        //Introduce self
        printHoriLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?\n");
        printHoriLine();

        //Await command
        awaitCommand(scanner);



        //Exit programme
        scanner.close();
        System.exit(0);
    }

    private static void printHoriLine(int length) {
        StringBuilder line = new StringBuilder("\t");

        for (int i = 0; i<length;i++) {
            line.append("_");
        }

        System.out.println(line);
    }

    private static void printHoriLine() {
        System.out.println("\t_______________________________________");
    }

    private static void exitBot(Scanner scanner) {
        printHoriLine();
        System.out.println("\tBye. Hope to see you again soon!\n");
        printHoriLine();
        scanner.close();
        System.exit(0);
    }

    private static void awaitCommand(Scanner scanner) {
        while(true) {
            String command = scanner.nextLine();
            String input = command.toUpperCase();
            switch(input) {
                case "BYE":
                case "EXIT":
                case "CLOSE":
                case "GOODBYE":
                    exitBot(scanner);
                    break;
                default:
                    printHoriLine();
                    System.out.println("\t" + command + "\n");
                    printHoriLine();
                    System.out.println("\n");
            }
        }
    }

}
