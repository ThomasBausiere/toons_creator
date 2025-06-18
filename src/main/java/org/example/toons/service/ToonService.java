package org.example.toons.service;


import org.example.toons.model.Toon;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ToonService {

    private  Map<UUID, Toon> toons;
    public ToonService() {
        toons = new HashMap<>();
        Toon toon1= Toon.builder()
                .id(UUID.randomUUID())
                .name("Toon1")
                .campaign("Campaign 1")
                .profession("Warrior")
                .level(20)
                .build();
        Toon toon2= Toon.builder()
                .id(UUID.randomUUID())
                .name("Toon2")
                .campaign("Campaign 1")
                .profession("Monk")
                .level(20)
                .build();
        Toon toon3= Toon.builder()
                .id(UUID.randomUUID())
                .name("Toon3")
                .campaign("Campaign 2")
                .profession("Mesmer")
                .level(12)
                .build();

        toons.put(toon1.getId(), toon1);
        toons.put(toon2.getId(), toon2);
        toons.put(toon3.getId(), toon3);

    }

    public List<Toon> getAllToon(){
        return toons.values().stream().toList();
    }

    public Toon getToonById(UUID id){
        return this.toons.get(id);
    }

    public Toon newToon(String campaign, String profession, String name, int level ){
        Toon newToon = Toon.builder()
                .id(UUID.randomUUID())
                .name(name)
                .campaign(campaign)
                .profession(profession)
                .level(level)
                .build();
        return toons.put(newToon.getId(), newToon);
    }

    public Toon updateToon(UUID id, String campaign, String profession, String name, int level){
        Toon toonToUpdate = getToonById(id);
        toonToUpdate.setName(name);
        toonToUpdate.setCampaign(campaign);
        toonToUpdate.setLevel(level);
        toonToUpdate.setProfession(profession);

        return toons.put(toonToUpdate.getId(), toonToUpdate);
    }



}
