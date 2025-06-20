package org.example.toons.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Boss {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_boss")
    UUID id;

    @NotNull(message="Ce champ ne peut pas être vide.")
    String name;

    @NotNull(message="Ce champ ne peut pas être vide.")
    String pos; // FYI stands for "position"


    public Boss(String name, String pos) {
        this.name = name;
        this.pos = pos;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }


}
