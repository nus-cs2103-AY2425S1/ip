import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> taskList = new ArrayList<>();
    private File file;
    private Scanner scanner;
    private String path  = "./data/meeju.txt";

    /* Note - The delimiter used is '!-' */

    public Storage() {
        this.file = new File(this.path);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (file.exists()) {
            try {
                this.scanner = new Scanner(this.file);
            } catch (Exception e) {
                System.out.println(e); //Ideally never reached here!
            }
            while (scanner.hasNext()) {
                String currentLine = scanner.nextLine();
                String[] currentLineParsed = currentLine.split("!-");
                Task task;
                try {
                    if (currentLineParsed[0].strip().equals("T")) {
                        task = new Todo(currentLineParsed[2]);
                        this.taskList.add(task);
                    } else if (currentLineParsed[0].strip().equals("D")) {
                        task = new Deadline(currentLineParsed[2].strip(),
                                currentLineParsed[3].strip());
                        this.taskList.add(task);
                    } else {
                        task = new Event(currentLineParsed[2].strip(),
                                currentLineParsed[3].strip(), currentLineParsed[4].strip());
                        this.taskList.add(task);
                    }
                    if (currentLineParsed[1].strip().equals("true")) {
                        task.setIsDone(true);
                    }
                } catch (Exception e) {
                    System.out.println(e); //Ideally never reached here!
                }
            }
        }
    }

    //Create a write method to write to file. each time a change is recorded, call this method
    public void updateFile() throws MeejuException {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(this.path);
        } catch (IOException e) {
            throw new MeejuException("IO exception!");
        }
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < getNumberOfTask(); i++) {
            String next;
            Task currentTask = this.taskList.get(i);
            next = currentTask.serializeDetails();
            content.append(next);
        }
        try {
            fileWriter.write(content.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new MeejuException("IO exception!");
        }
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }


    public int getNumberOfTask() {
        return this.taskList.size();
    }
    public void printList() {
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("\t" + (i+1) + ". " + this.taskList.get(i));
        }
    }
    public void markTask(String taskNumber) throws MeejuException{
        Task taskToMark;
        if (taskNumber.isEmpty()) {
            throw new MeejuException("Please specify which task to mark!");
        }
        try {
            taskToMark = getTask(Integer.parseInt(taskNumber) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new MeejuException("You have given me a non existent task to mark!");
        }
        if (taskToMark.getIsDone()) {
            throw new MeejuException("The task is already marked!");
        }
        taskToMark.setIsDone(true);
        System.out.println("Meow! I've marked this task as done:\n" +
                "\t" + taskToMark);
        this.updateFile();
    }

    public void unmarkTask(String taskNumber) throws MeejuException{
        Task taskToUnmark;
        if (taskNumber.isEmpty()) {
            throw new MeejuException("Please specify which task to unmark!");
        }
        try {
            taskToUnmark = getTask(Integer.parseInt(taskNumber) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new MeejuException("You have given me a non existent task to unmark!");
        }
        if (!taskToUnmark.getIsDone()) {
            throw new MeejuException("The task is not marked yet!");
        }
        taskToUnmark.setIsDone(false);

        System.out.println("Meow! I've marked this task as not done yet:\n" +
                "\t" + taskToUnmark);
        this.updateFile();
    }

    public void addTodoTask(String taskInstruction) throws MeejuException {
        if (taskInstruction.isEmpty()) {
            throw new MeejuException("Please give a caption to the task!");
        }
        Todo task = new Todo(taskInstruction);
        this.taskList.add(task);
        System.out.println("Meow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
        this.updateFile();
    }

    public void addDeadlineTask(String taskInstruction) throws MeejuException{
        if (taskInstruction.isEmpty()) {
            throw new MeejuException("Please give a caption to the task!");
        }
        String taskDescription;
        String taskDeadline;
        try {
            taskDescription = taskInstruction.substring(0, taskInstruction.indexOf(" /by"));
            taskDeadline = taskInstruction.substring(taskInstruction.indexOf(" /by") + 5);
        } catch (StringIndexOutOfBoundsException e){
            throw new MeejuException("I'm having a bit of trouble understanding the task.\n" +
                    "Could you please explain it using the correct format?\n" +
                    "The Correct format is -> deadline <desc> /by DD/MM/YYYY HHMM");
        }
        if (taskDescription.isEmpty() || taskDeadline.isEmpty()) {
            throw new MeejuException("I can't understand the task details!");
        }
        Deadline task = new Deadline(taskDescription, taskDeadline);
        this.taskList.add(task);

        System.out.println("Meow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
        this.updateFile();
    }

    public void addEventTask(String taskInstruction) throws MeejuException{
        if (taskInstruction.isEmpty()) {
            throw new MeejuException("Please give a caption to the task!");
        }
        String taskDescription;
        String taskStart;
        String taskEnd;
        try {
            taskDescription = taskInstruction.substring(0, taskInstruction.indexOf(" /from"));
            taskStart = taskInstruction.substring(taskInstruction.indexOf(" /from") + 7,
                    taskInstruction.indexOf(" /to"));
            taskEnd = taskInstruction.substring(taskInstruction.indexOf(" /to") + 5);
        } catch (StringIndexOutOfBoundsException e){
            throw new MeejuException("I'm having a bit of trouble understanding the task. \n" +
                    "Could you please explain it using the correct format?\n" +
                    "The Correct format is -> event <desc> /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM");
        }
        if (taskDescription.isEmpty() || taskStart.isEmpty() || taskEnd.isEmpty()) {
            throw new MeejuException("I can't understand the task details!");
        }

        Event task = new Event(taskDescription, taskStart, taskEnd);
        this.taskList.add(task);

        System.out.println("Meow! I've added this task:\n" +
                "\t" + task + "\nNow you have " + getNumberOfTask() + " tasks in the list.");
        this.updateFile();
    }

    public void deleteTask(String taskNumber) throws MeejuException {
        Task taskToDelete;
        if (taskNumber.isEmpty()) {
            throw new MeejuException("Please specify which task to delete!");
        }
        try {
            taskToDelete = getTask(Integer.parseInt(taskNumber) - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new MeejuException("You have given me a non existent task to delete!");
        }

        this.taskList.remove(Integer.parseInt(taskNumber) - 1);

        System.out.println("Meow! I've removed this task:\n" +
                "\t" + taskToDelete +
                "\nNow you have " + getNumberOfTask() + " tasks in the list.");
        this.updateFile();

    }

}
