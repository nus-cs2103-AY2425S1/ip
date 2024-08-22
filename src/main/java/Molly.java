import java.util.Scanner;
import java.util.ArrayList;

/**
 * Molly bot class containing static methods that help Molly interact with user.
 */
public class Molly {
    public static String name = "Molly";

    public static String[] helpCommands = {
            "todo (description) - creates a new todo", "deadline (description) /by (end time or date) - creates a deadline",
            "event (description) /from (start time or date) /to (end time or date) - creates an event",
            "list - shows you all your tasks",
            "mark (task number) / unmark (task number) - marks or unmarks your tasks as done",
            "delete (task number) - removes a task"
    };

    public Molly() {

    }

    /**
     * This static method prints a line for Molly's user interface
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * This is a public static method that makes Molly greet the user.
     */
    public static void greetUser() {
        Molly.printLine();
        System.out.println("Hello! I'm " + Molly.name);
        System.out.println("I am a virtual assistant designed to help you plan your tasks.");
        System.out.println("What can I do for you? For a guide, enter the '/help' command");
    }



    /**
     * This is a public static method that makes Molly say bye.
     */
    public static void sayBye() {
        Molly.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        Molly.printLine();
    }

    public static void printHelpCommands() {
        System.out.println("These are Molly's help commands.");
        for (int i = 0; i < helpCommands.length; i++) {
            System.out.println((i + 1) + ". " + helpCommands[i]);
        }
        Molly.printLine();


    }

    /**
     * @param botMemory
     * This is a public static method that converts the task list to a string to present to the user.
     */
    public static void listToString(ArrayList<Task> botMemory) {
        if (botMemory.isEmpty()) {
            System.out.println("No items in your list");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < botMemory.size(); i++) {
                System.out.println((i + 1) + ". " + botMemory.get(i).toString());
            }
        }
    }

    /**
     * This is a static method for the Molly bot that processes user inputs and manipulates the task array accordingly.
     */
    public static void assistUser() throws MollyException {
        ArrayList<Task> botMemory = new ArrayList<>();
        Scanner botScanner = new Scanner(System.in);
        String userInput = "";
        Molly.printLine();
        while (!userInput.toLowerCase().equals("bye")) {
            try {
                userInput = botScanner.nextLine();

                if (userInput.toLowerCase().equals("bye")) {
                    break;
                }

                if (userInput.startsWith("mark") || userInput.startsWith("unmark")) {
                    String[] markParts = userInput.split(" ");
                    if (markParts.length == 2) {
                        try {
                            int taskToMark = Integer.parseInt(markParts[1]) - 1;
                            botMemory.get(taskToMark).toggleTaskDone();
                            Molly.printLine();

                        } catch (NumberFormatException e) {
                            Molly.printLine();
                            throw new MollyException("Invalid command. Please enter a valid task number to mark.");
                        }
                    } else {
                        Molly.printLine();
                        throw new MollyException("Invalid  mark/unmark command. Please use 'mark/unmark (task number)'.");
                    }

                } else if (userInput.startsWith("delete")) {
                    String[] parts = userInput.split(" ");
                    if (parts.length == 2) {
                        try {
                            int taskToDelete = Integer.parseInt(parts[1]) - 1;
                            Molly.printLine();
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println(" " + botMemory.get(taskToDelete).toString());
                            botMemory.remove(taskToDelete);
                            System.out.println("Now you have " + botMemory.size() + " tasks in the list.");
                            Molly.printLine();
                        } catch (NumberFormatException e) {
                            Molly.printLine();
                            throw new MollyException("Invalid command. Please enter a valid task number.");
                        }
                    } else {
                        Molly.printLine();
                        throw new MollyException("Invalid  delete command. Please use 'delete (task number)'.");
                    }

                } else if (!userInput.toLowerCase().equals("list")) {
                    if (userInput.startsWith("todo")) {
                        if (userInput.equals("todo")) {
                            Molly.printLine();
                            throw new MollyException("Yikes! Sorry, the todo description cannot be empty.");
                        } else {
                            Task newToDo = new Task(userInput);
                            botMemory.add(newToDo);
                            Molly.printLine();
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(" " + newToDo.toString());
                            System.out.println("Now you have " + botMemory.size() + " task(s) in the list.");
                            System.out.println();
                            Molly.listToString(botMemory);
                            Molly.printLine();
                        }
                    } else if (userInput.startsWith("deadline")) {
                        if (userInput.equals("deadline")) {
                            Molly.printLine();
                            throw new MollyException("Yikes! Sorry, the description of a deadline cannot be empty.");
                        } else {
                            String[] deadlineDetails = userInput.substring(9).split( " /by ");
                            if (deadlineDetails.length == 2) {
                                Deadline newDeadline = new Deadline(userInput.substring(9, userInput.indexOf("/") - 1), userInput.substring(userInput.indexOf("/") + 1));
                                botMemory.add(newDeadline);
                                Molly.printLine();
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(" " + newDeadline.toString());
                                System.out.println("Now you have " + botMemory.size() + " tasks in the list.");
                                System.out.println();
                                Molly.listToString(botMemory);
                                Molly.printLine();
                            } else {
                                Molly.printLine();
                                throw new MollyException("Sorry, invalid format for deadline. Please follow the format: deadline (description) /by (end date)");
                            }
                        }
                    } else if (userInput.startsWith("event")) {
                        if (userInput.equals("event")) {
                            Molly.printLine();
                            System.out.println("Yikes! Sorry, the description of an event cannot be empty.");
                            Molly.printLine();
                        } else {
                            String[] eventDetails = userInput.substring(6).split( " /from | /to ");
                            if (eventDetails.length == 3) {
                                Event newEvent = new Event(userInput.substring(6, userInput.indexOf("/") - 1),
                                        userInput.substring(userInput.indexOf("/") + 6, userInput.lastIndexOf("/") - 1),
                                        userInput.substring(userInput.lastIndexOf("/") + 4));
                                botMemory.add(newEvent);
                                Molly.printLine();
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(" " + newEvent.toString());
                                System.out.println("Now you have " + botMemory.size() + " tasks in the list.");
                                System.out.println();
                                Molly.listToString(botMemory);
                                Molly.printLine();
                            } else {
                                Molly.printLine();
                                throw new MollyException("Sorry, invalid format for deadline. Please follow the format: event (description) /from (start date or time) /to (end date or time)");
                            }
                        }
                    } else if (userInput.equals("/help")) {
                        Molly.printHelpCommands();

                    } else {
                        throw new MollyException("I'm sorry, I do not know how to respond to that. Type '/help' for the list of allowed commands.");
                    }

                } else {
                    Molly.printLine();
                    Molly.listToString(botMemory);
                    Molly.printLine();
                }
            } catch (MollyException e) {
                System.out.println(e.getMessage());
                Molly.printLine();
            }

        }
        Molly.sayBye();

    }


    public static void main(String[] args) throws MollyException {
        Molly.greetUser();
        Molly.assistUser();
    }
}
