package yapyap;

import java.util.ArrayList;

/**
 * The Parser class is responsible for interpreting user input and executing the appropriate commands.
 */
public class Parser {

    /**
     * Parses and executes the user command, returning the response as a string.
     *
     * @param userInput The raw input from the user.
     * @param tasks The TaskList object to manipulate tasks.
     * @param ui The Ui object to handle user interactions.
     * @param storage The Storage object to handle saving and loading of tasks.
     * @return A string response to the user command.
     */
    public String executeCommandAndGetResponse(String userInput, TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();
        Commands command = parseCommand(userInput);
        assert command != null : "Parsed command should not be null";

        switch (command) {
        case BYE:
            storage.saveTasks(tasks.getTasks());
            response.append("Bye. Hope to see you again soon!\n");
            break;
        case LIST:
            response.append("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                response.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
            break;
        case MARK:
            response.append(markCommand(tasks, userInput, storage));
            break;
        case UNMARK:
            response.append(unmarkCommand(tasks, userInput, storage));
            break;
        case TODO:
            response.append(todoCommand(tasks, userInput, storage));
            break;
        case DEADLINE:
            response.append(deadlineCommand(tasks, userInput, storage));
            break;
        case EVENT:
            response.append(eventCommand(tasks, userInput, storage));
            break;
        case DELETE:
            response.append(deleteCommand(tasks, userInput, storage));
            break;
        case FIND:
            response.append(findCommand(tasks, userInput));
            break;
        default:
            response.append("IDK what you are yapping about!!\n");
        }
        return response.toString();
    }

    /**
     * Parses the user input and returns the corresponding command as a {@link Commands} enum.
     *
     * @param userInput The input string from the user.
     * @return The corresponding command as a {@link Commands} enum.
     */
    public Commands parseCommand(String userInput) {
        assert userInput != null : "User input should not be null";
        assert !userInput.trim().isEmpty() : "User input should not be empty";

        if (userInput.equals("bye")) {
            return Commands.BYE;
        } else if (userInput.equals("list")) {
            return Commands.LIST;
        } else if (userInput.startsWith("mark")) {
            return Commands.MARK;
        } else if (userInput.startsWith("unmark")) {
            return Commands.UNMARK;
        } else if (userInput.startsWith("todo")) {
            return Commands.TODO;
        } else if (userInput.startsWith("deadline")) {
            return Commands.DEADLINE;
        } else if (userInput.startsWith("event")) {
            return Commands.EVENT;
        } else if (userInput.startsWith("delete")) {
            return Commands.DELETE;
        } else if (userInput.startsWith("find")) {
            return Commands.FIND;
        } else {
            return Commands.OTHERS;
        }
    }

    /**
     * Marks a task as done based on user input, updates the storage, and returns the result as a string.
     *
     * @param tasks The current list of tasks.
     * @param userInput The input string by the user.
     * @param storage The storage used to save the updated tasks list.
     * @return A string response indicating the result of the mark command.
     */
    public String markCommand(TaskList tasks, String userInput, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        assert userInput != null : "User input should not be null";
        assert userInput.startsWith("mark") : "User input should start with 'mark'";
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            assert index >= 0 && index < tasks.size() : "Index should be within the range of task list size";
            tasks.get(index).mark();
            storage.saveTasks(tasks.getTasks());
            return "Nice! I've marked this task as done:\n"
                    + "  " + tasks.get(index) + "\n";
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Invalid task number! Please enter a valid task number, between 1 - " + tasks.size() + "\n";
        }
    }

    /**
     * Unmarks a task as not done based on the user input, updates the storage, and returns the result as a string.
     *
     * @param tasks The current list of tasks.
     * @param userInput The input string from the user.
     * @param storage The storage to save the updated tasks list.
     * @return A string response indicating the result of the unmark command.
     */
    public String unmarkCommand(TaskList tasks, String userInput, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        assert userInput != null : "User input should not be null";
        assert userInput.startsWith("unmark") : "User input should start with 'unmark'";
        try {
            int index = Integer.parseInt(userInput.split(" ")[1]) - 1;
            tasks.get(index).unmark();
            storage.saveTasks(tasks.getTasks());
            return "OK, I've marked this task as not done yet:\n"
                    + "  " + tasks.get(index) + "\n";
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Invalid task number! Please enter a valid task number, between 1 - " + tasks.size() + "\n";
        }
    }

    /**
     * Adds a new Todo task based on the user input, updates the storage, and returns the result as a string.
     *
     * @param tasks The current list of tasks.
     * @param userInput The input string from the user.
     * @param storage The storage to save the updated tasks list.
     * @return A string response indicating the result of the todo command.
     */
    public String todoCommand(TaskList tasks, String userInput, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        assert userInput != null : "User input should not be null";
        assert userInput.startsWith("todo") : "User input should start with 'todo'";
        try {
            String todoDescription = userInput.substring(5).trim();
            if (todoDescription.isEmpty()) {
                throw new YapperBotException("The description for a Todo task cannot be empty.");
            }
            int initialSize = tasks.size();
            tasks.addTask(new Todo(todoDescription));
            assert tasks.size() == initialSize + 1 : "Task list size should increase by 1 after adding a task";
            storage.saveTasks(tasks.getTasks());
            return "Got it. I've added this task:\n"
                    + "  " + tasks.get(tasks.size() - 1) + "\n"
                    + "Now you have " + tasks.size() + " task(s) in the list.\n";
        } catch (YapperBotException e) {
            return e.getMessage() + "\n";
        } catch (StringIndexOutOfBoundsException e2) {
            return "The description for a Todo task cannot be empty.\n";
        }
    }

    /**
     * Adds a new Deadline task based on the user input, updates the storage, and returns the result as a string.
     * The input must include a description and a valid deadline format.
     *
     * @param tasks The current list of tasks.
     * @param userInput The input string from the user.
     * @param storage The storage to save the updated tasks list.
     * @return A string response indicating the result of the deadline command.
     */
    public String deadlineCommand(TaskList tasks, String userInput, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        assert userInput != null : "User input should not be null";
        assert userInput.startsWith("deadline") : "User input should start with 'deadline'";
        try {
            String[] deadlineInput = userInput.substring(9).split(" /by ");
            if (deadlineInput.length < 2 || deadlineInput[0].trim().isEmpty()) {
                throw new YapperBotException("You need to provide a description with a deadline "
                        + "in the following formats:\n"
                        + "Eg. deadline play bball /by 2/12/2024 1800 or deadline return book /by 2024-12-02");
            }
            String deadlineDescription = deadlineInput[0];
            String by = deadlineInput[1];
            tasks.addTask(new Deadline(deadlineDescription, by));
            storage.saveTasks(tasks.getTasks());
            return "Got it. I've added this task:\n"
                    + "  " + tasks.get(tasks.size() - 1) + "\n"
                    + "Now you have " + tasks.size() + " task(s) in the list.\n";
        } catch (YapperBotException e) {
            return e.getMessage() + "\n";
        } catch (StringIndexOutOfBoundsException e2) {
            return "The description for a Deadline task cannot be empty.\n";
        }
    }

    /**
     * Adds a new Event task based on the user input, updates the storage, and returns the result as a string.
     * The input must include a description and valid time frames for the event.
     *
     * @param tasks The current list of tasks.
     * @param userInput The input string from the user.
     * @param storage The storage to save the updated tasks list.
     * @return A string response indicating the result of the event command.
     */
    public String eventCommand(TaskList tasks, String userInput, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        assert userInput != null : "User input should not be null";
        assert userInput.startsWith("event") : "User input should start with 'event'";
        try {
            String[] eventInput = userInput.substring(6).split(" /from | /to ");
            if (eventInput.length < 3 || eventInput[0].trim().isEmpty()) {
                throw new YapperBotException("You need to provide a description with a time frame "
                        + "in the following format:\n"
                        + "Eg. event play bball /from 2/12/2024 1400 /to 2/12/2024 1600");
            }
            String eventDescription = eventInput[0];
            String from = eventInput[1];
            String to = eventInput[2];
            tasks.addTask(new Event(eventDescription, from, to));
            storage.saveTasks(tasks.getTasks());
            return "Got it. I've added this task:\n"
                    + "  " + tasks.get(tasks.size() - 1) + "\n"
                    + "Now you have " + tasks.size() + " task(s) in the list.\n";
        } catch (YapperBotException e) {
            return e.getMessage() + "\n";
        } catch (StringIndexOutOfBoundsException e2) {
            return "The description for an Event task cannot be empty.\n";
        }
    }

    /**
     * Deletes a task based on the user input, updates the storage, and returns the result as a string.
     *
     * @param tasks The current list of tasks.
     * @param userInput The input string from the user.
     * @param storage The storage to save the updated tasks list.
     * @return A string response indicating the result of the delete command.
     */
    public String deleteCommand(TaskList tasks, String userInput, Storage storage) {
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";
        assert userInput != null : "User input should not be null";
        assert userInput.startsWith("delete") : "User input should start with 'delete'";
        try {
            int deleteTaskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            Task deleteTargetTask = tasks.get(deleteTaskNumber);
            tasks.deleteTask(deleteTargetTask);
            storage.saveTasks(tasks.getTasks());
            return "Noted. I've removed this task:\n"
                    + "  " + deleteTargetTask + "\n"
                    + "Now you have " + tasks.size() + " task(s) in the list.\n";
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Invalid task number! Please enter a valid task number, between 1 - " + tasks.size() + "\n";
        }
    }

    /**
     * Handles the find command by searching for tasks containing the specified keyword and returns the result.
     *
     * @param tasks The list of tasks to search within.
     * @param userInput The user input containing the find command and keyword.
     * @return A string response with the list of matching tasks.
     */
    public String findCommand(TaskList tasks, String userInput) {
        assert tasks != null : "TaskList should not be null";
        assert userInput != null : "User input should not be null";
        assert userInput.startsWith("find") : "User input should start with 'find'";
        try {
            String keyword = userInput.split(" ")[1].trim();
            ArrayList<Task> matchingTasks = new ArrayList<>();
            StringBuilder response = new StringBuilder();

            for (Task task : tasks.getTasks()) {
                if (task.getDescription().contains(keyword)) {
                    matchingTasks.add(task);
                }
            }

            if (matchingTasks.isEmpty()) {
                response.append("No matching tasks found.\n");
            } else {
                response.append("Here are the matching tasks in your list:\n");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    response.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
                }
            }
            return response.toString();

        } catch (IndexOutOfBoundsException e) {
            return "You must enter a keyword after 'find' to match tasks!\n";
        }
    }
}
