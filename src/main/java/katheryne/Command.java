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

    public String executeList(TaskList l) {
        return ui.getListMessage(l);
    }

    public String executeMark(String string)
            throws MissingInformationException, IndexOutOfBoundsException {
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2) {
            String msg = "You need to specify the task number to mark.";
            throw new MissingInformationException(msg);
        }
        if (Integer.parseInt(fullCommand[1])> taskList.getTaskList().size()) {
            throw new IndexOutOfBoundsException(Message.MESSAGE_INDEX_ERROR);
        }
        int id = Integer.parseInt(fullCommand[1])-1;
        taskList.mark(id);
        return ui.getMarkMessage(taskList.getTask(id));
    }

    public String executeUnmark(String string)
            throws MissingInformationException, IndexOutOfBoundsException {
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2) {
            String msg = "You need to specify the task number to mark.";
            throw new MissingInformationException(msg);
        }
        if (Integer.parseInt(fullCommand[1])> taskList.getTaskList().size()) {
            throw new IndexOutOfBoundsException(Message.MESSAGE_INDEX_ERROR);
        }
        int id = Integer.parseInt(fullCommand[1])-1;
        taskList.unmark(id);
        return ui.getUnmarkMessage(taskList.getTask(id));
    }

    public String executeAddToDo(String string)
            throws MissingInformationException {
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2 || fullCommand[1].isEmpty()) {
            String msg = "You need to specify the description of a todo task.";
            throw new MissingInformationException(msg);
        }
        String des = Parser.getToDoDes(string);
        ToDo todo = new ToDo(des);
        taskList.addTask(todo);
        return ui.getAddMessage(todo,taskList);
    }

    public String executeAddEvent(String string)
            throws MissingInformationException {
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2 || fullCommand[1].isEmpty()) {
            String msg = "You need to specify the description of an event.";
            throw new MissingInformationException(msg);
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
        taskList.addTask(event);
        return ui.getAddMessage(event,taskList);
    }

    public String executeAddDeadline(String string)
            throws MissingInformationException, InvalidInputException {
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2 || fullCommand[1].isEmpty()) {
            String msg = "You need to specify the description of a deadline.";
            throw new MissingInformationException(msg);
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
        if (!Parser.isValidDate(time)){
            throw new InvalidInputException(ui.getDateFormatErrorMessage());
        }

        Deadline deadline = new Deadline(des, time);
        taskList.addTask(deadline);
        return ui.getAddMessage(deadline,taskList);
    }

    public String executeDelete(String string)
            throws MissingInformationException{
        String[] fullCommand = Parser.parseCommand(string);
        if (fullCommand.length < 2 || fullCommand[1].isEmpty()) {
            String msg = "You need to specify the task number to delete.";
            throw new MissingInformationException(msg);
        }
        if (Integer.parseInt(fullCommand[1])> taskList.getTaskList().size()) {
            throw new IndexOutOfBoundsException(Message.MESSAGE_INDEX_ERROR);
        }
        int id = Integer.parseInt(fullCommand[1])-1;
        Task t = taskList.getTask(id);
        taskList.deleteTask(id);
        return ui.getDeleteMessage(t,taskList);
    }

}
