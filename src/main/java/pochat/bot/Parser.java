package pochat.bot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import pochat.exceptions.DateTimeInvalidException;
import pochat.exceptions.DeadlineFormatInvalidException;
import pochat.exceptions.EventFormatInvalidException;
import pochat.exceptions.TaskDescriptionEmptyException;
import pochat.exceptions.TaskIndexInvalidException;
import pochat.tasks.Deadline;
import pochat.tasks.Event;
import pochat.tasks.Task;
import pochat.tasks.ToDo;

class Parser {
    private final TaskList taskList;
    private final Ui ui = new Ui();

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

    public String parse(String textInput) throws DeadlineFormatInvalidException,
            DateTimeInvalidException, TaskDescriptionEmptyException, EventFormatInvalidException {
        if (textInput.equals("bye")) {
            return replyGoodbye();
        } else if (textInput.equals(("list"))) {
            return replyWithListOfTasks();
        } else if (textInput.startsWith("mark")) {
            return replyAndMarkTaskDone(textInput);
        } else if (textInput.startsWith("unmark")) {
            return replyAndMarkTaskUndone(textInput);
        } else if (textInput.startsWith("delete")) {
            return replyAndDeleteTask(textInput);
        } else if (textInput.startsWith("todo")) {
            return replyAndAddToDo(textInput);
        } else if (textInput.startsWith("deadline")) {
            return replyAndAddDeadline(textInput);
        } else if (textInput.startsWith("event")) {
            return replyAndAddEvent(textInput);
        } else if (textInput.startsWith("find")) {
            return replyToFind(textInput);
        } else {
            return replyToInvalidInput();
        }
    }

    private String replyGoodbye() {
        return this.ui.getGoodbyeMessage();
    }

    private String addTask(Task task) {
        if (this.taskList.contains(task)) {
            return this.ui.getDuplicateTaskMessage();
        }

        this.taskList.add(task);
        assert taskList.toList().contains(task);
        return this.ui.getAddTaskMessage(task, this.getNumTasks());
    }

    private String replyAndAddTask(String textInput) {
        Task task = new ToDo(textInput);
        return this.addTask(task);
    }

    private String replyAndAddTask(String textInput, String deadline) {
        Task task = new Deadline(textInput, deadline);
        return this.addTask(task);
    }

    private String replyAndAddTask(String textInput, String startDate, String endDate) {
        Task task = new Event(textInput, startDate, endDate);
        return this.addTask(task);
    }

    private String replyWithListOfTasks() {
        return this.ui.getListOfTasks(this.taskList);
    }

    private String replyAndMarkTaskDone(String textInput) {
        try {
            int indexPosition = 4;
            int taskIndex = Integer.parseInt(textInput.substring(indexPosition).trim()) - 1;
            Task task = this.taskList.markTaskAsDone(taskIndex);
            return this.ui.getMarkTaskDoneMessage(task);
        } catch (TaskIndexInvalidException | NumberFormatException e) {
            return this.ui.getInvalidIndexMessage();
        }
    }

    private String replyAndMarkTaskUndone(String textInput) {
        try {
            int indexPosition = 6;
            int taskIndex = Integer.parseInt(textInput.substring(indexPosition).trim()) - 1;
            Task task = this.taskList.unmarkTaskAsDone(taskIndex);
            return this.ui.getMarkTaskUndoneMessage(task);
        } catch (TaskIndexInvalidException | NumberFormatException e) {
            return this.ui.getInvalidIndexMessage();
        }
    }

    private String replyToInvalidInput() {
        return this.ui.getInvalidInputMessage();
    }

    private String replyAndDeleteTask(String textInput) {
        try {
            int indexPosition = 6;
            int taskIndex = Integer.parseInt(textInput.substring(indexPosition).trim()) - 1;
            Task task = this.taskList.remove(taskIndex);

            return ui.getDeleteTaskMessage(task, this.getNumTasks());
        } catch (TaskIndexInvalidException | NumberFormatException e) {
            return this.ui.getInvalidIndexMessage();
        }
    }

    private String replyAndAddToDo(String textInput) throws TaskDescriptionEmptyException {
        try {
            int taskDescriptionStart = 4;
            String taskDescription = textInput.substring(taskDescriptionStart).trim();

            if (taskDescription.isEmpty()) {
                throw new TaskDescriptionEmptyException();
            }

            return replyAndAddTask(taskDescription);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TaskDescriptionEmptyException();
        }
    }

    private String replyAndAddDeadline(String textInput) throws DeadlineFormatInvalidException,
            DateTimeInvalidException {
        try {
            int byIndex = textInput.indexOf("/by");

            int taskDescriptionStart = 8;
            int taskDescriptionEnd = byIndex;
            String taskDescription = textInput.substring(taskDescriptionStart, taskDescriptionEnd).trim();

            int deadlineStart = byIndex + 3;
            String deadline = textInput.substring(deadlineStart).trim();

            if (taskDescription.isEmpty()) {
                throw new DeadlineFormatInvalidException();
            } else if (isInvalidDateTime((deadline))) {
                throw new DateTimeInvalidException();
            }

            return replyAndAddTask(taskDescription, deadline);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DeadlineFormatInvalidException();
        }
    }

    private String replyAndAddEvent(String textInput) throws DateTimeInvalidException,
            EventFormatInvalidException {
        try {
            int fromIndex = textInput.indexOf("/from");
            int toIndex = textInput.indexOf("/to");

            int taskDescriptionStart = 5;
            int taskDescriptionEnd = fromIndex;
            String taskDescription = textInput.substring(taskDescriptionStart, taskDescriptionEnd).trim();

            int startDateStart = fromIndex + 5;
            int startDateEnd = toIndex;
            String startDate = textInput.substring(startDateStart, startDateEnd).trim();

            int endDateStart = toIndex + 3;
            String endDate = textInput.substring(endDateStart).trim();

            if (taskDescription.isEmpty()) {
                throw new EventFormatInvalidException();
            } else if (isInvalidDateTime(startDate) || isInvalidDateTime(endDate)) {
                throw new DateTimeInvalidException();
            }

            return replyAndAddTask(taskDescription, startDate, endDate);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EventFormatInvalidException();
        }
    }

    private String replyToFind(String textInput) {
        int keywordStart = 4;
        String keyword = textInput.substring(keywordStart).trim();
        return this.ui.findMatchingTasks(keyword, this.taskList);
    }

    private boolean isInvalidDateTime(String dateTime) {
        try {
            LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            return false;
        } catch (DateTimeParseException e) {
            return true;
        }
    }

    int getNumTasks() {
        return this.taskList.size();
    }
}
