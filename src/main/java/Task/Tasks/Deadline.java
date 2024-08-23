package Task.Tasks;

import Task.Task;

public class Deadline extends Task {

    private String time;
    public Deadline(String title,String time) {
        super(title);
        this.time = time;
    }

    public Deadline(String title, boolean done, String time) {
        super(title, done);
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Deadline: ");
        sb.append(super.toString());
        sb.append("[By :");
        sb.append(time);
        sb.append("]");
        return sb.toString();
    }
}
