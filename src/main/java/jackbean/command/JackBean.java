package jackbean.command;

import jackbean.task.TaskList;

import java.util.Scanner;

public class JackBean {
    public static void main(String[] args) {
        Ui.greet();
        TaskList taskList = new TaskList();
        Storage.fetchStorage(taskList);


        Scanner userInput = new Scanner(System.in);
        // GitHub CoPilot helped a lot with auto-complete (except personalisation)
        // it is actually really smart haha
        while (true) {
            String input = userInput.nextLine();

            // handle bye
            if (input.equalsIgnoreCase("bye")) { // I added equalsIgnoreCase() by myself
                Ui.sayGoodBye();
                userInput.close();
                break;
            }

            // handle other input
            try {
                String reply = Parser.parseUserInput(input, taskList);
                Ui.showMessage(reply);
            } catch (Exception e) {
                Ui.showMessage("Homie! There was an error:\n" + e.toString());
            }
        }

    }
}
