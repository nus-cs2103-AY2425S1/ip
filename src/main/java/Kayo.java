import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kayo {
    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();
        Greet();
        Scanner input = new Scanner(System.in);
        String inputString;
        while(true) {
            inputString = input.nextLine();
            String[] splitList = inputString.split(" ");
            if(inputString.equals("bye")) {
                break;
            } else if(splitList[0].equals("list")) {
                ListItems(taskList);
            } else if (splitList[0].equals("unmark")) {
                int index = Integer.parseInt(splitList[1])-1;
                taskList.get(index).setDone(false);
                System.out.print("OK, I've marked this task as not done yet: \n");
                System.out.print(taskList.get(index) + "\n");
            } else if(splitList[0].equals("mark")) {
                int index = Integer.parseInt(splitList[1])-1;
                taskList.get(index).setDone(true);
                System.out.print("Nice! I've marked this task at done: \n");
                System.out.print(taskList.get(index) + "\n");
            }else if(splitList[0].equals("todo")) {
                if(splitList.length==1) {
                    new DukeException("OOPS !! The description of a todo can't be empty");
                } else {
                    ToDo todo = new ToDo(splitList[1],false);
                    taskList.add(todo);
                    System.out.print("Got it. I've added this task: \n");
                    System.out.print(todo + "\n");
                    System.out.print("Now you have " + taskList.size() + " tasks in the list.\n");
                }
            } else if(splitList[0].equals("deadline")) {
                String[] bySplit = inputString.split("/by");
                Deadline deadline = new Deadline(splitList[1],false,bySplit[1]);
                taskList.add(deadline);
                System.out.print("Got it. I've added this task: \n");
                System.out.print(deadline + "\n");
                System.out.print("Now you have " + taskList.size() + " tasks in the list.\n");
            } else if(splitList[0].equals("event")) {
                String[] fromBySplit = inputString.split("/from|/to");
                Event event = new Event(splitList[1],false,fromBySplit[1],fromBySplit[2]);
                taskList.add(event);
                System.out.print("Got it. I've added this task: \n");
                System.out.print(event + "\n");
                System.out.print("Now you have " + taskList.size() + " tasks in the list.\n");
            } else {
                new DukeException("OOPS !! Sorry i dont know what that means!");
            }
        }
        Exit();
    }

    private static class Task{

        private String task;
        protected String typeOfTask = "T";
        private boolean isDone;

        public Task(String task, boolean isDone) {
            this.task = task;
            this.isDone = isDone;
        }
        public void setDone(boolean isDone){
            this.isDone = isDone;
        }
        public String toString(){
            String taskString = (isDone) ? "[X]": "[ ]";
            return taskString + task;
        }
    }
    private static void ListItems(List<Task> taskList) {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < taskList.size(); i++) {
            System.out.println(i+1 + ". "+ taskList.get(i));
        }
    }
    public static void Greet() {
        System.out.println("Hello! I'm Kayo! ");
        System.out.println("What can I do for you?");
    }
    public static void Exit(){
        System.out.println("Bye. I hope to see you again soon!");
    }

    public static class ToDo extends Task{

        public ToDo(String task, boolean isDone) {
            super(task, isDone);
        }
        @Override
        public String toString() {
            return "[T]"+ super.toString();
        }
    }
    public static class Deadline extends Task {
        protected String by;
        public Deadline(String task, boolean isDone, String by) {
            super(task, isDone);
            this.by = by;
        }
        @Override
        public String toString() {
            return "[D] "+super.toString() + "(by" + by + ")";
        }
    }
    public static class DukeException {
        public DukeException(String message) {
            System.out.println(message);
        }
    }

    public static class Event extends Task {
        protected String typeOfTask = "E";
        protected String from;
        protected String to;
        public Event(String task, boolean isDone, String from, String to) {
            super(task, isDone);
            this.from = from;
            this.to = to;
        }
        public void setFromTo(String from, String to) {
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString() {
            return "[E]" +super.toString() + "(from: " +from + " to: " + to +  ")";
        }
    }
}

