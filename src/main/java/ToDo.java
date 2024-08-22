package main.java;

public class ToDo extends Task{

    public ToDo(String inputStr) throws InvalidTaskNameException {
        String name = inputStr.substring(5);
        if (name.length() == 0) {
            throw new InvalidTaskNameException(name);
        }
    }

    @Override
    public String toString() {
        String res = "[T]";
        res += super.toString();
        return res;
    }
}
