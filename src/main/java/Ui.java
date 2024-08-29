import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public void printList(TaskList list) {
        printLine();
        System.out.println(list.printString());
        printLine();
    }

    protected void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

    public void goodbyeMessage() {
        printLine();
        System.out.println("    See you next time.");
        printLine();
    }

    public void nonsenseErrorMessage() {
        printLine();
        System.out.println("    Could you, like, write a sensible command please? \n");
        printLine();
    }

    public void saveFileErrorMessage() {
        printLine();
        System.out.println("    there hath been a failure in saving your work");
        printLine();
    }

    public void welcomeMessage() {
        String logo = "___________.__            __\n" +
                "\\_   _____/|  |   _______/  |_  ___________\n" +
                " |    __)_ |  |  /  ___/\\   __\\/ __ \\_  __ \\\n" +
                " |        \\|  |__\\___ \\  |  | \\  ___/|  | \\/\n" +
                "/_______  /|____/____  > |__|  \\___  >__|\n" +
                "        \\/           \\/            \\/";
        System.out.println(logo);
        printLine();
        System.out.println("    Hello, \"greetings\" from your friendly neighbourhood chatbot Elster..");
        System.out.println("    How can I help you today :|");
        printLine();
    }

    public void taskDoneMessage(Task task) {
        printLine();
        System.out.println("    Yes boss, marked the task as done.");
        System.out.println("      " + task.toString());
        printLine();
    }

    public void taskUndoneMessage(Task task) {
        printLine();
        System.out.println("    Interesting choice, I've marked the task as not done.");
        System.out.println("      " + task.toString());
        printLine();
    }

    public void indexOutOfBoundsErrorMessage() {
        printLine();
        System.out.println("    Ain't no such task in the middle of these woods");
        printLine();
    }

    public void alreadyDoneErrorMessage() {
        printLine();
        System.out.println("    So uh, the task is already done");
        printLine();
    }

    public void alreadyUndoneErrorMessage() {
        printLine();
        System.out.println("    So uh, the task already is not done");
        printLine();
    }
}
