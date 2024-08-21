import java.util.Scanner;
import java.util.ArrayList;

public class Tissue {
    private static ArrayList<String> textArray = new ArrayList<>();
    private static final String LINE =
            "--------------------------------------------------------------";
    private static final String INDENT = "       ";

    public static void main(String[] args) {
        chatFunction();
    }

    private static void chatFunction() {



        System.out.println(LINE);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Tissue");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        String in = scanner.nextLine();

        while (!in.equals("bye")) {
            System.out.println(LINE);
            if (in.equals("list")) {
                System.out.println(listText());
            } else {
                System.out.println(INDENT + "added: " + in);
                storeText(in);
            }

            System.out.println(LINE);
            in = scanner.nextLine();
        }

        scanner.close();

        System.out.println(LINE);
        System.out.print(INDENT);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void storeText(String value) {
        textArray.add(value);
    }

    private static String listText() {
        String parsedText = "";
        for (int i = 0; i < textArray.size(); i++) {
            parsedText += INDENT + String.valueOf(i + 1) + "." + " " + textArray.get(i) + "\n";
        }
        return parsedText;
    }
}
