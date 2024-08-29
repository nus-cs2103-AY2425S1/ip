package task;
import exception.MaxineException;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Deadline extends Task {

    protected String ddl;

    public Deadline() {
        super();
    }

    public Deadline(String description, String ddl) {
        super(description);
        try {
            this.ddl = dateTimeParser(ddl.trim());
        } catch (Exception e) {
            this.ddl = ddl;
        }
    }
    
    public void addToDeadline(String[] arr, ArrayList<Task> list) throws MaxineException {
        StringBuilder desc = new StringBuilder();
        StringBuilder ddl = new StringBuilder();
        desc.append(arr[1]);
        boolean isChecked = false;
        for (int i = 1; i < (arr.length - 1); i++) {
            if (arr[i].equals("/by")) {
                isChecked = true;
                break;
            }
        }
        if (!isChecked || arr[1].equals("/by")) {
            throw new MaxineException("Please follow this format: deadline [enter task] /by [enter deadline]");
        }
        boolean hasBy = false;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i].equals("/by")) {
                hasBy = true;
            }
            else if (hasBy) {
                String word = " " + arr[i];
                ddl.append(word);
            } else {
                String word = " " + arr[i];
                desc.append(word);
            }
        }
        Deadline task = new Deadline(desc.toString(), ddl.toString());
        list.add(task);
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + description + " (by: " + ddl + ")";
    }

    @Override
    public String writeToFile() {
        return "D" + super.writeToFile() + " / " + ddl;
    }

}

