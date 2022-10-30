package fsq.core.entity.stat;

import com.fasterxml.jackson.annotation.JsonProperty;
import fsq.core.entity.user.LtwKeyEntity;
import fsq.core.entity.user.User;

import javax.persistence.*;

@Entity
@Table(name = "ltw_stat_base")
public class Stat extends LtwKeyEntity {

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
