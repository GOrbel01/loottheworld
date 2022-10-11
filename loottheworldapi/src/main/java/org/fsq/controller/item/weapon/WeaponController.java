package org.fsq.controller.item.weapon;

import fsq.core.data.repository.item.weapon.WeaponRepository;
import fsq.core.entity.item.mapper.FieldUpdater;
import fsq.core.entity.item.mapper.UpdateItem;
import fsq.core.entity.item.weapon.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class WeaponController {
    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private FieldUpdater fieldUpdater;

    @GetMapping("/weapons")
    public List<Weapon> getFullWeaponList() {
        List<Weapon> result = weaponRepository.findAll();
        if (result == null || result.isEmpty()) {
            return new ArrayList<>();
        }
        return result;
    }

    @PostMapping("/weapon")
    public void postItem(@RequestBody Weapon weapon) {
        weaponRepository.save(weapon);
    }


    @PutMapping("/weapon/{id}")
    public void updateItem(@PathVariable(value = "id") Long id, @RequestBody Map<String, UpdateItem> params) {
        Weapon toUpdate = weaponRepository.findById(id).get();
        fieldUpdater.updateFields(toUpdate, params);
        weaponRepository.save(toUpdate);
    }

    @GetMapping("/weapon/{id}")
    public Weapon getWeaponById(@PathVariable(value = "id") Long id) {
        return weaponRepository.findById(id).get();
    }
}
