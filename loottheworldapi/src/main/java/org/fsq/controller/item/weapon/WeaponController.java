package org.fsq.controller.item.weapon;

import fsq.core.data.repository.item.weapon.WeaponRepository;
import fsq.core.entity.item.weapon.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WeaponController {
    @Autowired
    private WeaponRepository weaponRepository;

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

    @GetMapping("/weapon/{id}")
    public Weapon getWeaponById(@PathParam(value = "id") Long id) {
        return weaponRepository.findById(id).get();
    }
}
