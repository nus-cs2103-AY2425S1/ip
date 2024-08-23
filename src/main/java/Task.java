public class Task {

    private String input;
    private static int numOfTasks = 0;
    private int index;

    public Task(String input) {
        this.input = input;
        numOfTasks++;
        this.index = numOfTasks - 1;
    }

    public String addedString() {
        String line = "____________________________________________________________\n";
        return line + "added: " + input + "\n" + line;
    }

    public String displayTask() {
        return this.index+1 + ". " + this.input;
    }

    public int getIndex() {
        return this.index;
    }
}
