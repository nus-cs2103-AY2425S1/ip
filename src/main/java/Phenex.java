import java.util.Scanner;
import java.util.ArrayList;

public class Phenex {
    public static void main(String[] args) {
        String logo = "  _____    _    _   ______   _   _   ______  __   __ \n"
                + " |  __ \\  | |  | | |  ____| | \\ | | |  ____| \\ \\ / /\n"
                + " | |__) | | |__| | | |__    |  \\| | | |__     \\ V / \n"
                + " | |      | |  | | | |____  | |\\  | | |____   / . \\ \n"
                + " |_|      |_|  |_| |______| |_| \\_| |______| /_/ \\_\\\n";

        String line = "____________________________________________________________ \n";
        String greetMsg = "Hello! I'm \n"
                + logo
                + "Your favourite solid gold mobile suit! \n"
                + line
                + "What can I do for you? \n"
                + line;
        String farewellMsg = "Goodbye. Extinguish the Zeon forces on your way out!";

        System.out.println(greetMsg);

        // list feature
        Scanner scanner = new Scanner(System.in);
        String terminatingStr = "bye";
        ArrayList<String> lst = new ArrayList<>();
        String userInput = scanner.nextLine();

        while (!userInput.equals(terminatingStr)) {
            System.out.println("\t" + line);
            if (userInput.equals("list")) {
                int size = lst.size();
                for (int i = 0; i < size; i++) {
                    String row = (i + 1)
                            + ". "
                            + lst.get(i);
                    System.out.println(row);
                }
            } else {
                lst.add(userInput);
                String taskAddedMsg = "added: " + userInput;
                System.out.println(taskAddedMsg);
            }

            System.out.println("\t" + line);

            userInput = scanner.nextLine();
        }

        scanner.close();
        System.out.println(farewellMsg);
    }
}
