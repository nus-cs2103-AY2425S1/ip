public class Deadlines extends Task{
    public Deadlines(String desc) {
        super("");
        String[] section = desc.substring(9).split("/by ");
        super.description = section[0] + "(by: " + section[1] +")";
        taskType = "[D]";
    }
}
