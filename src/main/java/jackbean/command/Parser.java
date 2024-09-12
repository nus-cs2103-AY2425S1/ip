package jackbean.command;

import jackbean.exception.InvalidTaskTypeException;
import jackbean.exception.NotEnoughArgumentsException;
import jackbean.exception.TooManyArgumentsException;
import jackbean.task.Deadline;
import jackbean.task.Event;
import jackbean.task.TaskList;
import jackbean.task.Todo;

/**
 * A parser to interpret userInputs made by the user and return the appropriate response.
 * This parser is used by the JackBean class to handle user inputs.
 */
public class Parser {
    // GitHub copilot helped mostly with the response to help message
    private static final String helpMessage = "Yo homie! Here are the commands you can use:\n"
            + "1. list - to list all tasks\n"
            + "2. todo <description> - to add a todo task\n"
            + "3. deadline <description> /by <date> - to add a deadline task\n"
            + "4. event <description> /from <date> /to <date> - to add an event task\n"
            + "5. mark <task number> - to mark a task as done\n"
            + "6. unmark <task number> - to mark a task as undone\n"
            + "7. bye - to exit the program\n"
            + "8. help - to see this message again\n"
            + "9. delete <task number> - to delete a task\n"
            + "10. find <keyword> - to find tasks that match the keyword\n"
            + "11. maybe some easter eggs in the future? :) (eg. leo, lose to you)";

    /**
     * Parses the user input and returns the appropriate response.
     * This JavaDoc was written by GitHub Copilot.
     *
     * @param input    The user input to be parsed.
     * @param taskList The taskList to be updated based on the user input.
     * @return The response to the user input.
     * @throws NotEnoughArgumentsException If the user input does not have enough arguments.
     * @throws TooManyArgumentsException   If the user input has too many arguments.
     * @throws InvalidTaskTypeException    If the user input is not recognised.
     */
    public static String parseUserInput(String input, TaskList taskList)
            throws NotEnoughArgumentsException, TooManyArgumentsException, InvalidTaskTypeException {
        if (input.equalsIgnoreCase("help")) {
            return helpMessage;
        } else if (input.equalsIgnoreCase("list")) {
            return handleListCommand(taskList);
        } else if (input.toLowerCase().startsWith("mark")) {
            return handleMarkCommand(input, taskList);
        } else if (input.toLowerCase().startsWith("unmark")) {
            return handleUnmarkCommand(input, taskList);
        } else if (input.toLowerCase().startsWith("delete")) {
            return handleDeleteCommand(input, taskList);
        } else if (input.toLowerCase().startsWith("todo")) {
            return handleTodoCommand(input, taskList);
        } else if (input.toLowerCase().startsWith("deadline")) {
            return handleDeadlineCommand(input, taskList);
        } else if (input.toLowerCase().startsWith("event")) {
            return handleEventCommand(input, taskList);
        } else if (input.toLowerCase().startsWith("find")) {
            return handleFindCommand(input, taskList);
        } else {
            throw new InvalidTaskTypeException();
        }
    }

    private static String handleFindCommand(String input, TaskList taskList) {
        String keyword = input.substring(5).toLowerCase();
        StringBuilder reply = new StringBuilder("Yo homie!"
                + "Here are the tasks in your list that match your keyword:");
        int count = 0; // keep track of how many tasks match the keyword
        for (int i = 1; i <= taskList.getSize(); i++) {
            if (taskList.getTask(i).toString().toLowerCase().contains(keyword)) {
                count++;
                reply.append("\n").append(count).append(". ").append(taskList.getTask(i));
            } else if (input.toLowerCase().contains("deadline") && taskList.getTask(i) instanceof Deadline) {
                count++;
                reply.append("\n").append(count).append(". ").append(taskList.getTask(i));
            } else if (input.toLowerCase().contains("event") && taskList.getTask(i) instanceof Event) {
                count++;
                reply.append("\n").append(count).append(". ").append(taskList.getTask(i));
            } else if (input.toLowerCase().contains("todo") && taskList.getTask(i) instanceof Todo) {
                count++;
                reply.append("\n").append(count).append(". ").append(taskList.getTask(i));
            }
        }
        if (count == 0) {
            return "Sorry homie, I couldn't find any tasks that match your keyword :(";
        }
        return reply.toString();
    }

    private static String handleEventCommand(String input, TaskList taskList) throws TooManyArgumentsException, NotEnoughArgumentsException {
        String important = input.substring(6);
        String[] splitImportant = important.split(" /");
        if (splitImportant.length > 3) {
            throw new TooManyArgumentsException("event", "too many arguments");
        } else if (splitImportant.length < 3) {
            throw new NotEnoughArgumentsException("event", "not enough arguments");
        }
        String description = splitImportant[0];
        String from = splitImportant[1].substring(5);
        String to = splitImportant[2].substring(3);


        taskList.addTask(new Event(description, from, to));
        Storage.updateStorage(taskList);
        return "Got it homie! I've added your event, LESGOOOOO:\n"
                + taskList.getTask(taskList.getSize()) + "\n" + taskList.howManyTasks();
    }

    private static String handleDeadlineCommand(String input, TaskList taskList) throws TooManyArgumentsException, NotEnoughArgumentsException {
        String important = input.substring(9);
        String[] splitImportant = important.split(" /");
        if (splitImportant.length > 2) {
            throw new TooManyArgumentsException("deadline", "too many arguments");
        } else if (splitImportant.length < 2) {
            throw new NotEnoughArgumentsException("deadline", "not enough arguments");
        }
        String description = splitImportant[0];
        String by = splitImportant[1].substring(3);

        taskList.addTask(new Deadline(description, by));
        Storage.updateStorage(taskList);
        return "Got it homie! I've added your deadline, LESGOOOOO:\n"
                + taskList.getTask(taskList.getSize()) + "\n" + taskList.howManyTasks();
    }

    private static String handleTodoCommand(String input, TaskList taskList) throws NotEnoughArgumentsException {
        if (input.length() < 6) {
            throw new NotEnoughArgumentsException("todo", "not enough arguments");
        }
        String description = input.substring(5);
        taskList.addTask(new Todo(description));
        Storage.updateStorage(taskList);
        return "Got it homie! I've added your todo, LESGOOOOO:\n" + taskList.getTask(taskList.getSize())
                + "\n" + taskList.howManyTasks();
    }

    private static String handleDeleteCommand(String input, TaskList taskList) {
        // first use split and then parse the integer
        String[] splitInput = input.split(" ");
        int taskNumber = Integer.parseInt(splitInput[1]);

        StringBuilder reply = new StringBuilder();
        // now handle implementation
        reply.append("Got it my homie! I've removed this task:\n").append(taskList.getTask(taskNumber));
        taskList.removeTask(taskNumber);
        Storage.updateStorage(taskList);
        reply.append("\n").append(taskList.howManyTasks());
        return reply.toString();
    }

    private static String handleUnmarkCommand(String input, TaskList taskList) {
        // first use split and then parse the integer
        String[] splitInput = input.split(" ");
        int taskNumber = Integer.parseInt(splitInput[1]);

        // check if task is already undone
        if (!taskList.getTask(taskNumber).isDone()) {
            return "This task hasn't been marked done yet my homie:\n" + taskList.getTask(taskNumber);
        }

        // now handle implementation
        taskList.markTaskAsUndone(taskNumber);
        Storage.updateStorage(taskList);
        return "All good my homie! You've got this, I've undone this task:\n"
                + taskList.getTask(taskNumber);
    }

    private static String handleMarkCommand(String input, TaskList taskList) {
        // first use split and then parse the integer
        String[] splitInput = input.split(" ");
        int taskNumber = Integer.parseInt(splitInput[1]);

        // check if task is already done
        if (taskList.getTask(taskNumber).isDone()) {
            return "This task is already done my homie:\n" + taskList.getTask(taskNumber);
        }

        // now handle implementation
        taskList.markTaskAsDone(taskNumber);
        Storage.updateStorage(taskList);
        return "LESGOOO homie! Good job on finishing this task:\n"
                + taskList.getTask(taskNumber);
    }

    private static String handleListCommand(TaskList taskList) {
        StringBuilder reply = new StringBuilder("Yo homie!, here are the tasks in your list:");
        for (int i = 1; i <= taskList.getSize(); i++) {
            reply.append("\n").append(i).append(". ").append(taskList.getTask(i));
        }
        return reply.toString();
    }
}
