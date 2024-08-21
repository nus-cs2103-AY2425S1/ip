package processor;

import exceptions.AhmadException;
import response.Response;

public interface Processor {
  Response process(String prompt) throws AhmadException;
}
