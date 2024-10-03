package nl.athena.openehr.boomi;

import com.boomi.connector.api.*;
import com.boomi.util.LogUtil;

import java.util.logging.Logger;

/**
 * Boomi connector for OpenEHR.
 */
public class OpenEhrConnector implements Connector {

    private static final Logger LOG = LogUtil.getLogger(OpenEhrConnector.class);

    @Override
    public void initialize(AtomContext atomContext) {

    }

    @Override
    public Browser createBrowser(BrowseContext browseContext) {
        return null;
    }

    @Override
    public Operation createOperation(OperationContext operationContext) {
        return null;
    }

}
