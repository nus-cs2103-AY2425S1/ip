package joe;

import joe.task.Task;
import joe.ui.Ui;
import java.util.ArrayList;

public class UiStub extends Ui {
    public UiStub(String chatbotName) {
        super(chatbotName);
    }

    @Override
    public void greet() {
    }

    @Override
    public void farewell() {
    }

    @Override
    public void printHelp() {
    }

    @Override
    public void printListMessage(ArrayList<Task> list) {
    }

    @Override
    public void printDoneMessage(Task task) {
    }

    @Override
    public void printUndoneMessage(Task task) {
    }

    @Override
    public void printDeleteMessage(Task task, int size) {
    }

    @Override
    public void printTodoMessage(Task task, int size) {
    }

    @Override
    public void printDeadlineMessage(Task task, int size) {
    }

    @Override
    public void printEventMessage(Task task, int size) {
    }

    @Override
    public void printEmptyTaskErrorMessage() {
    }

    @Override
    public void printEmptyByErrorMessage() {
    }

    @Override
    public void printInvalidEventDateErrorMessage() {
    }

    @Override
    public void printInvalidDateFormatErrorMessage() {
    }

    public void printInvalidIndexErrorMessage() {
    }

    public void printReservedCharacterErrorMessage() {
    }

    public void printInvalidCommandErrorMessage() {
    }

    public void printPostponeErrorMessage() {
    }

    public void printFindMessage(ArrayList<Task> list) {
    }

    public void printPostponeMessage(Task task, int days) {
    }
}
