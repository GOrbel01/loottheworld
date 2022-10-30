package fsq.core.data.repository.stat;

import fsq.core.entity.stat.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatRepository extends JpaRepository<Stat, Integer> {
    List<Stat> findStatsByUserId(Long userId);
}
