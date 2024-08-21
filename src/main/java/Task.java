import java.util.Arrays;
import java.util.List;

public class Task {
    private boolean isCompleted;
    private String description;
    private final String lineBreak = "-------------------------------------";
    private final List<String> validCommands = Arrays.asList(
            "list",
            "mark",
            "unmark",
            "todo",
            "deadline",
            "event",
            "bye",
            "delete"
    );

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    public boolean isTerminate() {
        return this.description.equals("bye");
    }

    public void checkTask(TaskList commands) throws EchoException {
        try {
            String[] replyArray = this.description.split(" ");
            String firstCommand = replyArray[0];

            //Throw error if it does not contain the valid commands
            if (!validCommands.contains(firstCommand)) {
                throw new EchoException(lineBreak + "\nSorry! I don't get what you mean\n" + lineBreak);
            }

            if (this.isTerminate()) {   //Terminate chatbot if user types in "bye"
                System.out.println(lineBreak + "\nBye. Hope to see you again soon!\n" + lineBreak);
            } else if (firstCommand.equals("list")){ //Iterate through the String array and list out the commands
                commands.listAllTask();
            } else if (firstCommand.equals("mark")){ // Mark the specific task in commands
                int index = Integer.parseInt(replyArray[1]) - 1;
                Task currTask = commands.getTask(index);
                currTask.mark();
            } else if (firstCommand.equals("unmark")) { //Unmark the specific task in commands
                int index = Integer.parseInt(replyArray[1]) - 1;
                Task currTask = commands.getTask(index);
                currTask.unmark();
            } else if (firstCommand.equals("delete")) { //delete the task from the task list
                    int index = Integer.parseInt(replyArray[1]) - 1;
                    commands.deleteTask(index);
            } else { //Add the input provided into the String array and return a message "added:   "
                commands.addTask(this);
            }
        } catch (EchoException e) {
            System.err.println(e.getMessage());
        }

    }
    public void mark() {
        this.isCompleted = true;
        System.out.println(lineBreak + "\nNice! I've marked this task as done:\n" +
                this.toString() + "\n" + lineBreak);
    }

    public void unmark() {
        this.isCompleted = false;
        System.out.println(lineBreak + "\nOK, I've marked this task as not done yet:\n" +
                this.toString() + "\n" + lineBreak);
    }
}
