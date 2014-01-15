package com.github.tklae.health;

import com.github.tklae.configuration.WebConfiguration;
import com.yammer.metrics.core.HealthCheck;

public class StatusHealthCheck extends HealthCheck{

    private WebConfiguration configuration;

    public StatusHealthCheck(WebConfiguration configuration) {
        super("StatusHealthCheck");
        this.configuration = configuration;
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy(String.format("%s version: %s", configuration.getServiceName(), configuration.getVersionNumber()));
    }
}
