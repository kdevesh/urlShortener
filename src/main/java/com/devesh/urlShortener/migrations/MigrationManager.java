package com.devesh.urlShortener.migrations;

import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import io.smartcat.migration.MigrationEngine;
import io.smartcat.migration.MigrationResources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/*
Class to initiate DB Migration
 */
@Component
public class MigrationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MigrationManager.class);

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace;

    @Autowired
    private Session session;

    public void doMigration() {
        Assert.notNull(session, "Session object is null");
        Assert.hasText(keyspace, "Keyspace cannot be null or empty");
        printMetadata(session);
        migrateSchema(session);
    }

    private void migrateSchema(final Session session) {
        LOGGER.info("Executing schema migrations");

        final MigrationResources resources = findMigrationResources();

        MigrationEngine.withSession(session).migrate(resources);

        LOGGER.info("Done with schema migrations");
    }

    private MigrationResources findMigrationResources() {
        MigrationResources resources = new MigrationResources();
        resources.addMigration(new AddUserColumnFamily(1));
        return resources;
    }

    private void printMetadata(final Session session) {
        final Metadata metadata = session.getCluster().getMetadata();
        LOGGER.info("Connected to cluster = {}", metadata.getClusterName());

        for (final Host host : metadata.getAllHosts()) {
            LOGGER.info("Datacenter = {} host = {}", host.getDatacenter(), host.getAddress());
        }
    }

    @PostConstruct
    public void setup() {
        doMigration();
    }
}
