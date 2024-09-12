package pochat.bot;

import pochat.exceptions.TaskDescriptionEmptyException;
import pochat.tasks.Deadline;
import pochat.tasks.Event;
import pochat.tasks.Task;
import pochat.tasks.ToDo;

class Parser {
    private final TaskList taskList;

    private Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public static Parser of(TaskList taskList) {
        return new Parser(taskList);
    }

    /**
     * Takes in the input and parses it to determine the
     * right response from the chatbot. Also updates the TaskList with any
     * new tasks created.
     *
     * @param textInput the String input from the user
     * @return <code>true</code> if the input is bye and the chat is over, otherwise return
     * <code>false</code> which means the conversation continues
     */

    public String parse(String textInput) throws TaskDescriptionEmptyException {
        if (textInput.equals("bye")) {
            return sayGoodbye();
        } else if (textInput.equals(("list"))) {
            return replyWithListOfTextsEntered();
        } else if (textInput.startsWith("mark")) {
            int taskIndex = Integer.parseInt(textInput.substring(5)) - 1;
            return markTaskDone(taskIndex);
        } else if (textInput.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(textInput.substring(7)) - 1;
            return unmarkTaskDone(taskIndex);
        } else if (textInput.startsWith("delete")) {
            int taskIndex = Integer.parseInt(textInput.substring(7)) - 1;
            return deleteTask(taskIndex);
        } else if (textInput.startsWith("todo")) {
            try {
                String taskDescription = textInput.substring(5);
                if (taskDescription.isEmpty()) {
                    throw new TaskDescriptionEmptyException();
                }
                return addToListAndReply(taskDescription);
            } catch (StringIndexOutOfBoundsException e) {
                throw new TaskDescriptionEmptyException();
            }
        } else if (textInput.startsWith("deadline")) {
            try {
                int byIndex = textInput.indexOf("/by");

                String taskDescription = textInput.substring(9, byIndex - 1);
                String deadline = textInput.substring(byIndex + 4);

                if (taskDescription.isEmpty()) {
                    throw new TaskDescriptionEmptyException();
                }

                return addToListAndReply(taskDescription, deadline);
            } catch (StringIndexOutOfBoundsException e) {
                throw new TaskDescriptionEmptyException();
            }
        } else if (textInput.startsWith("event")) {
            try {
                int fromIndex = textInput.indexOf("/from");
                int toIndex = textInput.indexOf("/to");

                String taskDescription = textInput.substring(6, fromIndex - 1);
                String startDate = textInput.substring(fromIndex + 6, toIndex - 1);
                String endDate = textInput.substring(toIndex + 4);

                if (taskDescription.isEmpty()) {
                    throw new TaskDescriptionEmptyException();
                }

                return addToListAndReply(taskDescription, startDate, endDate);
            } catch (StringIndexOutOfBoundsException e) {
                throw new TaskDescriptionEmptyException();
            }
        } else if (textInput.startsWith("find")) {
            String keyword = textInput.substring(5);
            System.out.println(keyword);
            return this.taskList.findMatchingTasks(keyword);
        } else {
            return replyToInvalidInput();
        }
    }

    /**
     * Prints the goodbye message to the user
     */
    private String sayGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    private String addTaskToList(Task task) {
        this.taskList.add(task);
        assert this.taskList.toList().contains(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have "
                + this.getNumTasks() + " tasks in the list.";
    }

    private String addToListAndReply(String textInput) {
        Task task = new ToDo(textInput);
        return this.addTaskToList(task);
    }

    private String addToListAndReply(String textInput, String deadline) {
        Task task = new Deadline(textInput, deadline);
        return this.addTaskToList(task);
    }

    private String addToListAndReply(String textInput, String startDate, String endDate) {
        Task task = new Event(textInput, startDate, endDate);
        return this.addTaskToList(task);
    }

    private String replyWithListOfTextsEntered() {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < this.taskList.size(); i++) {
            message.append((i + 1) + ". " + this.taskList.get(i) + "\n");
        }

        return message.toString();
    }

    private String markTaskDone(int index) {
        Task task = this.taskList.get(index);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n" + task;
    }

    private String unmarkTaskDone(int index) {
        Task task = this.taskList.get(index);
        task.unmarkAsDone();
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    private String replyToInvalidInput() {
        return "Please enter a valid input and try again! Some examples of valid inputs are:\n" +
                "todo [description]\ndeadline [description] /by [deadline]\n" +
                "event [description] /from [start time] /to [end time]";
    }

    private int getNumTasks() {
        return this.taskList.size();
    }

    private String deleteTask(int index) {
        Task task = this.taskList.get(index);
        this.taskList.remove(task);

        return "Noted. I've removed this task:\n" + task + "\nNow you have "
                + this.getNumTasks() + " tasks in the list.";
    }
}