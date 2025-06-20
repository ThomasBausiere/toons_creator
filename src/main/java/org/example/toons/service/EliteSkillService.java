package org.example.toons.service;

import org.example.toons.dao.EliteSkillRepository;
import org.example.toons.model.Boss;
import org.example.toons.model.EliteSkill;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class EliteSkillService {

    private final BossService bossService;
    private final EliteSkillRepository eliteSkillRepository;


    public EliteSkillService(BossService bossService, EliteSkillRepository eliteSkillRepository) {
        this.bossService = bossService;
        this.eliteSkillRepository = eliteSkillRepository;

    }

    // CREATE
    public EliteSkill addEliteSkill(String name, String description, List<Boss> bossList) {
        EliteSkill newEliteSkill = EliteSkill.builder()
                .id(UUID.randomUUID())
                .name(name)
                .description(description)
                .bossList(bossList)
                .build();
        eliteSkillRepository.save(newEliteSkill);
        return newEliteSkill;
    }

    // READ
    public EliteSkill getEliteSkillById(UUID id) {return eliteSkillRepository.findById(id).orElse(null); }

    public List<EliteSkill> getAllEliteSkills() {return eliteSkillRepository.findAll();}

    // UPDATE
    public EliteSkill updateEliteSkillName(UUID id, String name, String description) {
        getEliteSkillById(id).setName(name);
        getEliteSkillById(id).setDescription(description);
        return eliteSkillRepository.save(getEliteSkillById(id));
    }
    public boolean addBossToEliteSkillList(UUID eliteSkillId, UUID bossId) {
        EliteSkill eliteSkill = eliteSkillRepository.findById(eliteSkillId).orElse(null);
        Boss boss = bossService.getBossById(bossId);

        if (eliteSkill == null || boss == null) return false;

        if (eliteSkill.getBossList() == null) {
            eliteSkill.setBossList(new ArrayList<>());
            System.out.println("Liste après ajout:");
            eliteSkill.getBossList().forEach(b -> System.out.println(b.getName()));
        }

        // vérification de duoblons
        if (eliteSkill.getBossList().stream().noneMatch(b -> b.getId().equals(bossId))) {

            eliteSkill.getBossList().add(boss);
            return true;
        }

        return false;
    }


    public boolean removeBossToEliteSkillList(UUID eliteSkillId, UUID bossId) {
        EliteSkill eliteSkill = eliteSkillRepository.findById(eliteSkillId).orElse(null);
        if (eliteSkill == null || eliteSkill.getBossList() == null) return false;

        return eliteSkill.getBossList().removeIf(boss -> boss.getId().equals(bossId));
    }


    // DELETE
    public boolean deleteEliteSkill(UUID id) {
        eliteSkillRepository.deleteById(id);
        return true;
    }



}
