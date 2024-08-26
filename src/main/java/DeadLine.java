package main.java;

import static java.lang.Integer.parseInt;

public class DeadLine extends Task {

    protected String endDate;

    public DeadLine (String inputString) throws InvalidTaskNameException, InvalidDateException {
        if (inputString.contains("/by ")) {
            int index = inputString.indexOf("/by ");
            String taskName = inputString.substring(0, index);
            String byDate = inputString.substring(index+4);
            if (taskName.length() == 0) {
                throw new InvalidTaskNameException();
            }

            if (byDate.length() == 0) {
                throw new InvalidDateException();
            }

            this.name = taskName;
            this.endDate = byDate;

        } else {
            throw new InvalidDateException();
        }
    }

    public DeadLine(String[] input) {
        int isDone = parseInt(input[0]);
        if (isDone == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        this.name = input[1];
        this.endDate = input[2];
    }

    @Override
    public String toString() {
        String res = "[D]";
        res += super.toString();
        res += "(by: " + this.endDate + ")";
        return res;
    }

    @Override
    public String toSave() {
        String res = "D|";
        res.concat(this.isDone ? "1|" : "0|");
        res.concat(this.name);
        res.concat("|");
        res.concat(this.endDate);
        return res;
    }
}
