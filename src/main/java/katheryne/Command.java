package katheryne;

import katheryne.exceptions.InvalidInputException;
import katheryne.exceptions.MissingInformationException;

public class Command {
    protected Ui ui;
    protected TaskList taskList;

    public Command(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public String executeList() {
        return ui.getListMessage() + taskList.toString();
    }


    /**
     * Mark corresponding task in the task list as done.
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getMarkMessage()
     */

    public String executeMark(String string)
            throws MissingInformationException, IndexOutOfBoundsException {
        try {
            String[] fullCommand;
            fullCommand = parseAndValidateCommand(string, "You need to specify the task number to mark.");

            int id = validateTaskIndex(fullCommand);
            taskList.mark(id);
            return ui.getMarkMessage(taskList.getTask(id));
        } catch (MissingInformationException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Unmark corresponding task in the task list as not done yet.
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getUnmarkMessage()
     */

    public String executeUnmark(String string)
            throws MissingInformationException, IndexOutOfBoundsException {
        try {
            String[] fullCommand;
            fullCommand = parseAndValidateCommand(string, "You need to specify the task number to mark.");
            int id = validateTaskIndex(fullCommand);
            taskList.unmark(id);
            return ui.getUnmarkMessage(taskList.getTask(id));
        } catch (MissingInformationException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    /**
     * Add a ToDo Task to the TaskList
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getAddMessage()
     */
    public String executeAddToDo(String string)
            throws MissingInformationException {
        try {
            String[] fullCommand = parseAndValidateCommand(string, "You need to specify the description of a todo task.");
            String des = Parser.getToDoDes(string);
            ToDo todo = new ToDo(des);
            return checkDuplicate(todo);
        } catch (MissingInformationException e) {
            return e.getMessage();
        }
    }


    /**
     * Add an Event Task to the TaskList
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getAddMessage()
     */

    public String executeAddEvent(String string)
            throws MissingInformationException {
        try {
            String[] fullCommand = parseAndValidateCommand(string, "You need to specify the description of an event.");
        } catch (MissingInformationException e) {
            return e.getMessage();
        }
        String fromTime = Parser.getEventFromTime(string);
        String toTime = Parser.getEventToTime(string);
        String des = Parser.getEventDes(string);
        if (fromTime.equals("-1")) {
            String msg = "You need to specify the from time of an event.";
            throw new MissingInformationException(msg);
        }
        if (toTime.equals("-1")) {
            String msg = "You need to specify the to time of an event.";
            throw new MissingInformationException(msg);
        }
        if (des.isEmpty()) {
            String msg = "You need to specify the description of an event.";
            throw new MissingInformationException(msg);
        }
        Event event = new Event(des, fromTime, toTime);
        return checkDuplicate(event);
    }

    /**
     * Add a Deadline Task to the TaskList
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getAddMessage()
     */
    public String executeAddDeadline(String string)
            throws MissingInformationException, InvalidInputException {
        try {
            String[] fullCommand = parseAndValidateCommand(string, "You need to specify the description of an event.");
        } catch (MissingInformationException e) {
            return e.getMessage();
        }
        String time = Parser.getDeadlineTime(string);
        String des = Parser.getDeadlineDes(string);

        if (time.isEmpty()) {
            String msg = "You need to specify the time of a deadline.";
            throw new MissingInformationException(msg);
        }

        if (des.isEmpty()) {
            String msg = "You need to specify the description of a deadline";
            throw new MissingInformationException(msg);
        }
        if (!Parser.isValidDate(time)) {
            throw new InvalidInputException(ui.getDateFormatErrorMessage());
        }

        Deadline deadline = new Deadline(des, time);
        return checkDuplicate(deadline);
    }


    /**
     * Delete a task from the task list
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getDeleteMessage()
     */
    public String executeDelete(String string)
            throws MissingInformationException {
        try {
            String[] fullCommand = parseAndValidateCommand(string, "You need to specify the task number to delete.");
            int id = validateTaskIndex(fullCommand);
            Task t = taskList.getTask(id);
            taskList.deleteTask(id);
            return ui.getDeleteMessage(t, taskList);
        } catch (MissingInformationException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    public String executeFind(String string) throws MissingInformationException {
        String keyword = Parser.getFindKeyWord(string);
        if (keyword.isEmpty()) {
            String msg = "You need to specify the content to be searched in your list.";
            throw new MissingInformationException(msg);
        } else {
            TaskList result = taskList.findTask(keyword);
            return ui.getFindResult(result);
        }
    }

    private String[] parseAndValidateCommand(String command, String errorMessage)
            throws MissingInformationException {
        String[] fullCommand = Parser.parseCommand(command);
        if (fullCommand.length < 2 || fullCommand[1].isEmpty()) {
            throw new MissingInformationException(errorMessage);
        }
        return fullCommand;
    }

    private int validateTaskIndex(String[] fullCommand)
            throws IndexOutOfBoundsException {
        int id = Integer.parseInt(fullCommand[1]) - 1;
        if (id >= taskList.getTaskList().size()) {
            throw new IndexOutOfBoundsException(Message.MESSAGE_INDEX_ERROR);
        }
        return id;
    }

    private void addTask(Task task) {
        taskList.addTask(task);
    }

    private String checkDuplicate(Task t) {
        if (taskList.hasDuplicates(t)) {
            return Message.MESSAGE_HAS_DUPLICATE;
        } else {
            addTask(t);
            return ui.getAddMessage(t, taskList);
        }
    }


}
