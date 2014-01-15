package com.github.tklae.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class ApiConfiguration extends Configuration {
    @NotEmpty
    @JsonProperty
    private String serviceName = "version_number";

    @NotEmpty
    @JsonProperty
    private String versionNumber = "version_number";

    public String getServiceName() {
        return serviceName;
    }

    public String getVersionNumber() {
        return versionNumber;
    }
}
