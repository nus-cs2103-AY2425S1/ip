package main.java;

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

    @Override
    public String toString() {
        String res = "[D]";
        res += super.toString();
        res += "(by: " + this.endDate + ")";
        return res;
    }
}
