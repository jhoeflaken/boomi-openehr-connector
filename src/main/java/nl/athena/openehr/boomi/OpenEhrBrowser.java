package nl.athena.openehr.boomi;

import com.boomi.connector.api.*;
import com.boomi.connector.util.BaseBrowser;
import com.boomi.util.ClassUtil;
import com.boomi.util.StreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

public class OpenEhrBrowser extends BaseBrowser {

    protected OpenEhrBrowser(BrowseContext context) {
        super(context);
    }

    @Override
    public ObjectTypes getObjectTypes() {
        final ObjectTypes objectTypes = new ObjectTypes();

        final ObjectType objectType = new ObjectType();
        objectType.setId("EHR_STATUS");
        objectType.setLabel("EHR_STATUS");
        objectTypes.getTypes().add(objectType);

        return objectTypes;
    }

    @Override
    public ObjectDefinitions getObjectDefinitions(
            String theObjectTypeId,
            Collection<ObjectDefinitionRole> theRoles) {
        final String customOperationType = getContext().getCustomOperationType();
        final String jsonSchema = getJsonSchema(customOperationType);
        return getJsonStructure(jsonSchema);
    }

    private ObjectDefinitions getJsonStructure(String theJsonSchema) {
        final ObjectDefinitions objectDefinitions = new ObjectDefinitions();

        final ObjectDefinition objectDefinition = new ObjectDefinition();
        objectDefinition.setElementName("");
        objectDefinition.setJsonSchema(theJsonSchema);
        objectDefinition.setInputType(ContentType.JSON);
        objectDefinition.setOutputType(ContentType.JSON);

        objectDefinitions.getDefinitions().add(objectDefinition);
        return objectDefinitions;
    }

    private String getJsonSchema(String theCustomOperationType) {
        final String fileName = "schemas/"  + theCustomOperationType + ".json";
        try (final InputStream is = ClassUtil.getResourceAsStream(theCustomOperationType + ".json")) {
            return StreamUtil.toString(is, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new ConnectorException("Failed to read schema file: " + fileName, e);
        }
    }

}
