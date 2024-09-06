package chatbot.interfaces;

public interface TaskStorageResult<T> {
    void showResult(MessageView<T> messageView);
}
