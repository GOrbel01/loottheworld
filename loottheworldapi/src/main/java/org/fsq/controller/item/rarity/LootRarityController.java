package org.fsq.controller.item.rarity;

import fsq.core.data.repository.item.rarity.LootRarityRepository;
import fsq.core.entity.item.mapper.FieldUpdater;
import fsq.core.entity.item.mapper.UpdateItem;
import fsq.core.entity.item.mapper.UpdateItemMulti;
import fsq.core.entity.item.rarity.LootRarity;
import org.fsq.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LootRarityController {
    @Autowired
    private LootRarityRepository lootRarityRepository;

    @Autowired
    private FieldUpdater fieldUpdater;

    @GetMapping("/loot-rarities")
    public List<LootRarity> getLootRarities() {
        List<LootRarity> result = lootRarityRepository.findLootRaritiesByUserIdOrderByOrder(SecurityUtils.getCurrentAuthenticatedUser().getId());
        if (result == null || result.isEmpty()) {
            return new ArrayList<>();
        }
        return result;
    }

    @GetMapping("/loot-rarity/{id}")
    public LootRarity getLootRarityById(@PathVariable(value = "id") Long id) {
        return lootRarityRepository.findById(id).get();
    }

    @PostMapping("/loot-rarity")
    public void postLootRarity(@RequestBody LootRarity rarity) {
        lootRarityRepository.save(rarity);
    }

    @PutMapping("/loot-rarity/{id}")
    public void updateLootRarity(@PathVariable(value = "id") Long id, @RequestBody Map<String, UpdateItem> params) {
        LootRarity toUpdate = lootRarityRepository.findById(id).get();
        fieldUpdater.updateFields(toUpdate, params);
        lootRarityRepository.save(toUpdate);
    }

    @PutMapping("/loot-rarity/orders")
    public void updateLootRarityOrder(@RequestBody Map<Long, Map<String, UpdateItem>>  params) {
        params.forEach((key,item) -> {
            LootRarity toUpdate = lootRarityRepository.findById(key).get();
            fieldUpdater.updateFields(toUpdate, item);
            lootRarityRepository.save(toUpdate);
        });
    }
}
