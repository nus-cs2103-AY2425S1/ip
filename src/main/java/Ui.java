public class Ui {
    TaskList tasks;
    public Ui (TaskList tasks) {
        this.tasks = tasks;
    }
    public void welcome() {
        System.out.println("----------------------\n" +
                "Hello! I'm Reo.\nWhat can I do for you?\n"
                + "----------------------");
    }

    public void list() {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += tasks.toString();
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    public void addTodo(Todo t) {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "I've added this todo:\n " + t.toString() + "\n";
        toPrint += "Now, you have " + tasks.size() + " task(s) in your list.\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    public void addTodoError() {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "Please enter a valid task name.\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    public void mark(Task t) {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "Good job! I've marked this item as done:\n";
        toPrint += t.toString() + "\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    public void markError() {
        String toPrint;
        toPrint = "----------------------\nPlease enter a valid task number.----------------------";
        System.out.println(toPrint);
    }

    public void unmark(Task t) {
        String toPrint;
        toPrint = "----------------------\n";
        toPrint += "Get better, I've marked this item as not done:\n";
        toPrint += t.toString() + "\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    public void unmarkError() {
        String toPrint;
        toPrint = "----------------------\nPlease enter a valid task number.----------------------";
        System.out.println(toPrint);
    }

    public void addDeadline(Deadline d) {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "I've added this deadline:\n";
        toPrint += d.toString() + "\n";
        toPrint += "Now, you have " + tasks.size() + " task(s) in your list.\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    public void addDeadlineError() {
        String toPrint;
        toPrint = "----------------------\nPlease enter a valid task name and deadline.\n----------------------";
        System.out.println(toPrint);
    }

    public void addEvent(Event e) {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "I've added this event:\n";
        toPrint += e.toString() + "\n";
        toPrint += "Now, you have " + tasks.size() + " task(s) in your list.\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    public void addEventError() {
        String toPrint;
        toPrint = "----------------------\nPlease enter a valid task name and to & from dates.\n----------------------";
        System.out.println(toPrint);
    }

    public void delete(Task t) {
        String toPrint = "";
        toPrint += "----------------------\n";
        toPrint += "I've deleted this task:\n";
        toPrint += t.toString() + "\n";
        toPrint += "Now, you have " + tasks.size() + " task(s) in your list.\n";
        toPrint += "----------------------";
        System.out.println(toPrint);
    }

    public void deleteError() {
        String toPrint;
        toPrint = "----------------------\nPlease enter a valid task number.\n----------------------";
        System.out.println(toPrint);
    }

    public void commandError() {
        String toPrint;
        toPrint = "----------------------\nERROR: Please enter a valid command.\n----------------------";
        System.out.println(toPrint);
    }

    public void bye() {
        System.out.println("Bye!");
    }
}
