package ahmad.processor;

import ahmad.exceptions.AhmadException;
import ahmad.response.Response;

public interface Processor {
    Response process(String prompt) throws AhmadException;
}
