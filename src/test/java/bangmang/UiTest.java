package bangmang;

import bangmang.exception.InvalidTaskFormatException;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UiTest {

    private Ui ui;
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
        taskList = new TaskList();
    }

    @Test
    public void testShowNoTasks() {
        String output = ui.showNoTasks();
        assertTrue(output.contains("Wah shiok! No tasks at the moment!"));
    }

    @Test
    public void testShowAllTasks() {
        Task task1 = new Task("Task 1");
        Task task2 = new Task("Task 2");
        taskList.add(task1);
        taskList.add(task2);
        ArrayList<Task> tasks = taskList.getTasks();

        String output = ui.showAllTasks(tasks);
        assertTrue(output.contains("1. " + task1.toString()));
        assertTrue(output.contains("2. " + task2.toString()));
    }

    @Test
    public void testShowAddedNewTask() {
        Task task = new Task("New task");
        taskList.add(task);

        String output = ui.showAddedNewTask(task, taskList);
        assertTrue(output.contains("Added to list liao: " + task.toString()));
        assertTrue(output.contains("Sian, now got " + taskList.size() + " tasks in your list."));
    }

    @Test
    public void testShowMarkedTask() throws InvalidTaskFormatException {
        Task task = new Task("Task 1");
        taskList.add(task);
        taskList.markTask(0);

        String output = ui.showMarkedTask(task);
        assertTrue(output.contains("Wah upz! You have marked this task as done: " + task.toString()));
    }

    @Test
    public void testShowUnmarkedTask() throws InvalidTaskFormatException {
        Task task = new Task("Task 1");
        taskList.add(task);
        taskList.markTask(0);
        taskList.unmarkTask(0);

        String output = ui.showUnmarkedTask(task);
        assertTrue(output.contains("Ok, I see you laze. You have marked this task as not done yet: " + task.toString()));
    }

    @Test
    public void testShowDeletedTask() throws InvalidTaskFormatException {
        Task task = new Task("Task to delete");
        taskList.add(task);
        taskList.delete(0);

        String output = ui.showDeletedTask(task, taskList);
        assertTrue(output.contains("Wah shiok! This task no more liao: " + task.toString()));
        assertTrue(output.contains("Now got only " + taskList.size() + " tasks left."));
    }

    @Test
    public void testShowError() {
        String errorMessage = "Test error message";

        String output = ui.showError(errorMessage);
        assertTrue(output.contains("Alamak! " + errorMessage));
    }
}

//package LittleMissHelpful;
//
//import LittleMissHelpful.Exception.InvalidTaskFormatException;
//import LittleMissHelpful.Tasks.TaskList;
//import LittleMissHelpful.Ui.Ui;
//import LittleMissHelpful.Tasks.Task;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UiTest {
//
//    private Ui ui;
//    private ByteArrayOutputStream outputStream;
//    private TaskList taskList;
//
//    @BeforeEach
//    public void setUp() {
//        ui = new Ui();
//        outputStream = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStream));
//        taskList = new TaskList();
//    }
//
//    @Test
//    public void testShowNoTasks() {
//        ui.showNoTasks();
//        String output = outputStream.toString().trim();
//        assertTrue(output.contains("Wah shiok! No tasks at the moment!"));
//    }
//
//    @Test
//    public void testShowAllTasks() {
//        Task task1 = new Task("Task 1");
//        Task task2 = new Task("Task 2");
//        taskList.add(task1);
//        taskList.add(task2);
//        ArrayList<Task> tasks = taskList.getTasks();
//
//        ui.showAllTasks(tasks);
//        String output = outputStream.toString().trim();
//        assertTrue(output.contains("1. " + task1.toString()));
//        assertTrue(output.contains("2. " + task2.toString()));
//    }
//
//    @Test
//    public void testShowAddedNewTask() {
//        Task task = new Task("New task");
//        taskList.add(task);
//
//        ui.showAddedNewTask(task, taskList);
//        String output = outputStream.toString().trim();
//        assertTrue(output.contains("Added to list liao: " + task.toString()));
//        assertTrue(output.contains("Sian, now got " + taskList.size() + " tasks in your list."));
//    }
//
//    @Test
//    public void testShowMarkedTask() throws InvalidTaskFormatException {
//        Task task = new Task("Task 1");
//        taskList.add(task);
//        taskList.markTask(0);
//
//        ui.showMarkedTask(task);
//        String output = outputStream.toString().trim();
//        assertTrue(output.contains("Wah upz! You have marked this task as done: " + task.toString()));
//    }
//
//    @Test
//    public void testShowUnmarkedTask() throws InvalidTaskFormatException {
//        Task task = new Task("Task 1");
//        taskList.add(task);
//        taskList.markTask(0);
//        taskList.unmarkTask(0);
//
//        ui.showUnmarkedTask(task);
//        String output = outputStream.toString().trim();
//        assertTrue(output.contains("Ok, I see you laze. You have marked this task as not done yet: " + task.toString()));
//    }
//
//    @Test
//    public void testShowDeletedTask() throws InvalidTaskFormatException {
//        Task task = new Task("Task to delete");
//        taskList.add(task);
//        taskList.delete(0);
//
//        ui.showDeletedTask(task, taskList);
//        String output = outputStream.toString().trim();
//        assertTrue(output.contains("Wah shiok! This task no more liao: " + task.toString()));
//        assertTrue(output.contains("Now got only " + taskList.size() + " tasks left."));
//    }
//
//    @Test
//    public void testShowError() {
//        String errorMessage = "Test error message";
//        ui.showError(errorMessage);
//        String output = outputStream.toString().trim();
//        assertTrue(output.contains("Alamak! " + errorMessage));
//    }
//}
