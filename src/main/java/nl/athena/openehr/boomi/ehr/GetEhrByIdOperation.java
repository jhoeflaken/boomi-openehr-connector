package nl.athena.openehr.boomi.ehr;

import com.boomi.connector.api.GetRequest;
import com.boomi.connector.api.OperationContext;
import com.boomi.connector.api.OperationResponse;
import com.boomi.connector.util.BaseGetOperation;

/**
 * Operation to get an EHR by its ID.
 */
public class GetEhrByIdOperation extends BaseGetOperation {

    /**
     * Create a new instance of the operation.
     *
     * @param theContext The Boomi operation context.
     */
    protected GetEhrByIdOperation(final OperationContext theContext) {
        super(theContext);
    }

    /**
     * Execute the operation.
     *
     * @param theRequest The request.
     * @param theResponse The response.
     */
    @Override
    protected void executeGet(
            final GetRequest theRequest,
            final OperationResponse theResponse) {

    }

}
