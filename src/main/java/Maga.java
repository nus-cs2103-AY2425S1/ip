import java.util.Scanner;

public class Maga {
    public static class TaskList {
        private Task[] taskList;
        private int taskCount = 0;

        public TaskList(int size) {
            taskList = new Task[size];
        }

        public void printTasks() {
            System.out.println("Take a look, all the tasks you have here, so many, yuuuuuuge\n");
            for (int i = 0; i < taskCount; i++) {
                int temp = i + 1;
                System.out.println(temp + ". " + taskList[i].getTaskType() + taskList[i].getStatusIcon()
                        + taskList[i].getDescription());
            }
        }

        public Task getTask(int id) {
            return taskList[id];
        }

        public void deleteTask(int taskNumber) {
            Task tempTask = taskList[taskNumber];
            try {
                taskCount--;
                System.out.print("I've deleted this task:\n" + tempTask.getTaskType() + tempTask.getStatusIcon() +
                        tempTask.getDescription() + "\nYou have " + taskCount + " task(s) now!\n");
                for (int i = taskCount; i < taskList.length - 2; i++) {
                    taskList[i] = taskList[i + 1];
                }
            } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid task specified!");
                taskCount++;
            }
        }

        public void markTask(int taskNumber) {
            Task temp = taskList[taskNumber];
            if (temp == null) {
                System.out.println("You're trying to mark a task that DOESN'T EXIST, like bad people on JAN 6. " +
                        "Some of the kindest and most lovely souls I've met");
            } else {
                temp.markAsDone();
                System.out.println(temp.getTaskType() + temp.getStatusIcon() + temp.getDescription());
                System.out.println("Ya boi Donald took the LIBERTY to mark this done:\n");
            }
        }

        public void unmarkTask(int taskNumber) {
            Task temp = taskList[taskNumber];
            if (temp == null) {
                System.out.println("Stop trying to unmark tasks like ILLEGAL ALIENS after" +
                        " I'm president: NOT HERE!");
            } else {
                temp.markAsUndone();
                System.out.println("Here's the task promised but not completed, just like the DEMS\n");
                System.out.println(temp.getStatusIcon() + temp.getDescription());
            }
        }

        public void addTask(String input) {
            Task task = new TodoTask("");
            if(input.startsWith("todo ")) {
                String descrip = input.substring(5).trim();
                task = new TodoTask(descrip);
            } else if(input.startsWith("event ")) {
                String descrip = input.substring(6).trim();
                String[] descripArray = descrip.split("/");
                if (descripArray.length != 2) {
                    System.out.println("An event needs a date!! Don't be Crooked Kamala!!");
                    return;
                }
                task = new EventTask(descripArray[0], descripArray[1]);
            } else if(input.startsWith("deadline ")) {
                String descrip = input.substring(9).trim();
                String[] descripArray = descrip.split("/");
                if (descripArray.length != 3) {
                    System.out.println("A deadline needs a start and end!! Filibusters are a threat to the " +
                            "American people!!");
                    return;
                }
                task = new DeadlineTask(descripArray[0], descripArray[1], descripArray[2]);
            }

            try {
                taskList[taskCount] = task;
                taskCount++;
                System.out.println("Another task for the American people added:\n" + task.getTaskType()
                        + task.getStatusIcon() + task.getDescription() + "\nYou have " + taskCount + " task(s) now!");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Tasklist is full!");
            }
        }
    }
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

    public static void printGreeting() {
        String logo = "  __  __                    \n"
                + " |  \\/  |  __ _   __ _   __ _  \n"
                + " | |\\/| | / _` | / _` | / _` | \n"
                + " | |  | || (_| || (_| || (_| || \n"
                + " |_|  |_| \\__,_| \\__, | \\__,_|  \n"
                + "                  |___/                           \n";
        System.out.println("Hello from\n" + logo +"\nI am THE best chatbot from the one and only" +
                " US of A trust me everyone says I'm the best. How can I help you serve the American people?" );
    }

    public static void closeBot() {
        System.out.println("Yeah I'ma see you in my next RALLY! A vote for me is a vote for America!");
    }

    public static void main(String[] args) {
        printGreeting();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // initialise taskList to store tasks
        TaskList taskList = new TaskList(100);

        while(!input.equalsIgnoreCase("bye")) {
            // display task list
            input = input.toLowerCase();
            if(input.equals("list")) {
                taskList.printTasks();
                input = scanner.nextLine();
                continue;
            }

            // mark tasks as done
            if (input.startsWith("mark ")) {
                char[] charArray = input.toCharArray();
                int taskNumber = Character.getNumericValue(charArray[charArray.length - 1]) - 1;
                taskList.markTask(taskNumber);
                input = scanner.nextLine();
                continue;
            }

            //marks tasks as undone
            if (input.toLowerCase().startsWith("unmark ")) {
                char[] charArray = input.toCharArray();
                int taskNumber = Character.getNumericValue(charArray[charArray.length - 1]) - 1;
                taskList.unmarkTask(taskNumber);
                input = scanner.nextLine();
                continue;
            }

            // delete tasks
            if(input.startsWith("delete ")) {
                String descrip = input.substring(7).trim();
                int tempInt;
                try {
                    tempInt = Integer.parseInt(descrip);
                } catch (Exception NumberFormatException) {
                    System.out.println("You can only delete a task number! No one calls amendments by their names!!");
                    input = scanner.nextLine();
                    continue;
                }

                System.out.println(tempInt);
                taskList.deleteTask(tempInt - 1);
                input = scanner.nextLine();
                continue;

            }

            // add tasks to tasklist
            if(input.startsWith("todo ") || input.startsWith("event ") || input.startsWith("deadline ")) {
                taskList.addTask(input);
                input = scanner.nextLine();
                continue;
            }

            // should never reach here unless command is invalid
            System.out.println("HEY! SLEEPY JOE and CROOKED KAMALA " +
                    "might be demented but you're not! Specify a command!");
            input = scanner.nextLine();
        }

        closeBot();
    }

}
