public class Events extends Task {

    public Events(String desc) {
        super("");
        String[] section = desc.substring(6).split("/from | /to ");
        super.description = section[0] + "(from: " + section[1] +" to: " + section[2] + ")";
        taskType = "[E]";
    }
}
