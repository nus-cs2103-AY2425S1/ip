import java.io.IOException;
import java.util.Scanner;

public class SumoDE {

    private Storage storage;
    private SumoTaskList tasks;

    public SumoDE (String filePath) {
        // handle Storage
        try {
            this.storage = new Storage(filePath);
        } catch (IOException e) {
            // Note: this will only happen when file don't exist and we cannot create new file in the path.
            // New File will be created when file doesn't exist in first place.
            System.out.println(
                    "Help! Sumo unable to save data due to unknown error!\n"
                    + "Please exit and try again if u wanna save"
            );
        }

        //handle SumoTaskList
        if (storage == null) {
            this.tasks = new SumoTaskList(); // we will use the version where we cannot save
        } else {
            try {
                this.tasks = new SumoTaskList(this.storage);
            } catch (IOException e) {
                //unlikely will happen since we already successfully initialise storage
                System.out.println(
                        "Help! Sumo unable to save data due to unknown error!\n"
                        + "Please exit and try again if u wanna save"
                );
                this.tasks = new SumoTaskList(); // we will use the version where we cannot save
            }

        }

    }

    public void run() {
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
                terminate = this.tasks.execute(command,item);
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
        sc.close();
    }


    public static void main(String[] args) {
        SumoDE sumoDE = new SumoDE("data\\taskSaved.txt");
        sumoDE.run();

    }
}
