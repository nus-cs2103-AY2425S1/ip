import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
public class Evelyn {
    private static String chatbotName = "Evelyn";
    private static String horizontalLine = "-----------------------------------------";
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList lst = new ArrayList(100);
    private static boolean cont = true;

    private static void decipher(String text) throws NoInputException {

        if ((Objects.equals(text, "bye")) || (Objects.equals(text, "BYE")) || (Objects.equals(text, "Bye"))) {
            System.out.println(horizontalLine);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontalLine);
            cont = false;
        } else if (Objects.equals(text, "list")) {
            System.out.println(horizontalLine);
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < lst.size(); i++) {
                System.out.println((i + 1) + "." + lst.get(i));
            }
            System.out.println(horizontalLine);
        }  else if (text.startsWith("mark")) {
            int index = Integer.parseInt(text.substring(5)) - 1;
            Task task = (Task) lst.get(index);
            System.out.println(horizontalLine);
            task.mark();
            System.out.println(horizontalLine);
        } else if (text.startsWith("unmark")) {
            int index = Integer.parseInt(text.substring(7)) - 1;
            Task task = (Task) lst.get(index);
            task.unmark();
        } else if (text.startsWith("todo")) {
            if (text.length() <= 5 || Character.isWhitespace(text.charAt(5))) {
                throw new InvalidInputException("invalid input for todo");
            }
            String description = text.substring(5);
            Todo newTodo = new Todo(description);
            lst.add(newTodo);
            System.out.println(horizontalLine);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newTodo.toString());
            System.out.println(lst.size() > 1 ? "Now you have " + lst.size() + " tasks in this list"
                    : "Now you have " + lst.size() + " task in this list");
            System.out.println(horizontalLine);
        } else if (text.startsWith("deadline")) {
            if (text.length() <= 9 || Character.isWhitespace(text.charAt(9))) {
                throw new InvalidInputException("invalid input for deadline");
            }
            String input = text.substring(9);
            String[] parts = input.split(" /by ");
            String description = parts[0];
            String deadline = parts[1];
            Deadline newDeadline = new Deadline(description, deadline);
            lst.add(newDeadline);
            System.out.println(horizontalLine);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newDeadline.toString());
            System.out.println(lst.size() > 1 ? "Now you have " + lst.size() + " tasks in this list"
                    : "Now you have " + lst.size() + " task in this list");
            System.out.println(horizontalLine);
        } else if (text.startsWith("event")) {
            if (text.length() <= 6 || Character.isWhitespace(text.charAt(6))) {
                throw new InvalidInputException("invalid input for event");
            }
            String input = text.substring(6);
            String[] partA = input.split(" /from " );
            String description = partA[0];
            String[] partB = partA[1].split(" /to ");
            String start = partB[0];
            String end = partB[1];
            Event newEvent = new Event(description, start, end);
            lst.add(newEvent);
            System.out.println(horizontalLine);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newEvent.toString());
            System.out.println(lst.size() > 1 ? "Now you have " + lst.size() + " tasks in this list"
                    : "Now you have " + lst.size() + " task in this list");
            System.out.println(horizontalLine);
        } else {
            throw new NoInputException("no input!");
        }
    }

    public static void main(String[] args) {
        String text = null;
        System.out.println(horizontalLine);
        System.out.println("Hi! I am Evelyn");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        while (cont) {
            text = scanner.nextLine();

            try {
                decipher(text);
            } catch (InvalidInputException e) {
                System.out.println(horizontalLine);
                System.out.println("You did not use the keywords properly!");
                System.out.println("Please use the following key words:");
                System.out.println("todo [task description]");
                System.out.println("deadline [task description] /by [date]");
                System.out.println("event [task description] /from [start date and time] /to [end date and time");
                System.out.println(horizontalLine);
            } catch (NoInputException e) {
                System.out.println(horizontalLine);
                System.out.println("Invalid");
                System.out.println("Please use the following key words:");
                System.out.println("todo [task description]");
                System.out.println("deadline [task description] /by [date]");
                System.out.println("event [task description] /from [start date and time] /to [end date and time");
                System.out.println(horizontalLine);
            }
        }
    }
}
