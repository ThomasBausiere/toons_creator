package org.example.toons.service;

import org.example.toons.model.Boss;
import org.example.toons.model.EliteSkill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class EliteSkillService {
    private final Map<UUID, EliteSkill> eliteSkills;

    public EliteSkillService() {
        this.eliteSkills = new HashMap<>();
    }

    // CREATE
    public EliteSkill addEliteSkill(String name, List<Boss> bossList) {
        EliteSkill newEliteSkill = EliteSkill.builder()
                .id(UUID.randomUUID())
                .name(name)
                .bossList(bossList)
                .build();
        return eliteSkills.put(newEliteSkill.getId(), newEliteSkill);

    }

    // READ
    public EliteSkill getEliteSkillById(UUID id) {return this.eliteSkills.get(id); }

    public List<EliteSkill> getAllEliteSkills() {return eliteSkills.values().stream().toList();}

    // UPDATE
    public EliteSkill updateEliteSkillName(UUID id, String name, List<Boss> listBoss) {
        eliteSkills.get(id).setName(name);
        //TODO      Ajouter un boss dans la List<Boss> à l'aide de son ID
        //todo          ou supprimer un boss de cette liste
    }

    // DELETE
    public boolean deleteEliteSkill(UUID id) {
        eliteSkills.remove(id);
        return true;
    }
}
