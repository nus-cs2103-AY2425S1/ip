package cypherchatbot.task;


import cypherchatbot.CypherException;

public abstract class Task {
    protected String description;
    protected Boolean isComplete;

    protected final Integer TASK_PRIORITY;

    public Task(String desc, Integer priority) {
        this.description = desc.trim();
        this.TASK_PRIORITY = priority;
        this.isComplete = false;
    }

    public void markAsComplete() throws CypherException {
        if (this.isComplete) {
            throw new CypherException("The task you are trying to mark as completed has already been completed");
        }
        this.isComplete = true;
    }

    public void markAsIncomplete() throws CypherException {
        if (!this.isComplete) {
            throw new CypherException("The task you are trying to mark as "
                    + "uncompleted is already marked as uncompleted");
        }
        this.isComplete = false;
    }

    @Override
    public String toString() {
        String str = this.isComplete ? "[X] " : "[ ] ";
        str += this.description;
        return str;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String toStringInFile();

}
