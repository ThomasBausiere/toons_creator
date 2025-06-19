package org.example.toons.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Toon {

    private UUID id;


    @Size(min = 3, max = 20, message = "Doit contenir entre 3 et 20 caractères.")
    private String name;

    @Size(min = 1,  message = "Ne doit pas être vide")
    private String campaign;

    @Size(min = 1, message = "Ne doit pas être vide")
    private String profession;

    @Min(1)
    @Max(20)
    private int level;

    private List<EliteSkill> eliteSkillList;
}
