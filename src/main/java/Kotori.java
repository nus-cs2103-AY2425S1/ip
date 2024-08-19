import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kotori {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        printGreeting();
        Scanner s = new Scanner(System.in);
        while (s.hasNext()){
            String input = s.nextLine();
            if (input.equals("bye")) {
                printExit();
                break;
            } else {
                list.add(input);
                printList(list);
            }
        }

        s.close();

    }

    public static void printLine() {
        System.out.println("    ___________________________________________");
    }

    public static void printMessage(String input) {
        printLine();
        System.out.println("    " + input);
        printLine();
    }

    public static void printList(List<String> list) {
        printLine();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(String.format("    %s. %s",i + 1, list.get(i)));
        }
        printLine();
    }

    public static void printGreeting() {
        printMessage("Hello! I'm Kotori.\n    What can I do for you?");
    }

    public static void printExit() {
        printMessage("Bye! Hope to see you again soon.");
    }
}
