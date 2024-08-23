import java.util.ArrayList;
import java.util.Scanner;

public class Edith {
    private static final String prompt = "Your next instruction: ";

    private final String lineBreak = "\n_______________________________________________________________________\n";
    private final String greeting = "Hello! I am EDITH, your personal chatbot companion:)"
            + "\nHow may I assist you?";
    private final String indentation = "    ";
    private final String exit = "It has been my pleasure helping you. Hope to see you again soon!";

    private ArrayList<Task> listOfTasks = new ArrayList<>();

    public Edith() {
    }

    public void greet() {
        System.out.println(lineBreak + greeting + lineBreak);
    }

    public void exit() {
        System.out.println(indentation + exit + lineBreak);
    }

    public void add(String string) {
        Task task;

        if (string.startsWith("todo ")) {
            String taskString = string.substring(5);
            if (taskString.isEmpty()) {
                throw new EdithException("Invalid task as no description for this todo was provided.");
            }

            task = new ToDo(taskString);
            addHelper(task);
        }
        if (string.startsWith("deadline ")) {
            String[] parts = string.split(" /by ");
            String taskString = parts[0].substring(9).trim();
            String dueDate = parts[1].trim();
            if (taskString.isEmpty() || dueDate.isEmpty()) {
                throw new EdithException("Invalid task as no description or due date for this deadline was provided.");
            }

            task = new Deadline(taskString, dueDate);
            addHelper(task);
        }
        if (string.startsWith("event ")) {
            String[] parts = string.split(" /from | /to ");
            String taskString = parts[0].substring(6).trim();
            String startTime = parts[1].trim();
            String endTime = parts[2].trim();
            if (taskString.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                throw new EdithException("Invalid task as no description or start time or end time for this event was provided.");
            }

            task = new Event(taskString, startTime, endTime);
            addHelper(task);
        }
    }

    public void addHelper(Task task) {
        listOfTasks.add(task);
        int numOfTasks = listOfTasks.size();
        String message1 = "Got it. I've added this task:";
        String message2 = "There are now " + numOfTasks + " tasks in your list.";

        System.out.println(indentation + message1);
        System.out.println(indentation + task);
        System.out.println(indentation + message2 + lineBreak);
    }

    public void delete(int index) {
        String deletedTask = listOfTasks.get(index).toString();
        listOfTasks.remove(index);

        int numOfTasks = listOfTasks.size();
        String message1 = "Certainly. I've removed this task:";
        String message2 = "There are now " + numOfTasks + " tasks in your list.";

        System.out.println(indentation + message1);
        System.out.println(indentation + deletedTask);
        System.out.println(indentation + message2 + lineBreak);
    }

    public void list() {
        String emptyListMessage = "Great news, you have no outstanding task! Have a break!";
        String filledListMessage = "Here are the tasks in your list:";

        if (listOfTasks.isEmpty()) {
            System.out.println(indentation + emptyListMessage + lineBreak);
        } else {
            System.out.println(indentation + filledListMessage);
            for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.println(indentation + (i+1) + ") " + listOfTasks.get(i));
            }
            System.out.println(lineBreak);
        }
    }

    public void mark(int index) {
        int num = index + 1;
        String message = "Alright, great job! I've marked task " + num + " as done:";
        listOfTasks.get(index).markTaskDone();

        System.out.println(indentation + message);
        System.out.println(indentation + listOfTasks.get(index) + lineBreak);
    }

    public void unmark(int index) {
        int num = index + 1;
        String message = "Sure, I've marked task " + num + " as not done yet:";
        listOfTasks.get(index).markTaskUndone();

        System.out.println(indentation + message);
        System.out.println(indentation + listOfTasks.get(index) + lineBreak);
    }

    public String invalidIndexMessage(int index) {
        return indentation + "Task " + index + " does not exist. Please enter a valid task number." + lineBreak;
    }

    public String invalidTaskMessage() {
        String string1 = "Invalid task due to missing details which were not provided. ";
        String string2 = "Please provide a valid instruction with the correct relevant details.";
        return indentation + string1 + string2 + lineBreak;
    }

    public void invalidInstruction() {
        throw new EdithException("Sorry but that is not an instruction I can execute.");
    }

    public static void main(String[] args) {
        Edith edith = new Edith();
        edith.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println(prompt);
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                edith.exit();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                edith.list();
            } else if (userInput.startsWith("mark ")) {
                int index = Integer.parseInt(userInput.substring(5).trim()) - 1;
                try {
                    edith.mark(index);
                } catch (IndexOutOfBoundsException e) {
                    String errorMessage = edith.invalidIndexMessage(index + 1);
                    System.err.println(errorMessage);
                }
            } else if (userInput.startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
                try {
                    edith.unmark(index);
                } catch (IndexOutOfBoundsException e) {
                    String errorMessage = edith.invalidIndexMessage(index + 1);
                    System.err.println(errorMessage);
                }
            } else if (userInput.startsWith("delete ")) {
                int index = Integer.parseInt(userInput.substring(7).trim()) - 1;
                try {
                    edith.delete(index);
                } catch (IndexOutOfBoundsException e) {
                    String errorMessage = edith.invalidIndexMessage(index + 1);
                    System.err.println(errorMessage);
                }
            } else if (userInput.startsWith("todo ") || userInput.startsWith("deadline ") || userInput.startsWith("event ")) {
                try {
                    edith.add(userInput);
                } catch (EdithException e) {
                    System.err.println(e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    String errorMessage = edith.invalidTaskMessage();
                    System.err.println(errorMessage);
                }
            } else {
                try {
                    edith.invalidInstruction();
                } catch (EdithException e) {
                    System.err.println(e);
                }
            }
        }

        scanner.close();
    }
}