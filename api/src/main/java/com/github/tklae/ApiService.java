package com.github.tklae;

import com.github.tklae.configuration.ApiConfiguration;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public class ApiService extends Service<ApiConfiguration> {


    public static void main(String[] args) throws Exception {
        new ApiService().run(args);
    }

    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap) {
        bootstrap.setName("api");
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment) throws Exception {
//        environment.addResource(new PreferencesResource(new PreferencesRepository(configuration, apiClientGenie, mapper, persister, new ApiExceptionMapper(mapper, persister)), analyticsRecorder));

//        environment.addHealthCheck(new StatusHealthCheck(configuration));
//        environment.addProvider(new BasicAuthProvider<PartnerUser>(new PartnerAuthenticator(configuration), "Easy Checkout"));

//        environment.addFilter(new LoggingFilter(), baseUrlFor(QuoteDetailsResource.class));
    }


    private String baseUrlFor(Class resource) {
        URI builder = UriBuilder.fromResource(resource).build();
        return builder.toASCIIString();
    }
}
