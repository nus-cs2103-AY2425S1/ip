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
            int descriptionStartIndex = 5;
            int taskIndex = Integer.parseInt(textInput.substring(descriptionStartIndex)) - 1;
            return replyAndMarkTaskDone(taskIndex);
        } else if (textInput.startsWith("unmark")) {
            int descriptionStartIndex = 7;
            int taskIndex = Integer.parseInt(textInput.substring(descriptionStartIndex)) - 1;
            return replyAndMarkTaskUndone(taskIndex);
        } else if (textInput.startsWith("delete")) {
            int descriptionStartIndex = 7;
            int taskIndex = Integer.parseInt(textInput.substring(descriptionStartIndex)) - 1;
            return replyAndDeleteTask(taskIndex);
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

    private String replyAndMarkTaskDone(int index) {
        try {
            Task task = this.taskList.markTaskAsDone(index);
            return this.ui.getMarkTaskDoneMessage(task);
        } catch (TaskIndexInvalidException e) {
            return this.ui.getInvalidIndexMessage();
        }
    }

    private String replyAndMarkTaskUndone(int index) {
        try {
            Task task = this.taskList.unmarkTaskAsDone(index);
            return this.ui.getMarkTaskUndoneMessage(task);
        } catch (TaskIndexInvalidException e) {
            return this.ui.getInvalidIndexMessage();
        }
    }

    private String replyToInvalidInput() {
        return this.ui.getInvalidInputMessage();
    }

    private String replyAndDeleteTask(int index) {
        try {
            Task task = this.taskList.remove(index);
            return ui.getDeleteTaskMessage(task, this.getNumTasks());
        } catch (TaskIndexInvalidException e) {
            return this.ui.getInvalidIndexMessage();
        }
    }

    private String replyAndAddToDo(String textInput) throws TaskDescriptionEmptyException {
        try {
            int taskDescriptionStart = 5;
            String taskDescription = textInput.substring(taskDescriptionStart);

            return replyAndAddTask(taskDescription);
        } catch (StringIndexOutOfBoundsException e) {
            throw new TaskDescriptionEmptyException();
        }
    }

    private String replyAndAddDeadline(String textInput) throws DeadlineFormatInvalidException,
            DateTimeInvalidException {
        try {
            int byIndex = textInput.indexOf("/by");

            int taskDescriptionStart = 9;
            int taskDescriptionEnd = byIndex - 1;
            String taskDescription = textInput.substring(taskDescriptionStart, taskDescriptionEnd);

            int deadlineStart = byIndex + 4;
            String deadline = textInput.substring(deadlineStart);

            if (!isValidDateTime((deadline))) {
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

            int taskDescriptionStart = 6;
            int taskDescriptionEnd = fromIndex - 1;
            String taskDescription = textInput.substring(taskDescriptionStart, taskDescriptionEnd);

            int startDateStart = fromIndex + 6;
            int startDateEnd = toIndex - 1;
            String startDate = textInput.substring(startDateStart, startDateEnd);

            int endDateStart = toIndex + 4;
            String endDate = textInput.substring(endDateStart);

            if (!isValidDateTime(startDate) || !isValidDateTime(endDate)) {
                throw new DateTimeInvalidException();
            }

            return replyAndAddTask(taskDescription, startDate, endDate);
        } catch (StringIndexOutOfBoundsException e) {
            throw new EventFormatInvalidException();
        }
    }

    private String replyToFind(String textInput) {
        int keywordStart = 5;
        String keyword = textInput.substring(keywordStart);
        return this.ui.findMatchingTasks(keyword, this.taskList);
    }

    private boolean isValidDateTime(String dateTime) {
        try {
            LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    int getNumTasks() {
        return this.taskList.size();
    }
}
