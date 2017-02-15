package br.com.trixlog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.core.GrantedAuthority;

/**
 * Created by kaynan on 21/01/17.
 */
public class Role implements GrantedAuthority {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
