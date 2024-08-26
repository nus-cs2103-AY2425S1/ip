package main.java;

import static java.lang.Integer.parseInt;

public class ToDo extends Task{

    public ToDo(String inputStr) throws InvalidTaskNameException {
        String name = inputStr;
        if (name.length() == 0) {
            throw new InvalidTaskNameException();
        }
        this.name = name;
    }
    public ToDo(String[] input) {
        int isDone = parseInt(input[0]);
        if (isDone == 0) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
        this.name = input[1];
    }


    @Override
    public String toString() {
        String res = "[T]";
        res += super.toString();
        return res;
    }

    @Override
    public String toSave() {
        String res = "T|";
        res.concat(this.isDone ? "1|" : "0|");
        res.concat(this.name);
        return res;
    }
}
