import java.util.*;
import java.io.*;

public class Rainy {
    public static void main(String[] args) throws InvalidIndexException, InvalidMarkAndUnmarkException, IOException {
        UI ui = new UI();
        Storage storage = new Storage();
        ui.welcomeMessage();
        TaskTracker tm;
        File newFile = new File("src/main/java/Rainy.txt");
        tm = storage.copyPreviousFiles(newFile);
        tm.receivedFirstInput();
        Scanner sc = new Scanner(System.in);
        String messages = sc.nextLine();
        String[] input = messages.split(" ");
        String[] splitByTask = messages.split("/");
        String message = input[0];
        int count;
        if ((message.equals("mark") || message.equals("unmark") || message.equals("delete")) && input.length == 2) {
            try {
                count = Integer.parseInt(input[1]);
            } catch (Exception e) {
                count = -1;
            }
        } else {
            count = -1;
        }
        Instructions instruction;
        try {
            instruction = Instructions.valueOf(message.toUpperCase());
        } catch(Exception e) {
            instruction = Instructions.INVALID;
        }
        while(instruction != Instructions.BYE) {
            switch (instruction) {
                case LIST:
                    System.out.println(tm.getList());
                    break;
                case MARK:
                    if (tm.getCounter() > 0) {
                        File f = new File("src/main/java/Rainy.txt");
                        storage.writeOverFile(f, tm);
                    }
                    if (count != -1) {
                        tm.markDone(count - 1);
                    } else {
                        ui.noCategoryDeclared();
                    }
                    break;
                case UNMARK:
                    if (tm.getCounter() > 0) {
                        File f = new File("src/main/java/Rainy.txt");
                        storage.writeOverFile(f, tm);
                    }
                    if (count != -1) {
                        tm.unmarkDone(count - 1);
                    } else {
                        ui.noCategoryDeclared();
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
                    break;
                case EVENT:
                    if (input.length == 1) {
                        ui.noEventDescription();
                    } else if (splitByTask.length < 5) {
                        ui.invalidEventDate();
                    } else {
                        tm.updateListEvent(splitByTask[0].substring(6), splitByTask[3].substring(0, 4) + "-" + splitByTask[2] + "-" + splitByTask[1].substring(3, 5), splitByTask[4]);
                    }
                    break;
                case SORT:
                    tm.sortList();
                    System.out.println(tm.getList());
                    break;
                case INVALID:
                    ui.noCategoryDeclared();
                    break;
            }
            messages = sc.nextLine();
            input = messages.split(" ");
            splitByTask = messages.split("/");
            message = input[0];
            if ((message.equals("mark") || message.equals("unmark") || message.equals("delete")) && input.length == 2) {
                try {
                    count = Integer.parseInt(input[1]);
                } catch (Exception e) {
                    count = -1;
                }
            } else {
                count = -1;
            }
            try {
                instruction = Instructions.valueOf(message.toUpperCase());
            } catch (Exception e) {
                instruction = Instructions.INVALID;
            }
        }
        if (tm.getCounter() >= 0) {
            File f = new File("src/main/java/Rainy.txt");
            storage.writeOverFile(f, tm);
        }
        ui.goodbyeMessage();
    }
}
