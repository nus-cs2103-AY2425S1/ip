import java.util.Scanner;
public class Void {
    public static void main(String[] args) {
       /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        String logo =
                "### ###   ## ##     ####   ### ##   \n" +
                " ##  ##  ##   ##     ##     ##  ##  \n" +
                " ##  ##  ##   ##     ##     ##  ##  \n" +
                " ##  ##  ##   ##     ##     ##  ##  \n" +
                " ### ##  ##   ##     ##     ##  ##  \n" +
                "  ###    ##   ##     ##     ##  ##  \n" +
                "   ##     ## ##     ####   ### ## \n";

        String[] greetings = {
                "Hello! I'm your friendly void cat, \n",
                "Purr... Hello, wanderer. I am \n",
                "Mew! Welcome human! I'm \n",
                "Greetings from the abyss, friend, for I am \n",
                "Meow! Happy to help, they call me \n"
        };

        String[] assistGreeting = {
                "How can this void assist you today?",
                "At your service, human.",
                "What help does human need today?",
                "Need any help?",
                "What can I do for you?"
        };

        // Example of exits
        String[] exits = {
                "Purr... Until next time, friend.",
                "Meow! I shall vanish into the shadows now.",
                "Farewell! May your path be clear.",
                "Mew! See you in the void again soon.",
                "The void calls, but I'll return. Goodbye!"
        };

        Scanner scanner = new Scanner(System.in);
        String input;
        //Tab string format
        String format = "\t%s%n";

        Task[] tasks = new Task[100];
        int taskCounter = 0;

        // Display a random greeting
        System.out.printf(format, "------------------------------------------------------------------");
        System.out.printf(format, greetings[(int) (Math.random() * greetings.length)]);

        System.out.println(logo.indent(4));
        // Display a random assist greeting
        System.out.printf(format, assistGreeting[(int) (Math.random() * assistGreeting.length)]);
        System.out.printf(format, "------------------------------------------------------------------");

        while (true) {
            input = scanner.nextLine();  // Reads user
            String[] splitInput = input.split(" ", 2); //to get the command and argument
            if (splitInput[0].equalsIgnoreCase("bye")) {
                System.out.printf(format, "------------------------------------------------------------------");
                //Display a random exit
                System.out.printf(format, exits[(int) (Math.random() * exits.length)]);
                break;  // Exit loop when bye
            } else if (splitInput[0].equalsIgnoreCase("list")) {
                if (taskCounter == 0) {
                    System.out.printf(format, "------------------------------------------------------------------");
                    System.out.printf(format, "No tasks found in list yet!");
                    System.out.printf(format, "------------------------------------------------------------------");

                } else {
                    System.out.printf(format, "------------------------------------------------------------------");
                    System.out.printf(format, "Here are the tasks in your list: ");
                    for (int i = 0; i < taskCounter; i++) {
                        System.out.printf(format, (i + 1) + ". " + tasks[i]);  // Lists all task objects
                    }
                    System.out.printf(format, "------------------------------------------------------------------");
                }
            } else if (splitInput[0].equalsIgnoreCase("mark")) {
                if (splitInput.length < 2) {
                    System.out.printf(format, "------------------------------------------------------------------");
                    System.out.printf(format, "Hm.. I don't know which to mark! Give me the task number please.");
                    System.out.printf(format, "------------------------------------------------------------------");

                } else {
                    int tasksIndex = Integer.parseInt(splitInput[1]) - 1;
                    if (tasksIndex >= taskCounter || tasksIndex < 0) { //checks if index is appropriate
                        System.out.printf(format, "------------------------------------------------------------------");
                        System.out.printf(format, "Sorry! Can't mark tasks out of the list");
                        System.out.printf(format, "------------------------------------------------------------------");

                    } else {
                        tasks[tasksIndex].markAsDone();
                        System.out.printf(format, "------------------------------------------------------------------");
                        System.out.printf(format, "Good job! I've marked this task as done:");
                        System.out.printf(format, tasks[tasksIndex]);
                        System.out.printf(format, "------------------------------------------------------------------");

                    }
                }
            } else if (splitInput[0].equalsIgnoreCase("unmark")) {
                if (splitInput.length < 2) {
                    System.out.printf(format, "------------------------------------------------------------------");
                    System.out.printf(format, "Hm.. I don't know which to unmark! Give me the task number please.");
                    System.out.printf(format, "------------------------------------------------------------------");
                } else {
                    int tasksIndex = Integer.parseInt(splitInput[1]) - 1;
                    if (tasksIndex >= taskCounter || tasksIndex < 0 ) { //checks if index is appropriate
                        System.out.printf(format, "------------------------------------------------------------------");
                        System.out.printf(format, "Sorry! Can't mark tasks out of the list");
                        System.out.printf(format, "------------------------------------------------------------------");

                    } else {
                        tasks[tasksIndex].unmarkAsDone();
                        System.out.printf(format, "------------------------------------------------------------------");
                        System.out.printf(format, "OK, I've marked this task as not done yet:");
                        System.out.printf(format, tasks[tasksIndex]);
                        System.out.printf(format, "------------------------------------------------------------------");
                    }
                }
            } else {
                if (input.isBlank()) {
                    System.out.printf(format, "------------------------------------------------------------------");
                    System.out.printf(format, "Meow! Empty tasks can't be added!");
                    System.out.printf(format, "------------------------------------------------------------------");

                } else {
                    tasks[taskCounter] = new Task(input);
                    taskCounter++;
                    System.out.printf(format, "------------------------------------------------------------------");
                    System.out.printf(format, "added: " + input);  // Echoes it
                    System.out.printf(format, "------------------------------------------------------------------");
                }
            }
        }

        System.out.printf(format, "------------------------------------------------------------------");
        scanner.close();

    }
}
