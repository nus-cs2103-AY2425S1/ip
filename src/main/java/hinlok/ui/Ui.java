package hinlok.ui;

import hinlok.tasks.TaskList;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui(){
        scanner = new Scanner(System.in);

    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Wassup, I am Hinlok and I love chinese songs. How can I help you today.\n" +
                "Type 'bye' to exit");
    }

    public void showLine() {
        System.out.println("____________________________");
    }

    public void showTasks(TaskList taskList) {
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println((i + 1) + "." + taskList.getTaskByIndex(i).toString());
        }
    }

    public void showError(String errorMessage){
        System.out.println(errorMessage);
    }

    public void showMessage(String s) {
        System.out.println(s);
    }
}
