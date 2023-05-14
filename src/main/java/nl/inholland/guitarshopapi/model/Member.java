package nl.inholland.guitarshopapi.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Member {

    @Id
    private long id;

    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

}
