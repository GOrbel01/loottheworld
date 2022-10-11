package org.fsq.controller.item.armor;

import fsq.core.data.repository.item.armor.ArmorRepository;
import fsq.core.entity.item.armor.Armor;
import fsq.core.entity.item.mapper.FieldUpdater;
import fsq.core.entity.item.mapper.UpdateItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ArmorController {
    @Autowired
    private ArmorRepository armorRepository;

    @Autowired
    private FieldUpdater fieldUpdater;

    @GetMapping("/armors")
    public List<Armor> getFullArmorList() {
        List<Armor> result = armorRepository.findAll();
        if (result == null || result.isEmpty()) {
            return new ArrayList<>();
        }
        return result;
    }

    @PostMapping("/armor")
    public void postItem(@RequestBody Armor armor) {
        armorRepository.save(armor);
    }

    @PutMapping("/armor/{id}")
    public void updateItem(@PathVariable(value = "id") Long id, @RequestBody Map<String, UpdateItem> params) {
        Armor toUpdate = armorRepository.findById(id).get();
        fieldUpdater.updateFields(toUpdate, params);
        armorRepository.save(toUpdate);
    }

    @GetMapping("/armor/{id}")
    public Armor getArmorById(@PathVariable(value = "id") Long id) {
        return armorRepository.findById(id).get();
    }
}
