package Kayo;

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
import java.util.stream.Collectors;

public class Kayo {
    public static String filepath;
    public static Storage storage;
    public static List<Task> listTasks;
    public static Ui ui;
    public static Parser parser;

    public Kayo(String filepath) {
        Kayo.filepath = filepath;
        storage = new Storage(filepath);
        listTasks = storage.load();
        ui = new Ui();
        parser = new Parser();
        ui.greet();
    }
    /**
     * Runs the Kayo chatbot
     */
    public static void run () {
        String inputString;
        Scanner input = new Scanner(System.in);
        while (true) {
            inputString = input.nextLine();
            String[] splitList = parser.splitString(inputString);
            if (inputString.equals("bye")) {
                break;
            } else if (splitList[0].equals("list")) {
                ui.listItems(listTasks);
            } else if (splitList[0].equals("unmark")) {
                int index = Integer.parseInt(splitList[1])-1;
                listTasks.get(index).setDone(false);
                ui.unmarkTask(listTasks.get(index));
            } else if (splitList[0].equals("mark")) {
                int index = Integer.parseInt(splitList[1])-1;
                listTasks.get(index).setDone(true);
                ui.markTask(listTasks.get(index));
            }else if (splitList[0].equals("todo")) {
                if (splitList.length==1) {
                    new DukeException("OOPS !! The description of a todo can't be empty");
                } else {
                    ToDo todo = new ToDo(splitList[1],false);
                    listTasks.add(todo);
                    ui.addTodo(todo);
                    ui.showTotalTasks(listTasks);
                }
            } else if (splitList[0].equals("deadline")) {
                Deadline deadline = parser.addDeadline(inputString);
                listTasks.add(deadline);
                ui.addDeadline(deadline);
                ui.showTotalTasks(listTasks);
            } else if (splitList[0].equals("event")) {
                Event event = parser.addEvent(inputString);
                listTasks.add(event);
                ui.addEvent(event);
                ui.showTotalTasks(taskList);
            } else if(splitList[0].equals("delete")) {
                int index = Integer.parseInt(splitList[1])-1;
                Task taskToDelete = listTasks.get(index);
                listTasks.remove(index);
                ui.deleteTask(taskToDelete);
            } else if(splitList[0].equals("find")) {
                List<Task> filteredList = taskList.stream()
                                  .filter(c -> c.getTask().contains(splitList[1]))
                                  .collect(Collectors.toList());
                ui.find(filteredList);
            } else {
                new DukeException("OOPS !! Sorry i dont know what that means!");
            }
            storage.updateData(listTasks);
        }
        ui.exit();
    }
    public static void main(String[] args) {
        Kayo kayo = new Kayo("data/kayo.txt");
        run();
    }
    private static class Ui {
        /**
         * Prints deadline in format
         */
        public void addDeadline(Deadline deadline) {
            System.out.print("Got it. I've added this task: \n");
            System.out.print(deadline + "\n");
        }
        /**
         * Prints event in format
         */
        public void addEvent(Event event) {
            System.out.print("Got it. I've added this task: \n");
            System.out.print(event + "\n");
        }
        /**
         * Prints todo in format
         */
        public void addTodo(ToDo todo) {
            System.out.print("Got it. I've added this task: \n");
            System.out.print(todo + "\n");
        }
        /**
         * shows total tasks in format
         */
        public void showTotalTasks(List<Task> listTasks) {
            System.out.print("Now you have " + listTasks.size() + " tasks in the list.\n");
        }
        /**
         * displays deleted task
         */
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
        public void listItems(List<Task> listTasks) {
            System.out.println("Here are the tasks in your list:");
            for(int i = 0; i < listTasks.size(); i++) {
                System.out.println(i+1 + ". "+ listTasks.get(i));
            }
        }
        public void greet() {
            System.out.println("Hello! I'm Kayo! ");
            System.out.println("What can I do for you?");
        }
        public void exit(){
            System.out.println("Bye. I hope to see you again soon!");
        }
        public void find(List<Task> taskList) {
            System.out.println("Here are the matching tasks in your list:");
            for(int i = 0; i < taskList.size(); i++) {
                System.out.println(i+1 + ". "+ taskList.get(i));
            }
        }
    }
    /**
     * Handles the storage and access of data
     * **/
    public static class Storage{
        public String filepath;
        /**
         * Handles the update of data
         * @param listTasks list of tasks to add to data
         * */
        private static void updateData(List<Task> listTasks) {
            try {
                FileWriter fw = new FileWriter("data/kayo.txt");
                String dataBody = "";
                for (int i = 0; i < listTasks.size(); i++) {
                    dataBody += listTasks.get(i).toString() + System.lineSeparator();
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
        /**
         * Loads data from database
         * @return List of tasks from database
         * */
        public List<Task> load () {
            List<Task> listTasks = new ArrayList<>();
            try {
                File f = new File(filepath);
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String readString = sc.nextLine();
                    Task task = getTask(readString);
                    listTasks.add(task);
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found");
            }
            return listTasks;
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
    /**
     * Handles Task details and state
     */
    public static class Task{

        private String task;
        protected String typeOfTask = "T";
        private boolean isDone;

        public Task(String task, boolean isDone) {
            this.task = task;
            this.isDone = isDone;
        }
        /**
         * Sets the task as done
         * @param isDone boolean to set state to
         */
        public void setDone(boolean isDone){
            this.isDone = isDone;
        }
        public String getTask() {
            return task;
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
    /**
     * Handles all deadline tasks
     */
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
    /**
     * Handles Exceptions in Chatbot
     */
    public static class DukeException {
        public DukeException(String message) {
            System.out.println(message);
        }
    }
    /**
     * Handles Parsing of data in Chatbot
     */
    public static class Parser {
        public String[] splitString(String inputString) {
            return inputString.split(" ");
        }
        /**
         * Parses formatted string to deadline
         * @param parseString String to parse
         * @return Deadline
         */
        public Deadline addDeadline(String parseString) {
            String[] bySplit = parseString.split("/by");
            Deadline deadline = new Deadline(parseString.split(" ")[1],false,bySplit[1]);
            return deadline;
        }
        /**
         * Parses formatted string to event
         * @param parseString String to parse
         * @return Event
         */
        public Event addEvent(String parseString) {
            String[] fromBySplit = parseString.split("/from|/to");
            Event event = new Event(parseString.split(" ")[1],false,fromBySplit[1],fromBySplit[2]);
            return event;
        }
    }
    /**
     * Handles all event tasks
     */
    public static class Event extends Task {
        protected String typeOfTask = "E";
        protected String from;
        protected String to;
        public Event(String task, boolean isDone, String from, String to) {
            super(task, isDone);
            this.from = from.trim();
            this.to = to.trim();
        }
        @Override
        public String toString() {
            return "[E] " +super.toString() + " (from: " +from + " to: " + to +  ")";
        }
    }
}

