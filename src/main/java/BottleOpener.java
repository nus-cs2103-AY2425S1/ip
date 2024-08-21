import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BottleOpener {
    public static void main(String[] args) {
        String spacer = "-----------------------------------\n";
        String botName = "BottleOpener";
        String greeting = "Hello! I'm " + botName + ".\n" + "What can I do for you?\n";
        String goodbye = "Bye! See you next time!\n";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(spacer + greeting + spacer);

        String userInput = "";
        int index = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            try {
                userInput = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else if (userInput.equalsIgnoreCase("list")) {

                String output = "";
                for (int i = 0; i < tasks.size(); i++) {
                    output = output + String.format("%d. %s%n", i+1, tasks.get(i));
                }
                System.out.println(spacer + output + spacer);

            } else {
                System.out.println(spacer + "added: " +
                        userInput + "\n" + spacer);

                Task userTask = new Task(userInput);
                tasks.add(index, userTask);
                index += 1;
            }
        }

        System.out.println(spacer + goodbye + spacer);
    }
}
