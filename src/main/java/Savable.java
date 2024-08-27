import java.io.IOException;

public interface Savable {
    void save(Storage storage) throws IOException;
}
