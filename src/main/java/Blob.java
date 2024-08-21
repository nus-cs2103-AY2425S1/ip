import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Blob {
    private static boolean active = true;
    private static int noOfTasks = 0;
    private static HashMap<Integer, Task> db = new HashMap<>();

    //TASK CLASS
    public static class Task {
        protected String name;
        protected boolean isDone;
        protected int taskNumber;

        public Task(String name, int taskNumber, boolean isDone) {
            this.name = name;
            this.isDone = isDone;
            this.taskNumber = taskNumber;
        }

        public void complete() {
            this.isDone = true;
        }

        public void undo() {
            this.isDone = false;
        }

        public String check() {
            return isDone ? "X" : " ";
        }

        @Override
        public String toString() {
            return "[" + this.check() + "] " + this.name;
        }
    }

    //TODO CLASS
    public static class Todo extends Task {
        public Todo(String name, int taskNumber, boolean isDone) {
            super(name,taskNumber,isDone);
        }

        @Override
        public String toString() {
            return "[T]" + "[" + this.check() + "] " + this.name;
        }
    }

    //DEADLINE CLASS
    public static class Deadline extends Task {
        private String deadline;
        public Deadline(String name, int taskNumber, boolean isDone, String deadline) {
            super(name,taskNumber,isDone);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return "[D]" + "[" + this.check() + "] " + this.name + " (by: " + deadline + ")";
        }
    }

    //EVENT CLASS
    public static class Event extends Task {

        private String start;
        private String end;

        public Event(String name, int taskNumber, boolean isDone, String start, String end) {
            super(name,taskNumber,isDone);
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[E]" + "[" + this.check() + "] " + this.name + " (from: " + start + " to: " + end + ")";
        }
    }

    public static void evaluateAction(String action) {
        String[] arr = action.split(" ");

        for (int i = 0; i < arr.length; i++) {
            String act = arr[i].toLowerCase();
            if (Objects.equals(act, "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("______________________________________________");
                active = false;
                break;
            } else if (Objects.equals(act, "list")) {
                System.out.println("Here are the tasks in your list:");
                for (Map.Entry<Integer, Task> entry : db.entrySet()) {
                    Task t = entry.getValue();
                    System.out.println(String.format("%d. %s", t.taskNumber, t));
                }
                System.out.println("______________________________________________");
                break;
            } else if (Objects.equals(act, "mark")) {
                try {
                    Integer index = Integer.valueOf(arr[i + 1]);
                    Task t = db.get(index);
                    t.complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                    System.out.println("______________________________________________");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Command!");
                }

                break;
            } else if (Objects.equals(act, "unmark")) {
                try {
                    Integer index = Integer.valueOf(arr[i + 1]);
                    Task t = db.get(index);
                    t.undo();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                    System.out.println("______________________________________________");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Command!");
                }
                break;

            } else if (Objects.equals(act, "todo")) {
                // task name error handling
                if (arr.length == 1) {
                    System.out.println("Sorry, I am unable to generate an empty 'todo' task!");
                    System.out.println("______________________________________________");
                    break;
                }
                // for task name string
                StringBuilder a = new StringBuilder(arr[i + 1]);
                for (int j = i + 2; j < arr.length; j++) {
                    StringBuilder str = new StringBuilder(" " + arr[j]);
                    a = a.append(str);
                }

                noOfTasks++;
                Todo t = new Todo(a.toString(), noOfTasks, false);
                db.put(t.taskNumber, t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + noOfTasks + " tasks in the list.");
                System.out.println("______________________________________________");
                break;

            } else if (Objects.equals(act, "deadline")) {
                int by = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (Objects.equals(arr[j], "/by")) {
                        by = j;
                    }
                }
                // task name error handling
                if (by <= 1) {
                    System.out.println("I require a description for your 'deadline' task 😅!");
                    System.out.println("______________________________________________");
                    break;
                }
                // task deadline error handling
                if (by == arr.length - 1) {
                    System.out.println("Your deadline task can't not have a deadline! Please enter your task again!");
                    System.out.println("______________________________________________");
                    break;
                }
                // for task name string
                StringBuilder a = new StringBuilder(arr[i + 1]);
                for (int l = i + 2; l < by; l++) {
                    StringBuilder str = new StringBuilder(" " + arr[l]);
                    a = a.append(str);
                }
                // for task deadline string
                StringBuilder s = new StringBuilder(arr[by + 1]);
                for (int k = by + 2; k < arr.length; k++) {
                    StringBuilder str = new StringBuilder(" " + arr[k]);
                    s = s.append(str);
                }

                noOfTasks++;
                Deadline d = new Deadline(a.toString(), noOfTasks, false, s.toString());
                db.put(d.taskNumber, d);
                System.out.println("Got it. I've added this task:");
                System.out.println(d);
                System.out.println("Now you have " + noOfTasks + " tasks in the list.");
                System.out.println("______________________________________________");
                break;

            } else if (Objects.equals(act, "event")) {
                int start = 0;
                int end = 0;
                for (int j = 0; j < arr.length; j++) {
                    if (Objects.equals(arr[j], "/from")) {
                        start = j;
                    } else if (Objects.equals(arr[j], "/to")) {
                        end = j;
                    }
                }
                // task name error handling
                if (start <= 1) {
                    System.out.println("What's the name of your event? Please add it 😅!");
                    System.out.println("______________________________________________");
                    break;
                }
                // start string error handling
                if (end - start <= 1) {
                    System.out.println("When does your event begin? Please add it 😅!");
                    System.out.println("______________________________________________");
                    break;
                }
                // end string error handling
                if (end == arr.length - 1) {
                    System.out.println("What time does your event end? Please let me know 😅!");
                    System.out.println("______________________________________________");
                    break;
                }
                //for task name string
                StringBuilder a = new StringBuilder(arr[i + 1]);
                for (int l = i + 2; l < start; l++) {
                    StringBuilder str = new StringBuilder(" " + arr[l]);
                    a = a.append(str);
                }
                //for start string
                StringBuilder st = new StringBuilder(arr[start + 1]);
                for (int k = start + 2; k < end; k++) {
                    StringBuilder str = new StringBuilder(" " + arr[k]);
                    st = st.append(str);
                }
                //for end string
                StringBuilder en = new StringBuilder(arr[end + 1]);
                for (int k = end + 2; k < arr.length; k++) {
                    StringBuilder str = new StringBuilder(" " + arr[k]);
                    en = en.append(str);
                }

                noOfTasks++;
                Event e = new Event(a.toString(), noOfTasks, false, st.toString(), en.toString());
                db.put(e.taskNumber, e);
                System.out.println("Got it. I've added this task:");
                System.out.println(e);
                System.out.println("Now you have " + noOfTasks + " tasks in the list.");
                System.out.println("______________________________________________");
                break;

            } else {
                System.out.println("ERROR! Unknown Command!");
                System.out.println("______________________________________________");
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner human = new Scanner(System.in);
        System.out.println("______________________________________________");
        System.out.println("Hello! I'm Blob");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________");

        while (active) {
            String action = human.nextLine();
            System.out.println("______________________________________________");
            evaluateAction(action);
        }
    }
}
