package org.example.toons.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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


    @Size(min = 1, message = "Ne doit pas être vide")
    private String name;

    @Size(min = 1, message = "Ne doit pas être vide")
    private String description;
    private List<Boss> bossList;

}
