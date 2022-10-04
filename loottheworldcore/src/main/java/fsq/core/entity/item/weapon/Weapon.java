package fsq.core.entity.item.weapon;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsq.core.entity.item.Item;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@DiscriminatorValue("ltw_item_base_weapon")
public class Weapon extends Item {
    @Column(name = "weapondmgmin")
    @JsonProperty("weapondmgmin")
    private BigDecimal weaponDamageMin;
    @Column(name = "weapondmgmax")
    @JsonProperty("weapondmgmax")
    private BigDecimal weaponDamageMax;

    @Transient
    private BigDecimal weaponDamageAvg;

    public Weapon() {
        super();
        this.weaponDamageMin = BigDecimal.ZERO;
        this.weaponDamageMax = BigDecimal.ZERO;
    }

    public BigDecimal getWeaponDamageMin() {
        return weaponDamageMin;
    }

    public void setWeaponDamageMin(BigDecimal weaponDamageMin) {
        this.weaponDamageMin = weaponDamageMin;
    }

    public BigDecimal getWeaponDamageMax() {
        return weaponDamageMax;
    }

    public void setWeaponDamageMax(BigDecimal weaponDamageMax) {
        this.weaponDamageMax = weaponDamageMax;
    }

    private void calculateAvgDamage() {
        if (weaponDamageMax == null || weaponDamageMin == null) {
            this.weaponDamageAvg = BigDecimal.ZERO;
            return;
        }
        BigDecimal diff = weaponDamageMax.subtract(weaponDamageMin);
        if (diff.equals(BigDecimal.ZERO)) {
            if (weaponDamageMax.compareTo(weaponDamageMin) > 0) {
                this.weaponDamageAvg = this.weaponDamageMax.setScale(1, RoundingMode.HALF_EVEN);
            } else {
                this.weaponDamageAvg = this.weaponDamageMin.setScale(1, RoundingMode.HALF_EVEN);
            }
        } else {
            BigDecimal result = weaponDamageMax.subtract(diff.divide(BigDecimal.valueOf(2)).setScale(1, RoundingMode.HALF_EVEN));
            this.weaponDamageAvg = result;
        }
    }

    public BigDecimal getWeaponDamageAvg() {
        calculateAvgDamage();
        return weaponDamageAvg;
    }
}
