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
     *     <code>false</code> which means the conversation continues
     */

    public String parse(String textInput) throws TaskDescriptionEmptyException {
        if (textInput.equals("bye")) {
            return replyGoodbye();
        } else if (textInput.equals(("list"))) {
            return replyWithListOfTextsEntered();
        } else if (textInput.startsWith("mark")) {
            int taskIndex = Integer.parseInt(textInput.substring(5)) - 1;
            return replyAndMarkTaskDone(taskIndex);
        } else if (textInput.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(textInput.substring(7)) - 1;
            return replyAndMarkTaskUndone(taskIndex);
        } else if (textInput.startsWith("delete")) {
            int taskIndex = Integer.parseInt(textInput.substring(7)) - 1;
            return replyAndDeleteTask(taskIndex);
        } else if (textInput.startsWith("todo")) {
            try {
                int taskDescriptionStart = 5;
                String taskDescription = textInput.substring(taskDescriptionStart);

                if (taskDescription.isEmpty()) {
                    throw new TaskDescriptionEmptyException();
                }
                return replyAndAddTaskToList(taskDescription);
            } catch (StringIndexOutOfBoundsException e) {
                throw new TaskDescriptionEmptyException();
            }
        } else if (textInput.startsWith("deadline")) {
            try {
                int byIndex = textInput.indexOf("/by");

                int taskDescriptionStart = 9;
                int taskDescriptionEnd = byIndex - 1;
                String taskDescription = textInput.substring(taskDescriptionStart, taskDescriptionEnd);

                int deadlineStart = byIndex + 4;
                String deadline = textInput.substring(deadlineStart);

                if (taskDescription.isEmpty()) {
                    throw new TaskDescriptionEmptyException();
                }

                return replyAndAddTaskToList(taskDescription, deadline);
            } catch (StringIndexOutOfBoundsException e) {
                throw new TaskDescriptionEmptyException();
            }
        } else if (textInput.startsWith("event")) {
            try {
                int fromIndex = textInput.indexOf("/from");
                int toIndex = textInput.indexOf("/to");

                int taskDescriptionStart = 6;
                int taskDescriptionEnd = fromIndex - 1;
                String taskDescription = textInput.substring(taskDescriptionStart, taskDescriptionEnd);

                int startDateStart = fromIndex + 6;
                int startDateEnd = toIndex - 1;
                String startDate = textInput.substring(startDateStart, startDateEnd);

                int endDateStart = toIndex + 4;
                String endDate = textInput.substring(endDateStart);

                if (taskDescription.isEmpty()) {
                    throw new TaskDescriptionEmptyException();
                }

                return replyAndAddTaskToList(taskDescription, startDate, endDate);
            } catch (StringIndexOutOfBoundsException e) {
                throw new TaskDescriptionEmptyException();
            }
        } else if (textInput.startsWith("find")) {
            int keywordStart = 5;
            String keyword = textInput.substring(keywordStart);

            return this.taskList.findMatchingTasks(keyword);
        } else {
            return replyToInvalidInput();
        }
    }

    /**
     * Prints the goodbye message to the user
     */
    private String replyGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    private String addTaskToList(Task task) {
        this.taskList.add(task);
        assert this.taskList.toList().contains(task);
        return "Got it. I've added this task:\n" + task + "\nNow you have "
                + this.getNumTasks() + " tasks in the list.";
    }

    private String replyAndAddTaskToList(String textInput) {
        Task task = new ToDo(textInput);
        return this.addTaskToList(task);
    }

    private String replyAndAddTaskToList(String textInput, String deadline) {
        Task task = new Deadline(textInput, deadline);
        return this.addTaskToList(task);
    }

    private String replyAndAddTaskToList(String textInput, String startDate, String endDate) {
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

    private String replyAndMarkTaskDone(int index) {
        Task task = this.taskList.get(index);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n" + task;
    }

    private String replyAndMarkTaskUndone(int index) {
        Task task = this.taskList.get(index);
        task.unmarkAsDone();
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    private String replyToInvalidInput() {
        return "Please enter a valid input and try again! Some examples of valid inputs are:\n"
                + "todo [description]\ndeadline [description] /by [deadline]\n"
                + "event [description] /from [start time] /to [end time]";
    }

    private String replyAndDeleteTask(int index) {
        Task task = this.taskList.get(index);
        this.taskList.remove(task);

        return "Noted. I've removed this task:\n" + task + "\nNow you have "
                + this.getNumTasks() + " tasks in the list.";
    }

    private int getNumTasks() {
        return this.taskList.size();
    }
}
