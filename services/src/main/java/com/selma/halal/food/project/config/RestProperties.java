package com.selma.halal.food.project.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
@ConfigBundle("rest-properties")
public class RestProperties {

    @ConfigValue(watch = true)
    private Boolean maintenanceMode;

    @ConfigValue(watch = true)
    private Boolean broken;

    public Boolean getMaintenanceMode() {

        return maintenanceMode;
    }

    public void setMaintenanceMode(final Boolean maintenanceMode) {

        this.maintenanceMode = maintenanceMode;
    }

    public Boolean isBroken() {
        return broken;
    }

    public void setBroken(final Boolean broken) {
        this.broken = broken;
    }
}
