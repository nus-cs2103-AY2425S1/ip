package tasks;

import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;

import static java.lang.Integer.parseInt;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalDate toDate;

    public Event (String inputString) throws InvalidTaskNameException, InvalidDateException {

        if (inputString.contains("/from ")) {
            int fromIndex = inputString.indexOf("/from ");
            String taskName = inputString.substring(0, fromIndex).trim();
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
                try {
                    this.fromDate = LocalDate.parse(fromDate.trim());
                } catch (DateTimeParseException ex) {
                    throw new InvalidDateException("Invalid from date format given");
                }

                try {
                    this.toDate = LocalDate.parse(toDate.trim());
                } catch (DateTimeParseException ex) {
                    throw new InvalidDateException("Invalid to date format given");
                }

                if (this.toDate.isBefore(this.fromDate)) {
                    throw new InvalidDateException("To date is before from date");
                }
            } else {
                throw new InvalidDateException("To date is not provided!");
            }
        } else {
            throw new InvalidDateException("From date is not provided!");
        }
    }

    public Event (String[] input) {
        int isDone = parseInt(input[0]);
        if (isDone == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        this.name = input[1].trim();
        this.fromDate = LocalDate.parse(input[2].trim());
        this.toDate = LocalDate.parse(input[3].trim());
    }

    @Override
    public String toString () {
        String res = "[E]";
        res += super.toString();
        res += " (from: " + this.fromDate.toString() + " to: " + this.toDate.toString() + ")";
        return res;
    }

    @Override
    public String toSave () {
        String res = "E|";
        res = res.concat(this.isDone ? "1|" : "0|");
        res = res.concat(this.name);
        res = res.concat("|");
        res = res.concat(this.fromDate.toString());
        res = res.concat("|");
        res = res.concat(this.toDate.toString());
        return res;
    }
}
