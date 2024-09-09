package rotodo.processes;

import java.util.Scanner;

/**
 * This class implements the CLI of RoTodo.
 *
 * @author Ng Kay Hian
 * @version CS2103T AY24/25 Semester 1
 */
public class Gui {
    private String text;
    private Scanner sc;

    /**
     * Initialise Ui object
     */
    public Gui() {
        text = "";
        this.banner();
        sc = new Scanner(System.in); // Create a Scanner object
    }

    /**
     * Displays output message stored in <code>text</code>.
     * Output is wrapped by lines.
     */
    public void showMessage() {
        System.out.println("  " + new String(new char[100]).replace("\0", "-"));
        System.out.println("    " + text.replace("\n", "\n    "));
        System.out.println("  " + new String(new char[100]).replace("\0", "-") + "\n");
        text = "";
    }

    public String getMessage() {
        String tmp = text.replace("\u001B[31m", "").replace("\u001B[0m", "");
        text = "";
        return tmp;
    }

    /**
     * Prints chatbot banner.
     */
    public void banner() {
        // Declaring ANSI_COLOR
        String ansiReset = "\u001B[0m";
        String ansiRed = "\u001B[31m";

        text += "Hello! I'm \n"
                //+ ("R__________E       __________            __   _____\n"
                //+ "R\\______   \\E  ____\\__   ___/____    ___|  | /  _  \\   ____\n"
                //+ " R|       _/E /  _ \\ |   |  /  _ \\  /  _   ||  / \\  | /  _ \\   ___\n"
                //+ " R|    |   \\E(  <_> ||   | (  <_> |(  <_>  ||  \\_/  |(  <_> | / o \\  _\n"
                //+ " R|____|_  /E \\____/ |___|  \\____/  \\_____/  \\_____/  \\____/  \\___/ (_) O o .\n"
                //+ "        R\\/E\n").replace("R", ansiRed).replace("E", ansiReset)
                + "RoTodo!\n"
                + "Your very own Robot Todo List!\n"
                + "How can I help you help yourself?";
    }

    /**
     * Prints chatbot help message
     */
    public void help() {
        text += "Options:\n"
                + "  help          list all available options\n"
                + "  list          Prints all tasks on tasklist\n"
                + "  mark INDEX    Mark task as done\n"
                + "  unmark INDEX  Mark task as undone\n"
                + "  bye           Exit program\n\n"
                + "Supported Task commands:\n"
                + "  todo TASK\n"
                + "                Add new ToDo task to tasklist\n"
                + "  deadline TASK /by DEADLINE\n"
                + "                Add new Deadline task to tasklist,\n"
                + "                with due by date/time\n"
                + "  event TASK /from START /to END\n"
                + "                Add new Event task to tasklist,\n"
                + "                with start and end date/time\n\n"
                + "  * datetime format: dd/MM/yyyy HHmm";
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void addMessage(String msg) {
        text += msg;
    }

    /**
     * Prepare goodbye text for printing.
     */
    public void exit() {
        sc.close();
        text += "Bye, RoTodo wish you all the best. Remember to finish all your tasks!";
    }
}
