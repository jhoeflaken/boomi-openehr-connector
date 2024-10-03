package nl.athena.openehr.boomi.operations.ehr;

import com.boomi.connector.api.*;
import com.boomi.connector.util.BaseGetOperation;
import nl.athena.openehr.boomi.OpenEhrConnection;
import nl.athena.openehr.boomi.client.RestClient;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequest;

import java.util.logging.Logger;

public class GetEhrByIdOperation extends BaseGetOperation {

    private final OpenEhrConnection connection;

    public GetEhrByIdOperation(OpenEhrConnection theConnection) {
        super(theConnection);
        connection = theConnection;
    }

    @Override
    protected void executeGet(GetRequest theRequest, OperationResponse theResponse) {
        final Logger logger = theResponse.getLogger();
        final ObjectIdData objectIdData = theRequest.getObjectId();
        final String objectId = objectIdData.getObjectId();

        final String uri = connection.getBaseUrl() + "/ehr/" + objectId;
        final HttpUriRequest request = new HttpGet(uri);

        try (final RestClient client = connection.getRestClient()) {
            client.execute(request, response -> {
                final int statusCode = response.getCode();
                final String body = connection.toString(response.getEntity().getContent());
                if (statusCode == 200) {
                    theResponse.addResult(objectIdData, OperationStatus.SUCCESS, String.valueOf(statusCode), "OK",
                            PayloadUtil.toPayload(body));
                } else if (statusCode == 404) {
                    theResponse.addEmptyResult(objectIdData, OperationStatus.SUCCESS, String.valueOf(statusCode), "Not Found");
                } else {
                    theResponse.addResult(objectIdData, OperationStatus.APPLICATION_ERROR, String.valueOf(statusCode), "Error",
                            PayloadUtil.toPayload(body));
                }

                return null;
            });
        } catch (Exception e) {
            theResponse.addErrorResult(objectIdData, OperationStatus.FAILURE, "500", "Internal Server Error", e);
        }
    }

}
