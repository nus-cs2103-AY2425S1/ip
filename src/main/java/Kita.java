import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;

public class Kita {
    private static void printLine() {
        System.out.println("____________________________________________________________\n");
    }
    private static ArrayList<String> commandsList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner getInput = new Scanner(System.in);

        printLine();
        System.out.println(" Hello! I'm Kita");
        System.out.println(" What can I do for you?");
        printLine();
        while (true) {
            String command = getInput.nextLine();
            printLine();
            if (command.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!\n");
                break;
            }
            else if (command.equals("list")) {
                for (int i = 1; i <= commandsList.size(); i++) {
                    System.out.println(i + ". " + commandsList.get(i-1));
                }
            }
            else {
                System.out.println("added: " + command);
                commandsList.add(command);
            }

            printLine();
        }
    }
}
