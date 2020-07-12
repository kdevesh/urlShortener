package com.devesh.urlShortener.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@PrimaryKeyClass
public class UserKey implements Serializable {
    private static final long serialVersionUID = 1L;
    @PrimaryKeyColumn(name = "email", type = PrimaryKeyType.PARTITIONED)
    private String email;
    @PrimaryKeyColumn(name = "firstname", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private String firstName;
    @PrimaryKeyColumn(name = "lastname", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private String lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserKey key = (UserKey) o;
        return Objects.equals(email, key.email)&&Objects.equals(firstName, key.firstName)&&Objects.equals(lastName, key.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email,firstName,lastName);
    }
}
