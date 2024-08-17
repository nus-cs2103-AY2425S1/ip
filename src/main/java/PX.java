import java.util.Scanner;
import java.util.ArrayList;

public class PX {
    private static String name = "PX";

    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    private static void PXSays(String arg) {
        System.out.println("    " + arg);
    }

    private static void printList(ArrayList<String> list) {
        printLine();
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            PXSays(index + ". " + list.get(i));
        }
        printLine();
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        printLine();
        PXSays("Hello! I'm " + name);
        PXSays("What can I do for you?");
        printLine();
        Scanner sc = new Scanner(System.in);

        while (true) {
            PXSays("");
            String input = sc.nextLine();
            if (input.equals("bye")) {
                printLine();
                PXSays("Bye. Hope to see you again soon!");
                printLine();
                break;
            }
            switch (input) {
                case "list":
                    printList(list);
                    break;
                default:
                    printLine();
                    list.add(input);
                    PXSays("added: " + input);
                    printLine();
            }
        }

        sc.close();
    }
}
