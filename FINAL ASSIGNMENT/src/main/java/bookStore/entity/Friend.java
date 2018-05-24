package bookStore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userOneId;
    private Long userTwoId;

    public Friend() {
    }

    public Friend(Long userOneId, Long userTwoId) {
        this.userOneId = userOneId;
        this.userTwoId = userTwoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserOneId() {
        return userOneId;
    }

    public void setUserOneId(Long userOneId) {
        this.userOneId = userOneId;
    }

    public Long getUserTwoId() {
        return userTwoId;
    }

    public void setUserTwoId(Long userTwoId) {
        this.userTwoId = userTwoId;
    }
}
