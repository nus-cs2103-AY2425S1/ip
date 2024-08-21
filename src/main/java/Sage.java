import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Sage {
    public static final String NAME = "Sage";
    public static final String HORIZONTAL_LINE = "_________________________________________________";

    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        segmentedText(String.format("Hello! I'm %s \nWhat can i do for you?", NAME));

        while (true) {
            String echo = userInput.nextLine();

            if (echo.equalsIgnoreCase("bye")) {
                segmentedText("Bye. Hope to see you again soon!");
                break;
            } else if (echo.equals("list")) {
                StringBuilder result = new StringBuilder();

                for (int i = 0; i < list.size(); i++) {
                    result.append(String.format("%d. %s\n", i + 1, list.get(i)));
                }
                segmentedText(String.valueOf(result));
            } else {
                list.add(echo);
                segmentedText("added: " + echo);
            }
        }
    }

    public static void segmentedText(String text) {
        String indentedText = text.indent(4);
        System.out.println(HORIZONTAL_LINE + "\n" + indentedText + HORIZONTAL_LINE);
    }
}
