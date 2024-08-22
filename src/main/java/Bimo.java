import java.util.Scanner;
public class Bimo {
    public static String name = "Bimo";
    public static String line = "    " + "___________________________________";
    public static Scanner scanner = new Scanner(System.in);
    public static int length = 0;
    public static Task[] list = new Task[100];
    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("    " + String.format("Hello! I'm %s", name));
        System.out.println("    " + "What can I do for you?");
        System.out.println(line);
        String command = scanner.nextLine();

        while (!command.toLowerCase().equals("bye")) {
            System.out.println(line);
            String action = command.split(" ")[0].toLowerCase();
            if (action.equals("list") && length > 0) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < length; i++) {
                    String message = String.format("    %d. %s", i + 1, list[i].toString());
                    System.out.println(message);
                }

            } else if (action.equals("list")) {
                System.out.println("    No tasks added");

            } else if (action.equals("mark")) {
                int index = Integer.valueOf(command.toLowerCase().split(" ")[1]) - 1;
                try {
                    updateList(true, index);
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("       " + list[index].toString());
                } catch(IllegalArgumentException e) {
                    System.out.println("    Task selected not in list");
                }

            } else if (action.equals("unmark")) {
                int index = Integer.valueOf(command.toLowerCase().split(" ")[1]) - 1;
                try {
                    updateList(false, index);
                    System.out.println("    OK, I've marked this task as not done yet:");
                    System.out.println("       " + list[index].toString());
                } catch (IllegalArgumentException e) {
                    System.out.println("    Task selected not in list");
                }


            } else if (action.equals("todo") ||
                        action.equals("deadline") ||
                        action.equals("event") ) {
                if (action.equals("todo")) {
                    try {
                        Task task = new ToDo(getDetails(command));
                        System.out.println("    Got it. I've added this task:");
                        Bimo.list[length] = task;
                        length += 1;
                        System.out.println("        "+ task.toString());
                        String word = length == 1 ? "task" : "tasks";
                        System.out.println(String.format("    Now you have %d %s in the list.", length, word));

                    } catch (MissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    }

                } else if (action.equals("deadline")) {
                    String[] temp = command.split("/");
                    try {
                        String details = getDetails(temp[0]);
                        String dueDate = processDate(true, temp);
                        Task task = new Deadline(details, dueDate);
                        System.out.println("    Got it. I've added this task:");
                        System.out.println("        "+ task.toString());
                        Bimo.list[length] = task;
                        length += 1;
                        String word = length == 1 ? "task" : "tasks";
                        System.out.println(String.format("    Now you have %d %s in the list.", length, word));
                    } catch (MissingDescriptionException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidDateException e) {
                        System.out.println(e.getMessage());
                    }

                } else {
                    try {
                        String[] temp = command.split("/");
                        String details = getDetails(temp[0]);
                        String start = processDate(true, temp);
                        String end = processDate(false, temp);
                        Task task = new Event(details, start ,end);
                        System.out.println("    Got it. I've added this task:");
                        Bimo.list[length] = task;
                        length += 1;
                        System.out.println("        "+ task.toString());
                        String word = length == 1 ? "task" : "tasks";
                        System.out.println(String.format("    Now you have %d %s in the list.", length, word));
                    } catch (MissingDescriptionException e){
                        System.out.println(e.getMessage());
                    } catch (InvalidDateException e) {
                        System.out.println(e.getMessage());
                    }

                }
            } else {
                System.out.println("    Invalid command, please try another command");
            }
            System.out.println(line);
            command = scanner.nextLine();
        }

        System.out.println("    " +"Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    /**
     *
     * @param complete boolean value that represents whether task is completed
     * @param index position of task in list to change status
     * @throws IllegalArgumentException
     */
    public static void updateList(boolean complete, int index) throws IllegalArgumentException {
        if (index < 0 || index >= length) {
            throw new IllegalArgumentException();
        } else if(complete) {
            list[index].markCompleted();
        } else {
            list[index].markUnCompleted();
        }
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

}
