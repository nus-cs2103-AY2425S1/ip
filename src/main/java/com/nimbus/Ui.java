package com.nimbus;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * Ui is a javaFX Application, used to create GUI
 */
public class Ui extends Application {
    private static FXMLLoader fxmlLoader;
    private static MainWindow mainWindowController;
    private String name;
    private String userImagePath;
    private String botImagePath;

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

            Ui.mainWindowController = Ui.fxmlLoader.<MainWindow>getController();
            mainWindowController.initialize(userImagePath, botImagePath);
            mainWindowController.setNimbus(new Nimbus("./data/data.txt", this));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Print welcome message onto window
     */
    public void showWelcomeMessage() {
        Ui.mainWindowController.showBotMessage("Hello! I'm " + name + '\n' + "What can I do for you?");
    }

    /**
     * Print goodbye message onto window
     */
    public void showGoodbyeMessage() {
        Ui.mainWindowController.showBotMessage("Bye. Hope to see you again soon!");
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
        Ui.mainWindowController.showBotMessage(msg.toString());
    }

    /**
     * Show a newly added task onto window
     * @param task task to be shown to window
     * @param newSize number of task after adding the task
     */
    public void showAddedTask(Task task, int newSize) {
        Ui.mainWindowController.showBotMessage("Got it. I've added this task:\n" + task + '\n'
                + "Now you have " + newSize + " tasks in the list.");
    }

    /**
     * Show a removed task onto window
     * @param task task removed
     * @param newSize number of task after removing the task
     */
    public void showRemovedTask(Task task, int newSize) {
        Ui.mainWindowController.showBotMessage("Noted. I've removed this task: " + task
                + "\nNow you have " + newSize + " tasks in the list.");
    }

    /**
     * Show a task has been marked as done
     * @param task task that has been marked as done
     */
    public void showDoneTask(Task task) {
        Ui.mainWindowController.showBotMessage("Nice! I've marked this task as done: " + task);
    }

    /**
     * Show a task has been marked as not done
     * @param task task that has been marked as not done
     */
    public void showNotDoneTask(Task task) {
        Ui.mainWindowController.showBotMessage("OK, I've marked this task as not done yet: " + task);
    }

    /**
     * Show all found tasks to user
     * @param tasks Found tasks to be shown
     */
    public void showFoundTask(TaskList tasks) {
        StringBuilder msg = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            msg.append(i + 1).append(". ").append(tasks.get(i));
        }
        Ui.mainWindowController.showBotMessage(msg.toString());
    }

    /**
     * Show all duplicate task related to the one user is trying to add
     * @param tasks All duplicate task
     */
    public void showDuplicateTask(TaskList tasks) {

        StringBuilder msg = new StringBuilder("Error: relevant task already exist in the system.\n");
        for (int i = 0; i < tasks.size(); ++i) {
            msg.append(tasks.get(i).toString());
        }
        Ui.mainWindowController.showBotMessage(msg.toString());
    }

    public void showUserMessage(String msg) {
        Ui.mainWindowController.showUserMessage(msg);
    }

    public void showError(String msg) {
        Ui.mainWindowController.showBotMessage("Sorry, I didn't get that.\n" + msg);
    }

    public void stopGraphicalUI() {
        Platform.exit();
    }
}
