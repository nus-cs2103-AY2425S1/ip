package lib;

public interface DbDriverInterface {
    void save(String rawString);

    String read();
}
