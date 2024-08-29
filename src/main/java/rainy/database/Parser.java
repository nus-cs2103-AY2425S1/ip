package rainy.database;
import rainy.tasks.*;
import rainy.rainyexceptions.*;

public class Parser {
    private String message;
    private int count;
    private String[] input;
    private String[] splitByTask;

    public Parser() {
        this.message = "";
        this.count = 0;
        this.input = new String[100000];
        this.splitByTask = new String[100000];
    }

    public String getMessage() {
        return this.message;
    }

    public int getCount() {
        return this.count;
    }

    public String[] getInput() {
        return this.input;
    }

    public String[] getSplitByTask() {
        return this.splitByTask;
    }

    public void firstInput(String userInput) {
        this.input = userInput.split(" ");
        this.splitByTask = userInput.split("/");
        this.message = this.input[0];
        if ((this.message.equals("mark") || this.message.equals("unmark") || this.message.equals("delete")) && this.input.length == 2) {
            try {
                this.count = Integer.parseInt(this.input[1]);
            } catch (Exception e) {
                this.count = -1;
            }
        } else {
            this.count = -1;
        }
    }

    public Instructions enumOperator(String newMessage) {
        Instructions instruction;
        try {
            instruction = Instructions.valueOf(newMessage.toUpperCase());
        } catch(Exception e) {
            instruction = Instructions.INVALID;
        }
        return instruction;
    }
}