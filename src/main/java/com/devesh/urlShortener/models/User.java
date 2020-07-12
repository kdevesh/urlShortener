package com.devesh.urlShortener.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Table("user")
public class User {
    @PrimaryKey
    private UserKey userKey;
    @Column("createdon")
    private LocalDateTime createdOn;
}
