import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private ArrayList<IndividualTask> list = new ArrayList<IndividualTask>();


    private String message = "";

    private String indent = "      ";
    private String separator = "------------------------------";

    public TaskList(ArrayList<IndividualTask> list) throws MentalHealthException {
        this.list = list;
    }
    public TaskList() {

    }

    public void getTasks() {
        System.out.println(this.indent + this.separator);
        for (int i = 0; i < list.size(); i++) {
            String number = String.valueOf(i+1);
            String format = this.formatListMessage(number, list.get(i));
            System.out.println(format);
        }
        System.out.println(this.indent + this.separator);
    }

    public ArrayList<IndividualTask> getListTask() {
        return this.list;
    }

    public void deleteTask(int num) {
        this.list.remove(num);
    }

    public void addTask(IndividualTask task) {
        this.list.add(task);
    }



    //Helper methods


    public String formatListMessage(String number, IndividualTask task) {
        return this.indent + number + "." + task.toString();
    }


}
