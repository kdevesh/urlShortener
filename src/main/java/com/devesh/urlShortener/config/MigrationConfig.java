package com.devesh.urlShortener.config;

import org.springframework.boot.autoconfigure.AbstractDependsOnBeanFactoryPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.cassandra.config.CassandraCqlTemplateFactoryBean;
import org.springframework.data.cassandra.core.cql.CqlTemplate;

/*
This configuration basically says that instances CqlTemplate and CassandraCqlTemplateFactoryBean should be created before migrationManager is created.
Which in turn results in Cassandra Session bean getting created as it is needed to initialize CqlTemplate
 */
@Configuration
public class MigrationConfig {

    @Order
    @Configuration
    public static class CassandraDriverDependsOnBeanFactoryPostProcessor extends AbstractDependsOnBeanFactoryPostProcessor {

        public CassandraDriverDependsOnBeanFactoryPostProcessor() {
            super(CqlTemplate.class, CassandraCqlTemplateFactoryBean.class, "migrationManager");
        }
    }
}
