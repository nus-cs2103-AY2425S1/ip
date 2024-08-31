package bob;

import bob.task.Task;

import java.util.ArrayList; 
import java.util.List;
import java.util.Scanner;

public class Bob {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        Ui.runDialogue(scanner, tasks);
    }

}
