package fsq.core.entity.item.armor;

import fsq.core.entity.item.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ltw_item_base_armor")
public class Armor extends Item {
}
