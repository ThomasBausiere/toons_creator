package org.example.toons.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EliteSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id_skill")
    private UUID id;


    @Size(min = 1, message = "Ne doit pas être vide")
    @Column(name="name_skill")
    private String name;

    @Size(min = 1, message = "Ne doit pas être vide")
    @Column(name="desc_skill")
    private String description;

    @OneToMany
    @Column(name="linked_boss_list")
    @JoinColumn(name = "elite_skill_id")
    private List<Boss> bossList;

}
