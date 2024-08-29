import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Kayo {
    public static String filepath;
    public static Storage storage;
    public static List<Task> taskList;
    public static Ui ui;
    public static Parser parser;
    public Kayo(String filepath) {
        Kayo.filepath = filepath;
        storage = new Storage(filepath);
        taskList = storage.load();
        ui = new Ui();
        parser = new Parser();
        ui.Greet();
    }
    public static void run() {
        String inputString;
        Scanner input = new Scanner(System.in);
        while(true) {
            inputString = input.nextLine();
            String[] splitList = parser.splitString(inputString);
            if(inputString.equals("bye")) {
                break;
            } else if(splitList[0].equals("list")) {
                ui.ListItems(taskList);
            } else if (splitList[0].equals("unmark")) {
                int index = Integer.parseInt(splitList[1])-1;
                taskList.get(index).setDone(false);
                ui.unmarkTask(taskList.get(index));
            } else if(splitList[0].equals("mark")) {
                int index = Integer.parseInt(splitList[1])-1;
                taskList.get(index).setDone(true);
                ui.markTask(taskList.get(index));
            }else if(splitList[0].equals("todo")) {
                if(splitList.length==1) {
                    new DukeException("OOPS !! The description of a todo can't be empty");
                } else {
                    ToDo todo = new ToDo(splitList[1],false);
                    taskList.add(todo);
                    ui.addTodo(todo);
                    ui.showTotalTasks(taskList);
                }
            } else if(splitList[0].equals("deadline")) {
                Deadline deadline = parser.addDeadline(inputString);
                taskList.add(deadline);
                ui.addDeadline(deadline);
                ui.showTotalTasks(taskList);
            } else if(splitList[0].equals("event")) {
                Event event = parser.addEvent(inputString);
                taskList.add(event);
                ui.addEvent(event);
                ui.showTotalTasks(taskList);
            }else if(splitList[0].equals("delete")) {
                int index = Integer.parseInt(splitList[1])-1;
                Task taskToDelete = taskList.get(index);
                taskList.remove(index);
                ui.deleteTask(taskToDelete);
            } else {
                new DukeException("OOPS !! Sorry i dont know what that means!");
            }
            storage.updateData(taskList);
        }
        ui.Exit();
    }
    public static void main(String[] args) {
        Kayo kayo = new Kayo("data/kayo.txt");
        run();
    }
    private static class Ui {
        public void addDeadline(Deadline deadline) {
            System.out.print("Got it. I've added this task: \n");
            System.out.print(deadline + "\n");
        }
        public void addEvent(Event event) {
            System.out.print("Got it. I've added this task: \n");
            System.out.print(event + "\n");
        }
        public void addTodo(ToDo todo) {
            System.out.print("Got it. I've added this task: \n");
            System.out.print(todo + "\n");
        }
        public void showTotalTasks(List<Task> taskList) {
            System.out.print("Now you have " + taskList.size() + " tasks in the list.\n");
        }
        public void deleteTask(Task task) {
            System.out.print("Noted. I've removed this task: \n");
            System.out.print(task + "\n");
        }
        public void markTask(Task task) {
            System.out.print("Nice! I've marked this task at done: \n");
            System.out.print(task + "\n");
        }
        public void unmarkTask(Task task){
            System.out.print("OK, I've marked this task as not done yet: \n");
            System.out.print(task + "\n");
        }
        public void ListItems(List<Task> taskList) {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < taskList.size(); i++) {
                System.out.println(i+1 + ". "+ taskList.get(i));
            }
        }
        public void Greet() {
            System.out.println("Hello! I'm Kayo! ");
            System.out.println("What can I do for you?");
        }
        public void Exit(){
            System.out.println("Bye. I hope to see you again soon!");
        }
    }
    private static class Storage{
        public String filepath;
        private static void updateData(List<Task> taskList) {
            try {
                FileWriter fw = new FileWriter("data/kayo.txt");
                String dataBody = "";
                for (int i = 0; i < taskList.size(); i++) {
                    dataBody += taskList.get(i).toString() + System.lineSeparator();
                }
                fw.write(dataBody);
                fw.close();
            } catch (IOException e) {
                System.out.println("File not found");
            }
        }
        public Storage(String filepath){
            this.filepath = filepath;
        }
        public List<Task> load () {
            List<Task> taskList = new ArrayList<>();
            try {
                File f = new File(filepath);
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String readString = sc.nextLine();
                    Task task = getTask(readString);
                    taskList.add(task);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
            return taskList;
        }
        private Task getTask(String readString) {
            String[] splitStrings = readString.split(" ");
            boolean isDone = !splitStrings[1].equals("[");
            Task task = null;
            String taskDetails = readString.substring(8);
            String[] splitDetails = taskDetails.split(" ");
            if (Objects.equals(splitStrings[0], "[T]")) {
                task = new ToDo(splitDetails[0],isDone);
            } else if (Objects.equals(splitStrings[0], "[D]")) {
                String byString = splitDetails[2].substring(0, splitDetails[2].length()-1);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy");
                String day = splitDetails[3].length()==1? "0" + splitDetails[3] : splitDetails[3];
                String data = splitDetails[2]+"/"+day+"/"+splitDetails[4].substring(0,splitDetails[4].length()-1);
                LocalDate date = LocalDate.parse(data,formatter);
                String newDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                task = new Deadline(splitDetails[0],isDone,newDate);
            } else if (Objects.equals(splitStrings[0], "[E]")) {
                task = new Event(splitDetails[0],isDone, splitDetails[2],splitDetails[4].substring(0, splitDetails[4].length()-1));
            }
            return task;
        }
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
            String taskString = (isDone) ? "[X] ": "[ ] ";
            return taskString + task;
        }
    }





    public static class ToDo extends Task {

        public ToDo(String task, boolean isDone) {
            super(task, isDone);
        }
        @Override
        public String toString() {
            return "[T] "+ super.toString();
        }
    }
    public static class Deadline extends Task {
        protected LocalDate by;
        public Deadline(String task, boolean isDone, String by) {
            super(task, isDone);
            this.by = LocalDate.parse(by.trim());
        }
        @Override
        public String toString() {
            return "[D] "+super.toString() + " (by " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
    public static class DukeException {
        public DukeException(String message) {
            System.out.println(message);
        }
    }
    public static class Parser {
        public String[] splitString(String inputString) {
            return inputString.split(" ");
        }
        public Deadline addDeadline(String parseString) {
            String[] bySplit = parseString.split("/by");
            Deadline deadline = new Deadline(parseString.split(" ")[1],false,bySplit[1]);
            return deadline;
        }
        public Event addEvent(String parseString) {
            String[] fromBySplit = parseString.split("/from|/to");
            Event event = new Event(parseString.split(" ")[1],false,fromBySplit[1],fromBySplit[2]);
            return event;
        }
    }
    public static class Event extends Task {
        protected String typeOfTask = "E";
        protected String from;
        protected String to;
        public Event(String task, boolean isDone, String from, String to) {
            super(task, isDone);
            this.from = from.trim();
            this.to = to.trim();
        }
        public void setFromTo(String from, String to) {
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString() {
            return "[E] " +super.toString() + " (from: " +from + " to: " + to +  ")";
        }
    }
}

