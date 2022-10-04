package fsq.core.data.repository.item.weapon;

import fsq.core.entity.item.weapon.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaponRepository  extends JpaRepository<Weapon, Long> {

}
