package ScoobyDoo;
import java.util.ArrayList;
import exception.InputFormatException;
import task.Task;

public class TaskList {
    private final ArrayList<Task> list;

    //max of task list
    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public String addTask(Task task) {
        if (checkListSize()) {
            list.add(task);
            return String.format(
                    "Got it. I've added this task:\n   %s\nNow you have %d %s in the list.",
                    task.toString(),
                    list.size(),
                    list.size() == 1? "task": "tasks");
        } else return "Too many tasks !!!";
    }

    private boolean checkListSize() {
        return !(list.size() > 100);
    }

    public String markTask(int i) throws InputFormatException {
        if (i > list.size() || i <= 0) {
            throw new InputFormatException("Your number is out of range");
        }
        list.get(i-1).markAsDone();
        return String.format("Nice! I've marked this task as done:\n %s", list.get(i-1).toString());
    }

    public String unmarkTask(int i) throws InputFormatException {
        if (i > list.size() || i <= 0) {
            throw new InputFormatException("Your number is out of range");
        }
        list.get(i-1).markAsUndone();
        return String.format("OK, I've marked this task as not done yet:\n %s", list.get(i-1).toString());
    }

    public String printList() {
        String listString = "Here are the task in your list:\n";
        int i = 1;
        for (Task task : list) {
            listString = listString.concat(i + "." + task.toString());
            ++i;
        }
        return listString;
    }

    public String deleteTask (int i) {
        Task deletedTask = list.remove(i - 1);
        return String.format("Noted. I've removed this task:\n %s\nNow you have %d tasks in the list."
                , deletedTask.toString(), list.size());
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

    public String toFileFormatString() {
        //using a combiner to add all the tasks string
        //only append the file once, which is when bye is called
        return list.stream().reduce("", (a, b) -> b.toFileFormatString() + "\n" + a, (a,b) -> a  + b);
    }

}
