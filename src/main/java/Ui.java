import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints message s with appropriate string in front
     * @param s
     */
    public void talk(String s) {
        System.out.println("Barcus: " + s);
    }


    public void showWelcome() {
        talk("Beep bop! Hello I am Barcus, ready to be of assistance!\n" +
                "Write 'bye' to leave!\n");
    }

    public void showGoodbye() {
        talk("Alright, good talk!");
    }

    public void showError(String e) {
        talk("Uh oh, " + e);
    }

    public String readCommand() {
        System.out.print("You: ");
        return this.scanner.nextLine();
    }

}
