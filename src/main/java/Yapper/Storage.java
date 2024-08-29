package Yapper;import java.io.File;import java.io.FileWriter;import java.io.IOException;import java.time.LocalDate;import java.util.Scanner;import java.util.ArrayList;public class Storage {    public File file;    public Storage(File file) {        this.file = file;    }    public void writeHistory(ArrayList<Task> listOfTasks) {        try {            FileWriter filewriter = new FileWriter(file);            for (Task task : listOfTasks) {                filewriter.write(task.toFile() + "\n");            }            filewriter.close();        } catch (IOException e) {            System.out.println("Unable to write history");        }    }    public TaskList loadHistory() {        ArrayList<Task> listOfTasks = new ArrayList<>();        try {            if (file.exists()) {                Scanner sc = new Scanner(file);                while (sc.hasNextLine()) {                    String nextline = sc.nextLine();                    loadTask(listOfTasks, nextline.substring(0, 1), nextline.substring(2));                }            }        } catch (IOException e) {            System.out.println("Unable to load history");        } catch (YapperException e) {            System.out.println(e.getMessage());        }        return new TaskList(listOfTasks, this);    }    public void loadTask(ArrayList<Task> listOfTasks, String task, String command) throws YapperException {        if (task.equals("T")) {            String done = command.substring(0,1);            String taskName = command.substring(2);            ToDo todo = new ToDo(taskName);            if (done.equals("D")) {                todo.setDone(true);            }            listOfTasks.add(todo);        } else if (task.equals("D")) {            String done = command.substring(0,1);            String input = command.substring(2);            String[] split = input.split(" /by ");            String taskName = split[0];            LocalDate deadlineTime = LocalDate.parse(split[1]);            Deadline deadline = new Deadline(taskName, deadlineTime);            if (done.equals("D")) {                deadline.setDone(true);            }            listOfTasks.add(deadline);        } else if (task.equals("E")) {            String done = command.substring(0,1);            String input = command.substring(2);            String[] split = input.split(" /from ");            String taskName = split[0];            String[] split2 = split[1].split(" /to ");            LocalDate fromDate = LocalDate.parse(split2[0]);            LocalDate toDate = LocalDate.parse(split2[1]);            Event event = new Event(taskName, fromDate, toDate);            if (done.equals("D")) {                event.setDone(true);            }            listOfTasks.add(event);        } else {            throw new YapperException("Error loading history");        }    }}