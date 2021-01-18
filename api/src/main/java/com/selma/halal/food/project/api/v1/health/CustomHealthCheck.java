package com.selma.halal.food.project.api.v1.health;

import com.selma.halal.food.project.config.RestProperties;
import org.eclipse.microprofile.health.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


@ApplicationScoped
public class CustomHealthCheck implements HealthCheck {

    @Inject
    private RestProperties restProperties;

    @Override
    public HealthCheckResponse call() {
        if (restProperties.isBroken()) {
            return HealthCheckResponse.named(CustomHealthCheck.class.getSimpleName()).down().build();
        }
        else {
            return HealthCheckResponse.named(CustomHealthCheck.class.getSimpleName()).up().build();
        }
    }

}