package com.github.tklae.resources;

import com.yammer.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/helloworld")
@Produces(HelloWorldResource.MEDIA_TYPE_HTML)
@Consumes(HelloWorldResource.MEDIA_TYPE_JSON)
public class HelloWorldResource {

    public static final String MEDIA_TYPE_JSON = MediaType.APPLICATION_JSON + ";charset=UTF-8";
    public static final String MEDIA_TYPE_XML = MediaType.APPLICATION_XML + ";charset=UTF-8";
    public static final String MEDIA_TYPE_HTML = MediaType.TEXT_HTML + ";charset=UTF-8";

    private final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    @GET
    @Timed
    public String fetch() {
        return "Hello World!";
    }

}
