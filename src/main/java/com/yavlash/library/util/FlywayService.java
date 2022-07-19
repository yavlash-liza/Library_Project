package com.yavlash.library.util;

import org.flywaydb.core.Flyway;

import static com.yavlash.library.util.PropertyManager.DATABASE_CONFIG;

public class FlywayService {
    private Flyway flyway;

    public FlywayService() {
        init();
    }

    public void migrate() {
        flyway.migrate();
    }

    public void clean() {
        flyway.clean();
    }

    private void init() {
        flyway = Flyway.configure()
                .dataSource(DATABASE_CONFIG.getString(PropertyName.URL_PROPERTY_NAME),
                        DATABASE_CONFIG.getString(PropertyName.USER_PROPERTY_NAME),
                        DATABASE_CONFIG.getString(PropertyName.PASSWORD_PROPERTY_NAME))
                .locations(DATABASE_CONFIG.getString(PropertyName.MIGRATION_LOCATION_PROPERTY_NAME))
                .load();
    }
}