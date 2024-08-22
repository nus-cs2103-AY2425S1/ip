package main.java;

public class ToDo extends Task{

    public ToDo(String inputStr) throws InvalidTaskNameException {
        String name = inputStr;
        if (name.length() == 0) {
            throw new InvalidTaskNameException();
        }
        this.name = name;
    }

    @Override
    public String toString() {
        String res = "[T]";
        res += super.toString();
        return res;
    }
}
