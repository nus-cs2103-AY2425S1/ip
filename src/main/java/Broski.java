import java.util.ArrayList;
import java.util.Scanner;

public class Broski {
    private static final String LINE = "_________________________________________";
    private final Scanner scanner = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>(100);

    private final TaskManager manager = new TaskManager();

    private final DateTimeParser dateTimeParser = new DateTimeParser();

    private static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Starts the chatbot with the initial prompts.
     */
    public void start() {
        printLine();
        System.out.println("Wassup! I'm Broski!");
        System.out.println("What can I do for you bro?");
        printLine();
        this.list = this.manager.loadTasks();
    }

    /**
     * The main content of the chatbot and all its possible responses.
     */
    public void chatbot() throws TodoException, DeadlineException,
            EventException, WrongInputException {
        String reply = scanner.nextLine();
        if (reply.equals("list")) {
            printLine();
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
            printLine();
            this.chatbot();
        } else if (reply.equals("bye")) {
            printLine();
            this.exit();
        } else if (reply.length() > 5 && reply.startsWith("mark")) {
            printLine();
            int i = Integer.parseInt(reply.split(" ")[1]);
            list.get(i).mark();
            System.out.println("Solid! Marked as done for you:");
            System.out.println(list.get(i));
            printLine();
            this.save();
            this.chatbot();
        } else if (reply.length() > 7 && reply.startsWith("unmark")) {
            printLine();
            int i = Integer.parseInt(reply.split(" ")[1]);
            list.get(i).unmark();
            System.out.println("Alright, I've marked the task as undone:");
            System.out.println(list.get(i));
            printLine();
            this.save();
            this.chatbot();
        } else if (reply.length() > 7 && reply.startsWith("delete")) {
            printLine();
            int i = Integer.parseInt(reply.split(" ")[1]);
            String temp = list.get(i).toString();
            list.remove(i);
            System.out.println("Gotcha, I've removed this task:");
            System.out.println(temp);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            this.save();
            this.chatbot();
        } else {
            printLine();
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
                        dateTimeParser.parseDateTime(reply.split(" /")[1]));
                list.add(deadline);
                System.out.println("Gotcha! I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else {
                String[] splitter = reply.split(" /");
                Event event = new Event(
                        splitter[0].replaceFirst("event ", ""),
                        dateTimeParser.parseDateTime(splitter[1]),
                        dateTimeParser.parseDateTime(splitter[2]));
                list.add(event);
                System.out.println("Gotcha! I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            }
            printLine();
            this.save();
            this.chatbot();
        }
    }

    /**
     * Exits the chatbot and closes the system.
     */
    public void exit() {
        System.out.println("Bye, bro. See ya around!");
        printLine();
    }

    public void save() {
        this.manager.saveTasks(this.list);
    }

    public void run() {
        try {
            this.chatbot();
        } catch (TodoException e) {
            System.out.println("Hey, your task description is empty bro.");
            printLine();
            this.run();
        } catch (DeadlineException e) {
            System.out.println("Hey, your task description" +
                    " is either empty or your deadline is missing/wonky bro.");
            printLine();
            this.run();
        } catch (EventException e) {
            System.out.println("Hey, your task description" +
                    " is either empty or your duration is missing/wonky bro.");
            printLine();
            this.run();
        } catch (WrongInputException e) {
            System.out.println("I'm sorry but I can't understand you bro." +
                    " Use todo, deadline or event please!");
            printLine();
            this.run();
        } catch (InvalidDateTimeException e) {
            System.out.println("Invalid date/time format. Please use dd/MM/yyyy HHmm format.");
            printLine();
            this.run();
        }
    }

    public static void main(String[] args) {
        Broski bot = new Broski();
        bot.start();
        bot.run();
    }
}
