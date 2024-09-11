package slave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Parser is an object that scans the user's input and performs the
 * corresponding action
 */
public class Parser {
    private List<Task> list;

    public enum TaskType {
        TODO,
        EVENT,
        DEADLINE
    }

    public Parser(List<Task> list) {
        this.list = list;
    }

    /**
     * Carries out the corresponding action to the user's input.
     * <p>
     * <<<<<<< HEAD
     *
     * @return a Pair indicating if there are more actions, and if there is a need for storage to call the save() method
     * =======
     * @return Slave's response to the user's input.
     * >>>>>>> Level-10
     */
    public String[] handleUserInput(String input) {
        try {
            Scanner inputScanner = new Scanner(input);
            String command = inputScanner.next();
            String body = "";
            if (inputScanner.hasNextLine()) {
                body = inputScanner.nextLine().substring(1);
            }
            inputScanner.close();
            switch (command) {
            case "bye":
                return new String[]{Ui.goodbye()};
            case "list":
                return listTasks();
            case "mark":
                return new String[]{markAsDone(body)};
            case "unmark":
                return new String[]{markAsIncomplete(body)};
            case "todo":
                return addToList(TaskType.TODO, body);
            case "deadline":
                return addToList(TaskType.DEADLINE, body);
            case "event":
                return addToList(TaskType.EVENT, body);
            case "delete":
                return deleteTask(body);
            case "clear":
                return clear();
            case "schedule":
                return scheduleOn(body);
            case "find":
                return find(body);
            default:
                return new String[]{"You're spouting gibberish..."};
            }

        } catch (NoSuchElementException e) {
            // handle empty inputs / only spaces (" ")
            // wait for next user input
            // no need to save as nothing has been done
            return new String[]{"Why are you so quiet?"};
        }

    }


    /**
     * Lists all the tasks in the list.
     *
     * @return a String representation of all the tasks in the list.
     */
    protected String[] listTasks() {
        String[] result = new String[2];
        result[0] = ("Can you not even remember the things you need to do?" +
                " That should be your job, not mine!\n");
        if (list.isEmpty()) {
            result[1] = ("You don't have anything on your list, and you can't even remember that?");
            return result;
        }
        result[1] = getTasksInList();
        return result;
    }

    /**
     * Genereates the String representation of all the tasks in the list.
     *
     * @return a string of all the tasks in the list.
     */
    protected String getTasksInList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(i + 1);
            sb.append(".");
            sb.append(list.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Marks the task as done by changing the Task's isCompleted value
     *
     * @param s is the index of the task as a String.
     * @return Slave's response to the user.
     */
    protected String markAsDone(String s) {
        try {
            int i = Integer.parseInt(s);

            if (i < 1 || i > list.size()) {
                return "You don't have a task number " + i;
            }
            Task t = list.get(i - 1);
            t.setAsCompleted();
            return "Finally doing something useful with your life eh...\n" + t;
        } catch (NumberFormatException nfe) {
            return "That's not a task number";
        }
    }

    /**
     * Marks the task as incomplete by changing the Task's isCompleted value.
     *
     * @param s is the index of the task as a String.
     * @return Slave's response to the user.
     */
    protected String markAsIncomplete(String s) {
        try {
            int i = Integer.parseInt(s);
            if (i < 1 || i > list.size()) {
                return "You don't have a task number " + i;
            }
            Task t = list.get(i - 1);
            t.setAsIncomplete();
            return "Slacking off now, are you?\n" + t;
        } catch (NumberFormatException nfe) {
            return "That's not a task number";
        }
    }

    /**
     * Adds the task to the list of tasks.
     *
     * @param task        is the type of task to be addded.
     * @param description is the details of the task to be added.
     * @return Slave's response to the user.
     */
    protected String[] addToList(TaskType task, String description) {
        /*
        0 - Todo
        1 - Deadline
        2 - Event
         */
        try {
            switch (task) {
            case TODO:
                // todo
                addTodoToList(description);
                break;
            case DEADLINE:
                // deadline
                addDeadlineToList(description);
                break;
            case EVENT:
                // event
                addEventToList(description);
                break;
            default:
                return new String[]{"That's not a type of task... you're wasting my time!"};
            }

            String[] result = new String[2];
            result[0] = "Hey maybe try using some of that memory of yours to remember these things...";
            result[1] = "added: " + list.get(list.size() - 1).toString();
            return result;
        } catch (InvalidTaskFormatException e) {
            return new String[]{"Can you not even tell me all the details for your event? Do you even want my help?"};
        } catch (DateTimeParseException dtpe) {
            return new String[]{"Give me the date in yyyy-mm-dd or I won't remember it for you"};
        } catch (InvalidChronologicalOrderException icoe) {
            return new String[]{"How can your event end before it started?"};
        }
    }

    /**
     * Adds a Todo to the task list with the specified description.
     *
     * @param description is the name of the todo task.
     * @throws InvalidTaskFormatException when the description is empty.
     */
    protected void addTodoToList(String description) throws InvalidTaskFormatException {
        //@@ author Carl Smotricz -reused
        // String.trim() method used to remove all whitespaces from a string to check if the string only
        // contains whitespaces is reused from the StackOverflow reply
        if (description.isEmpty() || description.trim().isEmpty()) {
            //@@author
            throw new InvalidTaskFormatException("No task descriptor");
        }
        list.add(new Todo(description));
    }

    /**
     * Adds a Deadline to the task list with the specified description.
     *
     * @param description is the description of the deadline including task name and deadline.
     * @throws InvalidTaskFormatException when the description is empty or formatted incorrectly.
     */
    protected void addDeadlineToList(String description) throws InvalidTaskFormatException {
        String[] details = description.split(" /by ");
        if (details.length == 1) {
            throw new InvalidTaskFormatException("Error with input string format");
        }
        // only accepts time in format yyyy-mm-dd
        LocalDate by = LocalDate.parse(details[1]);
        list.add(new Deadline(details[0], by));
    }

    /**
     * Adds an event to the task list with the specified description.
     *
     * @param description is the description of the task including the task name, start and end date.
     * @throws InvalidTaskFormatException         when the description is empty or formatted incorrectly.
     * @throws InvalidChronologicalOrderException when the event starts after it ends.
     */
    protected void addEventToList(String description)
            throws InvalidTaskFormatException, InvalidChronologicalOrderException {
        // only accepts time in format yyyy-mm-dd
        String[] eventArr = description.split(" /from ");
        if (eventArr.length == 1) {
            throw new InvalidTaskFormatException("Missing event start date");
        }
        String[] startEndDate = eventArr[1].split(" /to ");
        if (startEndDate.length == 1) {
            throw new InvalidTaskFormatException("Missing event end date");
        }
        LocalDate start = LocalDate.parse(startEndDate[0]);
        LocalDate end = LocalDate.parse(startEndDate[1]);
        list.add(new Event(eventArr[0], start, end));
    }

    /**
     * Removes the task from the list of tasks.
     *
     * @param s is the index of the task as a String.
     * @return Slave's response to the user.
     */
    protected String[] deleteTask(String s) {
        try {
            int index = Integer.parseInt(s);
            if (index < 1 || index > list.size()) {
                return new String[]{"You don't have a task number " + index};
            }
            String[] result = new String[2];
            result[0] = "Good to know that I have less things to remember now...";
            result[1] = "I'll forget about " + list.get(index - 1);
            list.remove(index - 1);
            return result;
        } catch (NumberFormatException nfe) {
            return new String[]{"That's not a task number"};
        }
    }

    /**
     * Finds all the tasks with a description containing the substring.
     *
     * @param substring is the substring to be matched with the tasks' description.
     * @return a String representation of all the matching tasks.
     */
    protected String[] find(String substring) {
        // There is a slight difference in this implementation compared to ab3
        // In ab3, calling DELETE after FIND deletes the task with the index provided by FIND
        // However, in this version, calling in order to delete the task, the user has to use the original
        // index of the task, provided by LIST, instead of that provided by FIND

        if (substring.isEmpty() | substring.trim().isEmpty()) {
            return new String[]{"Quit wasting my time... Tell me what you want found"};
        }
        String[] result = new String[4];
        StringBuilder sb = new StringBuilder();

        result[0] = "What's the point of that brain of yours if you don't use it\n";
        result[1] = "Fine I'll look for tasks related to [" + substring + "]";

        int count = 1;

        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            if (t.getTask().contains(substring)) {
                sb.append(count);
                sb.append(". ");
                sb.append(t);
                sb.append("\n");
                count++;
            }
        }

        if (count == 1) {
            result[2] = "\nThere's nothing related to [" + substring + "]";
            result[3] = "Should have known better than to waste time trying to help you...";
        } else {
            result[2] = sb.toString();
            assert !result[2].isEmpty() : "task list should not be empty";
            result[3] = "That's all";
        }
        return result;
    }


    /**
     * Deletes all tasks from the list of tasks.
     *
     * @return Slave's response to the user.
     */
    protected String[] clear() {
        list.clear();
        String[] result = new String[2];
        result[0] = "Starting off on a clean slate now are we?";
        result[1] = "Guess your previous tasks were too much for you to handle";
        return result;
    }

    /**
     * Finds all the timed events (e.g. Deadlines / Events) that are happening / yet to be due on the provided date.
     *
     * @param date is the string representation of the date.
     * @return Slave's response containing
     * a String representation of all timed events yet happening / yet to be due on the provided date.
     */
    protected String[] scheduleOn(String date) {
        if (date.isEmpty()) {
            throw new NoSuchElementException();
        }
        LocalDate target = LocalDate.parse(date);
        String[] result = new String[3];
        result[0] = "Your tasks are :";
        try {
            String filteredTaskList = getActiveTasks(target);
            if (filteredTaskList.isEmpty()) {
                return new String[]{"You have no Tasks on or ending after " + date};
            }
            result[1] = filteredTaskList;
            assert !result[1].isEmpty() : "the list of tasks on or ending after the date should not be empty";
            result[2] = "That's all your tasks for " + target.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            return result;
        } catch (DateTimeParseException | NoSuchElementException e) {
            return new String[]{"Give me the date in yyyy-mm-dd or I won't check your schedule"};
        }
    }

    /**
     * Filters the task list for tasks which are ongoing, e.g. deadlines yet to be due, events occurring on the date.
     *
     * @param target is the date used as the search parameter.
     * @return a String representation of all ongoing tasks.
     */
    protected String getActiveTasks(LocalDate target) {
        StringBuilder sb = new StringBuilder();
        list.forEach(task -> {
            if (task instanceof Event) {
                if (((Event) task).getRawStart().isBefore(target) && ((Event) task).getRawEnd().isAfter(target)) {
                    sb.append(task);
                }
            } else if (task instanceof Deadline) {
                if (((Deadline) task).getRawDeadline().isAfter(target) ||
                        ((Deadline) task).getRawDeadline().isEqual(target)) {
                    sb.append(task);
                }
            }
            sb.append("\n");
        });
        return sb.toString();
    }
}
