import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Scanner;


import java.io.File;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Bimo {
    public static String name = "Bimo";
    public static String line = "    " + "___________________________________";
    public static Scanner scanner = new Scanner(System.in);

    public static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("    " + String.format("Hello! I'm %s", name));
        System.out.println("    " + "What can I do for you?");
        System.out.println(line);
        String input = scanner.nextLine();

        boolean botIsActive = true;
        while (botIsActive) {
            try {
                File dataFile = new File("ip/data/Bimo.txt");
                Scanner fileReader = new Scanner(dataFile);
            } catch (InvalidPathException e) {
                System.out.println("File path not found, unable to run chatbot due to error");
                 break;
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                System.out.println("File not found, unable to run chatbot due to error");
                break;
            }
            Command command = null;
            String action = input.split(" ")[0].toLowerCase();
            try {
                command = getCommand(action);
            } catch (InvalidCommandException e) {
                System.out.println(line);
                System.out.println(e.getMessage());
                System.out.println(line);
                input = scanner.nextLine();
                continue;
            }
            System.out.println(line);
            switch (command) {
                case LIST:
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        String message = String.format("    %d. %s", i + 1, list.get(i).toString());
                        System.out.println(message);
                    }
                    break;
                case MARK:
                    //need to handle if no index given
                    //need to handle if already marked
                    try {
                        int index1 = updateList(true, input);
                        System.out.println("    Good job! I've marked this task as done:");
                        System.out.println("       " + list.get(index1).toString());
                    } catch(IllegalArgumentException e) {
                        System.out.println("    Task selected not found in list");
                    }
                    break;
                case UNMARK:
                    try {
                        int index2 = updateList(false, input);
                        System.out.println("    OK, I've marked this task as not done yet:");
                        System.out.println("       " + list.get(index2).toString());
                    } catch (IllegalArgumentException e) {
                        System.out.println("    Task selected not found in list");
                    }
                    break;
                case TODO:
                    try {
                        Task task = new ToDo(getDetails(input));
                        printTaskInfo(task);
                        writeToFile(task);
                    } catch (MissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    String[] temp = input.split("/by ");
                    try {
                        String details = getDetails(temp[0]);
                        String dueDate = processDate(true, true, temp);
                        Task task = new Deadline(details, dueDate);
                        printTaskInfo(task);
                        writeToFile(task);
                    } catch (MissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidDateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        String[] temp2 = input.split("/from ");
                        String details = getDetails(temp2[0]);
                        String start = processDate(false, false, temp2);
                        String end = processDate(false, true, temp2);
                        Task task = new Event(details, start, end);
                        printTaskInfo(task);
                        writeToFile(task);
                    } catch (MissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidDateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DELETE:
                    int index3 = Integer.valueOf(input.split(" ")[1]) - 1;
                    try {
                        Task task = delete(index3);
                        System.out.println("    Noted. I've removed this task:");
                        System.out.println("        " + task.toString());
                        String word = list.size() == 1 ? "task" : "tasks";
                        System.out.println(String.format("    Now you have %d %s in the list.", list.size(), word));

                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case BYE:
                    botIsActive = false;
                    System.out.println("    " +"Bye!!! Thanks for chatting!");
                    break;
            }
            System.out.println(line);
            if (botIsActive) {
                input = scanner.nextLine();
            }
        }
    }

    /**
     *
     * @param complete boolean value that represents whether task is completed
     * @param input String value of input typed by user
     * @throws IllegalArgumentException
     */
    public static int updateList(boolean complete, String input) throws IllegalArgumentException {
        int index = Integer.valueOf(input.toLowerCase().split(" ")[1]) - 1;
        if (index < 0 || index >= list.size()) {
            throw new IllegalArgumentException();
        } else if(complete) {
            list.get(index).markCompleted();
        } else {
            list.get(index).markUnCompleted();
        }
        return index;
    }
    /**
     *
     * @param mixed String that consists of action and description
     * @return Only the description but with white space in front
     * @throws MissingDescriptionException
     */
    public static String getDetails(String mixed) throws MissingDescriptionException {
        String[] temp = mixed.split(" ");
        if (temp.length <= 1) {
            throw new MissingDescriptionException("    Please key in description for your task");
        }
        temp[0] = "";
        return removeSpace(temp);
    }

    /**
     *
     * @param isDeadline To indicate whether it has one or two dates
     * @param isEnd To indicate whether to return first date or second
     * @param array An array of strings split using delimter
     * @return the corresponding date portion of string
     * @throws InvalidDateException
     */
    public static String processDate(boolean isDeadline, boolean isEnd,
        String[] array) throws InvalidDateException {
        if (array.length <= 1) {
            String type = isDeadline ? " /by" : " /from .... /to";
            throw new InvalidDateException("    Please provide a date using" + type);
        } else if (!isDeadline) {
            array = array[1].split(" /to ");
            if (array.length <= 1) {
                throw new InvalidDateException("    Please provide a deadline using /to");
            }

        }
        return isEnd ? array[1] : array[0];
    }

    /**
     *
     * @param index Position of element to remove
     * @throws IllegalArgumentException
     */
    public static Task delete(int index) throws IllegalArgumentException {
        if (list.size() <= 0 || index < 0 || index > list.size()) {
            throw new IllegalArgumentException("Task not found in list");
        }
        Task task = list.remove(index);
        return task;
    }

    /**
     *
     * @param action String representation of commands
     * @return A command enum object
     * @throws InvalidCommandException
     */
    public static Command getCommand(String action) throws InvalidCommandException {
        String cmd = action.toLowerCase();
        if (cmd.equals("list")) {
            return Command.LIST;
        } else if (cmd.equals("mark")) {
            return Command.MARK;
        } else if (cmd.equals("unmark")) {
            return Command.UNMARK;
        } else if (cmd.equals("todo")) {
            return Command.TODO;
        } else if (cmd.equals("event")) {
            return Command.EVENT;
        } else if (cmd.equals("deadline")) {
            return Command.DEADLINE;
        } else if (cmd.equals("delete")) {
            return Command.DELETE;
        } else if (cmd.equals("bye")){
            return Command.BYE;
        } else {
            throw new InvalidCommandException("    Sorry, I do not understand you \n" +
                    "    as this is not a valid command");
        }
    }

    /**
     * Abstraction to remove duplicated code
     * @param task Task to be added into list
     */
    public static void printTaskInfo(Task task) {
        System.out.println("    Got it. I've added this task:");
        Bimo.list.add(task);
        System.out.println("        " + task.toString());
        String word = list.size() == 1 ? "task" : "tasks";
        System.out.println(String.format("    Now you have %d %s in the list.", list.size(), word));
    }

    /**
     * Format the type of task, status, description and dates into
     * text format type|status|description|date1/date2
     * Split by | , if length == 2 means todo,
     * if length == 3 split by /, if length == 2 means event
     * @param task Task object inside list
     * @return Formatted text that can be written into data file
     */
    public static String convertTaskToText(Task task) {
        String text = "";
        String description = task.getTaskText();
        if (task instanceof ToDo) {
            text = "T|" + description;
        } else if (task instanceof Deadline) {
            //safe to cast since type checking is done
            text = "D|" + description + ((Deadline) task).getDateAsText();
        } else {
            text = "E|" + description + ((Event) task).getDatesAsText();
        }
        return text;
    }

    /**
     *
     * @param input Array of words formed from splitting by " "
     * @return String with empty space removed in front
     */
    public static String removeSpace(String[] input) {
        String [] temp = new String[input.length - 1];
        for (int i = 1; i < input.length; i++) {
            temp[i - 1] = input[i];
        }
        return String.join(" ", temp);
    }

    /**
     * Converts description of task to text in the form
     * type|status|description|date1|date2
     * @param task Task inside the list
     */
    public static void writeToFile(Task task) {
        String text = convertTaskToText(task) + System.lineSeparator();
        try {
            FileWriter writer = new FileWriter("ip/data/Bimo.txt", true);
            writer.write(text);
            writer.close();

        } catch (IOException e) {
            System.out.println("Enable to write to file for task " + task.toString());
        }
    }
}
