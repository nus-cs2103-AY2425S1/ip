package swbot;
import java.util.ArrayList;

/**
 * A class that handles all the inputs given by the user and parses through them switching between
 * different commands from the user.
 */
public class InputHandler {
    private ArrayList<Task> data;
    private Storage storage;

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
    public String overallHandler(String input) throws BuzzException {
        if (input.startsWith("mark")) {
            this.storage.saveTasks(data);
            return this.markHandle(input);
        } else if (input.startsWith("unmark")) {
            this.storage.saveTasks(data);
            return this.unmarkHandle(input);
        } else if (input.startsWith("delete")) {
            this.storage.saveTasks(data);
            return this.deleteHandle(input);
        } else if (input.equals("list")) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < data.size(); i++) {
                res.append((i + 1)).append(".").append(data.get(i).toString());
            }
            return res.toString();
        } else if (input.startsWith("todo")) {
            this.storage.saveTasks(data);
            return this.todoHandle(input);
        } else if (input.startsWith("deadline")) {
            this.storage.saveTasks(data);
            return this.deadlineHandle(input);
        } else if (input.startsWith("event")) {
            this.storage.saveTasks(data);
            return this.eventHandle(input);
        } else if (input.startsWith("find")) {
            this.storage.saveTasks(data);
            return this.findHandle(input);
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
    public String markHandle(String input) throws BuzzException {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= data.size()) {
            throw new BuzzException("OOPS!!! The task doesn't exist *BEEP*");
        }
        data.get(index).setDone(true);
        return "Mission accomplished! *bzzt*\n" + data.get(index).toString();
    }

    /**
     * Handles the "unmark" command from the user and marks a task in the list as not done
     *
     * @param input user input that is being passed to the chatbot
     * @throws BuzzException if the index being asked to unmark is out of range/non-existent
     */
    public String unmarkHandle(String input) throws BuzzException {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= data.size()) {
            throw new BuzzException("OOPS!!! The task doesn't exist *BEEP*");
        }
        data.get(index).setDone(false);
        return "Argh next time! *bzzt*\n" + data.get(index).toString();
    }

    /**
     * Handles the "delete" command from the user and deletes the specified task from the list
     *
     * @param input user input that is being passed to the chatbot for deletion
     * @throws BuzzException if the index being asked to delete is out of range or does not exist in the list
     */
    public String deleteHandle(String input) throws BuzzException {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= data.size()) {
            throw new BuzzException("OOPS!!! The task doesn't exist *BEEP*");
        }
        Task task = data.remove(index);
        return "*POOF* Circuits fried! Deleted the mission.\n" + task.toString() + "\n"
                + "You currently have " + data.size() + " missions available *reeeee*";
    }

    /**
     * Handles the "todo" type task from the user and adds it into the tasklist
     *
     * @param input user input that is being passed to the chatbot to add to the list
     * @throws BuzzException if the description of the task was not specified
     */
    public String todoHandle(String input) throws BuzzException {
        if (input.length() <= 5) {
            throw new BuzzException("NOOO! Description is empty *crash*");
        }
        Task task = new Todo(input.substring(5));
        data.add(task);
        return "Understood boss. Added!\n" + task.toString() + "\n"
                + "You currently have " + data.size() + " missions available *reeeee*";
    }

    /**
     * Handles the addition of a "deadline" task into the list
     *
     * @param input user input that is being passed to the chatbot to add as a deadline
     * @throws BuzzException if the description of the deadline task is empty
     */
    public String deadlineHandle(String input) throws BuzzException {
        String[] parts = input.split("/by");
        if (parts.length < 2 || parts[0].length() <= 9) {
            throw new BuzzException("NOOO! Description is empty *crash*");
        }
        Task task = new Deadline(parts[0].substring(9), parts[1].trim());
        data.add(task);
        return "Understood boss. Added!\n" + task.toString() + "\n"
                + "You currently have " + data.size() + " missions available *reeeee*";
    }

    /**
     * Handles the "event" task type and adds it into the list as an event
     *
     * @param input user input that is being passed to the chatbot to add as an event
     * @throws BuzzException if description of the event is empty
     */
    public String eventHandle(String input) throws BuzzException {
        String[] parts = input.split("/");
        if (parts.length < 3 || parts[0].length() <= 6) {
            throw new BuzzException("OOPS!!! The description of an event cannot be empty.");
        }
        Task task = new Event(parts[0].substring(6), parts[1].substring(5), parts[2].substring(3));
        data.add(task);
        return "Understood boss. Added!\n" + task.toString() + "\n"
                + "You currently have " + data.size() + " missions available *reeeee*";
    }

    /**
     * Handles the find command by the user and tries to find the corresponding keyword.
     *
     * @param input user input provided to the chatbot
     * @throws BuzzException if description is empty
     */
    public String findHandle(String input) throws BuzzException {
        StringBuilder result = new StringBuilder("I have found a few matches sir! *wooop*\n");
        if (input.length() <= 5) {
            throw new BuzzException("NOOO! Description is empty *crash*");
        }
        String wordSearch = input.substring(5).trim();
        int tasks = 1;
        for (int i = 0; i < data.size(); i++) {
            Task task = data.get(i);
            if (task.description.contains(wordSearch)) {
                result.append(tasks).append(".").append(task.toString());
                tasks++;
            }
        }
        if (tasks == 1) {
            return "Sorry boss can't find anything :(";
        }
        return result.toString();
    }
}
