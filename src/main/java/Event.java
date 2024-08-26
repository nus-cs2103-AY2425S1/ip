package main.java;

import static java.lang.Integer.parseInt;

public class Event extends Task {

    protected String fromDate;
    protected String toDate;

    public Event (String inputString) throws InvalidTaskNameException, InvalidDateException {

        if (inputString.contains("/from ")) {
            int fromIndex = inputString.indexOf("/from ");
            String taskName = inputString.substring(0, fromIndex);
            if (taskName.length() == 0) {
                throw new InvalidTaskNameException();
            }

            if (inputString.contains("/to ")) {
                int toIndex = inputString.indexOf("/to ");
                if (toIndex < fromIndex) {
                    throw new InvalidDateException("Wrong date order provided!");
                }
                String fromDate = inputString.substring(fromIndex + 6, toIndex);
                String toDate = inputString.substring(toIndex + 4);
                if (fromDate.length() == 0) {
                    throw new InvalidDateException("From date not provided");
                } else if (toDate.length() == 0) {
                    throw new InvalidDateException("To date not provided");
                }
                this.name = taskName;
                this.fromDate = fromDate;
                this.toDate = toDate;
            } else {
                throw new InvalidDateException("To date is not provided!");
            }
        } else {
            throw new InvalidDateException("From date is not provided!");
        }
    }

    public Event(String[] input) {
        int isDone = parseInt(input[0]);
        if (isDone == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        this.name = input[1];
        this.fromDate = input[2];
        this.toDate = input[3];
    }

    @Override
    public String toString() {
        String res = "[E]";
        res += super.toString();
        res += "(from: " + this.fromDate + " to: " + this.toDate + ")";
        return res;
    }

    @Override
    public String toSave() {
        String res = "D|";
        res.concat(this.isDone ? "1|" : "0|");
        res.concat(this.name);
        res.concat("|");
        res.concat(this.fromDate);
        res.concat("|");
        res.concat(this.toDate);
        return res;
    }
}
