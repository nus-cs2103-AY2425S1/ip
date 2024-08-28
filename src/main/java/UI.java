import command.CommandResponse;

import java.util.Scanner;

public class UI {
    private final Scanner scan;

    public UI() {
        this.scan = new Scanner(System.in);
    }

    private String addMsgWithOutline(String msg) {
        return "\t\t\t---------------------------------------------------------------\n" + "\t\t\t" + msg + "\n" + "\t\t\t---------------------------------------------------------------";
    }

    public String getUserInput() {
        return this.scan.nextLine();
    }

    public void greeting() {
        System.out.println(this.addMsgWithOutline("Hello! I'm EchoChat\n\t\t\tWhat can I do for you?"));
    }

    public void printErrorMessage(Exception e) {
        System.out.println(this.addMsgWithOutline(e.getMessage()));
    }

    // TODO: print command
    public void printCommandResponse(CommandResponse commandResponse) {
        if (commandResponse.isExitCommand()) {
            return;
        }
        System.out.println(this.addMsgWithOutline(commandResponse.getResponse()));
    }

    public void exit() {
        System.out.println(this.addMsgWithOutline("Bye. Hope to see you again soon!"));
    }
}
