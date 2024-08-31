package Joseph;

import java.util.ArrayList;
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        index -= 1;
        return tasks.remove(index);
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        index -= 1;
        return tasks.get(index);
    }

    public void markTask(int index) throws IndexOutOfBoundsException {
        index -= 1;
        tasks.get(index).setDone();
    }

    public void unmarkTask(int index) throws IndexOutOfBoundsException {
        index -= 1;
        tasks.get(index).unDone();
    }

    public void handleCommand(Parser.Command command, String input,
                              Parser parser, UI ui, Storage storage) {
        try {
            switch (command) {
                case LIST:
                    ui.printListMessage(tasks);
                    break;
                case MARK:
                    int markNum = parser.parseTaskNumber(input, command.getCommandText());
                    String markDetails = getTask(markNum).getDetails();
                    markTask(markNum);
                    storage.saveTasks(tasks);
                    ui.printMarkMessage(markDetails);
                    break;
                case UNMARK:
                    int unmarkNum = parser.parseTaskNumber(input, command.getCommandText());
                    String unmarkDetails = getTask(unmarkNum).getDetails();
                    unmarkTask(unmarkNum);
                    storage.saveTasks(tasks);
                    ui.printUnmarkMessage(unmarkDetails);
                    break;
                case TODO:
                    String todoDetails = parser.parseTodoDetails(input, command.getCommandText());
                    ToDo todo = new ToDo(todoDetails);
                    addTask(todo);
                    storage.saveTasks(tasks);
                    ui.printTodoMessage(todo.getDetails());
                    break;
                case DEADLINE:
                    String[] deadlineDetails = parser.parseDeadlineDetails(input, command.getCommandText());
                    Deadline deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                    addTask(deadline);
                    storage.saveTasks(tasks);
                    ui.printDeadlineMessage(deadline.getDetails());
                    break;
                case EVENT:
                    String[] eventDetails = parser.parseEventDetails(input, command.getCommandText());
                    JEvent event = new JEvent(eventDetails[0], eventDetails[1], eventDetails[2]);
                    addTask(event);
                    storage.saveTasks(tasks);
                    ui.printJEventMessage(event.getDetails());
                    break;
                case DELETE:
                    int deleteNum = parser.parseTaskNumber(input, command.getCommandText());
                    String deleteDetails = getTask(deleteNum).getDetails();
                    deleteTask(deleteNum);
                    storage.saveTasks(tasks);
                    ui.printDeleteMessage(deleteDetails);
                    break;
                default:
                    ui.printUnrecognisedMessage();
                    break;
            }
        } catch (InsufficientDetailsException e) {
            ui.printErrorMessage(e.getMessage());
        }
    }
}
