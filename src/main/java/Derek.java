import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;


/**
 * Derek is a bot that interacts with the user, manages tasks, and provides functionalities like
 * adding tasks, marking them as complete or incomplete, deleting tasks, and more.
 */
public class Derek {
    private static String logo = " ---    ---\n"
            + "| # |  | # |\n"
            + " ---    ---\n"
            + "  \\      /\n"
            + "    ----\n";

    private static String sadLogo = " ---    ---\n"
            + "| # |  | # |\n"
            + " ---    ---\n"
            + "    ----\n"
            + "  /      \\\n";


    private String user;

    private static String leavingMessage = String.format("Ok...\n" + sadLogo);


    public static void main(String[] args) {
        Derek instance = new Derek();
        instance.introduction();
    }

    /**
     * Introduces Derek and initiates user interaction.
     */
    public void introduction() {
        System.out.println("Hello! I'm Derek! Can we be friends?\n" + logo);
        String userInput =
                "Your response (Y/N):";
        System.out.println(userInput);
        Scanner sc = new Scanner(System.in);
        String response = sc.nextLine();
        if (response.equalsIgnoreCase("Y")) {
            System.out.println("Great! I have always wanted a friend!\n"
                    + "What do I call you?");
            Scanner name = new Scanner(System.in);
            this.user = name.nextLine();
            this.firstInteraction();
        } else if (response.equalsIgnoreCase("N")) {
            System.out.println(leavingMessage);
        }

    }

    public void firstInteraction() {
        System.out.println("\n" + "Hi! " + user + "! So, I guess as a friend I just become your little slave!\n"
                + "What do you want me to do?\n");
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        if (task.equalsIgnoreCase("bye")) {
            System.out.println(leavingMessage);
        } else {
            System.out.println(task + "\n");
            echo();
        }
    }

    public static void echo() {
        System.out.println("anything else?");
        Scanner sc = new Scanner(System.in);
        String task = sc.nextLine();
        if (task.equalsIgnoreCase("bye")) {
            System.out.println(leavingMessage);
        } else {
            System.out.println( task + "\n");
            echo();
        }
    }


}