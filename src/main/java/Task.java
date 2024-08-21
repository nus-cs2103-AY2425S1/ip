public class Task {
    private String taskname;
    private boolean done;
    public Task(String s) {
        taskname = s;
        done = false;
    }

    public static Task of(String userInput) {
        String[] splitUserInput = userInput.split(" ", 2);
        String identifier = splitUserInput[0];

        if (identifier.equals("todo")) {
            return new ToDo(splitUserInput[1]);
        } else if (identifier.equals("deadline")) {
            String[] details = splitUserInput[1].split("/by ", 2);
            return new Deadline(details[0], details[1]);
        } else {
            //task not recognised
            return null;
        }
    }

    public void done() {
        done = true;
    }

    public void undone() {
        done = false;
    }

    @Override
    public String toString() {
        String str = "";
        if (done) {
            str = "[X] ";
        } else {
            str = "[ ] ";
        }
        str = str + taskname;
        return str;
    }
}
