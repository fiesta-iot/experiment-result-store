/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.iot.fiesta.eee.experimentdatastore.model.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "femoResults"
})
public class FemoResults {

    @JsonProperty("femoResults")
    private List<FemoResult> femoResults = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     *
     */
    public FemoResults() {
    }

    /**
     *
     * @param femoResults
     */
    public FemoResults(List<FemoResult> femoResults) {
        super();
        this.femoResults = femoResults;
    }

    @JsonProperty("femoResults")
    public List<FemoResult> getFemoResults() {
        return femoResults;
    }

    @JsonProperty("femoResults")
    public void setFemoResults(List<FemoResult> femoResults) {
        this.femoResults = femoResults;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
