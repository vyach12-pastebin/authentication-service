package com.finracy.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "unverified_users")
public class UnverifiedUser {
    @Id
    private String id;
    private String email;
    private String password;
    private Instant createdAt;
    private String verificationCode;
}
