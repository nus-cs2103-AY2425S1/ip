import java.io.IOException;
import java.util.Scanner;

public class SumoDE {


    public static void main(String[] args) {

        // Initialisation
        SumoTaskList tasks;
        try {

            tasks = new SumoTaskList("data\\taskSaved.txt");
        } catch (IOException e) {
            // Note: this will only happen when file don't exist and we cannot create new file in the path.
            // New File will be created when file doesn't exist in first place.
            tasks = new SumoTaskList();
            System.out.println("Welp! Sumo unable to save data due to unknown error!\n"
                    + "Please exit and try again if u wanna save");
        }

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
            String commandString;
            String item;
            Command command;

            if (spaceLocation == -1) {
                commandString = input;
                item = "";
            } else {
                commandString = input.substring(0,spaceLocation);
                item = input.substring(spaceLocation+1);
            }


            try {
                command = Command.valueOf(commandString.toUpperCase());
                terminate = tasks.execute(command,item);
            }catch (IllegalArgumentException e) {
                System.out.println("Sumo dunno your command \"" + commandString +"\" ! Check spelling of your first word.");
            }catch (WrongSyntaxForCommandException | UnknownCommandException | NonExistentTaskException e) {
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
        tasks.finaliseChange();
        sc.close();

    }
}
