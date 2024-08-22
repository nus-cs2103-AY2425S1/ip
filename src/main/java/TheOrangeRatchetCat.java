import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

enum CommandType {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE;
}

public class TheOrangeRatchetCat {

    public static void main(String[] args) {
        // The ChatBot named OrangeRatchetCat
        ratchetCatBot();
    }

    private static void ratchetCatBot() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm TheOrangeRatchetCat");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        List<Task> items = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Reads a line of text
        // Perhaps you could try and take out the Try block and
        // Wrap the specific if conditions in the try-catch block.
        // Such as for unmark, mark and todo
        // Try to catch error for the other 3 conditions as well
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                input = TheOrangeRatchetCat.checkList(input, items, scanner);
                continue;
            }
            if (input.startsWith("mark")) {
                String numberPart = input.substring(4).trim();
                input = TheOrangeRatchetCat.markingTask(numberPart, items, scanner);
                continue;
            }
            if (input.startsWith("unmark")) {
                String numberPart = input.substring(6).trim();
                input = TheOrangeRatchetCat.unmarkingTask(numberPart, items, scanner);
                continue;
            }
            if (input.startsWith("todo")) {
                try {
                    String taskDescription = input.substring(4).trim();
                    input = TheOrangeRatchetCat.addingToDo(taskDescription, items, scanner);
                    continue;
                } catch (TheOrangeRatchetCatException e) {
                    System.out.println(e.getMessage());
                    input = scanner.nextLine();
                    continue;
                }
            }
            if (input.startsWith("deadline")) {
                try {
                    input = TheOrangeRatchetCat.addingDeadline(input, items, scanner);
                    continue;
                } catch (TheOrangeRatchetCatException e) {
                    System.out.println(e.getMessage());
                    input = scanner.nextLine();
                    continue;
                }
            }
            // Make sure to handle incorrect string inputs for event (not just event, but for other tasks as well)
            if (input.startsWith("event")) {
                try {
                    input = TheOrangeRatchetCat.addingEvent(input, items, scanner);
                    continue;
                } catch (TheOrangeRatchetCatException e) {
                    System.out.println(e.getMessage());
                    input = scanner.nextLine();
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Correct input format for adding event: event <Task> /from <input> /to <input>");
                    input = scanner.nextLine();
                    continue;
                }
            }
            if (input.startsWith("delete")) {
                int indexToDelete = Integer.parseInt(input.substring(6).trim());
                input = TheOrangeRatchetCat.deleteTask(indexToDelete, items, scanner);
                continue;
            }
            System.out.print("Inappropriate Command try again with adding either a Deadline/Todo/Event: ");
            input = scanner.nextLine(); // Reads the next line of input text again
        }
        TheOrangeRatchetCat.bidFarewell();
        scanner.close(); // Close the scanner to avoid resource leaks
    }

    private static String checkList(String input, List<Task> items, Scanner scanner) {
        int index = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task item : items) {
            System.out.println(index + "." + item);
            index++;
        }
        return scanner.nextLine();
    }

    private static String markingTask(String input, List<Task> items, Scanner scanner) {
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            Task markingTask = items.get(taskIndex);
            markingTask.isDone = true;
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + markingTask.getStatusIcon() + "] " + markingTask.description);
            System.out.println("____________________________________________________________");
            return scanner.nextLine(); // Reads the next line of input text again
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine(); // Reads the next line of input text again
        } catch (NumberFormatException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine(); // Reads the next line of input text again
        }
    }

    private static String unmarkingTask(String input, List<Task> items, Scanner scanner) {
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            Task markingTask = items.get(taskIndex);
            markingTask.isDone = false;
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + markingTask.getStatusIcon() + "] " + markingTask.description);
            System.out.println("____________________________________________________________");
            return scanner.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine(); // Reads the next line of input text again
        }
    }

    private static String deleteTask(int index, List<Task> items, Scanner scanner) {
        try {
            Task taskToDelete = items.get(index - 1);
            items.remove(index - 1);
            Task.taskCount--;
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskToDelete);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
            return scanner.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        }
    }

    private static String addingToDo(String input, List<Task> items, Scanner scanner) throws TheOrangeRatchetCatException {
        if (input.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing!");
        }
        Task nextTask = new ToDo(input);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTask);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
        items.add(nextTask);
        return scanner.nextLine();
    }

    private static String addingDeadline(String input, List<Task> items, Scanner scanner) throws TheOrangeRatchetCatException {
        // Split the input string by "/by"
        String[] parts = input.split("/by");
        // The description is the first part after removing the word "deadline"
        String taskDescription = parts[0].replace("deadline", "").trim();
        if (taskDescription.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing!");
        }
        // The "by" part is the second part, if it exists
        String date = parts.length > 1 ? parts[1].trim() : "";
        if (date.isEmpty()) {
            throw new TheOrangeRatchetCatException("You need to provide a deadline!");
        }
        Task nextTask = new Deadline(taskDescription, date);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTask);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
        items.add(nextTask);
        return scanner.nextLine();
    }

    private static String addingEvent(String input, List<Task> items, Scanner scanner) throws TheOrangeRatchetCatException {
        // Split the input string by "/from"
        String[] parts = input.split("/from");
        // The taskDescription is the first part after removing the word "event"
        String taskDescription = parts[0].replace("event", "").trim();
        if (taskDescription.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing!");
        }
        // Further split the remaining part by "/to"
        String[] dateParts = parts[1].split("/to");
        // The "fromDate" is the first part
        String fromDate = dateParts[0].trim();
        // The "toDate" is the second part
        String toDate = dateParts.length > 1 ? dateParts[1].trim() : "";
        Task nextTask = new Event(taskDescription, fromDate, toDate);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTask);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
        items.add(nextTask);
        return scanner.nextLine();
    }

    private static void bidFarewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
