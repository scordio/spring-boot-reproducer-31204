package io.github.scordio.reproducer31204;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonEntity {

    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
