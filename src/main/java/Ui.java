import java.util.Scanner;

public class Ui {
    public Ui() {

    }

    /**
     *  prints an error message when there is no data file
     */
    public void showLoadingError() {
        this.showLine();
        System.out.println("    Hey there! There is an error loading the data from the data file");
        this.showLine();
    }

    /**
     *  prints a welcome message
     */
    public void showWelcome() {
        this.showLine();
        System.out.println("    Hello! I'm Tuesday, a randomly created bot.\nWhat can I do for you?");
        this.showLine();
    }

    /**
     *  reads the lines typed out & returns the command
     */
    public String readCommand() {
        Scanner scannerObj = new Scanner(System.in); // Create a Scanner object
        return scannerObj.nextLine();
    }

    /**
     *  prints a divider line
     */
    public void showLine() {
        System.out.println("    _______________________________");
    }

    public void showError(String error_msg) {

    }
}
