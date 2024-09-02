import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public void initialiseLoadTasks(ArrayList<String> stringListOfTasks) throws DevonReadDatabaseException {
        for (String line : stringListOfTasks) {
            String[] fields = line.split(Storage.DB_DELIMITER);
            Task newTask;

            switch (fields[0]) {
                case "Deadline":
                    newTask = new Deadline(
                            fields[2],
                            LocalDateTime.parse(fields[3], Storage.DATE_TIME_FORMATTER_FOR_DB)
                    );
                    break;
                case "Event":
                    newTask = new Event(
                            fields[2],
                            LocalDateTime.parse(fields[3], Storage.DATE_TIME_FORMATTER_FOR_DB),
                            LocalDateTime.parse(fields[4], Storage.DATE_TIME_FORMATTER_FOR_DB)
                    );
                    break;
                case "Todo":
                    newTask = new Todo(fields[2]);
                    break;
                default:
                    throw new DevonReadDatabaseException();
            }

            boolean toBeMarkedAsDone = Integer.parseInt(fields[1]) == 1;
            if (toBeMarkedAsDone) {
                newTask.markAsDoneSilently();
            }

            this.addTask(newTask);
        }
    }

    protected void addTask(Task task) {
        this.tasks.add(task);
    }

    protected Task getTask(int taskNumber) {
        return this.tasks.get(taskNumber);
    }

    protected void removeTask(int taskNumber) {
        this.tasks.remove(taskNumber);
    }

    protected void printList() {
        System.out.println("\t" + "Here are the tasks in your list:");
        for (int i = 0; i < this.getNumberOfTasks(); i++) {
            Task current = this.getTask(i);
            String formattedEntry = String.format(
                    "\t" + "%d. %s",
                    i + 1,
                    current
            );
            System.out.println(formattedEntry);
        }
    }
}
