package fsq.core.entity.item.rarity;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsq.core.entity.user.LtwKeyEntity;
import fsq.core.entity.user.User;

import javax.persistence.*;

@Entity
@Table(name = "ltw_loot_rarity_base")
public class LootRarity extends LtwKeyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "color")
    @JsonProperty("color")
    private String colorHex;

    @Column(name = "rarityorder")
    private Integer order;

    @Column(name = "tier")
    private Integer tier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColorHex() {
        return colorHex;
    }

    public void setColorHex(String colorHex) {
        this.colorHex = colorHex;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }
}
