import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Hoshi {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        String logo = """

                 __    __    ______        _______. __    __   __ \s
                |  |  |  |  /  __  \\      /       ||  |  |  | |  |\s
                |  |__|  | |  |  |  |    |   (----`|  |__|  | |  |\s
                |   __   | |  |  |  |     \\   \\    |   __   | |  |\s
                |  |  |  | |  `--'  | .----)   |   |  |  |  | |  |\s
                |__|  |__|  \\______/  |_______/    |__|  |__| |__|\s
                                                                  \s
                """;

        System.out.println(logo);

        ArrayList<Task> arrayList = new ArrayList<>();

        getFileContents("./data/hoshi.txt", arrayList);

        System.out.println("""
                ____________________________________________________________
                Hello! Im Hoshi!
                What can I do for you?
                ____________________________________________________________
                """);


        while (true) {


            System.out.println("Add ToDo/Deadline/Event OR List(Lowercase): ");

            // take in input
            String input = scanner.nextLine();

            // bye
            if (input.equalsIgnoreCase("bye")) {
                break;

                // list
            } else if (input.equalsIgnoreCase("list")) {

                if (!arrayList.isEmpty()) {

                    // for loop for listing
                    for (int i = 0; i < arrayList.size(); i++) {

                        System.out.println(i + 1 + ". " + arrayList.get(i).toString() + "\n");
                    }
                } else {
                    System.out.println("Hoshi doesn't have anything stored! Please add a task first");
                }


                // mark
            } else if (input.toLowerCase().startsWith("mark")) {

                if (input.trim().length() < 5) {
                    System.out.println("Please specify the task number to mark!");

                } else {

                    // split string
                    String[] splitInput = input.split(" ");

                    // get only the number from the 2nd half of the splitInput
                    int markIndex = Integer.parseInt(splitInput[1]) - 1;

                    try {

                        // if specified index is not out of bounds
                        if (markIndex <= arrayList.size() - 1) {

                            arrayList.get(markIndex).setIsDone(true);

                            System.out.println("Nice! I've marked this task as done: \n");
                            System.out.println(arrayList.get(markIndex).toString() + "\n");
                        } else {
                            throw new HoshiException("Hoshi doesn't have such a task!");
                        }


                    } catch (HoshiException e) {
                        System.out.println(e.getMessage());
                    }

                }


                // unmark
            } else if (input.toLowerCase().startsWith("unmark")) {

                if (input.trim().length() < 7) {
                    System.out.println("Please specify the task number to unmark! \n");

                } else {

                    // split string
                    String[] splitInput = input.split(" ");

                    // get only the number from the 2nd half of the splitInput
                    int markIndex = Integer.parseInt(splitInput[1]) - 1;

                    try {

                        // if specified index is not out of bounds
                        if (markIndex <= arrayList.size() - 1) {

                            // set isDone to false
                            arrayList.get(markIndex).setIsDone(false);

                            System.out.println("OK, I've marked this task as not done yet: \n");
                            System.out.println(arrayList.get(markIndex).toString() + "\n");

                        } else {
                            throw new HoshiException("Hoshi doesn't have such a task! \n");
                        }

                    } catch (HoshiException e) {
                        System.out.println(e.getMessage());
                    }
                }


                // delete a task
            } else if (input.toLowerCase().startsWith("delete")) {

                if (input.length() < 7) {
                    System.out.println("Please specify the task number to delete! \n");

                } else {

                    // split string
                    String[] splitInput = input.split(" ");

                    // get only the number from the 2nd half of the splitInput
                    int markIndex = Integer.parseInt(splitInput[1]) - 1;

                    System.out.println("OK, Hoshi has removed ( " + arrayList.get(markIndex).getDesc() + " )! \n");

                    arrayList.remove(markIndex);
                }

                // add a Task
            } else if (input.toLowerCase().startsWith("add")) {

                if (input.trim().length() < 4) {

                    System.out.println("Please specify the task to add! E.g. Add {task to be added} \n");

                } else {
                    {

                    }
                    String[] splitInput = input.split(" ");


                    String taskInput = splitInput[1];

                    switch (taskInput) {
                    case "todo" -> {

                        System.out.println("Understood! What is your ToDo? ");
                        String desc = scanner.nextLine();

                        try {

                            if (desc.isEmpty()) {
                                throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                            }

                            Todo newToDo = new Todo(desc);
                            arrayList.add(newToDo);
                            System.out.println("added: " + input);

                        } catch (HoshiException e) {
                            System.out.println(e.getMessage());
                        }

                    }
                    case "deadline" -> {

                        System.out.println("Understood! What is your Deadline? ");
                        String desc = scanner.nextLine();

                        try {

                            if (desc.isEmpty()) {
                                throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                            }

                            System.out.println("When would you like your Deadline to be due by? ");

                            // take in input
                            String endTime = scanner.nextLine();

                            Deadline newDeadline = new Deadline(desc, endTime);
                            arrayList.add(newDeadline);
                            System.out.println("added: " + input);

                        } catch (HoshiException e) {
                            System.out.println(e.getMessage());
                        }


                    }
                    case "event" -> {

                        System.out.println("Understood! What is your Event? ");

                        String desc = scanner.nextLine();

                        try {
                            if (desc.isEmpty()) {
                                throw new HoshiException("Hoshi doesn't understand! Is input empty? \n");
                            }

                            System.out.println("When would you like your Event to start? ");

                            // take in input
                            String startTime = scanner.nextLine();

                            System.out.println("When would you like your Event to end? ");

                            // take in input
                            String endTime = scanner.nextLine();

                            Event newEvent = new Event(desc, startTime, endTime);
                            arrayList.add(newEvent);
                            System.out.println("added: " + input);


                        } catch (HoshiException e) {
                            System.out.println(e.getMessage());
                        }


                    }
                    default ->

                        // in event of invalid input
                            System.out.println("Hoshi doesn't understand! Please try again with the above keywords");
                    }

                }

            } else {

                System.out.println("Hoshi doesn't understand, try a different input? ");
                System.out.println("____________________________________________________________");

            }


        }

        writeToFile("./data/Hoshi.txt",arrayList);
        bye();


    }


    /**
     * Gets tasks from hoshi txt file if user is not new else greets the user.
     *
     * @param filePath String filepath that contains the path of the hoshi txt file.
     * @param arrayList ArrayList of 3 types of tasks to be retrieved from hoshi txt file.
     */
    private static void getFileContents(String filePath, ArrayList<Task> arrayList) throws FileNotFoundException {

        File file = new File(filePath);

        try {
            Scanner scanner = new Scanner(file);

            //Deadline, D, deadline1, 1200

            while (scanner.hasNext()) {

                String line = scanner.nextLine();
                String[] parts = line.split(", ");

                String taskType = parts[0];
                Boolean isDone = Boolean.FALSE;
                if (Objects.equals(parts[1], "D")) {
                    isDone = Boolean.TRUE;
                }
                String description = parts[2];

                switch (taskType) {
                case "Todo":

                    Todo todo = new Todo(description, isDone);
                    arrayList.add(todo);
                    break;

                case "Deadline":

                    String deadlineEndTime = parts[3];
                    Deadline deadline = new Deadline(description, isDone, deadlineEndTime);
                    arrayList.add(deadline);
                    break;

                case "Event":

                    String endTime = parts[3];
                    String startTime = parts[4];
                    Event event = new Event(description, isDone, endTime, startTime);
                    arrayList.add(event);
                    break;

                default:

                    System.out.println("Hoshi is not aware of this task type: " + taskType + "!");
                    break;
                }

            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Hoshi has detected a new user! Welcome!");
        }

    }

    /**
     * Writes tasks added and retrieved during the program to hoshi txt file.
     *
     * @param filePath String filepath that contains the path of the hoshi txt file.
     * @param arrayList ArrayList of 3 types of tasks to be written to hoshi txt file.
     */
    private static void writeToFile(String filePath, ArrayList<Task> arrayList) throws IOException {

        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (Task task : arrayList) {

                // Deadline(TaskType), T(D = Done/ ND = Not Done), Description, endTime, startTime
                String taskType = task.getClass().getName();
                String isDone = task.getStatusIcon();
                if (Objects.equals(isDone, " ")) {
                    isDone = "ND";
                } else {
                    isDone = "D";
                }
                String description = task.getDesc();

                String additionalFields = "";

                if (taskType.equals("Deadline")) {

                    Deadline deadline = ((Deadline) task);
                    additionalFields = ", " + deadline.getEndTime();

                } else if (taskType.equals("Event")) {

                    Event event = ((Event) task);
                    additionalFields = ", " + event.getEndTime() + ", " + event.getStartTime();

                }

                String textToAdd = taskType + ", " + isDone + ", " + description + additionalFields + System.lineSeparator();
                fileWriter.write(textToAdd);
            }
            fileWriter.close();


        } catch (IOException e) {
            System.out.println("Hoshi has an error! " + e.getMessage());
        }

    }

    /**
     * Prints bye message when user terminates the program
     *
     */
    static void bye() {

        System.out.println("""
                Bye. Hope to see you again soon!\s
                ____________________________________________________________
                """);
    }


}
