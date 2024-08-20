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
        ArrayList<String> tasks = new ArrayList<String>();

        while (true) {
            try {
                userInput = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            } else if (userInput.equalsIgnoreCase("list")) {

                String taskOutput = "";
                for (int i = 0; i < tasks.size(); i++) {
                    String eachTask = String.format("%d. %s",i+1, tasks.get(i));
                    taskOutput = taskOutput + eachTask + "\n";
                }
                System.out.println(spacer + taskOutput + spacer);

            } else {
                System.out.println(spacer + "added: " +
                        userInput + "\n" + spacer);

                tasks.add(index, userInput);
                index += 1;
            }
        }

        System.out.println(spacer + goodbye + spacer);
    }
}
