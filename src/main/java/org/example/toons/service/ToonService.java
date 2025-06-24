package org.example.toons.service;


import org.example.toons.dao.ToonRepository;
import org.example.toons.model.Toon;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ToonService {
    private final ToonRepository toonRepository;




    public ToonService(ToonRepository toonRepository) {

            this.toonRepository = toonRepository;
//
//
    }



    public List<Toon> getAllToon(){
        return toonRepository.findAll();
    }

    public Toon getToonById(UUID id){
        return this.toonRepository.findById(id).orElse(null);
    }

    public Toon newToon(String campaign, String profession, String name, int level ){
        Toon newToon = Toon.builder()
                .name(name)
                .campaign(campaign)
                .profession(profession)
                .level(level)
                .build();
        return toonRepository.save(newToon);
    }

    public Toon updateToon(UUID id, String campaign, String profession, String name, int level){
        getToonById(id).setName(name);
        getToonById(id).setCampaign(campaign);
        getToonById(id).setLevel(level);
        getToonById(id).setProfession(profession);

        return getToonById(id);
    }

    public boolean deleteToonById(UUID id){
        toonRepository.deleteById(id);
        return true;
    }

    public List<Toon> searchToons(String search) {
        return toonRepository.searchToonsByKeyword(search);
    }





}
