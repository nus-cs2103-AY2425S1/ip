import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListOfTask {
    private ArrayList<Task> tasks;

    public ListOfTask() {
        this.tasks = new ArrayList<Task>();
    }

    public void copyFile(BufferedReader file) throws IOException {
        try {
            String line;
            while ((line = file.readLine()) != null) {
                String[] arr = line.split(" \\| ");
                boolean isDone = (arr[1] == "1 ");

                if (arr[0].equals("T")) {
                    this.tasks.add(new ToDoTask(arr[2], isDone));

                } else if (arr[0].equals("D")) {
                    this.tasks.add(new DeadlineTask(arr[2], isDone, arr[3]));

                } else if (arr[0].equals("E")) {
                    String[] timings = arr[4].split("-");
                    String timeOfDay = timings[1].substring(1);
                    String startTime = timings[0] + timeOfDay;
                    String endTime = timings[1];
                    this.tasks.add(new EventTask(arr[2], isDone, arr[3], startTime, endTime));
                }
            }
        } finally {
            file.close();
        }
    }

    public int getTotal() {
        return this.tasks.size();
    }

    public Task addToDo(String t) {
        Task task = new ToDoTask(t, false);
        this.tasks.add(task);
        return task;
    }

    public Task addDeadline(String t, String date) {
        Task task = new DeadlineTask(t, false, date);
        this.tasks.add(task);
        return task;
    }

    public Task addEvent(String t, String date, String start, String end) {
        Task task = new EventTask(t, false, date, start, end);
        this.tasks.add(task);
        return task;
    }

    public Task deleteTask(int i) {
        return this.tasks.remove(i - 1);
    }

    public String markDone(int i) {
        return "     ____________________________________________________________ \n" +
                this.tasks.get(i - 1).markDone() + "\n" +
                "     ____________________________________________________________ \n";
    }

    public String markUndone(int i) {
        return "     ____________________________________________________________ \n" +
                this.tasks.get(i - 1).markUndone() + "\n" +
                "     ____________________________________________________________ \n";
    }

    public String printList() {
        String output = "     ____________________________________________________________ \n" +
                "     Here are the tasks in your list: \n";
        for (int i = 0; i < this.tasks.size(); i++) {
            int label = i + 1;
            output += "     " + label + ". " + this.tasks.get(i).printTask() + "\n";
        }

        return output +
                "     ____________________________________________________________ \n";
    }
}
