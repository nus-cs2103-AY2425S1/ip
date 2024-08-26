import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a Jude, the personal assistant chatbot.
 * <p>
 * This class helps a person to keep track of various things.
 *
 * @author Judy Park
 **/
public class Jude {
    public static void main(String[] args) {

        // Initialize the variables
        String name = "Jude";
        List<Task> list = new ArrayList<>();
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new Parser();
        String filePath = "data/jude.txt";

        // Load from the save file
        try {
            loadFile(filePath, list);
        } catch (JudeException je) {
            System.out.println(je.getMessage());
            je.printStackTrace();
            return;
        }

        // Initiate the chat
        System.out.println("Hello I'm " + name);
        System.out.println("What can I do for you, poyo?");

        while (true) {
            String userInput;

            // Save the file
            try {
                saveFile(filePath, createTaskListText(list));
            } catch (JudeException je) {
                System.out.println(je);
                continue;
            }

            // Read an input from the user
            try {
                userInput = bi.readLine();
            } catch (IOException ie) {
                System.out.println("IO operation was failed or interrupted. Please try again, poyo...");
                continue;
            }

            // Handle Invalid Input Exception
            try {
                parser.setUpUserInput(userInput);
            } catch (JudeException je) {
                System.out.println(je.getMessage());
                continue;
            }

            String command = parser.getCommand();

            if (command.equals("bye")) {
                break;

            } else if (command.equals("list")) {
                printTaskList(list);

            } else if (command.equals("mark")) {

                String[] descriptions = parser.getDescriptions();
                int index;

                // Handle if number input is not valid
                try {
                    index = Integer.parseInt(descriptions[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("The command for mark should be followed by a number. Please try again, poyo");
                    continue;
                }

                // Handle if index is within the valid list size
                if (index >= list.size() || index < 0) {
                    System.out.println("The task number provided is not valid."
                            + " Please provide your instruction again., poyo...");
                    continue;
                }

                // Mark the task as done
                Task task = list.get(index);
                task.markAsDone();
                System.out.printf("Poyo! I've marked this task as done:\n%s\n", task.toStringDetails());

            } else if (command.equals("unmark")) {

                String[] descriptions = parser.getDescriptions();
                int index;

                // Handle if number input is not valid
                try {
                    index = Integer.parseInt(descriptions[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("The command for unmark should be followed by a number. Please try again, poyo");
                    continue;
                }

                // Handle if index is within the valid list size
                if (index >= list.size() || index < 0) {
                    System.out.println("The task number provided is not valid."
                            + " Please provide your instruction again, poyo...");
                    continue;
                }

                // Unmark the task as done
                Task task = list.get(index);
                task.unmarkAsDone();
                System.out.printf("Poyo! I've unmarked this task as not done:\n%s\n", task.toStringDetails());

            } else if (command.equals("delete")) {

                String[] descriptions = parser.getDescriptions();
                int index;

                // Handle if number input is not valid
                try {
                    index = Integer.parseInt(descriptions[0]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("The command for delete should be followed by a number. Please try again, poyo");
                    continue;
                }

                // Handle if index is within the valid list size
                if (index >= list.size() || index < 0) {
                    System.out.println("The task number provided is not valid."
                            + " Please provide your instruction again, poyo...");
                    continue;
                }

                Task task = list.get(index);
                list.remove(index);
                System.out.printf("Poyo! I've deleted this task:\n%s\n", task.toStringDetails());

                // Check if the command is to add a task
            } else {
                Task task;
                String[] descriptions = parser.getDescriptions();
                try {
                    if (command.equals("to-do")) {
                        task = new Todo(descriptions[0]);
                    } else if (command.equals("deadline")) {
                        task = new Deadline(descriptions[0], descriptions[1]);
                    } else if (command.equals("event")) {
                        task = new Event(descriptions[0], descriptions[1], descriptions[2]);
                    } else {  // Invalid input
                        continue;
                    }
                    list.add(task);
                    System.out.println("Poyo! added: " + task.toStringDetails());
                } catch (JudeException je) {
                    System.out.println(je.getMessage());
                }
            }
        }
        // Terminate the chat
        System.out.println("Poyo. Hope to see you again soon!");
    }

    private static void printTaskList(List<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), list.get(i).toStringDetails());
        }
    }

    private static String createTaskListText(List<Task> list) {
        String text = "";
        for (Task task : list) {
            text += task.toFileFormat() + "\n";
        }
        return text;
    }

    private static void saveFile(String filePath, String textToAdd) throws JudeException {
        File save = new File(filePath);

        // Check if file exists, create a file if it doesn't
        if (!save.exists()) {
            try {
                save.createNewFile();
            } catch (IOException ie) {
                throw new JudeException("An error has occurred while creating a save file.");
            }
        }

        // Write to the save file
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException ie) {
            throw new JudeException("IOException has occurred while writing to a save file.");
        }
    }

    private static void loadFile(String filePath, List<Task> list) throws JudeException {
        File file = new File(filePath);

        // Check if file exists
        if (!file.exists()) {
            System.out.println("No save file found. Starting with an empty task list.");
            return;
        }

        Scanner fileReader;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException fe) {
            throw new JudeException("File is not found. Starts with an empty list.");
        }


        while (fileReader.hasNextLine()) {
            String[] split = fileReader.nextLine().split(" \\| ");
            String errorMessage = "There was an error while loading the file.";

            // Handles file format with no Type letter
            if (split.length < 3) {
                throw new JudeException(errorMessage);
            }
            boolean isDone = split[1].equals("1");
            String description = split[2];

            switch (split[0]) {
            case "T":
                if (split.length != 3) {
                    throw new JudeException(errorMessage);
                }
                list.add(new Todo(description, isDone));
                break;
            case "D":
                if (split.length != 4) {
                    throw new JudeException(errorMessage);
                }
                String[] dateAndTime = split[3].split(" ");
                list.add(new Deadline(description, dateAndTime[0], dateAndTime[1], isDone));
                break;
            case "E":
                if (split.length != 5) {
                    throw new JudeException(errorMessage);
                }
                String[] fromDateAndTime = split[3].split(" ");
                String[] toDateAndTime = split[4].split(" ");

                list.add(new Event(
                        description, fromDateAndTime[0], fromDateAndTime[1],
                        toDateAndTime[0], toDateAndTime[1], isDone));
                break;
            default:
                break;
            }
        }
    }
}


