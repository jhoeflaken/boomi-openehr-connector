package nl.athena.openehr.boomi;

import com.boomi.connector.api.BrowseContext;
import com.boomi.connector.api.Browser;
import com.boomi.connector.api.Operation;
import com.boomi.connector.api.OperationContext;
import com.boomi.connector.util.BaseConnector;
import nl.athena.openehr.boomi.operations.ehr.GetEhrByIdOperation;

public class OpenEhrConnector extends BaseConnector {

    @Override
    public Browser createBrowser(BrowseContext theBrowseContext) {
        return new OpenEhrBrowser(theBrowseContext);
    }

    @Override
    protected Operation createGetOperation(OperationContext theOperationContext) {
        final String operation = theOperationContext.getCustomOperationType();
        switch (operation) {
            case "GET_EHR_BY_ID":
                return new GetEhrByIdOperation(new OpenEhrConnection(theOperationContext));
            default:
                throw new IllegalArgumentException("Unknown OpenEhr service: " + operation);
        }
    }
}
