package rainy.database;
import java.util.*;
import java.io.*;
import rainy.rainyexceptions.*;
import rainy.tasks.*;

public class Rainy {
    public static void main(String[] args) throws InvalidIndexException, InvalidMarkAndUnmarkException, IOException {
        UI ui = new UI();
        Storage storage = new Storage();
        ui.welcomeMessage();
        TaskTracker tm;
        Parser ps = new Parser();
        File newFile = new File("src/main/java/Rainy.txt");
        tm = storage.copyPreviousFiles(newFile);
        tm.receivedFirstInput();
        Scanner sc = new Scanner(System.in);
        String messages = sc.nextLine();
        ps.firstInput(messages);
        String[] input = ps.getInput();
        String[] splitByTask = ps.getSplitByTask();
        String message = ps.getMessage();
        int count = ps.getCount();
        Instructions instruction = ps.enumOperator(message);
        while(instruction != Instructions.BYE) {
            switch (instruction) {
                case LIST:
                    System.out.println(tm.getList());
                    break;
                case MARK:
                    if (count != -1) {
                        tm.markDone(count - 1);
                    } else {
                        ui.noCategoryDeclared();
                    }
                    if (tm.getCounter() > 0) {
                        File f = new File("src/main/java/Rainy.txt");
                        storage.writeOverFile(f, tm);
                    }
                    break;
                case UNMARK:
                    if (count != -1) {
                        tm.unmarkDone(count - 1);
                    } else {
                        ui.noCategoryDeclared();
                    }
                    if (tm.getCounter() > 0) {
                        File f = new File("src/main/java/Rainy.txt");
                        storage.writeOverFile(f, tm);
                    }
                    break;
                case DELETE:
                    if (count != -1) {
                        tm.delete(count - 1);
                    } else {
                        ui.noCategoryDeclared();
                    }
                    if (tm.getCounter() > 0) {
                        File f = new File("src/main/java/Rainy.txt");
                        storage.writeOverFile(f, tm);
                    }
                    break;
                case TODO:
                    if (input.length == 1) {
                        ui.noToDoDescription();
                    } else {
                        tm.updateListToDo(splitByTask[0].substring(5));
                    }
                    if (tm.getCounter() > 0) {
                        File f = new File("src/main/java/Rainy.txt");
                        storage.writeOverFile(f, tm);
                    }
                    break;
                case DEADLINE:
                    if (input.length == 1) {
                        ui.noDeadlineDescription();
                    } else if (splitByTask.length == 1) {
                        ui.noEndDateDeadline();
                    } else if (splitByTask.length < 4) {
                        ui.invalidDateDeadline();
                    }
                    else {
                        tm.updateListDeadline(splitByTask[0].substring(9), "" + splitByTask[3].substring(0, 4) + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5) + " " + splitByTask[3].substring(5, 9));
                    }
                    if (tm.getCounter() > 0) {
                        File f = new File("src/main/java/Rainy.txt");
                        storage.writeOverFile(f, tm);
                    }
                    break;
                case EVENT:
                    if (input.length == 1) {
                        ui.noEventDescription();
                    } else if (splitByTask.length < 5) {
                        ui.invalidEventDate();
                    } else {
                        tm.updateListEvent(splitByTask[0].substring(6), splitByTask[3].substring(0, 4) + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5), splitByTask[4]);
                    }
                    if (tm.getCounter() > 0) {
                        File f = new File("src/main/java/Rainy.txt");
                        storage.writeOverFile(f, tm);
                    }
                    break;
                case SORT:
                    tm.sortList();
                    System.out.println(tm.getList());
                    break;

                case FIND:
                    tm.findTask(ps.findTask(messages));
                case INVALID:
                    ui.noCategoryDeclared();
                    break;
            }
            messages = sc.nextLine();
            ps.firstInput(messages);
            input = ps.getInput();
            splitByTask = ps.getSplitByTask();
            message = ps.getMessage();
            count = ps.getCount();
            instruction = ps.enumOperator(message);
        }
        if (tm.getCounter() >= 0) {
            File f = new File("src/main/java/Rainy.txt");
            storage.writeOverFile(f, tm);
        }
        ui.goodbyeMessage();
    }
}
