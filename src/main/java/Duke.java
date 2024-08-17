import java.util.Scanner;

public class Duke {

    public static void printTask(String[] tasks) {
        for (int i = 0; i < tasks.length; i++) {
            String task = tasks[i];
            if (task == null) {
                break;
            }
            System.out.println(i + ". "+ task);
        }
    }

    public static void main(String[] args) {





        // Initialisation
        String[] tasks = new String[100];
        int tasksIndex = 0;

        // Note: those extra spaces before \n is done intentionally
        // so it is easier to see the logo and edit it subsequently
        // I know it is waste of space and unnecessary
        String logo = "           ___           \n"
                    + "          |* *|          \n"
                    + "          |\\_/|          \n"
                    + "  ---------------------  \n"
                    + "  |                   |  \n"
                    + "-----     SUMO      -----\n"
                    + "| | |               | | |\n"
                    + "-----      DE       -----\n"
                    + "  |                   |  \n"
                    + "  ---------------------  \n"
                    + "  |                   |  \n"
                    + "  |      /-----\\      |  \n"
                    + "  |_____/       \\_____|  \n";

        String goodbye = "------------------------------------\n"
                        + "Goodbye! Sumo hope to see you again!\n"
                        + "------------------------------------\n";












        //Actual programme

        System.out.println("    Hello, I am Sumo-DE \n"
                + logo
                + '\n'
                + " How can Sumo help you?"
        );

        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String lowercaseInput = input.toLowerCase();
            if (lowercaseInput.equals("bye")){
                break;
            } else if (lowercaseInput.equals("list")) {
                printTask(tasks);
            } else {
                tasks[tasksIndex++] = input;
                System.out.println(("Added: " +input));
            }
        }

        System.out.println(goodbye);
        sc.close();
    }
}
