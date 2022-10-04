package fsq.core.entity.stat;

import javax.persistence.*;

@Entity
@Table(name = "ltw_base_stat")
public class Stat {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "statid")
    private Integer statId;
    @Column(name="statname")
    private String statName;

    public Stat() {

    }

    public Stat(int statId) {
        this.statId = statId;
    }

    public Integer getStatId() {
        return statId;
    }

    public void setStatId(Integer statId) {
        this.statId = statId;
    }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }
}
