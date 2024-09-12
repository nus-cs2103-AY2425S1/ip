package task;

import chatbot.Parser;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskList taskList = new TaskList();

        Scanner sc1 = new Scanner("[T][ ] task 1");
        Parser.parseFromTxtTaskList(sc1, taskList);
        assert(new Todo("task 1") == taskList.removeTask(1));
        sc1.close();

        Scanner sc2 = new Scanner("[D][ ] task 2 (by: 2024-06-22 1600)");
        Parser.parseFromTxtTaskList(sc2, taskList);
        taskList.markTaskAsDone(1);
        System.out.println(taskList.getTask(1));

        assert(
                new Deadline("task 2", "2024-06-22 1600") ==
                taskList.removeTask(1));
        sc2.close();

        Scanner sc3 = new Scanner("[E][ ] task 3 (from: 2024-06-22 1600 to: 2024-06-22 1800)");
        Parser.parseFromTxtTaskList(sc3, taskList);
        assert(
                new Event("task 3", "2024-06-22 1600", "2024-06-22 1800") ==
                taskList.removeTask(1));
        sc3.close();
    }
}
