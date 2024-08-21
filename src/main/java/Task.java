public class Task {
    private boolean isCompleted;
    private String description;

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

    public void checkTask(TaskList commands) {
        String[] replyArray = this.description.split(" ");
        String firstCommand = replyArray[0];
        if (this.isTerminate()) {   //Terminate chatbot if user types in "bye"
            System.out.println("-------------------------------------");
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println("-------------------------------------");
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
        } else { //Add the input provided into the String array and return a message "added:   "
            commands.addTask(this);
        }
    }
    public void mark() {
        this.isCompleted = true;
        System.out.println("-------------------------------------");
        System.out.println("\tNice! I've marked this task as done:\n\t  " + this.toString());
        System.out.println("-------------------------------------");
    }

    public void unmark() {
        this.isCompleted = false;
        System.out.println("-------------------------------------");
        System.out.println("\tOK, I've marked this task as not done yet:\n\t  " + this.toString());
        System.out.println("-------------------------------------");
    }
}
