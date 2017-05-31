/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.iot.fiesta.eee.experimentdatastore.restlet;

import eu.iot.fiesta.eee.experimentdatastore.store.StoreAccess;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import eu.iot.fiesta.eee.experimentdatastore.model.KatResult;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.restlet.data.Header;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

public class StoreHandler extends ServerResource {

    @Post
    public Representation handleRegister(Representation entity) throws ResourceException, IOException {

        Series<Header> series = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
        String userId = series.getFirstValue("userId", true);
        String femoId = series.getFirstValue("femoId", true);
        String jobId = series.getFirstValue("jobId", true);

        String reqBody = entity.getText();
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        
        KatResult katResult = new KatResult();
        
        try {
            katResult = objectMapper.readValue(reqBody, KatResult.class);
        } catch (IOException ex) {
            Logger.getLogger(StoreHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        StoreAccess sa = new StoreAccess();
        try {
            sa.storeExperimentResult(userId, femoId, jobId, katResult);
        } catch (SQLException ex) {
            Logger.getLogger(StoreHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        return new StringRepresentation("done");
    }

    @Get
    public Representation handleLookup() {

        Series<Header> series = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
//      String userId = series.getValuesMap().keySet().toString();
        String userId = series.getFirstValue("userId", true);
        String femoId = series.getFirstValue("femoId", true);
        String jobId = series.getFirstValue("jobId", true);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        
//        KatResult katResult = new KatResult();
        String result = "";
        
         StoreAccess sa = new StoreAccess();
        try {
            result = sa.getExperimentResult(userId, femoId, jobId);
        } catch (SQLException ex) {
            Logger.getLogger(StoreHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new StringRepresentation(result);
    }

    @Put
    public Representation handleUpdate(Representation entity) throws ResourceException, IOException {

        Representation result;
        String reqBody = entity.getText();
        String repoId = (String) getRequest().getAttributes().get("result_id");
        String resourceId = (String) getRequest().getAttributes().get("resource_id");

        return new StringRepresentation(reqBody);

    }

    @Delete
    public Representation handleRemove() throws ResourceException, IOException {

        Representation result;
        String repoId = (String) getRequest().getAttributes().get("repository_id");
        String resourceId = (String) getRequest().getAttributes().get("resource_id");

        return new StringRepresentation("DELETE called");

    }

}
