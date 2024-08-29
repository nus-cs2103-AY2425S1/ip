package echobot;

import echobot.command.CommandResponse;

import java.util.Scanner;

public class UI {
    private final Scanner scan;

    public UI() {
        this.scan = new Scanner(System.in);
    }

    private String addMsgWithOutline(String msg) {
        return "\t\t\t---------------------------------------------------------------\n" + "\t\t\t" + msg + "\n" + "\t\t\t---------------------------------------------------------------";
    }

    /**
     * Gets user input.
     *
     * @return User input.
     */
    public String getUserInput() {
        return this.scan.nextLine();
    }


    /**
     * Prints greeting message.
     */
    public void greeting() {
        System.out.println(this.addMsgWithOutline("Hello! I'm EchoChat\n\t\t\tWhat can I do for you?"));
    }

    /**
     * Prints the error message from the Exception.
     *
     * @param e The exception.
     */
    public void printErrorMessage(Exception e) {
        System.out.println(this.addMsgWithOutline(e.getMessage()));
    }

    /**
     * Prints the response of the command.
     *
     * @param commandResponse The response of the command.
     */
    public void printCommandResponse(CommandResponse commandResponse) {
        if (commandResponse.isExitCommand()) {
            return;
        }
        System.out.println(this.addMsgWithOutline(commandResponse.getResponse()));
    }

    /**
     * Prints exit message.
     */
    public void exit() {
        System.out.println(this.addMsgWithOutline("Bye. Hope to see you again soon!"));
    }
}
