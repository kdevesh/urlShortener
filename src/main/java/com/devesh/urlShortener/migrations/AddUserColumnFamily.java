package com.devesh.urlShortener.migrations;

import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.schemabuilder.SchemaBuilder;
import io.smartcat.migration.SchemaMigration;
import io.smartcat.migration.exceptions.MigrationException;

import static com.datastax.driver.core.DataType.text;
import static com.datastax.driver.core.DataType.timestamp;

/*
Class to create User Column Family in Cassandra (Migrations)
 */
public class AddUserColumnFamily extends SchemaMigration {

    public AddUserColumnFamily(int version) {
        super(version);
    }

    @Override
    public String getDescription() {
        return "Add User Column Family";
    }

    @Override
    public void execute() throws MigrationException {
        final String createTable = SchemaBuilder
                .createTable("user")
                .addPartitionKey("email", text())
                .addClusteringColumn("firstName", text())
                .addClusteringColumn("lastName", text())
                .addColumn("createdOn", timestamp())
                .ifNotExists().withOptions()
                .clusteringOrder("firstName", SchemaBuilder.Direction.DESC)
                .clusteringOrder("lastName", SchemaBuilder.Direction.DESC)
                .gcGraceSeconds(345600)
                .comment("User table created")
                .buildInternal();

        executeWithSchemaAgreement(new SimpleStatement(createTable));
    }
}
