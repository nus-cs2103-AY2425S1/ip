import java.util.Scanner;
import java.util.ArrayList;

public class streams {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from Streams!");
        System.out.println("What can I do for you?");
        ArrayList<Task> todo = new ArrayList<>();
        String cmd = "";
        do {
            cmd = sc.nextLine().trim();
            try {
                if (cmd.equals("list")) {
                    if (todo.isEmpty()) {
                        System.out.println("your task list is empty!!");
                    } else {
                        for (int i = 0; i < todo.size(); i++) {
                            System.out.println((i + 1) + ". " + todo.get(i));
                        }
                    }
                } else if (cmd.startsWith("mark")) {
                    handleMarkUnmark(cmd, todo, true);
                } else if (cmd.startsWith("unmark")) {
                    handleMarkUnmark(cmd, todo, false);
                } else if (cmd.startsWith("todo")) {
                    handleToDo(cmd, todo);
                } else if (cmd.startsWith("deadline")) {
                    handleDeadline(cmd, todo);
                } else if (cmd.startsWith("event")) {
                    handleEvent(cmd, todo);
                } else if (cmd.equals("bye")) {
                    System.out.println("bye. hope to see you again soon!");
                } else {
                    System.out.println("incorrect command: " + cmd);
                    System.out.println("take help from the guideline for prompt");
                }
            } catch (Exception e) {
                System.out.println("an unexpected error occurred: " + e.getMessage());
            }
        } while (!cmd.equals("bye"));
        sc.close();
    }

    private static void handleMarkUnmark(String cmd, ArrayList<Task> todo, boolean isDone) {
        try {
            int no = Integer.parseInt(cmd.substring(cmd.indexOf(' ') + 1).trim()) - 1;
            if (no < 0 || no >= todo.size()) {
                System.out.println("invalid task number");
            } else {
                if (isDone) {
                    todo.get(no).markAsDone();
                    System.out.println("marked task done: " + todo.get(no));
                } else {
                    todo.get(no).markAsNotDone();
                    System.out.println("marked task not done: " + todo.get(no));
                }
            }
        } catch (StringIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("error parsing task number");
        }
    }

    private static void handleToDo(String cmd, ArrayList<Task> todo) {
        if (cmd.length() <= 5) {
            System.out.println("the description of a todo cannot be empty");
            return;
        }
        String description = cmd.substring(5).trim();
        if (description.isEmpty()) {
            System.out.println("the description of a todo cannot be empty");
        } else {
            todo.add(new ToDoTask(description));
            System.out.println("added todo: " + description);
        }
    }

    private static void handleDeadline(String cmd, ArrayList<Task> todo) {
        try {
            if (cmd.length() <= 9) {
                System.out.println("the description of a deadline cannot be empty");
                return;
            }
            String[] sub = cmd.substring(9).trim().split(" /by ");
            if (sub.length != 2) {
                System.out.println("the format for deadlines is 'deadline [description] /by [date]'");
                return;
            }
            String description = sub[0].trim();
            String by = sub[1].trim();
            if (description.isEmpty()) {
                System.out.println("the description of a deadline cannot be empty");
            } else {
                todo.add(new DeadlineTask(description, by));
                System.out.println("added deadline: " + description + " (by: " + by + ")");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("the format for deadlines is 'deadline [description] /by [date]'");
        }
    }

    private static void handleEvent(String cmd, ArrayList<Task> todo) {
        try {
            if (cmd.length() <= 6) {
                System.out.println("the description of an event cannot be empty");
                return;
            }
            String[] sub = cmd.substring(6).trim().split(" /from ");
            if (sub.length != 2) {
                System.out.println("the format for events is 'event [description] /from [date] /to [date]'");
                return;
            }
            String description = sub[0].trim();
            String[] time = sub[1].split(" /to ");
            if (time.length != 2) {
                System.out.println("the format for events is 'event [description] /from [date] /to [date]'");
                return;
            }
            String from = time[0].trim();
            String to = time[1].trim();
            if (description.isEmpty()) {
                System.out.println("the description of an event cannot be empty");
            } else {
                todo.add(new EventTask(description, from, to));
                System.out.println("added event: " + description + " (from: " + from + " to: " + to + ")");
            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("the format for events is 'event [description] /from [date] /to [date]'");
        }
    }

    static class Task {
        private boolean isDone;
        private String description;
        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }
        public void markAsDone() {
            this.isDone = true;
        }
        public void markAsNotDone() {
            this.isDone = false;
        }
        @Override
        public String toString() {
            return (isDone ? "[X] " : "[ ] ") + description;
        }
    }

    static class ToDoTask extends Task {
        public ToDoTask(String description) {
            super(description);
        }
        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    static class DeadlineTask extends Task {
        private String by;
        public DeadlineTask(String description, String by) {
            super(description);
            this.by = by;
        }
        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    static class EventTask extends Task {
        private String from;
        private String to;
        public EventTask(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
        }
    }
}
