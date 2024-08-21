package processor;

import response.Response;

public interface Processor {
  Response process(String prompt) throws Exception;
}
