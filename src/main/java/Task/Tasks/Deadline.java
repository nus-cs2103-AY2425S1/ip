package Task.Tasks;

import Task.Task;

class Deadline extends Task {

    private String time;
    public Deadline(String title,String time) {
        super(title);
        this.time = time;
    }

    public Deadline(String title, boolean done, String time) {
        super(title, done);
        this.time = time;
    }
}
