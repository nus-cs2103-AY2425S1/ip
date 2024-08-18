import java.util.Scanner;

public class SumoDE {

    public static void printTask(Task[] tasks) {
        System.out.println("Below is the list of tasks:");
        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            if (task == null) {
                break;
            }
            System.out.println((i+1) + ". "+ task);
        }
    }

    public static void main(String[] args) {





        // Initialisation
        Task[] tasks = new Task[100];
        int tasksIndex = 0;

        // Note: those extra spaces before \n is done intentionally
        // so it is easier to see the logo and edit it subsequently
        // I know it is waste of space and unnecessary
        String logo = "           ___\n"
                    + "          |* *|\n"
                    + "          |\\_/|\n"
                    + "  ---------------------\n"
                    + "  |                   |\n"
                    + "-----     SUMO      -----\n"
                    + "| | |               | | |\n"
                    + "-----      DE       -----\n"
                    + "  |                   |\n"
                    + "  ---------------------\n"
                    + "  |                   |\n"
                    + "  |      /-----\\      |\n"
                    + "  |_____/       \\_____|\n";

        String goodbye = "------------------------------------\n"
                        + "Goodbye! Sumo hope to see you again!\n"
                        + "------------------------------------\n";












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


            // performing commands
            switch(command) {
                case "bye":
                case "exit":  // added this to allow flexibility though not required by qn
                    terminate = true;
                    break;
                case "list":
                    printTask(tasks);
                    break;
                case "mark":
                    {
                        int index = Integer.parseInt(item);
                        tasks[index-1].mark();
                    }
                    break;
                case "unmark":
                    {
                        int index = Integer.parseInt(item);
                        tasks[index-1].unmark();
                    }
                    break;
                case "todo":
                case "deadline":
                case "event":
                    tasks[tasksIndex] = Task.of(command, item);  // used factory method to be more neat and OOP
                    System.out.println("Sumo has added this task for you.\n"
                            + tasks[tasksIndex]
                            + "\n"
                            + "There are now "
                            + (tasksIndex + 1)
                            + " task(s) in total!");
                    tasksIndex++;
                    break;
                default:
                    tasks[tasksIndex++] = new Todo(input);
                    System.out.println(("Added: " +input));
            }

            if (!terminate) {
                System.out.println("------------------------------------\n" +
                        "Do you need anything else from SUMO?\n" +
                        "------------------------------------");
            }
        }

        // loop ended, cleaning up

        System.out.println(goodbye);
        sc.close();
    }
}
