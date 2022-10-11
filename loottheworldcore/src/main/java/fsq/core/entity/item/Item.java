package fsq.core.entity.item;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vladmihalcea.hibernate.type.json.JsonType;
import fsq.core.entity.stat.ItemStat;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="ltw_item_base")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ltw_table_itemtype")
@TypeDef(name = "json", typeClass = JsonType.class)
public class Item implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;

    @Column(name = "itemtype")
    private String itemType;

    @Type(type="json")
    @Column(name = "itemstats", columnDefinition = "json")
    @JsonProperty("itemstats")
    private List<ItemStat> itemStats;

    @Column(name = "armor")
    private Integer armor;

    public Item() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public List<ItemStat> getItemStats() {
        return itemStats;
    }

    public void setItemStats(List<ItemStat> itemStats) {
        this.itemStats = itemStats;
    }

    public Integer getArmor() {
        return armor;
    }

    public void setArmor(Integer armor) {
        this.armor = armor;
    }
}
