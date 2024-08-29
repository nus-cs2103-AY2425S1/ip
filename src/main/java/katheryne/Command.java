package katheryne;

import katheryne.exceptions.InvalidInputException;
import katheryne.exceptions.MissingInformationException;

public class Command {
    protected Ui ui;
    protected TaskList taskList;

     *Constructor for Command Class
     * @param taskList taskList
     * @param ui Ui
     */
    public Command(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

<<<<<<< HEAD
    public String executeList() {
<<<<<<< HEAD
        return ui.getListMessage();
=======
        return ui.getListMessage(taskList);
>>>>>>> branch-Level-9
=======
    /**
     *Generate a list of all tasks stored.
     * @param l TaskList
     * @return a string from ui.getListMessage()
     */
    public String executeList(TaskList l) {
        return ui.getListMessage(l);
>>>>>>> A-JavaDoc
    }

    /**
     *Mark corresponding task in the task list as done.
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getMarkMessage()
     */
    public String executeMark(String string) throws MissingInformationException, IndexOutOfBoundsException {
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2) {
            throw new MissingInformationException("You need to specify the task number to mark.");
        }
        if (Integer.parseInt(fullCommand[1])> taskList.getTaskList().size()) {
            throw new IndexOutOfBoundsException(Message.MESSAGE_INDEX_ERROR);
        }
        int id = Integer.parseInt(fullCommand[1])-1;
        taskList.mark(id);
        return ui.getMarkMessage(taskList.getTask(id));
    }


    /**
     * Unmark corresponding task in the task list as not done yet.
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getUnmarkMessage()
     */
    public String executeUnmark(String string) throws MissingInformationException, IndexOutOfBoundsException {
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2) {
            throw new MissingInformationException("You need to specify the task number to mark.");
        }
        if (Integer.parseInt(fullCommand[1])> taskList.getTaskList().size()) {
            throw new IndexOutOfBoundsException(Message.MESSAGE_INDEX_ERROR);
        }
        int id = Integer.parseInt(fullCommand[1])-1;
        taskList.unmark(id);
        return ui.getUnmarkMessage(taskList.getTask(id));
    }

    /**
     * Add a ToDo Task to the TaskList
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getAddMessage()
     */
    public String executeAddToDo(String string) throws MissingInformationException {
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2 || fullCommand[1].isEmpty()) {
            throw new MissingInformationException("You need to specify the description of a todo task.");
        }
        String des = Parser.getToDoDes(string);
        ToDo todo = new ToDo(des);
        taskList.addTask(todo);
        return ui.getAddMessage(todo,taskList);
    }


    /**
     * Add an Event Task to the TaskList
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getAddMessage()
     */

    public String executeAddEvent(String string) throws MissingInformationException {
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2 || fullCommand[1].isEmpty()) {
            throw new MissingInformationException("You need to specify the description of an event.");
        }
        String fromTime = Parser.getEventFromTime(string);
        String toTime = Parser.getEventToTime(string);
        String des = Parser.getEventDes(string);
        if (fromTime.equals("-1")) {
            throw new MissingInformationException("You need to specify the from time of an event.");
        }
        if (toTime.equals("-1")) {
            throw new MissingInformationException("You need to specify the to time of an event.");
        }
        if (des.isEmpty()) {
            throw new MissingInformationException("You need to specify the description of an event.");
        }
        Event event = new Event(des, fromTime, toTime);
        taskList.addTask(event);
        return ui.getAddMessage(event,taskList);
    }


    /**
     * Add a Deadline Task to the TaskList
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getAddMessage()
     */
    public String executeAddDeadline(String string) throws MissingInformationException, InvalidInputException {
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2 || fullCommand[1].isEmpty()) {
            throw new MissingInformationException("You need to specify the description of a deadline.");
        }
        String time = Parser.getDeadlineTime(string);
        String des = Parser.getDeadlineDes(string);

        if (time.isEmpty()) {
            throw new MissingInformationException("You need to specify the time of a deadline.");
        }

        if (des.isEmpty()) {
            throw new MissingInformationException("You need to specify the description of a deadline");
        }
        if (!Parser.isValidDate(time)){
            throw new InvalidInputException(ui.getDateFormatErrorMessage());
        }

        Deadline deadline = new Deadline(des, time);
        taskList.addTask(deadline);
        return ui.getAddMessage(deadline,taskList);
    }


    /**
     * Delete a task from the task list
     * @param string String from the Scanner(System.in)
     * @return a string from ui.getDeleteMessage()
     */
    public String executeDelete(String string) throws MissingInformationException{
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2 || fullCommand[1].isEmpty()) {
            throw new MissingInformationException("You need to specify the task number to delete.");
        }
        if (Integer.parseInt(fullCommand[1])> taskList.getTaskList().size()) {
            throw new IndexOutOfBoundsException(Message.MESSAGE_INDEX_ERROR);
        }
        int id = Integer.parseInt(fullCommand[1])-1;
        Task t = taskList.getTask(id);
        taskList.deleteTask(id);
        return ui.getDeleteMessage(t,taskList);
    }

    public String executeFind(String string) throws MissingInformationException{
        String keyword = Parser.getFindKeyWord(string);
        if (keyword.isEmpty()) {
            String msg = "You need to specify the content to be searched in your list.";
            throw new MissingInformationException(msg);
        } else {
            TaskList result = taskList.findTask(keyword);
            return ui.getFindResult(result);
        }
    }

}
