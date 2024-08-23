import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Meerkat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        String lines = "____________________________________________________________";
        String greeting = """
                Hello! I'm a meerkat from singapore
                What can I do for you?
                """;
        String bye = """
                Goodnight, sleep tight, Hope I don't ever see you again!
                """;
        System.out.println(lines + "\n" + greeting + lines);
        List<String> listOfItems = new ArrayList<>();
        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(lines + "\n" + bye + lines);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                System.out.println(lines);
                for (int i = 1; i < listOfItems.size() + 1; i++) {
                    System.out.println(i + ". " + listOfItems.get(i-1));
                }
                System.out.println(lines);
            } else {
                listOfItems.add(input);
                System.out.println(lines + "\nadded: " + input + "\n" + lines);
            }

        }
        System.exit(0);
    }
}
