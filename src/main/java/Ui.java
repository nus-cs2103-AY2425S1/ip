import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public void printList(TaskList list) {
        list.printList();
    }

    protected void printLine() {
        System.out.println("    ____________________________________________________________________________");
    }

    private void addToList() {

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
}
