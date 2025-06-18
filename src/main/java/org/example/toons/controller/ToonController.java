package org.example.toons.controller;

import org.example.toons.model.Toon;
import org.example.toons.service.ToonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String submitToon(@ModelAttribute("toon") Toon toon){
        toonService.newToon(
                toon.getCampaign(),
                toon.getProfession(),
                toon.getName(),
                toon.getLevel()
        );
        return "redirect:/list";
    }

    @GetMapping("/update")
    public String updateToon(Model model, UUID id){
        model.addAttribute("toon", toonService.getToonById(id));
        return "toon";
    }

    //Update

    //Delete




}
