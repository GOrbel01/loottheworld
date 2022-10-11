package org.fsq.controller.item;

import fsq.core.data.repository.item.ItemRepository;
import fsq.core.entity.item.Item;
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
    public List<Item> getItems() {
        List<Item> result = itemRepository.findAll();
        if (result == null || result.isEmpty()) {
            return new ArrayList<>();
        }
        return result;
    }

    @PostMapping("/item")
    public void postItem(@RequestBody Item weapon) {
        itemRepository.save(weapon);
    }
}
