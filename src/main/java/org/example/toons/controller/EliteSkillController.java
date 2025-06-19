package org.example.toons.controller;

import org.example.toons.model.EliteSkill;
import org.example.toons.service.EliteSkillService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

    //list

    //UPDATE


    //add_boss

    //remove_boss

    //DELETE
}
