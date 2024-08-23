import java.util.Scanner;

public class Maga {
    public static abstract class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "[X] " : "[ ] "); // mark done task with X
        }

        public void markAsDone() {
            isDone = true;
        }

        public void markAsUndone() {
            isDone = false;
        }

        public String getDescription() {
            return description;
        }

        public abstract String getTaskType();
    }

    public static class TodoTask extends Task {
        public TodoTask(String description) {
            super(description);
        }

        public String getTaskType() {
            return "[T]";
        }
    }
    public static class EventTask extends Task {
        protected String time;

        public EventTask(String description, String time) {
            super(description);
            this.time = time;
        }

        public String getTaskType() {
            return "[E]";
        }
    }
    public static class DeadlineTask extends Task{
        protected String from;
        protected String to;

        public DeadlineTask(String description, String from, String to) {
            super(description);
            this.from = from;
            this.to = to;
        }

        public String getTaskType() {
            return "[D]";
        }
    }
    public static void main(String[] args) {
        String logo = "  __  __                    \n"
                + " |  \\/  |  __ _   __ _   __ _  \n"
                + " | |\\/| | / _` | / _` | / _` | \n"
                + " | |  | || (_| || (_| || (_| || \n"
                + " |_|  |_| \\__,_| \\__, | \\__,_|  \n"
                + "                  |___/                           \n";
        System.out.println("Hello from\n" + logo +"\nI am THE best chatbot from the one and only" +
                " US of A trust me everyone says I'm the best. How can I help you serve the American people?" );
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        // task array
        Task[] arr = new Task[100];
        int count = 0;
        while(!input.equalsIgnoreCase("bye")) {
            // using list command
            input = input.toLowerCase();
            if(input.equals("list")) {
                System.out.println("Oh dear here are all the tasks in the list, so many, yuuge\n");
                for (int i = 0; i < count; i++) {
                    int temp = i + 1;
                    System.out.println(temp + ". " + arr[i].getTaskType() + arr[i].getStatusIcon()
                            + arr[i].getDescription());
                }

                input = scanner.nextLine();
                continue;
            }

            // marking things as done and undone
            if (input.startsWith("mark ")) {
                System.out.println("Ya boi Donald took the liberty to mark this done:\n");
                char[] charArray = input.toCharArray();
                Task temp = arr[Character.getNumericValue(charArray[charArray.length - 1]) - 1];
                temp.markAsDone();
                System.out.println(temp.getTaskType() + temp.getStatusIcon() + temp.getDescription());
                input = scanner.nextLine();
                continue;
            }

            if (input.toLowerCase().startsWith("unmark ")) {
                System.out.println("Here's the task promised but not completed, just like the dems\n");
                char[] charArray = input.toCharArray();
                Task temp = arr[Character.getNumericValue(charArray[charArray.length - 1]) - 1];
                temp.markAsUndone();
                System.out.println(temp.getStatusIcon() + temp.getDescription());
                input = scanner.nextLine();
                continue;
            }

            // adding things as per normal
            Task tempTask = new TodoTask("");
            if(input.startsWith("todo")) {
                String descrip = input.substring(5).trim();
                tempTask = new TodoTask(descrip);
            } else if(input.startsWith("event")) {
                String descrip = input.substring(6).trim();
                String[] descripArray = descrip.split("/");
                tempTask = new EventTask(descripArray[0], descripArray[1]);
            } else if(input.startsWith("deadline")) {
                String descrip = input.substring(9).trim();
                String[] descripArray = descrip.split("/");
                tempTask = new DeadlineTask(descripArray[0], descripArray[1], descripArray[2]);
            }

            arr[count] = tempTask;
            count++;
            System.out.println("Another task for the American people added:\n" + tempTask.getTaskType()
            + tempTask.getStatusIcon() + tempTask.getDescription());
            input = scanner.nextLine();
        }

        System.out.println("Yeah goodbye. Remember a vote for me is a vote for America!");
    }

}
