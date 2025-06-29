package org.example.toons.controller;

import jakarta.validation.Valid;
import org.example.toons.model.Toon;
import org.example.toons.service.ToonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
public class ToonController {

    private final ToonService toonService;

    public ToonController(ToonService toonService) {
        this.toonService = toonService;
    }

    @GetMapping("/")
    public String homepage(){
            return "homepage";
    }

    //READ

    //Récupérer par id
    @GetMapping("/detail/{toonId}")
    public String getToonById(Model model, @PathVariable("toonId") UUID toonId){
        model.addAttribute("toon",toonService.getToonById(toonId));
        return "detail";
    }

    //Récupérer tout
    @GetMapping("/list")
    public String getAllToons(Model model){
        List<Toon> toons = toonService.getAllToon();
        model.addAttribute("toons", toons);
        return "list";
    }

    //Récupérer par formulaire 

    //Create
    //Vers formulaire d'ajout:
    @GetMapping("/create")
    public String addToon(Model model){
        model.addAttribute("toon" ,new Toon());
        return "toon";
    }

    @PostMapping("/add")
    public String submitToon(@Validated @ModelAttribute("toon") Toon toon, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("erreur lors de la creation d'un toon");
            return "toon";
        }else{
            toonService.newToon(
                    toon.getCampaign(),
                    toon.getProfession(),
                    toon.getName(),
                    toon.getLevel()
            );
            return "redirect:/list";
        }
    }



    //Update
    //navigation vers page toUpdate
    @GetMapping("/update/{toonId}")
    public String updateToon(Model model,  @PathVariable("toonId") UUID toonId){
        model.addAttribute("toon", toonService.getToonById(toonId));
        return "toon";
    }
    //mise à jour du toon
    @PutMapping("/add")
    public String submitChange(@Validated @ModelAttribute("toon") Toon toon, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("erreur lors de l'update");
            return "redirect:/list";
        }else {
            toonService.updateToon(
                    toon.getId(),
                    toon.getCampaign(),
                    toon.getProfession(),
                    toon.getName(),
                    toon.getLevel()
            );
            return "redirect:/list";
        }

    }

    //Delete

    @GetMapping("/delete/{toonId}")
    public String deleteToon(@PathVariable("toonId") UUID toonId){
        toonService.deleteToonById(toonId);
        return "redirect:/list";
    }




}
