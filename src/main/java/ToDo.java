package main.java;

public class ToDo extends Task{

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        String res = "[T]";
        res += super.toString();
        return res;
    }
}
