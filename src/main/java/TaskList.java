import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArrayList;
    protected static int COUNT = 0;

    public TaskList(ArrayList<Task> taskArrayList){
        this.taskArrayList = taskArrayList;
    }

    public void addTask(Task task) {
        taskArrayList.add(task);
        COUNT++;
    }

    public void printList() throws TheBotFatherException {
        int size = taskArrayList.size();
        if (size < 1) throw new TheBotFatherException("How do I print what is not there?");
        for (int i = 0; i < size; i++) {
            Ui.print(i + 1 + ". " + taskArrayList.get(i));
        }
        this.printCount();
    }

    public void markAsDone(int index) throws TheBotFatherException{
        try {
            Task task = taskArrayList.get(index);
            task.markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, you don't even have those many tasks son");
        }
    }

    public void unmark(int index) throws TheBotFatherException{
        try {
            Task task = taskArrayList.get(index);
            task.unmark();
            Ui.print("A man who doesn't spend time with his family can never be a real man.");
            Ui.print(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, you don't even have those many tasks son");
        }
    }

    public void delete(int index) throws TheBotFatherException{
        try {
            Task task = taskArrayList.get(index);
            Ui.print("You are sure you wanted to do that right? Anyways... too late");
            taskArrayList.remove(index);
            Ui.print(task.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new TheBotFatherException("To be a real man you need to know how to count, you don't even have those many tasks son");
        }
        COUNT--;
    }

    public String toFile() {
        StringBuilder dataInFile = new StringBuilder();
        for (Task task : taskArrayList) {
            dataInFile.append(task.toFile() + System.lineSeparator());
        }

        return dataInFile.toString();
    }

    protected void printTask(int index) {
        Ui.print(taskArrayList.get(index).toString());
    }

    protected void printCount() {
        Ui.print("You have " + TaskList.COUNT + " tasks in the list.");
    }
}
