import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.*;
import exceptions.*;

public class Bob {
    
    public static void main(String[] args) throws CommandNotFoundException, MissingParamsException, PositionException {
        // init items
        ArrayList<Task> taskList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int markPos;
        String skeleton = """
                ____________________________________________________________ \s
                 Hello! I'm Bob \s
                 What can I do for you? \s
                ____________________________________________________________ \s
                """;
        String blankline = "____________________________________________________________ \s";

        // starting
        System.out.println(skeleton);
        boolean exit = false;

        // main logic loop
        while (!exit) {
            System.out.print("Text: ");
            String input = scanner.nextLine();
            String cmd = input.split(" ", 2)[0];
            String rest = input.split(" ", 2).length > 1 ? input.split(" ", 2)[1] : "";
            String[] splitString;

            // check for the input case
            try {
                switch (cmd.toLowerCase()) {
                    case "bye":
                        exit = true;
                        break;

                    case "list":
                        System.out.println("Here are the tasks in your list:" + "\n"
                                + getList(taskList) + blankline);
                        break;

                    case "mark":
                        markPos = Integer.parseInt(rest) - 1;
                        taskList.get(markPos).setDone();
                        System.out.println("Nice! I've marked this task as done:" + "\n"
                                + taskList.get(markPos) + "\n" + blankline);
                        updateDataFile(getList(taskList));
                        break;

                    case "unmark":
                        markPos = Integer.parseInt(rest) - 1;
                        taskList.get(markPos).setUndone();
                        System.out.println("OK, I've marked this task as not done yet:" + "\n"
                                + taskList.get(markPos) + "\n" + blankline);
                        updateDataFile(getList(taskList));
                        break;

                    case "todo":
                        if (Objects.equals(rest, "")) throw new MissingParamsException("todo");
                        taskList.add(new Todo(rest));
                        System.out.println(String.format(" Got it. I've added this task:" + "\n"
                                + taskList.get(taskList.size() - 1) + "\n"
                                + "Now you have %s tasks in the list", taskList.size()) + "\n" + blankline);
                        updateDataFile(getList(taskList));
                        break;

                    case "event":
                        splitString = rest.split("/from|/to");
                        if (Objects.equals(splitString[0], "") || Objects.equals(splitString[1], "")) {
                            throw new MissingParamsException("event");
                        }
                        taskList.add(new Event(splitString[0], "from:" + splitString[1]
                                + "to:" + splitString[2]));
                        System.out.println(String.format(" Got it. I've added this task:" + "\n"
                                + taskList.get(taskList.size() - 1) + "\n"
                                + "Now you have %s tasks in the list", taskList.size()) + "\n" + blankline);
                        updateDataFile(getList(taskList));
                        break;

                    case "deadline":
                        splitString = rest.split("/by");
                        if (Objects.equals(splitString[0], "") || Objects.equals(splitString[1], "")) {
                            throw new MissingParamsException("deadline");
                        }
                        taskList.add(new Deadline(splitString[0], splitString[1]));
                        System.out.println(String.format(" Got it. I've added this task:" + "\n"
                                + taskList.get(taskList.size() - 1) + "\n"
                                + "Now you have %s tasks in the list", taskList.size()) + "\n" + blankline);
                        updateDataFile(getList(taskList));
                        break;

                    case "delete":
                        markPos = Integer.parseInt(rest) - 1;
                        if (markPos >= taskList.size() || markPos < 0) {
                            throw new PositionException(markPos);
                        }
                        Task currTask = taskList.get(markPos);
                        taskList.remove(markPos);
                        System.out.println(String.format("Noted. I've removed this task:" + "\n"
                                + currTask + "\n"
                                + "Now you have %s tasks in the list", taskList.size()) + "\n" + blankline);
                        updateDataFile(getList(taskList));
                        break;
                        
                    default:
                        throw new CommandNotFoundException();
                }
            } catch (CommandNotFoundException | MissingParamsException | PositionException c) {
                System.out.println(c + "\n" + blankline);
            }

        }
        scanner.close();
        System.out.println(blankline + "\n" + "Bye. Hope to see you again soon!");

    }

    public static String getList(ArrayList<Task> taskList) {
        String retString = "";
        for (int i = 0; i < taskList.size(); i++) {
            retString += (i + 1) + ". " + taskList.get(i) + "\n";
        }
        return retString;
    }

    /**
     * Updates the data file whenever tasklist is updated
     * @param s The string to write to the data file
     */
    public static void updateDataFile(String s) {
        try {
            File dataFile = new File("src/main/data/dataFile.txt");

            // check for file
            if (!dataFile.exists()) {
                if (dataFile.createNewFile()) {
                    System.out.println("File created: " + dataFile.getName());
                } else {
                    System.out.println("Failed to create the file.");
                }
            } else {
                FileWriter writer = new FileWriter("src/main/data/dataFile.txt");
                writer.write(s);
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
