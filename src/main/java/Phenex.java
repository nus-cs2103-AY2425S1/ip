import java.util.Scanner;

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

        // begin echoing
        Scanner scanner = new Scanner(System.in);
        String terminatingStr = "bye";
        String userInput = scanner.next();

        while (!userInput.equals(terminatingStr)) {
            String echoMsg = line + "\t" + userInput + " \n" + line;
            System.out.println(echoMsg);
            userInput = scanner.next();
        }

        scanner.close();
        System.out.println(farewellMsg);
    }
}
