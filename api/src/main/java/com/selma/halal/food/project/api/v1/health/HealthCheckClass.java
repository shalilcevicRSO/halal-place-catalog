package com.selma.halal.food.project.api.v1.health;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ConfigBundle("health-properties")
@ApplicationScoped
public class HealthCheckClass {

    @ConfigValue(watch = true)
    private boolean healthy;


    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }
}