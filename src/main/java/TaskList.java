import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> toDo;

    TaskList () {
        this.toDo = new ArrayList<Task>();
    }

    public void updateTask(int idx, String command) {
        if (command.equals("mark")) {
            this.toDo.get(idx).mark();
        } else {
            this.toDo.get(idx).unMark();
        }
    }
    public void addItem(String taskName, String taskType) {
        Task newTask = new ToDoTask(taskName);
        this.toDo.add(newTask);

        System.out.println("Alright I have added this task: \n" + newTask.toString() + "\nYou now have " + this.toDo.size() + " tasks in your list right now!");
    }

    @Override
    public String toString() {
        if (this.toDo.size() == 0) {
            return "The list is empty, why not add something!";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < this.toDo.size(); i++) {
                if (i == 0) {
                    sb.append((i + 1) + ". " + this.toDo.get(i).toString());
                } else {
                    sb.append("\n" + (i + 1) + ". " + this.toDo.get(i).toString());
                }
            }
            return sb.toString();
        }
    }

}
