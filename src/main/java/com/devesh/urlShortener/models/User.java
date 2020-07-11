package com.devesh.urlShortener.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Table("user")
public class User {
    @PrimaryKeyColumn(name = "email", type = PrimaryKeyType.PARTITIONED)
    private String email;
    @PrimaryKeyColumn(name = "firstName", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private String firstName;
    @PrimaryKeyColumn(name = "lastName", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private String lastName;
    private LocalDateTime createdOn;
}
