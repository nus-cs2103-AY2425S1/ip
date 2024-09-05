package R2D2;
import java.util.ArrayList;

/**
 * A class that handles all the inputs given by the user and parses through them switching between
 * different commands from the user.
 */
public class InputHandler {
    private ArrayList<Task> data;
    private Storage storage;
    private String hline = "____________________________________________________________";

    /**
     * Creates an inputHandler object that will take care of all the user inputs
     *
     * @param data data to be sent to the output and where the chatbot stores the tasks
     * @param storage storage object which takes care of loading and saving tasks to the database
     */
    public InputHandler(ArrayList<Task> data, Storage storage) {
        this.data = data;
        this.storage = storage;
    }

    /**
     * Handles the majority of the user inputs through other functions in the InputHandler class
     *
     * @param input user input that is being passed to the chatbot
     * @throws BuzzException if any of the commands given by the user is not a valid one
     */
    public void overallHandler(String input) throws BuzzException  {
        if (input.startsWith("mark")) {
            this.markHandle(input);
            this.storage.saveTasks(data);
        } else if (input.startsWith("unmark")) {
            this.unmarkHandle(input);
            this.storage.saveTasks(data);
        } else if (input.startsWith("delete")) {
            this.deleteHandle(input);
            this.storage.saveTasks(data);
        } else if (input.equals("list")) {
            System.out.println(hline);

            for (int i = 0; i < data.size(); i++) {
                System.out.println((i + 1) + "." + data.get(i).toString());
            }

            System.out.println(hline);
        } else if (input.startsWith("todo")) {
            this.todoHandle(input);
            this.storage.saveTasks(data);
        } else if (input.startsWith("deadline")) {
            this.deadlineHandle(input);
            this.storage.saveTasks(data);
        } else if (input.startsWith("event")) {
            this.eventHandle(input);
            this.storage.saveTasks(data);
        } else if (input.startsWith("find")) {
            this.findHandle(input);
            this.storage.saveTasks(data);
        } else {
            throw new BuzzException("GRRR! I do not know what that means. Try again! *bzzrg*");
        }
    }

    /**
     * Handles the "mark" command by parsing through it and ensuring that it is a valid index
     *
     * @param input user input that is being passed to the chatbot
     * @throws BuzzException if the index being asked to mark is out of range/ non existent
     */
    public void markHandle(String input) throws BuzzException {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= data.size()) {
            throw new BuzzException("OOPS!!! The task doesn't exist *BEEP*");
        }
        data.get(index).setDone(true);
        System.out.println(hline);
        System.out.println("Mission accomplished! *bzzt*");
        System.out.println(data.get(index).toString());
        System.out.println(hline);
    }

    /**
     * Handles the "unmark" command from the user and marks a task in the list as not done
     *
     * @param input user input that is being passed to the chatbot
     * @throws BuzzException if the index being asked to unmark is out of range/non-existent
     */
    public void unmarkHandle(String input) throws BuzzException {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= data.size()) {
            throw new BuzzException("OOPS!!! The task doesn't exist *BEEP*");
        }
        data.get(index).setDone(false);
        System.out.println(hline);
        System.out.println("Argh next time! *bzzt*");
        System.out.println(data.get(index).toString());
        System.out.println(hline);
    }

    /**
     * Handles the "delete" command from the user and deletes the specified task from the list
     *
     * @param input user input that is being passed to the chatbot for deletion
     * @throws BuzzException if the index being asked to delete is out of range or does not exist in the list
     */
    public void deleteHandle(String input) throws BuzzException {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= data.size()) {
            throw new BuzzException("OOPS!!! The task doesn't exist *BEEP*");
        }
        Task task = data.remove(index);
        System.out.println(hline);
        System.out.println("*POOF* Circuits fried! Deleted the mission.");
        System.out.println(task.toString());
        System.out.println("You currently have " + data.size() + " missions available *reeeee* ");
        System.out.println(hline);

    }

    /**
     * Handles the "todo" type task from the user and adds it into the tasklist
     *
     * @param input user input that is being passed to the chatbot to add to the list
     * @throws BuzzException if the description of the task was not specified
     */
    public void todoHandle(String input) throws BuzzException {
        if (input.length() <= 5) {
            throw new BuzzException("NOOO! Description is empty *crash*");
        }
        Task task = new Todo(input.substring(5));
        data.add(task);
        System.out.println(hline);
        System.out.println("Understood boss. Added!");
        System.out.println(task.toString());
        System.out.println("You currently have " + data.size() + " missions available *reeeee* ");
        System.out.println(hline);
    }

    /**
     * Handles the addition of a "deadline" task into the list
     *
     * @param input user input that is being passed to the chatbot to add as a deadline
     * @throws BuzzException if the description of the deadline task is empty
     */
    public void deadlineHandle(String input) throws BuzzException {
        String[] parts = input.split("/by");
        if (parts.length < 2 || parts[0].length() <= 9) {
            throw new BuzzException("NOOO! Description is empty *crash*");
        }
        Task task = new Deadline(parts[0].substring(9), parts[1].trim());
        data.add(task);
        System.out.println(hline);
        System.out.println("Understood boss. Added!");
        System.out.println(task.toString());
        System.out.println("You currently have " + data.size() + " missions available *reeeee* ");
        System.out.println(hline);
    }

    /**
     * Handles the "event" task type and adds it into the list as an event
     *
     * @param input user input that is being passed to the chatbot to add as an event
     * @throws BuzzException if description of the event is empty
     */
    public void eventHandle(String input) throws BuzzException {
        String[] parts = input.split("/");
        if (parts.length < 3 || parts[0].length() <= 6) {
            throw new BuzzException("OOPS!!! The description of an event cannot be empty.");
        }
        Task task = new Event(parts[0].substring(6), parts[1].substring(5), parts[2].substring(3));
        data.add(task);
        System.out.println(hline);
        System.out.println("Understood boss. Added!");
        System.out.println(task.toString());
        System.out.println("You currently have " + data.size() + " missions available *reeeee* ");
        System.out.println(hline);
    }

    public void findHandle(String input) throws BuzzException {
        if (input.length() <= 5) {
            throw new BuzzException("NOOO! Description is empty *crash*");
        }
        String wordSearch = input.substring(5).trim();
        System.out.println(hline);
        System.out.println("I have found a few matches sir! *wooop*");
        int tasks = 1;
        for (int i = 0; i < data.size(); i++) {
            Task task = data.get(i);
            if (task.description.contains(wordSearch)) {
                System.out.println(tasks + "." + task.toString());
                tasks++;
            }
        }
        if (tasks == 1) {
            System.out.println("Sorry boss can't find anything :(");
        }
        System.out.println(hline);
    }
}
