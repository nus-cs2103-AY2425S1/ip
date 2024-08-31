package Mario;
import Mario.Tasks.Deadline;
import Mario.Tasks.Event;
import Mario.Tasks.Task;
import Mario.Tasks.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Mario {

    /** Task List */
    private static TaskList lst;
<<<<<<< HEAD
    /** Parser that interprets user input */
    private Parser parser = new Parser(this);
    /** Ui which formats chatbot responses */
=======
>>>>>>> branch-A-CodingStandard
    private static final Ui ui = new Ui();
    /** Stores task history */
    private static final Storage storage = new Storage();

    public Mario() throws IOException {
        if(storage.loadTasks() == null || storage.loadTasks().isEmpty()) {
            lst = new TaskList();
        } else {
            lst = new TaskList((ArrayList<Task>) storage.loadTasks());
        }
    }

    public static void main(String[] args) throws IOException {
        Mario mario = new Mario();
        mario.run();
    }

    /**
     * Executes the main loop of the chatbot.
     * Prints the welcome message and continuously sends user input to Parser
     *
     * @throws IOException
     */
    public void run() throws IOException {
        String logo = ui.getLine()
                + "It's-a me, Mario! \n"
                + "How can I help you today?\n"
                + ui.getLine();

        System.out.println(logo);

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();
            parser.parseCommand((input));

        }
        while (!input.equals("bye") && !input.equals("Bye"));

        scanner.close();

    }

    /**
     * Returns a formatted list of tasks.
     */
    public static void listTasks() {
        int len = lst.length();
        if (len == 0) {
            System.out.println(ui.betweenLines(
                    "There's nothing panned yet!"));
        } else {
            System.out.println(ui.getLine()
                    + "     Here you go:");

            for (int i = 0; i < len; i++) {
                int order = i + 1;
                System.out.println("     " + order + ". "
                        + lst.getTask(order).getName());
            }
            System.out.println(ui.getLine());
        }
    }

    /**
     * Adds a todo task to the Task list
     * @param name of task
     * @throws IOException
     */
    public static void addTodoTask(String name) throws IOException {
        ToDo todo = new ToDo(name);
        lst.addTask(todo);
        saveToStorage(lst);
        System.out.println(ui.betweenLines(
                 "All right! I've added this task: \n"
                + "        " + todo.getName() + "\n"
                + "     Now you have " + lst.length()
                + " task(s) in your list!\n"));
    }


    /**
     * Adds a deadline task to the task list.
     * @param name
     * @param deadline
     * @throws IOException
     */
    public static void addDeadlineTask(String name, String deadline) throws IOException {
        Deadline dl = new Deadline(name, deadline);
        lst.addTask(dl);
        saveToStorage(lst);
        System.out.println(ui.betweenLines(
                "All right! I've added this task: \n"
                + "        " + dl.getName() + "\n"
                + "     Now you have " + lst.length()
                        + " task(s) in your list!\n"));
    }

    /**
     * Adds an event task to the task list.
     * @param name
     * @param start Starting time of the event.
     * @param end Ending time of the event.
     * @throws IOException
     */
    public static void addEventTask(String name, String start, String end) throws IOException {
        Event event = new Event(name, start, end);
        lst.addTask(event);
        saveToStorage(lst);
        System.out.print(ui.betweenLines(
                "All right! I've added this task: \n"
                + "        " + event.getName() + "\n"
                + "     Now you have " + lst.length() + " task(s) in your list!\n"));
    }

    public int getNumTasks() {
        return this.lst.length();
    }

    /**
     * Marks task as completed.
     * @param num Index of the task on the task list, starting from 1.
     * @throws IOException
     */
    public void markTask(int num) throws IOException {
        lst.markCompleted(num);
        saveToStorage(lst);
        System.out.println(ui.betweenLines(
                 "Okey-dokey! I've marked this task as done: \n"
                + "       " + lst.getTask(num).getName() + "\n"));
    }


    /**
     * Unmarks task as completed.
     * @param num Index of the task on the task list, starting from 1.
     * @throws IOException If num is invalid.
     */
    public void unmarkTask(int num) throws IOException {
        lst.markUncompleted(num);
        saveToStorage(lst);
        System.out.println(ui.betweenLines(
                "Okey-dokey! I've marked this task as not done yet: \n"
                + "        " + lst.getTask(num).getName() + "\n"));
    }

    /**
     * Removes task from the task list.
     * @param num Index of the task on the task list, starting from 1.
     * @throws IOException If num is invalid.
     */
    public void removeTask(int num) throws IOException {
        System.out.println(ui.betweenLines(
                "Okey-dokey! I've removed this task: \n"
                + "        " + lst.getTask(num).getName() + "\n"
                + "     Now you have " + (lst.length()-1)
                        + " task(s) in your list!\n"));
        lst.removeTask(num);
        saveToStorage(lst);

    }

    /**
     * Prints the goodbye message and ends the programme.
     */
    public void bye() {
        System.out.println(ui.betweenLines("Buh-bye! See you soon!" ));
    }

    /**
     * Updates storage to contain the latest task list information.
     * @param lst List of tasks in the task list
     * @throws IOException
     */
    public static void saveToStorage(TaskList lst) throws IOException {
        storage.saveTasks(lst);
    }

    /**
     * Displays tasks with the keyword in their name.
     *
     * @param keyword
     */
    public void findTask(String keyword) {
        List<Task> res = new ArrayList<>();
        for (Task task : lst.getAllTasks()) {
            if (task.getName().contains(keyword)) {
                res.add(task);
            }
        }
        if (!res.isEmpty()) {
            System.out.println(ui.getLine()
                    + "     Here are the matching tasks in your list:\n");
            for (int i = 0; i < res.size(); i++) {
                Task task = res.get(i);
                int id = i + 1;
                System.out.println("        "
                        + id +  ". " + task.getName());
            }
            System.out.println(ui.getLine());
        } else {
            System.out.println(
                    ui.betweenLines("Oh no! There's no such task"));
        }
    }

}
