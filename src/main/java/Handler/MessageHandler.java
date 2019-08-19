package Handler;


import Request.Request;
import Response.Response;

import java.util.Collection;

public interface MessageHandler {

    Response execute(Request request);

    Collection<String> getSupportedRequest();

}
