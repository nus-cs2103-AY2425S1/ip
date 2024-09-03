package echo;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class TaskList {
    List<Task> tasks;
    public TaskList(List<Task> tasks) throws EchoException, FileNotFoundException {
        this.tasks = tasks;

    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public static String listToString(List<Task> tasks) {
        StringBuilder listString = new StringBuilder();
        int count = 1;
        for (Task task : tasks) {
            listString.append(task.getTypeLetter()).append("|").append(task.getIsDone()).append("|").append(task.getTaskDes()).append("|").append(task.getAdd()).append("\n");

        }
        return listString.toString();
    }

    public void find(String toFind) {
        List<Task> taskList = new ArrayList<>();
        for (Task task : tasks) {

            String des = task.getTaskDes();
            if (Parser.isPresent(des, toFind)) {
                taskList.add(task);
            }
        }
        Ui.showClassFound(taskList);
    }



    public  String listToString() {
        StringBuilder listString = new StringBuilder();
        int count = 1;
        for (Task task : tasks) {
            listString.append(task.getTypeLetter()).append("|").append(task.getIsDone()).append("|").append(task.getTaskDes()).append("|").append(task.getAdd()).append("\n");

        }
        return listString.toString();
    }


    public Task deleteTask( int num) throws EchoException {
        try {

            if (num > 0 && num <= this.tasks.size()) {
                Task removedTask = this.tasks.remove(num - 1);

                return removedTask;
            } else {
                throw new EchoException("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            throw new EchoException("Please enter a valid number.");
        }
    }

    public void markTask(int index) throws EchoException {
        if (index > 0 && index <= tasks.size()) {
            Task doneTask = tasks.get(index - 1);
            doneTask.setDone();
        } else {
            throw new EchoException("Invalid task number.");
        }
    }

    public void unmarkTask(int index) throws EchoException {
        if (index > 0 && index <= tasks.size()) {
            Task undoneTask = tasks.get(index - 1);
            undoneTask.setUndone();
        } else {
            throw new EchoException("Invalid task number.");
        }
    }

    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public List<Task> getTasks() {
        return tasks;
    }


    public Deadline addDeadline( String input) throws EchoException, DateTimeParseException {
        String[] details = input.split(" /by ", 2);
        if (details.length == 2) {
            String[] des = details[0].split(" ", 2);
            Deadline deadlineTask = new Deadline(details[0], details[1]);
            this.tasks.add(deadlineTask);
            return deadlineTask;
        } else {
            throw new EchoException("Please specify the task description and deadline.");

        }
    }

    public Events addEvent( String input) throws EchoException, DateTimeParseException {
        String eventDes = Parser.parseEventDes(input);
        String[] times = Parser.parseEventTime(input);
        Events eventTask = new Events(eventDes, times[0], times[1]);
        this.tasks.add(eventTask);
        return eventTask;

    }


    public Todo addTodo(String taskDescription) {
        Todo todo = new Todo(taskDescription);
        this.tasks.add(todo);
        return todo;
    }


    public int size() {
        return tasks.size();
    }
}
