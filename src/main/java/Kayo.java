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
    public static void main(String[] args) {
        List<Task> taskList = new ArrayList<>();
        Greet();
        Scanner input = new Scanner(System.in);
        String inputString;
        try {
            File f = new File("data/kayo.txt");
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String readString = sc.nextLine();
                Task task = getTask(readString);
                taskList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
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
            }else if(splitList[0].equals("delete")) {
                int index = Integer.parseInt(splitList[1])-1;
                Task taskToDelete = taskList.get(index);
                taskList.remove(index);
                System.out.print("Noted. I've removed this task: \n");
                System.out.print(taskToDelete + "\n");
                System.out.print("Now you have " + taskList.size() + " tasks in the list.\n");
            } else {
                new DukeException("OOPS !! Sorry i dont know what that means!");
            }
            updateData(taskList);
        }
        Exit();
    }

    private static Task getTask(String readString) {
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

