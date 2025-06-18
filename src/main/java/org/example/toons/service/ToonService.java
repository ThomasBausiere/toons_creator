package org.example.toons.service;


import org.example.toons.model.Toon;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ToonService {

    private  Map<UUID, Toon> toons;
    public ToonService() {
        toons = new HashMap<>();
        Toon toon1= Toon.builder()
                .id(UUID.randomUUID())
                .name("Warrior Firwan")
                .campaign("Prophecie")
                .profession("Warrior")
                .level(20)
                .build();
        Toon toon2= Toon.builder()
                .id(UUID.randomUUID())
                .name("Rysa Wys")
                .campaign("Faction")
                .profession("Assassin")
                .level(20)
                .build();
        Toon toon3= Toon.builder()
                .id(UUID.randomUUID())
                .name("Demonix Firwan")
                .campaign("Prophecie")
                .profession("Necromancer")
                .level(20)
                .build();
        Toon toon4= Toon.builder()
                .id(UUID.randomUUID())
                .name("Adrobora Firwan")
                .campaign("Nightfall")
                .profession("Paragon")
                .level(20)
                .build();
        Toon toon5= Toon.builder()
                .id(UUID.randomUUID())
                .name("Jack Daniel Rt")
                .campaign("Faction")
                .profession("Ritualist")
                .level(20)
                .build();

        toons.put(toon1.getId(), toon1);
        toons.put(toon2.getId(), toon2);
        toons.put(toon3.getId(), toon3);
        toons.put(toon4.getId(), toon4);
        toons.put(toon5.getId(), toon5);

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

        return toonToUpdate;
    }

    public boolean deleteToonById(UUID id){
        toons.remove(id);
        return true;
    }

    public List<Toon> searchToons(String search) {
        List<Toon> toonList = new ArrayList<>();
            toons.values().stream().forEach(toon -> {
                if(toon.getName().contains(search) || toon.getCampaign().contains(search) || toon.getProfession().contains(search)) {
                    toonList.add(toon);
                }
            });
        return toonList;
    }





}
