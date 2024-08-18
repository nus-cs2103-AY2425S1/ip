import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class SumoDE {

    private static void printTask(List<Task> tasks) {
        System.out.println("Below is the list of tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task == null) {
                break;
            }
            System.out.println((i+1) + ". "+ task);
        }
    }

    private static boolean execute(String command, String item, List<Task> tasks)
            throws NonExistentTaskException, UnknownCommandException, WrongSyntaxForCommandException {
        switch(command) {
            case "bye":
            case "exit":  // added this to allow flexibility though not required by qn
                return true;
            case "list":
                printTask(tasks);
                break;
            case "mark":
            {
                int index = Integer.parseInt(item);
                if (index >= tasks.size() || index <= 0) {
                    throw new NonExistentTaskException(index);
                }
                tasks.get(index-1).mark();
            }
            break;
            case "unmark":
            {
                int index = Integer.parseInt(item);
                if (index >= tasks.size() || index <= 0) {
                    throw new NonExistentTaskException(index);
                }
                tasks.get(index - 1).unmark();
            }
            break;
            case "todo":
            case "deadline":
            case "event":
                tasks.add(Task.of(command, item));  // used factory method to be more neat and OOP
                System.out.println("Sumo has added this task for you.\n"
                        + tasks.get(tasks.size() - 1)
                        + "\n"
                        + "There are now "
                        + (tasks.size())
                        + " task(s) in total!");
                break;
            default:
                throw new UnknownCommandException(command);
        }
        return false;
    }

    public static void main(String[] args) {





        // Initialisation
        List<Task> tasks = new ArrayList<>();


        // Note: those extra spaces before \n is done intentionally
        // so it is easier to see the logo and edit it subsequently
        // I know it is waste of space and unnecessary
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
                terminate = execute(command,item,tasks);
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

            // performing commands



        }

        // loop ended, cleaning up

        System.out.println(goodbye);
        sc.close();
    }
}
