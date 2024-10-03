package nl.athena.openehr.boomi.operations.ehr;

import com.boomi.connector.api.AtomConfig;
import com.boomi.connector.api.Connector;
import com.boomi.connector.api.ObjectDefinitionRole;
import com.boomi.connector.api.OperationType;
import com.boomi.connector.testutil.SimpleOperationContext;

import java.util.Map;

public class ExtendedOperationContext extends SimpleOperationContext {

    private final String customOpType;

    public ExtendedOperationContext(
            AtomConfig config,
            Connector connector,
            OperationType opType,
            String customOpType,
            Map<String, Object> connProps,
            Map<String, Object> opProps,
            String objectTypeId,
            Map<ObjectDefinitionRole, String> cookies) {
        super(config, connector, opType, connProps, opProps, objectTypeId, cookies);
        this.customOpType = customOpType;
    }

    @Override
    public String getCustomOperationType() {
        return customOpType;
    }

}
