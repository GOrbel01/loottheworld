package fsq.core.data.repository.item.rarity;

import fsq.core.entity.item.rarity.LootRarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LootRarityRepository extends JpaRepository<LootRarity, Long> {
    List<LootRarity> findLootRaritiesByUserIdOrderByOrder(Long userId);
}
