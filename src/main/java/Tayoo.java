import java.util.ArrayList;
import java.util.Scanner;

public class Tayoo {
    private static ArrayList<String> tasklist = new ArrayList<String>(100);
    public static void main(String[] args) {
        String name = "Tayoo";
        Scanner scanner = new Scanner(System.in);

        //Introduce self
        printText("Hello! I'm " + name + "\nWhat can I do for you?\n");

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

    private static void printText(String text) {
        printHoriLine();
        System.out.println(text);
        printHoriLine();
        System.out.println("\n");
    }

    private static void exitBot(Scanner scanner) {
        printText("\tBye. Hope to see you again soon!\n");
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
                case "LIST":
                    printTaskList();
                    break;
                default:
                    addTask(command);
            }
        }
    }

    private static void addTask(String task) {
        if (tasklist.size() >= 100) {
            printText("Too many tasks! Complete some first! >:( ");
            return;
        }
        tasklist.add(task);
        printText("added: " + task);
    }

    private static void printTaskList() {
        StringBuilder toPrint = new StringBuilder();
        int length = tasklist.size();

        for (int i = 0; i < length; i++) {
            toPrint.append(i+1 + ". " + tasklist.get(i) + "\n");
        }

        printText(toPrint.toString());
        return;
    }

}
