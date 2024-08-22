public class TaskList {
    private static final Task[] LIST = new Task[100];
    private static int numItems = 0;

    public void addTask(Task task) {
        LIST[numItems] = task;
        numItems++;
    }

    public Task getItem(int label) {
        return LIST[label - 1];
    }

    public int getTaskCount() {
        return numItems;
    }

    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < numItems; i++) {
            output.append(String.valueOf(i + 1))
                    .append(". ")
                    .append(LIST[i].toString())
                    .append("\n");
        }
        return output.toString();
    }
}
