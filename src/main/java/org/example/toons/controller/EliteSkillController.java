package org.example.toons.controller;

import org.example.toons.model.Boss;
import org.example.toons.model.EliteSkill;
import org.example.toons.service.BossService;
import org.example.toons.service.EliteSkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class EliteSkillController {

    private final EliteSkillService eliteSkillService;
    private final BossService bossService;

    public EliteSkillController(EliteSkillService eliteSkillService, BossService bossService) {
        this.eliteSkillService = eliteSkillService;
        this.bossService = bossService;
    }


    //CRUD

    //Create

    //vers le form pour ajouter une comp
    @GetMapping("/create-skill")
    public String addSkill(Model model){
        model.addAttribute("eliteSkill", new EliteSkill());
        return "skill";
    }
    //valider le formulaire de création:
    @PostMapping("/add-skill")
    public String submitSkill(@Validated @ModelAttribute("eliteSkill") EliteSkill eliteSkill, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("Erreur dans le formulaire de skill");
            return "skill";
        }

        if (eliteSkill.getId() == null) {
            eliteSkillService.addEliteSkill(
                    eliteSkill.getName(),
                    eliteSkill.getDescription(),
                    eliteSkill.getBossList()
            );
        } else {
            eliteSkillService.updateEliteSkillName(
                    eliteSkill.getId(),
                    eliteSkill.getName(),
                    eliteSkill.getDescription()
            );
        }

        return "redirect:/skill-list";
    }
    //READ

    //detail
    @GetMapping("/skill-detail/{skillId}")
    public String getEliteSkillById(Model model, @PathVariable("skillId")UUID skillId){
        model.addAttribute("skill", eliteSkillService.getEliteSkillById(skillId));
        return "skill-detail";
    }
    //list
    @GetMapping("/skill-list")
    public String getAllSkill(Model model){
        List<EliteSkill> eliteSkills = eliteSkillService.getAllEliteSkills();
        model.addAttribute("eliteSkills", eliteSkills);
        return "skill-list";
    }

    //UPDATE

        //vers le form d'update
        @GetMapping("/skill-update/{skillId}")
        public String updateSkill(Model model, @PathVariable("skillId") UUID skillId){
            model.addAttribute("eliteSkill", eliteSkillService.getEliteSkillById(skillId));
        return "skill";
        }

        //récuperation et validation du form de modification
//        @PostMapping("/add-skill")
//        public String submiteSkillChange(@Validated @ModelAttribute("eliteSkill") EliteSkill eliteSkill, BindingResult bindingResult) {
//            if (bindingResult.hasErrors()) {
//                System.out.println("erreur lors de l'update de la compétence");
//                return "skill";
//            } else {
//                eliteSkillService.updateEliteSkillName(
//                        eliteSkill.getId(),
//                        eliteSkill.getName(),
//                        eliteSkill.getDescription()
//                );
//                return "redirect:/skill-list";
//            }
//        }

    //add_boss
    @GetMapping("/skill/{id}/add-boss")
    public String showAddBossForm(@PathVariable UUID id, Model model) {
        EliteSkill skill = eliteSkillService.getEliteSkillById(id);

        if (skill == null) {
            System.err.println(" Aucun EliteSkill trouvé avec l'ID: " + id);
            return "redirect:/skill-list";
        }

        List<Boss> allBosses = new ArrayList<>(bossService.getBosses().values());

        if (skill.getBossList() != null) {
            allBosses.removeIf(b -> skill.getBossList().stream().anyMatch(eb -> eb.getId().equals(b.getId())));
        }

        model.addAttribute("skillId", id);
        model.addAttribute("availableBosses", allBosses);
        return "skill-add-boss";
    }

    @PostMapping("/skill/{id}/add-boss-confirm")
    public String confirmAddBoss(@PathVariable UUID id, @RequestParam(required = false) List<UUID> selectedBosses) {
        if (selectedBosses != null) {
            for (UUID bossId : selectedBosses) {
                eliteSkillService.addBossToEliteSkillList(id, bossId);
                System.out.println("Ajout de boss : " + bossId);
            }
        }
        return "redirect:/skill-detail/" + id;
    }

    //remove_boss
    @GetMapping("/skill/{id}/remove-boss")
    public String showRemoveBossForm(@PathVariable UUID id, Model model) {
        EliteSkill skill = eliteSkillService.getEliteSkillById(id);

        model.addAttribute("skillId", id);
        model.addAttribute("linkedBosses", skill.getBossList());
        return "skill-rm-boss";
    }

    @PostMapping("/skill/{id}/rm-boss-confirm")
    public String confirmRemoveBoss(@PathVariable UUID id, @RequestParam(required = false) List<UUID> selectedBosses) {
        if (selectedBosses != null) {
            for (UUID bossId : selectedBosses) {
                eliteSkillService.removeBossToEliteSkillList(id, bossId);
            }
        }
        return "redirect:/skill-detail/" + id;
    }
    //DELETE
    @GetMapping("/skill-delete/{skillId}")
    public String deleteEliteSkill(@PathVariable("skillId") UUID skillId){
        eliteSkillService.deleteEliteSkill(skillId);
        return "redirect:/skill-list";
    }
}
