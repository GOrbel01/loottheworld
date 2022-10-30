package fsq.core.entity.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@MappedSuperclass
public class LtwKeyEntity {
    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @JsonProperty("userid")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
