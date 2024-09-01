import java.util.Scanner;

// Ui class will deal with interactions with users, including input, output
public class Ui {

    private final String botname;

    public Ui(String name) {
        this.botname = name;
    }


    /**
     * Shows the welcome default message of the chatbot.
     *
     */
    public void showWelcome() {
        //Introduce self
        printText("Hello! I'm " + this.botname + "\nAt your service! O7");
    }

    public void showExit() {
        printText("\tBye. Hope to see you again soon!\n");
    }

    private void showLine() {
        System.out.println("\t_______________________________________________________________________");
    }

    /**
     * Prints out the text provided to it in a standardised "segment". Automatically include the linebreak, showLine()
     * and newline
     *
     * @param text String to be printed out by the chatbot
     */
    public void printText(String text) {
        System.out.println("\n");
        System.out.println(text);
        showLine();
    }

    public void printError(String text) {
        System.out.println("Error! \n");
        System.out.println(text);
        showLine();
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);

        return scanner.nextLine().trim();
    }

}
