import java.util.*;
public class TaskList {
    private List<Task> tasks;
    private Parser p = new Parser();

    private abstract class Task {
        protected String name;
        protected boolean isDone;

        public Task(String description) throws EmptyInputException {
            if (description.length() > 0 && description.charAt(0) != '/') {
                this.name = description;
                this.isDone = false;
            }
            else throw new EmptyInputException();
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
        public void complete() {
            this.isDone = true;
        }
        public void uncomplete() {
            this.isDone = false;
        }
        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.name;
        }
    }

    private class Todo extends Task {
        public Todo(String description) throws EmptyInputException {
            super(description);
        }
        @Override
        public String toString() {
            return "[T]" + "[" + this.getStatusIcon() + "] " + this.name;
        }
    }

    private class Deadline extends Task {
        private String deadline;
        public Deadline(String description) throws EmptyInputException {
            super(description);
            int slashIndex = description.indexOf("/");

            // Extract the substring before the slash
            String beforeSlash = description.substring(0, slashIndex).trim();
            this.name = beforeSlash;

            // Extract the substring after the slash and trim it
            String temp = description.substring(slashIndex + 1).trim();

            // Reformat the deadline to the desired format
            deadline = "(by: " + temp.substring(3).trim() + ")";
        }
        @Override
        public String toString() {
            return "[D]" + "[" + this.getStatusIcon() + "] " + this.name + " " + deadline;
        }
    }

    private class Event extends Task {

        String window;

        public Event(String description) throws EmptyInputException {
            super(description);

            String[] parts = description.split("/");

            // Extract the event description before the first backslash
            String eventDescription = parts[0].trim();
            this.name = eventDescription;

            // Extract the first and second parts after the backslashes, if they exist
            String fromPart = parts.length > 1 ? parts[1].trim() : "";
            String toPart = parts.length > 2 ? parts[2].trim() : "";

            fromPart = fromPart.replaceFirst("^from\\s*", "").trim();
            toPart = toPart.replaceFirst("^to\\s*", "").trim();

            // Add "from:" and "to:" labels
            fromPart = "from: " + fromPart;
            toPart = "to: " + toPart;

            // Reformat the output
            window = "(" + fromPart + " " + toPart + ")";

        }
        @Override
        public String toString() {
            return "[E]" + "[" + this.getStatusIcon() + "] " + this.name + " " + window;
        }
    }


    public TaskList() {
        tasks = new ArrayList<>();
    }
    public void addTask(String task) throws DelphiException {
        if (!task.equals("list")) {
            if (Parser.checkStringPrefix(task, 4, "todo")) {
                Task tsk;
                if (task.length() > 4) tsk = new Todo(task.substring(5));
                else tsk = new Todo("");
                tasks.add(tsk);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tsk);
                System.out.println("Now you have " + tasks.size()+ " tasks in the list.");
            } else if (Parser.checkStringPrefix(task, 8, "deadline")) {
                Task tsk;
                if (task.length() > 8) tsk = new Deadline(task.substring(9));
                else tsk = new Todo("");
                tasks.add(tsk);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tsk);
                System.out.println("Now you have " + tasks.size()+ " tasks in the list.");
            } else if (Parser.checkStringPrefix(task, 5, "event")){
                Task tsk;
                if (task.length() > 5) tsk = new Event(task.substring(6));
                else tsk = new Event("");
                tasks.add(tsk);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tsk);
                System.out.println("Now you have " + tasks.size()+ " tasks in the list.");
            } else {
                throw new InvalidInputException();
            }
        }
        else printTasks();
    }
    public Task removeTask(int i) {
        if (i <= tasks.size()) {
            Task t = tasks.get(i-1);
            tasks.remove(i-1);
            return t;
        } else {
            return null; //may want to add error handling for invalid indexes here
        }
    }
    public int getSize() {
        return tasks.size();
    }
    public void markTaskAsDone(int i) {
        if (i <= tasks.size()) tasks.get(i-1).complete();
        //may want to add error handling for invalid indexes here
    }
    public void markTaskAsUndone(int i) {
        if (i <= tasks.size()) tasks.get(i-1).uncomplete();
        //may want to add error handling for invalid indexes here
    }
    public String getTask(int i) {
        if (i <= tasks.size()) return tasks.get(i-1).toString();
        else return "";
    }
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i).toString());
        }
    }
}
