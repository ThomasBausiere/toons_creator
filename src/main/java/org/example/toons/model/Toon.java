package org.example.toons.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Toon {
    private UUID id;
    private String campaign;
    private String profession;
    private String name;
    private int level;
}
