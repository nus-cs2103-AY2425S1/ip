package tasks;
import exceptions.InvalidDateException;
import exceptions.InvalidTaskNameException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static java.lang.Integer.parseInt;

public class DeadLine extends Task {

    protected LocalDate endDate;

    public DeadLine (String inputString) throws InvalidTaskNameException, InvalidDateException {
        inputString = inputString.trim();
        if (inputString.contains("/by ")) {
            int index = inputString.indexOf("/by ");
            String taskName = inputString.substring(0, index).trim();
            String byDate = inputString.substring(index+4);
            if (taskName.length() == 0) {
                throw new InvalidTaskNameException();
            }

            if (byDate.length() == 0) {
                throw new InvalidDateException();
            }

            this.name = taskName;
            try {
                this.endDate = LocalDate.parse(byDate);
            } catch (DateTimeParseException ex) {
                throw new InvalidDateException("Invalid date format given");
            }

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
        this.name = input[1].trim();
        this.endDate = LocalDate.parse(input[2].trim());
    }

    @Override
    public String toString() {
        String res = "[D]";
        res += super.toString();
        res += " (by: " + this.endDate.toString() + ")";
        return res;
    }

    @Override
    public String toSave() {
        String res = "D|";
        res = res.concat(this.isDone ? "1|" : "0|");
        res = res.concat(this.name);
        res = res.concat("|");
        res = res.concat(this.endDate.toString());
        return res;
    }
}
