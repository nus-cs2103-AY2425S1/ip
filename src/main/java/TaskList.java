import java.util.ArrayDeque;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    //max of task list
    public TaskList(int i) {
        list = new ArrayList<>();
    }

    public String addTask(Task task) {
        if (checkListSize()) {
            list.add(task);
            return String.format(
                    "Got it. I've added this task:\n   %s\nNow you have %d %s in the list.",task.getTaskString(), list.size(), list.size() == 1? "task": "tasks");
        } else return "Too many tasks !!!";
    }

    private boolean checkListSize() {
        return !(list.size() > 100);
    }

    public Task getTask(int i) {
        return list.get(i);
    }

    public String printList() {
        String listString = "Here are the task in your list:\n";
        int i = 1;
        for (Task task : list) {
            listString = listString.concat(i + "." + task.getTaskString());
            ++i;
        }
        return listString;
    }

    public String deleteTask (int i) {
        Task deletedTask = list.remove(i - 1);
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list."
                , deletedTask.getTaskString(), list.size());
    }

    public static int getDeleteNumber(String input) throws InputFormatException{
        String[] splitDelete = input.split(" ", 2);
        if (splitDelete.length != 2) {
            throw new InputFormatException("Oops! Please specify a number after \"delete\"");
        }
        try {
            return Integer.parseInt(splitDelete[1]);
        } catch (NumberFormatException e){
            throw new InputFormatException("Please input a number after \"delete\"");
        }
    }


}
