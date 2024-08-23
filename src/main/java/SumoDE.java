import java.util.Scanner;

public class SumoDE {


    public static void main(String[] args) {

        // Initialisation
        SumoTaskList tasks = new SumoTaskList();

        String logo = """
                           ___
                          |* *|
                          |\\_/|
                  ---------------------
                  |                   |
                -----     SUMO      -----
                | | |               | | |
                -----      DE       -----
                  |                   |
                  ---------------------
                  |                   |
                  |      /-----\\      |
                  |_____/       \\_____|
                """;

        String goodbye = """
                ------------------------------------
                Goodbye! Sumo hope to see you again!
                ------------------------------------
                """;

        //Actual programme

        System.out.println("------------------------------------\n" +
                "    Hello, I am Sumo-DE\n"
                + logo
                + '\n'
                + " How can Sumo help you?\n"
                + "------------------------------------"
        );

        Scanner sc = new Scanner(System.in);
        boolean terminate = false;

        while (!terminate) {
            String input = sc.nextLine();

            // Splitting command and action
            int spaceLocation = input.indexOf(" ");
            String command;
            String item;

            if (spaceLocation == -1) {
                command = input.toLowerCase();  // to lower case to allow some flexibility for better experience
                item = "";
            } else {
                command = input.substring(0,spaceLocation).toLowerCase();
                item = input.substring(spaceLocation+1);
            }

            try {
                terminate = tasks.execute(command,item);
            } catch (WrongSyntaxForCommandException | UnknownCommandException | NonExistentTaskException e) {
                System.out.println(e.getMessage());
            } finally {
                if (!terminate) {
                    System.out.println("""
                            ------------------------------------
                            Do you need anything else from SUMO?
                            ------------------------------------""");
                }
            }

        }

        // loop ended, cleaning up
        System.out.println(goodbye);
        sc.close();
    }
}
