package nl.athena.openehr.boomi.operations.ehr;

import com.boomi.connector.api.OperationType;
import com.boomi.connector.testutil.ConnectorTester;
import com.boomi.connector.testutil.SimpleOperationResult;
import nl.athena.openehr.boomi.OpenEhrConnector;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tests for the {@link GetEhrByIdOperation} class.
 */
public class GetEhrByIdTests {

    @Test
    public void testGetOperation() {
        final Map<String, Object> connectionProperties = new HashMap<>();
        connectionProperties.put("baseUrl", "http://localhost:8080/ehrbase/rest/openehr/v1");
        connectionProperties.put("username", "ehrbase-user");
        connectionProperties.put("password", "SuperSecretPassword");

        final Map<String, Object> operationProperties = new HashMap<>();

        final OpenEhrConnector openEhrConnector = new OpenEhrConnector();
        final ConnectorTester connectorTester = new ConnectorTester(openEhrConnector);

        final ExtendedOperationContext operationContext = new ExtendedOperationContext(
                connectorTester.getConfig(),
                openEhrConnector,
                OperationType.GET,
                "GetEhrById",
                connectionProperties,
                operationProperties,
                "cbe269ba-653e-46a3-9528-a1449a8fb052",
                null);

        connectorTester.setOperationContext(operationContext);
        List<SimpleOperationResult> results = connectorTester.executeGetOperation("cbe269ba-653e-46a3-9528-a1449a8fb052");

        for (SimpleOperationResult result : results) {
            System.out.println(result);
        }
    }

}
