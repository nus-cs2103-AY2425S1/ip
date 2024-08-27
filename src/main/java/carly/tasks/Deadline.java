package carly.tasks;

import java.text.MessageFormat;

public class Deadline extends Task{
    private static final String TASKTYPE = "D";
    private final String duedate;

    public Deadline(String description, String duedate) {
        super(description);
        this.isDone = false;
        this.duedate = duedate;
    }

    private String getDuedate(){
        return this.duedate;
    }

    @Override
    public String toString(){
        return MessageFormat.format("[{0}]{1} (by: {2})",
                TASKTYPE,
                super.toString(),
                this.getDuedate());
    }
}
