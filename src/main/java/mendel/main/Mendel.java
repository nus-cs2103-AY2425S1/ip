package mendel.main;

import java.util.Scanner;

import mendel.frontend.Parser;
import mendel.frontend.UI;
import mendel.mendelexception.MendelException;
import mendel.mendelexception.ServerError;

/**
 * The main class for the Mendel application.
 * It serves as the entry point to the app.
 */
public class Mendel {
    private final Parser taskManager;
    private final UI ui;

    /**
     * Constructs a new instance of the Mendel application.
     */
    public Mendel() {
        this.taskManager = new Parser();
        this.ui = new UI();
    }

    /**
     * Starts the main loop of the Mendel application.
     * Continuously reads user input, processes commands, and outputs results
     * until the "bye" command is entered. Handles exceptions by printing error messages
     * and terminates the application in case of a server error.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        String currAction = "hello";

        ui.showWelcome(this.taskManager);
//        ui.autoRemindDeadlines(this.taskManager);
        while (!currAction.equals("bye")) {
            currAction = sc.nextLine().trim();
            try {
                ui.preetyPrint(taskManager.manage(currAction));
            } catch (MendelException e) {
                ui.preetyPrint(e.toString());
            } catch (ServerError e) {
                ui.preetyPrint(e.toString());
                taskManager.manage("bye");
                currAction = "bye";
            }
        }
    }

    /**
     * The main method and entry point for the Mendel application.
     */
    public static void main(String[] args) {
        new Mendel().run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String currAction) {
        String response = "";
        try {
            response = taskManager.manage(currAction);
            ui.preetyPrint(response);
        } catch (MendelException e) {
            response = e.toString();
            ui.preetyPrint(response);
        } catch (ServerError e) {
            response = e.toString();
            ui.preetyPrint(response);
            taskManager.manage("bye");
        }
        return response;
    }
}
