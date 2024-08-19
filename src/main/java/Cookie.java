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
    public void handleInput(String input) {
        String[] parseInput = input.split(" ", 2);
        String command = parseInput[0];
        String description = (parseInput.length > 1) ? parseInput[1] : "";

        switch (command) {
            case "list":
                System.out.println(this.printTasks());
                break;
            case "mark":
                System.out.println("Cookie has marked this as done! Good job! \n" +
                        this.markDone(Integer.parseInt(description)));
                break;
            case "unmark":
                System.out.println("Cookie has unmarked this task! \n" +
                        this.unmarkDone(Integer.parseInt(description)));
                break;
            case "todo":
                addTask(new ToDo(description));
                System.out.println(this.printLatestTask() + this.printNoTasks() + "\n");
                break;
            case "deadline":
                String[] deadlineDetails = description.split(" /by ", 2);
                addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
                System.out.println(this.printLatestTask() + this.printNoTasks() + "\n");
                break;
            case "event":
                String[] eventDetails = description.split(" /from | /to ");
                addTask(new Event(eventDetails[0], eventDetails[1], eventDetails[2]));
                System.out.println(this.printLatestTask() + this.printNoTasks() + "\n");
                break;
            default:
                System.out.println(input);
                break;
        }
    }
    public static void main(String[] args) {
        Cookie cookie = new Cookie();
        System.out.println(cookie.printLogo() + cookie.printGreet());

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equals("bye")) {
            cookie.handleInput(input);
            input = scanner.nextLine();
        }

        scanner.close();
        System.out.println(cookie.printQuit());

    }
}
