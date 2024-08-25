import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList = new ArrayList<>();  
    private int listIndex = 0;

    public TaskManager() {
        this.taskList = new ArrayList<>();
        this.listIndex = 0;
    }

    public String[] add(TaskType taskType, String input) throws GladosException {
        switch (taskType) {
        case TODO:
            String todoDescription = input.trim();
            checkDescription(todoDescription);
            taskList.add(new Todo(todoDescription));
            break;
        case EVENT:
            checkDescription(input.trim());
            String[] eventInputs = input.split(" /from ");
            String eventDescription = eventInputs[0].trim();
            checkDescription(eventDescription);
            if (eventInputs.length != 2) {
                throw new DateRangeNotFoundException();
            }
            String[] dateRange = eventInputs[1].split(" /to ");
            if (dateRange.length != 2 || dateRange[0].trim().equals("") || dateRange[1].trim().equals("")) {
                throw new DateRangeNotFoundException();
            }
            taskList.add(new Event(eventInputs[0].trim(), dateRange[0].trim(), dateRange[1].trim()));
            break;
        case DEADLINE:
            checkDescription(input.trim());
            String[] deadlineInputs = input.split(" /by ");
            String deadlineDescription = deadlineInputs[0].trim();
            checkDescription(deadlineDescription);
            if (deadlineInputs.length != 2 || deadlineInputs[1].trim().equals("")) {
                throw new DateNotFoundException();
            }
            taskList.add(new Deadline(deadlineInputs[0].trim(), deadlineInputs[1].trim()));
            break;
        default:
            break;
        }
        listIndex++;
        return new String[]{taskList.get(listIndex - 1).toString(), String.valueOf(listIndex)};
    }

    public String[] delete(int index) throws TaskNotFoundException{
        if (index - 1 < 0 || index - 1 >= listIndex) {
            throw new TaskNotFoundException();
        }
        Task task = taskList.remove(index - 1);
        listIndex--;
        return new String[]{task.toString(), String.valueOf(listIndex)};
    }

    public ArrayList<Task> list() {
        return this.taskList;
    }

    public String mark(int index) {
        Task task = taskList.get(index - 1);
        task.mark();
        return task.toString();
    }

    public String unmark(int index) {
        Task task = taskList.get(index - 1);
        task.unmark();
        return task.toString();
    }

    private static void checkDescription(String description) throws DescriptionNotFoundException {
        if (description.equals("")) {
           throw new DescriptionNotFoundException();
        }
   }
}
