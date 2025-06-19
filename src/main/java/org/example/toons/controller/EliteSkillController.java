package org.example.toons.controller;

import org.example.toons.model.EliteSkill;
import org.example.toons.service.EliteSkillService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public class EliteSkillController {

    private final EliteSkillService eliteSkillService;
    public EliteSkillController(EliteSkillService eliteSkillService){ this.eliteSkillService=eliteSkillService;}


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
    public String sumbitSkill(@Validated @ModelAttribute("eliteSkill") EliteSkill eliteSkill, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("erreur lors de la creation d'un skill");
            return "skill";
        }else{
            eliteSkillService.addEliteSkill(
                    eliteSkill.getName(),
                    eliteSkill.getDescription(),
                    eliteSkill.getBossList()
            );
            return "redirect:/skill-list";
        }
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
        model.addAttribute("skill", eliteSkillService.getEliteSkillById(skillId));
        return "skill";
        }

        //récuperation et validation du form de modification
        @PutMapping("/add-skill")
        public String submiteSkillChange(@Validated @ModelAttribute("skill") EliteSkill eliteSkill, BindingResult bindingResult ){
            if(bindingResult.hasErrors()){
                System.out.println("erreur lors de l'update de la compétence");
                return "redirect:/skill-list";
            }else {
                eliteSkillService.updateEliteSkillName(
                        eliteSkill.getId(),
                        eliteSkill.getName(),
                        eliteSkill.getDescription()
                );
                return "redirect:/skill-list";
            }
        }

    //add_boss

    //remove_boss

    //DELETE
    @GetMapping("/delete/{skillId}")
    public String deleteEliteSkill(@PathVariable("skillId") UUID skillId){
        eliteSkillService.deleteEliteSkill(skillId);
        return "redirect:/list";
    }
}
