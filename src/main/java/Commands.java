import java.io.IOException;
import java.util.regex.Matcher;

public class Commands {
    private TaskList tasks;
    private Storage saveSystem;

    public Commands(TaskList tasks, Storage saveSystem) {
        this.tasks = tasks;
        this.saveSystem = saveSystem;
    }

    public void printLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void addTask(Task newTask) throws IOException{
        this.tasks.addTask(newTask);
        saveSystem.writeTasksToFile(this.tasks.getAllTasks());
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void createEvent(String command, Matcher eventMatcher) throws IOException {
        Task newTask = new Event(eventMatcher.group(1), eventMatcher.group(2), eventMatcher.group(3));
        this.addTask(newTask);
    }

    public void createDeadline(String command, Matcher deadlineMatcher) throws IOException {
        Task newTask = newTask = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
        this.addTask(newTask);
    }

    public void createToDo(String command, Matcher todoMatcher) throws IOException {
        Task newTask = new ToDo(todoMatcher.group(1));
        this.addTask(newTask);
    }

    public void mark(String command) throws IOException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length <= 1) {
            throw new KitaMissingIndex();
        }

        int numberToMark = Integer.parseInt(splitCommand[1]);

        if (numberToMark > this.tasks.size() || numberToMark <= 0) {
            throw new KitaOutofBounds();
        }

        System.out.println("Nice! I've marked this task as done:");
        this.tasks.setTaskCompleted(numberToMark - 1, true);
        this.saveSystem.writeTasksToFile(this.tasks.getAllTasks());
    }

    public void unmark(String command) throws IOException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length <= 1) {
            throw new KitaMissingIndex();
        }

        int numberToMark = Integer.parseInt(splitCommand[1]);

        if (numberToMark > this.tasks.size() || numberToMark <= 0) {
            throw new KitaOutofBounds();
        }

        System.out.println("OK, I've marked this task as not done yet:");
        this.tasks.setTaskCompleted(numberToMark - 1, false);
        saveSystem.writeTasksToFile(this.tasks.getAllTasks());
    }

    public void delete(String command) throws IOException {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length <= 1) {
            throw new KitaMissingIndex();
        }

        int numberToDelete = Integer.parseInt(splitCommand[1]);
        if (numberToDelete > this.tasks.size() || numberToDelete <= 0) {
            throw new KitaOutofBounds();
        }

        System.out.println("Noted. I've removed this task:");
        this.tasks.removeTask(numberToDelete - 1);
        saveSystem.writeTasksToFile(this.tasks.getAllTasks());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        System.out.println(this.tasks);
    }

    public void hello() {
        this.printLine();
        System.out.println(" Hello! I'm Kita!");
        System.out.println(" What can I do for you?");
        this.printLine();
    }

    public void bye() {
        System.out.println(" Bye. Hope to see you again soon!\n");
        this.printLine();
    }
}
