package org.example.toons.service;

import org.example.toons.model.Boss;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BossService {

    private Map<UUID, Boss> bosses;

    public BossService() {
        bosses = new HashMap<>();

        addBoss("Phlog the Indomitable", "Dragon's Gullet (Ascalon");
        addBoss("Galrath", "Kessex Peak (Kryta)");
        addBoss("Desnas Hubor", "Tangle Root (Maguuma Jungle)");
        addBoss("Custodian Fidius", "Dunes of Despair (Crystal Desert)");
        addBoss("Maw the Mountain Heart", "Dreadnought's Drift (Shiverpeak Mountains)");
        addBoss("Cairn the Destroyer", "Abaddon's Mouth (Ring of Fire Islands)");
        addBoss("Justiciar Kasandra", "D'Alessio Seaboard (explorable area) (War in Kryta)");
        addBoss("Barthimus the Provident", "Cursed Lands (War in Kryta)");
        addBoss("Whuup Buumbuul", "Snake Dance (Shiverpeak Mountains)");
        addBoss("Balthazar's Cursed", "Perdition Rock (Ring of Fire Islands)");
    }

    private void addBoss(String name, String pos) {
        UUID id = UUID.randomUUID();
        Boss boss = Boss.builder()
                .Id(id)
                .name(name)
                .pos(pos)
                .build();
        bosses.put(id, boss);
    }

    public Map<UUID, Boss> getBosses() {
        return bosses;
    }

    public Map<UUID, String> getBossMap() {
        Map<UUID, String> result = new HashMap<>();
        for (Map.Entry<UUID, Boss> entry : bosses.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getName() + " – " + entry.getValue().getPos());
        }
        return result;
    }

    public Boss getBossById(UUID id) {
        return bosses.get(id); // Map<UUID, Boss>
    }

    public Boss getBossByName(String name) {
        return bosses.values().stream()
                .filter(boss -> boss.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
