package com.example.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    public String id;
    public String username;
    public String email;
    public String password;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<Role> roles;

}
