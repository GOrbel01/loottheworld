package org.fsq.controller.item;

import fsq.core.data.repository.item.ItemRepository;
import fsq.core.entity.item.Item;
import org.fsq.security.user.CurrentUser;
import org.fsq.security.user.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/item")
    public Item getItem() {
        return new Item();
    }

    @GetMapping("/items")
    public List<Item> getItems(@CurrentUser UserPrinciple userPrincipal) {
        List<Item> result = itemRepository.findItemsByUserId(userPrincipal.getId());
        if (result == null || result.isEmpty()) {
            return new ArrayList<>();
        }
        return result;
    }

    @PutMapping("/items/batch")
    public void generateRandomWeapons() {

    }

    @PostMapping("/item")
    public void postItem(@RequestBody Item weapon) {
        itemRepository.save(weapon);
    }
}
