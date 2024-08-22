import java.util.Scanner;
import java.util.ArrayList;
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
                    } catch (MissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case DEADLINE:
                    String[] temp = input.split("/");
                    try {
                        String details = getDetails(temp[0]);
                        String dueDate = processDate(true, temp);
                        Task task = new Deadline(details, dueDate);
                        printTaskInfo(task);
                    } catch (MissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidDateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case EVENT:
                    try {
                        String[] temp2 = input.split("/");
                        String details = getDetails(temp2[0]);
                        String start = processDate(true, temp2);
                        String end = processDate(false, temp2);
                        Task task = new Event(details, start, end);
                        printTaskInfo(task);
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
        return String.join(" ", temp);
    }

    /**
     *
     * @param first To indicate whether its start or end date
     * @param array An array of strings split by /
     * @return the corresponding date portion of string
     * @throws InvalidDateException
     */
    public static String processDate(boolean first, String[] array) throws InvalidDateException {
        if (array.length <= 1) {
            throw new InvalidDateException("    Please provide a date using /");
        } else if (!first && array.length <= 2) {
            throw new InvalidDateException("    Please provide a due date");
        }
        String[] temp = first ? array[1].split(" ") : array[2].split(" ");
        if (temp.length <= 1) {
            throw new InvalidDateException("    Please provide by or from or to before date");
        }
        temp[0] = "";
        return String.join(" ", temp);
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
}
