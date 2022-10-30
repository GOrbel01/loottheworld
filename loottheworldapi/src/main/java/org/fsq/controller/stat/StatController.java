package org.fsq.controller.stat;

import fsq.core.entity.item.mapper.FieldUpdater;
import fsq.core.entity.item.mapper.UpdateItem;
import fsq.core.entity.stat.Stat;
import fsq.core.data.repository.stat.StatRepository;
import fsq.core.entity.stat.StatLookup;
import fsq.core.entity.user.User;
import org.fsq.security.user.CurrentUser;
import org.fsq.security.user.UserPrinciple;
import org.fsq.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class StatController {
    @Autowired
    private StatRepository statRepository;

    @Autowired
    private FieldUpdater fieldUpdater;

    @GetMapping("/stats")
    public List<Stat> getStats() {
        return statRepository.findStatsByUserId(SecurityUtils.getCurrentAuthenticatedUser().getId());
    }

    @GetMapping("/stats/lookup")
    public List<StatLookup> getStatsForSelect() {
        return mapStats(statRepository.findStatsByUserId(SecurityUtils.getCurrentAuthenticatedUser().getId()));
    }

    @GetMapping("/stat/{id}")
    public Stat getById(@PathVariable Integer id) {
        return statRepository.findById(id).get();
    }

    @PostMapping("/stat")
    public void postStat(@RequestBody Stat stat) {
        statRepository.save(stat);
    }

    @PutMapping("/stat/{id}")
    public void updateStat(@PathVariable(value = "id") Integer statId, @RequestBody Map<String, UpdateItem> params) {
        Stat toUpdate = statRepository.findById(statId).get();
        fieldUpdater.updateFields(toUpdate, params);
        statRepository.save(toUpdate);
    }

    private List<StatLookup> mapStats(List<Stat> stats) {
        List<StatLookup> result = new ArrayList<>();
        for (Stat stat : stats) {
            StatLookup l = new StatLookup();
            l.setValue(stat.getStatId());
            l.setLabel(stat.getStatName());
            result.add(l);
        }
        return result;
    }
}
