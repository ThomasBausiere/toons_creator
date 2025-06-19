package org.example.toons.model;

import jakarta.validation.constraints.NotNull;
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
public class EliteSkill {
    private UUID id;

    @NotNull(message="Ce champ ne peut pas être vide.")
    private String name;
    @NotNull(message="Ce champ ne peut pas être vide.")
    private String description;
    private List<Boss> bossList;

}
