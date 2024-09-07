package duke.ui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * The {@code Notgpt} class is a simple command-line application that serves as an interface
 * for interacting with the user. It prints a logo and a greeting message, then passes control
 * to a parser that processes user input.
 */
public class Notgpt{
    /**
     * Prints a line divider to the console.
     * <p>
     * This method is used to create a visual line separation between different sections
     * of the console output.
     * </p>
     */
    public static void lnDiv() {
        System.out.println("___________________________________________________________________________");
    }

    /**
     * The main method serves as the entry point for the application.
     * <p>
     * It initializes the application by printing a logo and a greeting message,
     * then passes control to the {@code Parser} to process user commands. Any exceptions
     * that occur during execution are caught, logged to a file, and displayed to the user.
     * </p>
     *
     * @param args command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        NotgptJavaFX.main(args);
    }

//        try {
//            Scanner scanner = new Scanner(System.in);
//            String logo = " _   _       _                 _\n"
//                    + "| \\ | | ___ | |_    ____ ____ | |_\n"
//                    + "|  \\| |/ _ \\  __|  / _` | '_ \\| __|\n"
//                    + "| |\\  | (_) | |_  | (_| | |_) | |_\n"
//                    + "|_| \\_|\\___/ \\__|  \\__, | .__/ \\__|\n"
//                    + "                   |___/|_|\n";
//            System.out.println(logo + "\n" + "hi, I'm Not-gpt,\ndo you really need me to do sth for you?");
//            lnDiv();
//            Parser.parse(scanner);
//        } catch (Exception e) {
//            e.printStackTrace();
//            try (FileWriter fw = new FileWriter("error.log", true);
//                 PrintWriter pw = new PrintWriter(fw)) {
//                e.printStackTrace(pw);
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            }
//            System.out.println("An error occurred. Press Enter to exit.");
//            new Scanner(System.in).nextLine();
//        }
//    }
}

