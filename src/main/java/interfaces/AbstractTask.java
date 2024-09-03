package main.java.interfaces;

public interface AbstractTask {
    void setDone(boolean status);
    String toString();
    String serialize();
}
