package com.nimbus;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Ui extends Application {
    private String name;
    private String userImagePath;
    private String botImagePath;
    private static FXMLLoader fxmlLoader;
    private static MainWindow MainWindowController;

    @Override
    public void init() {
        Ui.fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
        Parameters params = getParameters();
        this.name = params.getRaw().get(0);
        this.userImagePath = params.getRaw().get(1);
        this.botImagePath = params.getRaw().get(2);
    }

    @Override
    public void start(Stage stage) {
        try {
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            Ui.MainWindowController = Ui.fxmlLoader.<MainWindow>getController();
            MainWindowController.initialize(userImagePath, botImagePath);
            MainWindowController.setNimbus(new Nimbus("./data/data.txt", this));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print welcome message onto window
     */
    public void showWelcomeMessage() {
        Ui.MainWindowController.showBotMessage("Hello! I'm " + name + '\n' + "What can I do for you?");
    }

    /**
     * Print goodbye message onto window
     */
    public void showGoodbyeMessage() {
        Ui.MainWindowController.showBotMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Show all task onto window
     * @param tasks tasks to be shown onto window
     */
    public void showAllTasks(TaskList tasks) {
        StringBuilder msg = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            msg.append((i + 1)).append(". ").append(tasks.get(i)).append('\n');
        }
        Ui.MainWindowController.showBotMessage(msg.toString());
    }

    /**
     * Show a newly added task onto window
     * @param task task to be shown to window
     * @param newSize number of task after adding the task
     */
    public void showAddedTask(Task task, int newSize) {
        Ui.MainWindowController.showBotMessage("Got it. I've added this task:\n" + task + '\n' +
                "Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Show a removed task onto window
     * @param task task removed
     * @param newSize number of task after removing the task
     */
    public void showRemovedTask(Task task, int newSize) {
        Ui.MainWindowController.showBotMessage("Noted. I've removed this task: " + task +
                "\nNow you have " + newSize + " tasks in the list.");
    }

    /**
     * Show a task has been marked as done
     * @param task task that has been marked as done
     */
    public void showDoneTask(Task task) {
       Ui.MainWindowController.showBotMessage("Nice! I've marked this task as done: " + task);
    }

    /**
     * Show a task has been marked as not done
     * @param task task that has been marked as not done
     */
    public void showNotDoneTask(Task task) {
        Ui.MainWindowController.showBotMessage("OK, I've marked this task as not done yet: " + task);
    }

    public void showFoundTask(TaskList tasks) {
        StringBuilder msg = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            msg.append(i + 1).append(". ").append(tasks.get(i));
        }
        Ui.MainWindowController.showBotMessage(msg.toString());
    }

    public void showUserMessage(String msg) {
        Ui.MainWindowController.showUserMessage(msg);
    }

    public void showError(String msg) {
        Ui.MainWindowController.showBotMessage("Sorry, I didn't get that.\n" + msg);
    }

    public void stopGUI() {
        Platform.exit();
    }
}
