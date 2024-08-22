import java.util.ArrayList;
import java.util.Scanner;

public class Broski {
    private final static String line = "_________________________________________";
    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Task> list = new ArrayList<>(100);

    /**
     * Starts the chatbot with the initial prompts.
     */
    public void start() {
        System.out.println(line);
        System.out.println("Wassup! I'm Broski!");
        System.out.println("What can I do for you bro?");
        System.out.println(line);
    }

    /**
     * The main content of the chatbot and all its possible responses.
     */
    public void chatbot() throws TodoException, DeadlineException,
            EventException, WrongInputException {
        String reply = scanner.nextLine();
        if (reply.equals("list")) {
            System.out.println(line);
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
            System.out.println(line);
            this.chatbot();
        } else if (reply.equals("bye")) {
            System.out.println(line);
            this.exit();
        } else if (reply.length() > 5 && reply.startsWith("mark")) {
            System.out.println(line);
            int i = Integer.parseInt(reply.split(" ")[1]);
            list.get(i).mark();
            System.out.println("Solid! Marked as done for you:");
            System.out.println(list.get(i));
            System.out.println(line);
            this.chatbot();
        } else if (reply.length() > 7 && reply.startsWith("unmark")) {
            System.out.println(line);
            int i = Integer.parseInt(reply.split(" ")[1]);
            list.get(i).unmark();
            System.out.println("Alright, I've marked the task as undone:");
            System.out.println(list.get(i));
            System.out.println(line);
            this.chatbot();
        } else if (reply.length() > 7 && reply.startsWith("delete")) {
            System.out.println(line);
            int i = Integer.parseInt(reply.split(" ")[1]);
            String temp = list.get(i).toString();
            list.remove(i);
            System.out.println("Gotcha, I've removed this task:");
            System.out.println(temp);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            this.chatbot();
        } else {
            System.out.println(line);
            if (reply.length() == 4 && reply.startsWith("todo")) {
                throw new TodoException();
            }
            if ((reply.length() == 8 && reply.startsWith("deadline")) ||
                    (reply.startsWith("deadline") && reply.split(" /").length != 2)) {
                throw new DeadlineException();
            }
            if ((reply.length() == 5 && reply.startsWith("event")) ||
                    (reply.startsWith("event") && reply.split(" /").length != 3)) {
                throw new EventException();
            }
            if (!(reply.startsWith("todo") || reply.startsWith("deadline") ||
                    reply.startsWith("event"))) {
                throw new WrongInputException();
            }
            if (reply.length() > 5 && reply.startsWith("todo")) {
                Todo todo = new Todo(reply.replaceFirst("todo ", ""));
                list.add(todo);
                System.out.println("Gotcha! I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (reply.length() > 9 && reply.startsWith("deadline")) {
                Deadline deadline = new Deadline(
                        reply.replaceFirst("deadline ", "").split(" /")[0],
                        reply.split(" /")[1]);
                list.add(deadline);
                System.out.println("Gotcha! I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else {
                String[] splitter = reply.split(" /");
                Event event = new Event(
                        splitter[0].replaceFirst("event ", ""),
                        splitter[1],
                        splitter[2]);
                list.add(event);
                System.out.println("Gotcha! I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            System.out.println(line);
            this.chatbot();
        }
    }

    /**
     * Exits the chatbot and closes the system.
     */
    public void exit() {
        System.out.println("Bye, bro. See ya around!");
        System.out.println(line);
    }

    public static void main(String[] args) throws TodoException, DeadlineException,
            EventException, WrongInputException {
        Broski bot = new Broski();
        bot.start();
        try {
            bot.chatbot();
        } catch (TodoException e) {
            System.out.println("Hey, your task description is empty bro.");
            System.out.println(line);
        } catch (DeadlineException e) {
            System.out.println("Hey, your task description" +
                    " is either empty or missing a deadline bro.");
            System.out.println(line);
        } catch (EventException e) {
            System.out.println("Hey, your task description" +
                    " is either empty or missing a duration bro.");
            System.out.println(line);
        } catch (WrongInputException e) {
            System.out.println("I'm sorry but I can't understand you bro." +
                    " Use task, deadline or event please!");
        }
    }
}
