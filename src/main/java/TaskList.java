import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<IndividualTask> tasks = new ArrayList<IndividualTask>();


    private String message = "";

    private String indent = "      ";
    private String separator = "------------------------------";

    public TaskList(ArrayList<IndividualTask> tasks) throws MentalHealthException {
        this.tasks = tasks;
    }
    public TaskList() {

    }

    public void getTasks() {
        System.out.println(this.indent + this.separator);
        for (int i = 0; i < tasks.size(); i++) {
            String number = String.valueOf(i+1);
            String format = this.formatListMessage(number, tasks.get(i));
            System.out.println(format);
        }
        System.out.println(this.indent + this.separator);
    }

    public ArrayList<IndividualTask> getListTask() {
        return this.tasks;
    }

    public void deleteTask(int num) {
        this.tasks.remove(num);
    }

    public void addTask(IndividualTask task) {
        this.tasks.add(task);
    }



    //Helper methods


    public String formatListMessage(String number, IndividualTask task) {
        return this.indent + number + "." + task.toString();
    }


}
