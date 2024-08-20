import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BottleOpener {
    public static void main(String[] args) {
        String spacer = "-----------------------------------\n";
        String botName = "BottleOpener";
        String greeting = "Hello! I'm " + botName + ".\n" + "What can I do for you?\n";
        String goodbye = "Bye! See you next time!\n";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(spacer + greeting + spacer);

        String userInput = "";
        while (true) {
            try {
                userInput = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (!userInput.equalsIgnoreCase("bye")) {
                System.out.println(spacer + userInput + "\n" + spacer);
            } else {
                break;
            }
        }

        System.out.println(spacer + goodbye + spacer);
    }
}
