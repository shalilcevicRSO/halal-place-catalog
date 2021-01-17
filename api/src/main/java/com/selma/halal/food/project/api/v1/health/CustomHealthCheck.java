package com.selma.halal.food.project.api.v1.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


@Liveness
@ApplicationScoped
public class CustomHealthCheck implements HealthCheck {

    @Inject
    private HealthCheckClass healthCheck;

    @Override
    public HealthCheckResponse call() {
        try {
            if (healthCheck.isHealthy()) {
                System.out.println("Up");
                return HealthCheckResponse.named(CustomHealthCheck.class.getSimpleName()).up().build();

            } else {
                System.out.println("Down");
                return HealthCheckResponse.named(CustomHealthCheck.class.getSimpleName()).down().build();
            }
        } catch (Exception e) {
            System.out.println("Exception occured in CustomHealthCheck.");
        }
        return HealthCheckResponse.named(CustomHealthCheck.class.getSimpleName()).down().build();
    }
}