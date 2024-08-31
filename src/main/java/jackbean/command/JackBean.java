package jackbean.command;

import java.util.Scanner;

import jackbean.task.TaskList;

/**
 * JackBean is a chatbot that helps you manage your tasks.
 * It can add, delete, mark as done, and list tasks.
 * It can also save your tasks to a file and load them back.
 * This JavaDoc was written by GitHub Copilot.
 */
public class JackBean {
    /**
     * Main entry-point for the JackBean application.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param args The command line arguments.
     */
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
