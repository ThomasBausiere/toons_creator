package org.example.toons.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
public class Toon {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="toon_id")
    private UUID id;

    @Column(name="toon_name")
    @Size(min = 3, max = 20, message = "Doit contenir entre 3 et 20 caractères.")
    private String name;

    @Column(name="toon_campaign")
    @Size(min = 1,  message = "Ne doit pas être vide")
    private String campaign;

    @Column(name="toon_profession")
    @Size(min = 1, message = "Ne doit pas être vide")
    private String profession;

    @Min(1)
    @Max(20)
    @Column(name="toon_level")
    private int level;

    @ManyToMany
    @Column(name="toon_elite_list")
    private List<EliteSkill> eliteSkillList;
}
