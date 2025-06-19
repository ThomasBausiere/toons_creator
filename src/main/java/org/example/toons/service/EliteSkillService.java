package org.example.toons.service;

import org.example.toons.model.Boss;
import org.example.toons.model.EliteSkill;

import java.util.*;

public class EliteSkillService {
    private final Map<UUID, EliteSkill> eliteSkills;
    private final BossService bossService = new BossService();


    public EliteSkillService() {
        this.eliteSkills = new HashMap<>();
        addEliteSkill("Spiteful Spirit", "Curses foes to take damage when attacking.", null);
        addEliteSkill("Savage Shot", "Interrupts a foe's action and causes bleeding.", null);
        addEliteSkill("Elemental Attunement", "Increases energy regeneration and elemental power.", null);
        addEliteSkill("Shield of Deflection", "Blocks incoming attacks while health is above 50%.", null);
        addEliteSkill("I Am the Strongest!", "Heals and empowers based on adrenaline.", null);
        addEliteSkill("Arcane Mimicry", "Copies an elite skill from a target ally.", null);
    }

    // CREATE
    public EliteSkill addEliteSkill(String name, String description, List<Boss> bossList) {
        EliteSkill newEliteSkill = EliteSkill.builder()
                .id(UUID.randomUUID())
                .name(name)
                .description(description)
                .bossList(bossList)
                .build();
         eliteSkills.put(newEliteSkill.getId(), newEliteSkill);
        return newEliteSkill;
    }

    // READ
    public EliteSkill getEliteSkillById(UUID id) {return this.eliteSkills.get(id); }

    public List<EliteSkill> getAllEliteSkills() {return eliteSkills.values().stream().toList();}

    // UPDATE
    public EliteSkill updateEliteSkillName(UUID id, String name, String description) {
        eliteSkills.get(id).setName(name);
        eliteSkills.get(id).setDescription(description);
        return eliteSkills.get(id);
    }
    public boolean addBossToEliteSkillList(UUID eliteSkillId, UUID bossId) {
        EliteSkill eliteSkill = eliteSkills.get(eliteSkillId);
        Boss boss = bossService.getBossById(bossId);

        if (eliteSkill == null || boss == null) return false;

        if (eliteSkill.getBossList() == null) {
            eliteSkill.setBossList(new ArrayList<>());
        }

        // vérification de duoblons
        if (eliteSkill.getBossList().stream().noneMatch(b -> b.getId().equals(bossId))) {
            eliteSkill.getBossList().add(boss);
            return true;
        }

        return false;
    }
    public boolean removeBossToEliteSkillList(UUID eliteSkillId, UUID bossId) {
        EliteSkill eliteSkill = eliteSkills.get(eliteSkillId);
        if (eliteSkill == null || eliteSkill.getBossList() == null) return false;

        return eliteSkill.getBossList().removeIf(boss -> boss.getId().equals(bossId));
    }


    // DELETE
    public boolean deleteEliteSkill(UUID id) {
        eliteSkills.remove(id);
        return true;
    }



}
