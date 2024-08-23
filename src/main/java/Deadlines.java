public class Deadlines extends Task{
    public Deadlines(String desc) throws TaskException {
        super("");
        try {
            String[] section = desc.substring(9).split("/by ");
            super.description = section[0] + "(by: " + section[1] + ")";
            taskType = "[D]";
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new TaskException("Sorry, the desctiption you " +
                    "gave does not follow the format for deadlines.");
        }
    }
}
