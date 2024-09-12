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
    private TaskList taskList;

    /**
     * Constructs a JackBean chatbot.
     * This JavaDoc was written by GitHub Copilot.
     */
    public JackBean() {
        taskList = new TaskList();
        Storage.fetchStorage(taskList);
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        // guard conditions
        switch (input.toLowerCase()) {
        case "bye":
            return "Bye homie! Come back if you need anything else. " + "JackBean, signing off!";
        case "hi":
        case "hey":
        case "yo":
        case "hello":
            return "Hello homie! I'm JackBean, a chatbot designed to help you with your daily tasks!"
                    + "\nHow may I help you today my homie?";
        case "leo":
            return "Leo is the BEST BOSS EVER!";
        case "lose to you":
            return "NAH HOMIE, LOSE TO YOU";
        default:
            break;
        }

        // handle other input
        try {
            String reply = Parser.parseUserInput(input, taskList);
            return reply;
        } catch (Exception e) {
            return "Homie! There was an error:\n" + e.toString();
        }
    }

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
