package noosy.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import noosy.gui.DialogBox;
import noosy.task.Task;
import noosy.task.TaskList;

public class Gui extends Ui {
    private VBox dialogContainer;

    public Gui(VBox dialogContainer) {

        this.dialogContainer = dialogContainer;
}

@Override
public void showWelcome() {
    String welcomeMessage = "Heyo! This is Noosy! \nNoosy is da best, tell me what you need! :>";
    dialogContainer.getChildren().
            addAll(DialogBox.getNoosyDialog(welcomeMessage, new Image("/images/red teletubby.jpeg")));
}

@Override
public void showGoodbye() {
    String goodbyeMessage = "Alright, hope that helped. \nSee ya!";
    dialogContainer.getChildren().
            addAll(DialogBox.getNoosyDialog(goodbyeMessage, new Image("/images/red teletubby.jpeg")));
}

@Override
public void showTaskAdded(TaskList tasks, Task task) {
    String taskAddedMessage = "I added it to the list! \n" + task + "\nWe've now got " + tasks.size() + " tasks!";
    dialogContainer.getChildren().
            addAll(DialogBox.getNoosyDialog(taskAddedMessage, new Image("/images/red teletubby.jpeg")));
}

@Override
public void showTaskDone(Task done) {
    String taskDoneMessage = "Hooray you've done this: \n" + done;
    dialogContainer.getChildren().
            addAll(DialogBox.getNoosyDialog(taskDoneMessage, new Image("/images/red teletubby.jpeg")));
}

@Override
public void showUndone(Task undone) {
    String taskUnmarkedMessage = "Ok don't worry, you can continue working on this: \n" + undone;
    dialogContainer.getChildren().
            addAll(DialogBox.getNoosyDialog(taskUnmarkedMessage, new Image("/images/red teletubby.jepg")));
}

@Override
public void showDeleted(Task deleted) {
    String deletedMessage = "I've kicked it out for you: \n" + deleted;
    dialogContainer.getChildren().
            addAll(DialogBox.getNoosyDialog(deletedMessage, new Image("/images/red teletubby.jpeg")));
}

@Override
public void showTaskList(TaskList tasks) {
    String listMessage = "Heyo, here are the tasks we have:\n";
    dialogContainer.getChildren().
            addAll(DialogBox.getNoosyDialog(listMessage + tasks.toString(), new Image("/images/red teletubby.jpeg")));
}

@Override
public void showFindCommand(TaskList tasksWithKeyword) {
    if (tasksWithKeyword.size() == 0) {
        dialogContainer.getChildren().
                addAll(DialogBox.getNoosyDialog(
                        "Hmm, no task found. Try other words?", new Image("/images/red teletubby.jpeg")));
    } else {
        String resultMessage = "Ahh, I've found the following for you! \n";
        dialogContainer.getChildren().
                addAll(DialogBox.getNoosyDialog(resultMessage + tasksWithKeyword,
                        new Image("/images/red teletubby.jpeg")));
    }
}

@Override
public void showError(String errorMsg) {
    dialogContainer.getChildren().
            addAll(DialogBox.getNoosyDialog("Error: " + errorMsg, new Image("/images/red teletubby.jpeg")));
}

@Override
public void showLoadingError() {
    dialogContainer.getChildren().
            addAll(DialogBox.getNoosyDialog("Error loading tasks", new Image("/images/red teletubby.jpeg")));
}
}
