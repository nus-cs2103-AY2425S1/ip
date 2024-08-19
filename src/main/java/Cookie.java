import java.util.Scanner;
import java.util.ArrayList;
public class Cookie {
    enum Commands {
        list,
        bye,
        todo,
        deadline,
        event,
    }
    private ArrayList<Task> tasks = new ArrayList<>();
    private void addTask(Task task) {
        tasks.add(task);
    }
    private String markDone(int index) {
        return tasks.get(index - 1).markDone();
    }
    private String unmarkDone(int index) {
        return tasks.get(index - 1).unmarkDone();
    }
    private String printTasks() {
        int count = 1;
        StringBuilder list = new StringBuilder();
        for(Task task: this.tasks) {
            list.append(count++).append(": ").append(task.toString()).append("\n");
        }
        return list.toString();
    }
    private String printNoTasks() {
        return "\nNow you have " + tasks.size() + " tasks in the list.";
    }
    private String printLatestTask() {
        return "Got it. Cookie has added this task:\n  " + tasks.get(tasks.size() - 1);
    }
    public String printLogo() {
        return "    o      o    \n"
                + " ____\\____/____\n"
                + "|   _      _   |\n"
                + "|  / \\    / \\  |   /\n"
                + "|  \\_/    \\_/  |  /\n"
                + "|              | /\n"
                + "|______________|/\n"
                + "\n";
    }
    public String printGreet() {
        return "Hello! I'm Cookie \n"
                + "How can I help you?\n"
                + "\n";
    }
    public String printQuit() {
        return "Bye. See you soon!";
    }
    public void handleInput(String input) throws CookieException {
        String[] parseInput = input.split(" ", 2);
        String command = parseInput[0];
        String description = (parseInput.length > 1) ? parseInput[1] : "";

        switch (command) {
            case "list":
                System.out.println(this.printTasks());
                break;

            case "mark":
                if (description.isEmpty()) {
                    throw new CookieException("Cookie does not know which task to mark.\n " +
                            "(Please enter an integer after \"mark\")");
                }
                System.out.println("Cookie has marked this as done! Good job! \n" +
                        this.markDone(Integer.parseInt(description)));
                break;

            case "unmark":
                if (description.isEmpty()) {
                    throw new CookieException("Cookie does not know which task to unmark.\n" +
                            "(Please enter an integer after \"unmark\")");
                }
                System.out.println("Cookie has unmarked this task! \n" +
                        this.unmarkDone(Integer.parseInt(description)));
                break;

            case "todo":
                if (description.isEmpty()) {
                    throw new CookieException("Please enter a task for you to do.");
                }
                addTask(new ToDo(description));
                System.out.println(this.printLatestTask() + this.printNoTasks() + "\n");
                break;

            case "deadline":
                String[] deadlineDetails = description.split(" /by ", 2);
                if (deadlineDetails.length < 2 || deadlineDetails[0].isEmpty() || deadlineDetails[1].isEmpty()) {
                    throw new CookieException("Deadlines must include a task todo and a due date \n" +
                            "[task] /by [deadline]");
                }
                addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                System.out.println(this.printLatestTask() + this.printNoTasks() + "\n");
                break;

            case "event":
                String[] eventDetails = description.split(" /from | /to ");
                if (eventDetails.length < 2 || eventDetails[0].isEmpty() ||
                        eventDetails[1].isEmpty() || eventDetails[2].isEmpty()) {
                    throw new CookieException("Events must include a task, a start time and an end time \n" +
                            "[task] /from [start] /to [end]");
                }
                addTask(new Event(eventDetails[0], eventDetails[1], eventDetails[2]));
                System.out.println(this.printLatestTask() + this.printNoTasks() + "\n");
                break;

            default:
                throw new CookieException("Cookie does not understand this command. I'm sorry!!");
        }
    }
    public static void main(String[] args) {
        Cookie cookie = new Cookie();
        System.out.println(cookie.printLogo() + cookie.printGreet());

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            try {
                cookie.handleInput(input);
            } catch (CookieException e) {
                System.out.println(e.getMessage());
            } finally {
                input = scanner.nextLine();
            }
        }
        scanner.close();
        System.out.println(cookie.printQuit());

    }
}
