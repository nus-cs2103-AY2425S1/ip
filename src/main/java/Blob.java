import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Blob {
    private static boolean active = true;
    private static ArrayList<Task> db;

    static {
        // to handle issues related to finding 'database.csv'!
        try {
            String filePath = "./src/main/java/database.csv";
            File f = new File(filePath);
            if (f.createNewFile()) {
                FileWriter fw = new FileWriter(f);
                fw.write("type,is_checked,task_name,time1,time2\n");
                fw.close();
            }

            db = getFileContents("./src/main/java/database.csv");
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No 'database.csv' file found in 'src' > 'main' > 'java' folder!");
        } catch (IOException e) {
            throw new RuntimeException("Database file was unable to be generated!");
        }
    }

    //TASK CLASS
    public static class Task {
        protected String type;
        protected String name;
        protected boolean isDone;

        public Task(String name, boolean isDone) {
            this.name = name;
            this.isDone = isDone;
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
        public Todo(String name, boolean isDone) {
            super(name,isDone);
            super.type = "T";
        }

        @Override
        public String toString() {
            return "[T]" + "[" + this.check() + "] " + this.name;
        }
    }

    //DEADLINE CLASS
    public static class Deadline extends Task {
        private LocalDateTime deadline;
        public Deadline(String name, boolean isDone, String deadline) {
            super(name,isDone);
            this.deadline = LocalDateTime.parse(deadline);
            super.type = "D";
        }

        @Override
        public String toString() {
            return "[D]" + "[" + this.check() + "] " + this.name + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d HH:mm")) + ")";
        }
    }

    //EVENT CLASS
    public static class Event extends Task {

        private LocalDateTime start;
        private LocalDateTime end;

        public Event(String name, boolean isDone, String start, String end) {
            super(name, isDone);
            this.start = LocalDateTime.parse(start);
            this.end = LocalDateTime.parse(end);
            super.type = "E";
        }

        @Override
        public String toString() {
            return "[E]" + "[" + this.check() + "] " + this.name + " (from: " + start.format(DateTimeFormatter.ofPattern("MMM d HH:mm")) + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d HH:mm")) + ")";
        }
    }

    public static void evaluateAction(String action) throws IOException {
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
                for (int j = 0; j < db.size(); j++) {
                    Task t = db.get(j);
                    System.out.println(String.format("%d. %s", j + 1, t));
                }
                System.out.println("______________________________________________");
                break;
            } else if (Objects.equals(act, "mark")) {
                try {
                    int index = Integer.parseInt(arr[i + 1]);
                    Task t = db.get(index - 1);
                    t.complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(t);
                    System.out.println("______________________________________________");
                    updateFileContents("./src/main/java/database.csv", db);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Command!");
                }

                break;
            } else if (Objects.equals(act, "unmark")) {
                try {
                    int index = Integer.parseInt(arr[i + 1]);
                    Task t = db.get(index - 1);
                    t.undo();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(t);
                    System.out.println("______________________________________________");
                    updateFileContents("./src/main/java/database.csv", db);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Command!");
                }
                break;

            } else if (Objects.equals(act, "delete")) {
                try {
                    int index = Integer.parseInt(arr[i + 1]);
                    Task t = db.get(index - 1);
                    db.remove(t);
                    System.out.println("Noted, I've removed this task:");
                    System.out.println(t);
                    System.out.println("Now you have " + db.size() + " tasks in the list.");
                    System.out.println("______________________________________________");
                    updateFileContents("./src/main/java/database.csv", db);
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

                Todo t = new Todo(a.toString(), false);
                db.add(t);
                System.out.println("Got it. I've added this task:");
                System.out.println(t);
                System.out.println("Now you have " + db.size() + " tasks in the list.");
                System.out.println("______________________________________________");
                updateFileContents("./src/main/java/database.csv", db);
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
                StringBuilder sDate = new StringBuilder(arr[by + 1]);
                StringBuilder sTime = new StringBuilder(arr[by + 2]);
                String ISO8601Format = sDate.toString() + "T" + sTime.toString() + ":00";

                try {
                    Deadline d = new Deadline(a.toString(), false, ISO8601Format);
                    db.add(d);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(d);
                    System.out.println("Now you have " + db.size() + " tasks in the list.");
                    System.out.println("______________________________________________");
                    updateFileContents("./src/main/java/database.csv", db);
                } catch (DateTimeParseException e) {
                    System.out.println("Input dates and times not in the format 'yyyy-mm-dd HH:mm'!");
                    System.out.println("______________________________________________");
                }
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
                StringBuilder stDate = new StringBuilder(arr[start + 1]);
                StringBuilder stTime = new StringBuilder(arr[start + 2]);
                String startISO8601Format = stDate.toString() + "T" + stTime.toString() + ":00";

                //for end string
                StringBuilder enDate = new StringBuilder(arr[end + 1]);
                StringBuilder enTime = new StringBuilder(arr[end + 2]);
                String endISO8601Format = enDate.toString() + "T" + enTime.toString() + ":00";

                try {
                    Event e = new Event(a.toString(), false, startISO8601Format, endISO8601Format);
                    db.add(e);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                    System.out.println("Now you have " + db.size() + " tasks in the list.");
                    System.out.println("______________________________________________");
                    updateFileContents("./src/main/java/database.csv", db);
                } catch (DateTimeParseException e) {
                    System.out.println("Input dates and times not in the format 'yyyy-mm-dd HH:mm'!");
                    System.out.println("______________________________________________");
                }
                break;

            } else {
                System.out.println("ERROR! Unknown Command!");
                System.out.println("______________________________________________");
                break;
            }
        }
    }

    private static ArrayList<Task> getFileContents(String filePath) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        s.nextLine(); //skips headers

        while (s.hasNext()) {
            String[] array = s.nextLine().split(",");
            String taskType = array[0];
            int isChecked = Integer.parseInt(array[1]);
            String taskName = array[2];

            if (taskType.equals("T")) {
                Todo t = new Todo(taskName, isChecked == 1);
                tasks.add(t);
            } else if (taskType.equals("D")) {
                Deadline d = new Deadline(taskName, isChecked == 1, array[3]);
                tasks.add(d);
            } else if (taskType.equals("E")) {
                Event e = new Event(taskName, isChecked == 1, array[3], array[4]);
                tasks.add(e);
            }
        }
        return tasks;
    }

    private static void updateFileContents(String filePath, ArrayList<Task> database) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder allData = new StringBuilder("type,is_checked,task_name,time1,time2\n");
        for (int i = 0; i < database.size(); i++) {
            Task t = database.get(i);
            String isChecked = String.valueOf(t.isDone ? 1 : 0);
            if (t.type.equals("T")) {
                StringBuilder data = new StringBuilder(String.format("%s,%s,%s\n", t.type, isChecked, t.name));
                allData.append(data);
            } else if (t.type.equals("D")) {
                Deadline d = (Deadline) t;
                StringBuilder data = new StringBuilder(String.format("%s,%s,%s,%s\n", t.type, isChecked, t.name, d.deadline));
                allData.append(data);
            } else if (t.type.equals("E")) {
                Event e = (Event) t;
                StringBuilder data = new StringBuilder(String.format("%s,%s,%s,%s,%s\n", t.type, isChecked, t.name, e.start, e.end));
                allData.append(data);
            }
        }
        fw.write(allData.toString());
        fw.close();
    }

    public static void main(String[] args) throws IOException {
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
