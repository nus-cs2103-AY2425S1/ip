import java.util.ArrayList;
import java.util.Scanner;
public class Void {

    public static void main(String[] args) {
        String logo =
                "### ###   ## ##     ####   ### ##\n" +
                " ##  ##  ##   ##     ##     ##  ##\n" +
                " ##  ##  ##   ##     ##     ##  ##\n" +
                " ##  ##  ##   ##     ##     ##  ##\n" +
                " ### ##  ##   ##     ##     ##  ##\n" +
                "  ###    ##   ##     ##     ##  ##\n" +
                "   ##     ## ##     ####   ### ##\n";

        String[] greetings = {
//                "Hello! I'm your friendly void cat, \n",
//                "Purr... Hello, wanderer. I am \n",
//                "Mew! Welcome human! I'm \n",
                "Greetings from the abyss, friend, for I am\n"
//                ,
//                "Meow! Happy to help, they call me \n"
        };

        String[] assistGreeting = {
//                "How can this void assist you today?",
                "At your service, human."
//                ,
//                "What help does human need today?",
//                "Need any help?",
//                "What can I do for you?"
        };

        // Example of exits
        String[] exits = {
//                "Purr... Until next time, friend.",
                "Meow! I shall vanish into the shadows now."
//                ,
//                "Farewell! May your path be clear.",
//                "Mew! See you in the void again soon.",
//                "The void calls, but I'll return. Goodbye!"
        };

        Scanner scanner = new Scanner(System.in);
        String input;
        //Tab string format
        String format = "\t%s%n";

//        Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<>();
//        int taskCounter = 0;

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

            try {
                if (splitInput[0].equalsIgnoreCase("bye")) {
                    System.out.printf(format, "------------------------------------------------------------------");
                    //Display a random exit
                    System.out.printf(format, exits[(int) (Math.random() * exits.length)]);
                    break;  // Exit loop when bye
                } else if (splitInput[0].equalsIgnoreCase("list")) {
                    if (tasks.size() == 0) {
                        throw new VoidException("No tasks found in list yet!");
                    } else {
                        System.out.printf(format, "------------------------------------------------------------------");
                        System.out.printf(format, "Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.printf(format, (i + 1) + ". " + tasks.get(i));  // Lists all task objects
                        }
                        System.out.printf(format, "------------------------------------------------------------------");
                    }
                } else if (splitInput[0].equalsIgnoreCase("mark")) {
                    if (splitInput.length < 2) {
                        throw new VoidException("Hm.. I don't know which to mark! Give me the task number please.");
                    } else {
                        int tasksIndex = Integer.parseInt(splitInput[1]) - 1;
                        if (tasksIndex >= tasks.size() || tasksIndex < 0) { //checks if index is appropriate
                            throw new VoidException("Sorry! Can't mark tasks out of the list");

                        } else {
                            Task t = tasks.get(tasksIndex);
                            t.markAsDone();
                            System.out.printf(format, "------------------------------------------------------------------");
                            System.out.printf(format, "Good job! I've marked this task as done:");
                            System.out.printf("\t\t%s%n", t);
                            System.out.printf(format, "------------------------------------------------------------------");

                        }
                    }
                } else if (splitInput[0].equalsIgnoreCase("unmark")) {
                    if (splitInput.length < 2) {
                        throw new VoidException("Hm.. I don't know which to unmark! Give me the task number please.");
                    } else {
                        int tasksIndex = Integer.parseInt(splitInput[1]) - 1;
                        if (tasksIndex >= tasks.size() || tasksIndex < 0) { //checks if index is appropriate
                            throw new VoidException("Sorry! Can't unmark tasks out of the list");

                        } else {
                            Task t = tasks.get(tasksIndex);
                            t.unmarkAsDone();
                            System.out.printf(format, "------------------------------------------------------------------");
                            System.out.printf(format, "OK, I've marked this task as not done yet:");
                            System.out.printf("\t\t%s%n", t);
                            System.out.printf(format, "------------------------------------------------------------------");
                        }
                    }
                } else if (splitInput[0].equalsIgnoreCase("todo")) {
                    if (splitInput.length == 1 || splitInput[1].isBlank()) {
                        throw new VoidException("Meow! The description of a todo can't be empty!");
                    } else {
                        ToDo t = new ToDo(splitInput[1]);
                        t.addTaskMessage(tasks);

                    }
                } else if (splitInput[0].equalsIgnoreCase("deadline")) {
                    if (splitInput.length == 1 || splitInput[1].isBlank()) {
                        throw new VoidException("Meow! The description of a deadline can't be empty!");

                    } else {
                        String[] details = splitInput[1].split(" /by ");
                        if (details.length != 2) {
                            throw new VoidException("AH! The description of a deadline and the deadline can't be empty!\n\tRemember to put a /by after the description!");

                        } else {
                            Deadline d = new Deadline(details[0], details[1]);
                            d.addTaskMessage(tasks);
                        }
                    }
                } else if (splitInput[0].equalsIgnoreCase("event")) {
                    if (splitInput.length == 1 || splitInput[1].isBlank()) {
                        throw new VoidException("Meow! The description of an event can't be empty!");
                    } else {
                        String[] details = splitInput[1].split(" /from | /to ");
                        if (details.length != 3) {
                            throw new VoidException("AH! The description of an event, and the start and end time can't be empty!\n\tRemember to put a /from and /to after the description!");
                        } else {
                            Event e = new Event(details[0], details[1], details[2]);
                            e.addTaskMessage(tasks);
                        }
                    }
                } else {
                    throw new VoidException("OOPS!! My apologies, I don't know what that means =T^T=");
                }
            } catch (VoidException e) {
                System.out.printf(format, "------------------------------------------------------------------");
                System.out.printf(format, e.getMessage());
                System.out.printf(format, "------------------------------------------------------------------");
            }
        }

        System.out.printf(format, "------------------------------------------------------------------");
        scanner.close();

    }
}
