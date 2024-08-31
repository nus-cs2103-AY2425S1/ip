package wansbot.tasks;

public class Task {
    private String name;
    private boolean finished;

    public Task(String name) {
        this.name = name;
        this.finished = false;
    }

    // Returns status of task
    public boolean isDone() {
        return this.finished;
    }

    // finishes selected task
    public void finish() {
        this.finished = true;
    }

    // unchecks a finished task
    public void unfinish() {
        this.finished = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Task) {
            Task t = (Task) obj;
            if (this.name.equals(t.name)) {
                return true;
            }
        }

        return false;
    }

    // thos method returns true if keyword is contained within task name.
    public Boolean hasName(String keyword) {
        if (this.name.contains(keyword)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
