package main.LittleMissHelpful;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import main.LittleMissHelpful.Task.Task;

public class Ui {
    private static final String LINE_BREAK = "---------------------------------";
    private BufferedReader br;

    public Ui() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void showWelcome() {
        System.out.println(LINE_BREAK);
        System.out.println("Hello! I'm Ah Bang Mang.\nWhat you want sia?");
        System.out.println(LINE_BREAK);
    }

    public void showExit() {
        System.out.println(LINE_BREAK);
        System.out.println("Ok, I zao first then!");
        System.out.println(LINE_BREAK);
    }

    public void showLine() {
        System.out.println(LINE_BREAK);
    }

    public void showNoTasks() {
        System.out.println(LINE_BREAK);
        System.out.println("Wah shiok! No tasks at the moment!");
        System.out.println(LINE_BREAK);
    }

    public void showAllTasks(ArrayList<Task> tasks) {
        System.out.println(LINE_BREAK);
        System.out.println("Siao liao! This your current task list leh...");
        for (int i = 0; i < tasks.size(); i++) {
            int listNumber = i + 1;
            Task t = tasks.get(i);
            System.out.println(listNumber + ". " + t.toString());
        }
        System.out.println(LINE_BREAK);
    }

    public void showAddedNewTask(Task t, TaskList tasks) {
        System.out.println(LINE_BREAK);
        System.out.println("Added to list liao: " + t.toString());
        System.out.println("Sian, now got " + tasks.size() + " tasks in your list.");
        System.out.println(LINE_BREAK);
    }

    public void showMarkedTask(Task t) {
        System.out.println(LINE_BREAK);
        System.out.println("Wah upz! You have marked this task as done: " + t.toString());
        System.out.println(LINE_BREAK);
    }

    public void showUnmarkedTask(Task t) {
        System.out.println(LINE_BREAK);
        System.out.println("Ok, I see you laze. You have marked this task as not done yet: " + t.toString());
        System.out.println(LINE_BREAK);
    }

    public void showDeletedTask(Task t, TaskList tasks) {
        System.out.println(LINE_BREAK);
        System.out.println("Wah shiok! This task no more liao: " + t.toString());
        System.out.println("Now got only " + tasks.size() + " tasks left.");
        System.out.println(LINE_BREAK);
    }

    public void showError(String message) {
        System.out.println(LINE_BREAK);
        System.out.println("Alamak! " + message);
        System.out.println(LINE_BREAK);
    }

    public String readCommand() throws IOException {
        return br.readLine();
    }

}
