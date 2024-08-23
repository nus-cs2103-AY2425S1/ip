public class Task {
    protected String description;
    protected  boolean isDone;

    public static Task createTask(String description) {
        String strippedDescription = description.trim().toLowerCase();
        if (strippedDescription.startsWith("todo")) {
            return new Todo(description);
        } else if(strippedDescription.startsWith("deadline")) {
            String[] splitStr = description.split("/");
            String des = splitStr[0].trim();
            String deadline = splitStr[1].split(" ")[1];
            return new Deadline(des, deadline);
        } else if (strippedDescription.startsWith("event")) {
            String[] splitStr = description.split("/");
            String des = splitStr[0].trim();
            String start = splitStr[1].replace("from ", "").trim();
            String end = splitStr[2].replace("to ", "").trim();
            return new Event(des, start, end);
        }
        return null;
    }

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    public void changeStatus(boolean newIsDone) {
       this.isDone = newIsDone;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
