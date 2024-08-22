import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BottleOpener {
    public static void main(String[] args) {
        String spacer = "-----------------------------------\n";
        String botName = "BottleOpener";
        String greeting = "Hello! I'm " + botName + ".\n" + "What can I do for you?\n";
        String goodbye = "Bye! See you next time!\n";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(spacer + greeting + spacer);

        int index = 0;
        ArrayList<Task> tasks = new ArrayList<Task>();
        boolean flag = true;

        while (flag) {
            String userInput;

            try {
                userInput = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (userInput.startsWith("bye")) {

                System.out.println(spacer + goodbye + spacer);
                flag = false;
                break;

            } else if (userInput.startsWith("list")) {

                String output = "";
                for (int i = 0; i < tasks.size(); i++) {

                    output = output + String.format("%d. %s%n", i + 1, tasks.get(i));
                }
                System.out.println(spacer + output + spacer);

            } else if (userInput.contains("mark")) {

                String instruction = "";
                int number = -1;
                Task chosenTask;

                try {
                    String[] inp = userInput.split(" ", 2);
                    instruction = inp[0];
                    number = Integer.parseInt(inp[1]);
                    chosenTask = tasks.get(number - 1);
                } catch (Exception e) {
                    System.out.println("Invalid!\n");
                    break;
                }

                if (instruction.equals("mark")) {
                    Task modifiedTask = chosenTask.markAsDone();
                    tasks.set(number - 1, modifiedTask);
                    System.out.println(spacer + modifiedTask + "\n" + spacer);
                } else if (instruction.equals("unmark")) {
                    Task modifiedTask = chosenTask.markAsUndone();
                    tasks.set(number - 1, modifiedTask);
                    System.out.println(spacer + modifiedTask + "\n" + spacer);
                }

            } else {

                System.out.println(spacer + "added: " + userInput + "\n" + spacer);
                Task newTask = new Task(userInput);
                tasks.add(index, newTask);
                index += 1;

            }

        }
    }
}
