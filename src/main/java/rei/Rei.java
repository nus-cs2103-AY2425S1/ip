package rei;

import javax.sound.sampled.FloatControl;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


/**
 * Main class for Rei bot.
 */
public class Rei {
    /**
     * Runs the bot.
     * Loads database if exist.
     * @param args
     * @throws Exception
     */
    private static final String LOGO = "  ____  _____ ___ \n"
            + " |  _ \\| ____|_ _|\n"
            + " | |_) |  _|  | | \n"
            + " |  _ <| |___ | | \n"
            + " |_| \\_\\_____|___|\n";
    private static final Path FILE_PATH = Path.of("/Users/macbookpro/Documents/CS2103T/rei/rei.txt");

    public static void main(String[] args) throws Exception {
        System.out.println("Annyeong! I'm\n" + LOGO);
        System.out.println("Here's your list of tasks!");

        try {
            String fileContent = Files.readString(FILE_PATH);
            // Printing the content inside the file
            System.out.println(fileContent);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot read file : " + e.getMessage());
        }


        System.out.println("What can I do for you?");
        System.out.println("-----------YOU------------");


        List<Task> listOfTasks = new ArrayList<>();

        List<String> storedTasks = Files.readAllLines(FILE_PATH);
        String taskPrompt;
        for (int i = 0; i < storedTasks.size(); i++) {
            taskPrompt = storedTasks.get(i);
            if (taskPrompt.startsWith("T ")) {
                listOfTasks.add(Task.createToDo(taskPrompt.substring(8)));
                if (taskPrompt.charAt(4) == '1') {
                    listOfTasks.get(i).markAsDone();
                }
            } else if (taskPrompt.startsWith("D ")) {
                listOfTasks.add(Task.createDeadline(taskPrompt.substring(8, taskPrompt.lastIndexOf('|')),
                        LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf('|') + 2))));
                if (taskPrompt.charAt(4) == '1') {
                    listOfTasks.get(i).markAsDone();
                }
            } else { // Event

                listOfTasks.add(Task.createEvent(taskPrompt.substring(8, taskPrompt.lastIndexOf('|')),
                        LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf('|') + 2, taskPrompt.lastIndexOf("to") - 1)),
                        LocalDateTime.parse(taskPrompt.substring(taskPrompt.lastIndexOf("to") + 3))));
            }
        }


        Scanner scanner = new Scanner(System.in);

        while (!scanner.hasNext("annyeong") && !scanner.hasNext("Annyeong")) {
            if (scanner.hasNext("list")) {
                System.out.println("-----------REI♥-----------");
                System.out.println("Here are the tasks in your list: ");


                for (int i = 0; i < listOfTasks.size(); i++) {
                    System.out.println((i + 1) + ". " + listOfTasks.get(i));
                }
                scanner.nextLine();
            } else if (scanner.hasNext("mark")) {
                System.out.println("-----------REI♥-----------");
                scanner.next();

                // Read the rest of the line after "mark"
                String prompt = scanner.nextLine().trim();

                // Check if the rest of the line is an integer
                if (prompt.isEmpty() || !prompt.matches("\\d+")) {
                    System.out.println("State the task number.");
                    System.out.println("-----------YOU------------");
                    continue;
                }

                int id = Integer.parseInt(prompt);
                if (id <= listOfTasks.size() && id > 0) {
                    listOfTasks.get(id - 1).markAsDone();
                    System.out.println("Okay! I've marked this task as done: ");
                    System.out.println(listOfTasks.get(id - 1));
                } else {
                    System.out.println("No task found. Please retry!");
                }

            } else if (scanner.hasNext("unmark")) {
                System.out.println("-----------REI♥-----------");
                scanner.next();

                // Read the rest of the line after "unmark"
                String prompt = scanner.nextLine().trim();

                // Check if the rest of the line is an integer
                if (prompt.isEmpty() || !prompt.matches("\\d+")) {
                    System.out.println("State the task number.");
                    System.out.println("-----------YOU------------");
                    continue;
                }

                int id = Integer.parseInt(prompt);
                if (id <= listOfTasks.size() && id > 0) {
                    listOfTasks.get(id - 1).markAsNotDone();
                    System.out.println("Okay! I've marked this task as not done yet: ");
                    System.out.println(listOfTasks.get(id - 1));
                } else {
                    System.out.println("No task found. Please retry!");
                }
            } else if (scanner.hasNext("todo")
                        || scanner.hasNext("deadline")
                        || scanner.hasNext("event")) {

                String prompt = scanner.nextLine();
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                System.out.println("-----------REI♥-----------");

                if (prompt.startsWith("todo")) {
                    if (isAllWhitespace(prompt.substring(4))) {
                        System.out.println("Task is empty. Please state the task name.");
                        System.out.println("-----------YOU------------");
                        continue;
                    }
                    listOfTasks.add(Task.createToDo(prompt.substring(5)));
                } else if (prompt.startsWith("deadline")) {
                    if (isAllWhitespace(prompt.substring(8))) {
                        System.out.println("Task is empty. Please state the task and deadline.");
                        System.out.println("-----------YOU------------");
                        continue;
                    }

                    if (prompt.indexOf("/by") == -1) {
                        System.out.println("When is the deadline? Please state the task with the deadline.");
                        System.out.println("-----------YOU------------");
                        continue;
                    }

                    try {
                        listOfTasks.add(Task.createDeadline(prompt.substring(9, prompt.indexOf("/by")),
                                LocalDateTime.parse(prompt.substring(prompt.indexOf("/by") + 4))));
                    } catch (DateTimeParseException e) {
                        System.out.println("Wrong date format");
                        continue;
                    }


                } else { // event
                    if (isAllWhitespace(prompt.substring(5))) {
                        System.out.println("Event is empty. Please state the event and time range.");
                        System.out.println("-----------YOU------------");
                        continue;
                    }

                    if (prompt.indexOf("/from") == -1 || prompt.indexOf("/to") == -1) {
                        System.out.println("State the START and FINISH time of the event");
                        System.out.println("-----------YOU------------");
                        continue;
                    }

                    try {
                        listOfTasks.add(Task.createEvent(prompt.substring(6, prompt.indexOf("/from")),
                                        LocalDateTime.parse(prompt.substring(prompt.indexOf("/from") + 6, prompt.indexOf("/to") - 1)),
                                        LocalDateTime.parse(prompt.substring(prompt.indexOf("/to") + 4))));
                    } catch (DateTimeParseException e) {
                        System.out.println("Input date in the format of yyyy-mm-dd");
                        continue;
                    }

                }
                System.out.println("Got it. I've added this task:");
                System.out.println("    " + listOfTasks.get(listOfTasks.size() - 1));
                System.out.println(String.format("Now you have %d tasks in the list.", listOfTasks.size()));

            } else if (scanner.hasNext("delete")) {
                System.out.println("-----------REI♥-----------");
                scanner.next();

                // Read the rest of the line after "delete"
                String prompt = scanner.nextLine().trim();

                // Check if the rest of the line is an integer
                if (prompt.isEmpty() || !prompt.matches("\\d+")) {
                    System.out.println("State the task number.");
                    System.out.println("-----------YOU------------");
                    continue;
                }

                int id = Integer.parseInt(prompt);
                if (id <= listOfTasks.size() && id > 0) {
                    listOfTasks.remove(id - 1);
                    System.out.println("Okay! I've deleted this task.");
                } else {
                    System.out.println("No task found. Please retry!");
                }
            } else {
                System.out.println("-----------REI♥-----------");
                System.out.println("I don't understand what you want me to do.");
                System.out.println("-----------YOU------------");
                scanner.nextLine();
                continue;
            }

            System.out.println("-----------YOU------------");
        }

        scanner.close();

        String dataStorage = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            dataStorage += listOfTasks.get(i).storeData() + "\n";
        }
        Files.writeString(FILE_PATH, dataStorage);

        System.out.println("-----------REI♥-----------");
        System.out.println("Annyeong. Hope to see you soon.");

    }

    public static boolean isAllWhitespace(String input) {
        return input.replaceAll("\\s+","").isEmpty();
    }

}
