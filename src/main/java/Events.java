public class Events extends Task {

    public Events(String desc) throws EmptyEventException, EmptyEventTimingException {
        super(desc);
        if (desc.equals("")) {
            throw new EmptyEventException("      OOPS!!! The description of a event cannot be empty leh.");
        }
        // Split the description to extract the timings
        String[] parts1 = desc.split(" /from ");
        //throw exception if start time not given or it is whitespace
        if (parts1.length < 2 || parts1[1].trim().isEmpty()) {
            throw new EmptyEventTimingException("     OOPS! Event start time not given leh");
        }

        String[] parts2 = parts1[1].split(" /to ");
        //throw exception if end time not given or it is whitespace
        if (parts2.length < 2 || parts2[1].trim().isEmpty()) {
            throw new EmptyEventTimingException("     OOPS! Event end time not given leh");
        }
    }

    @Override
    public String print() {
        //splits string to get the timings and desc
        String[] parts1 = super.print().split(" /from ");
        String[] parts2 = parts1[1].split(" /to ");
        return "[E]" + parts1[0] + " (from: " + parts2[0] + " to: " + parts2[1] + ")";
    }
}