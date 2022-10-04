package org.fsq.controller.item.armor;

import fsq.core.data.repository.item.armor.ArmorRepository;
import fsq.core.entity.item.armor.Armor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ArmorController {
    @Autowired
    private ArmorRepository armorRepository;

    @GetMapping("/armors")
    public List<Armor> getFullArmorList() {
        List<Armor> result = armorRepository.findAll();
        if (result == null || result.isEmpty()) {
            return new ArrayList<>();
        }
        return result;
    }

    @PostMapping("/armor")
    public void postItem(@RequestBody Armor weapon) {
        armorRepository.save(weapon);
    }

    @GetMapping("/armor/{id}")
    public Armor getArmorById(@PathVariable(value = "id") Long id) {
        return armorRepository.findById(id).get();
    }
}
