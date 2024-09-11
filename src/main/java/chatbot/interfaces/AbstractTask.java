package chatbot.interfaces;

public interface AbstractTask {
    void setDone(boolean status);
    boolean descriptionContains(String phrase);
    String toString();
    String serialize();
}
