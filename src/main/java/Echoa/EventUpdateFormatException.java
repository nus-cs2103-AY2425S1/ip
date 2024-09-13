package echoa;

public class EventUpdateFormatException extends  UpdateFormatException {
    public EventUpdateFormatException() {
        super();
    }

    @Override
    public String getMessage() {
        return "ToDo Update Format is as shown: \n"
                + "update [label] "
                + "/d [new description]) "
                + "/s [new start date and/or new start time] "
                + "/e [new end date and/or new end time] "
                + "(omit if not required)";
    }
}
