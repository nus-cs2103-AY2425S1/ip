package main.java;

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

    @Override
    public String toString() {
        String res = "[E]";
        res += super.toString();
        res += "(from: " + this.fromDate + " to: " + this.toDate + ")";
        return res;
    }
}
