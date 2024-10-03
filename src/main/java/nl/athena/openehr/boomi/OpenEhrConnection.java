package nl.athena.openehr.boomi;

import com.boomi.connector.api.OperationContext;
import com.boomi.connector.util.BaseConnection;
import nl.athena.openehr.boomi.client.RestClient;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.CredentialsProvider;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.client5.http.protocol.HttpClientContext;
import org.apache.hc.core5.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class OpenEhrConnection extends BaseConnection<OperationContext> {

    private final String baseUrl;

    /**
     * Create a new OpenEhrConnection.
     *
     * @param context The OperationContext.
     */
    public OpenEhrConnection(OperationContext context) {
        super(context);

        final String url = context.getConnectionProperties().getProperty("baseUrl");
        baseUrl = url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }

    /**
     * Get a RestClient for the OpenEHR server.
     *
     * @return A RestClient for the OpenEHR server.
     */
    public RestClient getRestClient() {
        final CloseableHttpClient httpClient = createHttpClient();
        final HttpContext httpContext = createHttpContext();
        return new RestClient(httpClient, httpContext);
    }

    private CloseableHttpClient createHttpClient() {
        return HttpClientBuilder.create()
                .build();
    }

    private HttpContext createHttpContext() {
        final HttpClientContext httpContext = HttpClientContext.create();

        final CredentialsProvider credentialsProvider = createCredentialsProvider();
        httpContext.setCredentialsProvider(credentialsProvider);

        return httpContext;
    }

    private CredentialsProvider createCredentialsProvider() {
        final String username = getContext().getConnectionProperties().getProperty("username");
        final String password = getContext().getConnectionProperties().getProperty("password");
        final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password.toCharArray());
        final AuthScope authScope = new AuthScope(null, -1);

        final BasicCredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(authScope, credentials);

        return provider;
    }

    /**
     * Convert an InputStream to a String.
     *
     * @param is The InputStream to convert.
     * @return The String representation of the InputStream.
     * @throws IOException if an I/O error occurs.
     */
    public String toString(InputStream is) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    /**
     * Get the base URL of the OpenEHR server. Does not end with a slash.
     *
     * @return The base URL without a trailing slash.
     */
    public String getBaseUrl() {
        return baseUrl;
    }

}
