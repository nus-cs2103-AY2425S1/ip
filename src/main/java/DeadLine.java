package main.java;

public class DeadLine extends Task {

    protected String endDate;

    public DeadLine (String inputString) throws InvalidTaskNameException, NoDateException {
        if (inputString.contains("/by ")) {
            int index = inputString.indexOf("/by ");
            String taskName = inputString.substring(0, index);
            String byDate = inputString.substring(index+4);
            if (taskName.length() == 0) {
                throw new InvalidTaskNameException(taskName);
            }

            if (endDate.length() == 0) {
                throw new NoDateException();
            }

            this.name = taskName;
            this.endDate = byDate;

        }
        throw new NoDateException();
    }

    @Override
    public String toString() {
        String res = "[D]";
        res += super.toString();
        res += "(by: " + this.endDate + ")";
        return res;
    }
}
