package nl.athena.openehr.boomi.client;

import org.apache.hc.client5.http.classic.methods.HttpUriRequest;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import java.io.Closeable;
import java.io.IOException;

public class RestClient implements Closeable {

    private final CloseableHttpClient httpClient;
    private final HttpContext httpContext;

    public RestClient(
            CloseableHttpClient theHttpClient,
            HttpContext theHttpContext) {
        httpClient = theHttpClient;
        httpContext = theHttpContext;
    }

    public <T> T execute(
            HttpUriRequest theRequest,
            HttpClientResponseHandler<? extends T> theResponseHandler) throws IOException {
        return httpClient.execute(theRequest, httpContext, theResponseHandler);
    }

    @Override
    public void close() throws IOException {
        httpClient.close();
    }

}
