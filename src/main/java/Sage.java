import java.util.Scanner;

public class Sage {
    public static final String NAME = "Sage";
    public static final String HORIZONTAL_LINE = "_________________________________________________";

    public static void segmentedText(String text) {
        String indentedText = text.indent(4);
        System.out.println(HORIZONTAL_LINE + "\n" + indentedText + HORIZONTAL_LINE);
    }

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        segmentedText("Hello! I'm " + NAME + "\nWhat can i do for you?");

        while (true) {
            String echo = userInput.nextLine();

            if (echo.equalsIgnoreCase("bye")) {
                segmentedText("Bye. Hope to see you again soon!");
                break;
            } else {
                segmentedText(echo);
            }
        }
    }
}
