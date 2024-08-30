import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Alvis {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> tracker = new ArrayList<>();
        System.out.println("So you require my assistance?");

        while (true) {
            String userInput = sc.nextLine().toLowerCase();
            switch (userInput.split(" ")[0]) {
            case "bye":
                System.out.println("May we meet again");
                break;
            case "list":
                System.out.println("Let me help you to remember what you need to do ...");
                for (int i = 0; i < tracker.size(); ++i) {
                        System.out.println("" + (i+1) + ". " + tracker.get(i).toString());
                    }
                break;
            case "toggle":
                try {
                    int index = Integer.parseInt(userInput.substring(7)) + 1;
                    tracker.get(index).toggleStatus();
                    System.out.println("As requested, I have changed the status of your task");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("I cannot do that, the index is invalid");
                }
                break;
            case "todo":
                try {
                    Task toDo = new ToDo(userInput.substring(5));
                    addTask(toDo, tracker);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please input a body for the task");
                }
                break;
            case "deadline":
                int byIndex = userInput.indexOf("/by");
                Task deadline = new Deadline(userInput.substring(9, byIndex), userInput.substring(byIndex + 4));
                addTask(deadline, tracker);
                break;
            case "event":
                int fromIndex = userInput.indexOf("/from");
                int toIndex = userInput.indexOf("/to");
                Task event = new Event(userInput.substring(6, fromIndex), userInput.substring(fromIndex + 6, toIndex - 1),
                        userInput.substring(toIndex + 4));
                addTask(event, tracker);
                break;
            default:
                System.out.println("I don't recognise that. Type /help for the commands");
            }
        }
    }

    public static void addTask(Task task, List<Task> tracker) {
        tracker.add(task);
        System.out.println("Understood, I have added to your list: \n" + task.toString());
        System.out.println("You have " + tracker.size() + " tasks");
    }

//    public static int[] getDelimiterIndex (String input) {
//        int firstIdx = input.indexOf("/");
//        int secondIdx = input.indexOf("/", firstIdx + 1);
//        return new int[]{firstIdx, secondIdx};
//    }
}
