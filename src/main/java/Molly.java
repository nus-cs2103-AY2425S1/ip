import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Molly bot class containing static methods that help Molly interact with user.
 */
public class Molly {
    public static String name = "Molly";

    public static final String FILE_PATH = "./data/Molly.txt";

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
     * This method loads tasks from the Molly.txt file, creating new tasks for each line and adding them to the botMemory ArrayList.
     * @return
     */
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> botMemory = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNextLine()) {
                    String taskData = fileScanner.nextLine();
                    Task task = parseTask(taskData);
                    if (task != null) {
                        botMemory.add(task);
                    }
                }
                fileScanner.close();
            } catch (IOException e) {
                System.out.println("Error loading tasks: " + e.getMessage());
            }
        } else {
            file.getParentFile().mkdirs();
        }
        return botMemory;
    }


    /**
     * This method saves all the tasks from the botMemory ArrayList into the Molly.txt file
     * @param botMemory
     */
    public static void saveTasks(ArrayList<Task> botMemory) {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            for (Task task : botMemory) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * This method parses tasks when reading the Molly.txt file, returning the tasks so that they
     * can be added to the botMemory ArrayList.
     * @param taskData
     * @return
     */

    public static Task parseTask(String taskData) {
        if (taskData.startsWith("[T]")) {
            String description = taskData.substring(7);
            Task task = new Task(description);
            if (taskData.charAt(4) == 'X') {
                task.markDone();
            }
            return task;
        } else if (taskData.startsWith("[D]")) {
            int byIndex = taskData.indexOf("(by:");
            if (byIndex != -1) {
                String description = taskData.substring(7, byIndex - 1);
                String by = taskData.substring(byIndex + 5, taskData.length() - 1);
                Deadline deadline = new Deadline(description, by);
                if (taskData.charAt(4) == 'X') {
                    deadline.markDone();
                }
                return deadline;
            }
        } else if (taskData.startsWith("[E]")) {
            int fromIndex = taskData.indexOf("(from:");
            int toIndex = taskData.indexOf("to:");
            if (fromIndex != -1 && toIndex != -1) {
                String description = taskData.substring(7, fromIndex - 1);
                String from = taskData.substring(fromIndex + 7, toIndex - 1);
                String to = taskData.substring(toIndex + 4, taskData.length() - 1);
                Event event = new Event(description, from, to);
                if (taskData.charAt(4) == 'X') {
                    event.markDone();
                }
                return event;
            }
        }

        return null;
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
        //ArrayList<Task> botMemory = new ArrayList<>();
        ArrayList<Task> botMemory = Molly.loadTasks();
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
                            Molly.saveTasks(botMemory);

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
                            Molly.saveTasks(botMemory);
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
                            Molly.saveTasks(botMemory);
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
<<<<<<< HEAD
=======
                                String description = deadlineDetails[0].trim();
                                String by = deadlineDetails[1].trim();
                                Deadline newDeadline = new Deadline(description, by);
>>>>>>> branch-Level-8
                                botMemory.add(newDeadline);
                                Molly.printLine();
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(" " + newDeadline.toString());
                                Molly.saveTasks(botMemory);
                                System.out.println("Now you have " + botMemory.size() + " tasks in the list.");
                                System.out.println();
                                Molly.listToString(botMemory);
                                Molly.printLine();
                            } else {
                                Molly.printLine();
                                throw new MollyException("Sorry, invalid format for deadline. Please follow the format: deadline (description) /by (end date). The end date can be in the format DD-MM-YYYY HHmm (24 hour format).");
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
<<<<<<< HEAD

=======
                                String description = eventDetails[0].trim();
                                String from = eventDetails[1].trim();
                                String to = eventDetails[2].trim();
                                Event newEvent = new Event(description, from, to);
>>>>>>> branch-Level-8
                                botMemory.add(newEvent);
                                Molly.printLine();
                                System.out.println("Got it. I've added this task: ");
                                System.out.println(" " + newEvent.toString());
                                Molly.saveTasks(botMemory);
                                System.out.println("Now you have " + botMemory.size() + " tasks in the list.");
                                System.out.println();
                                Molly.listToString(botMemory);
                                Molly.printLine();
                            } else {
                                Molly.printLine();
                                throw new MollyException("Sorry, invalid format for deadline. Please follow the format: event (description) /from (start date or time) /to (end date or time). The start and end date/times can be in the format DD-MM-YYYY HHmm (24 hour format)");
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
        Molly.saveTasks(botMemory);
        Molly.sayBye();

    }


    public static void main(String[] args) throws MollyException {
        Molly.greetUser();
        Molly.assistUser();
    }
}
