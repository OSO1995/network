package nc.project.network.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Area")
public class Area {

    @Id
    @SequenceGenerator(name = "area_id_sequence_gen",
            sequenceName = "area_id_sequence", initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "area_id_sequence_gen")
    @Column(name = "ID_Area")
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Area")
    private Set<Switch> switches;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Area")
    private Set<User> users;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Area")
    private Set<Cable> cables;

    public Area() {
    }

    public Area(Set<Switch> switches) {
        this.switches = switches;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Switch> getSwitches() {
        return switches;
    }

    public void setSwitches(Set<Switch> switches) {
        this.switches = switches;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Cable> getCables() {
        return cables;
    }

    public void setCables(Set<Cable> cables) {
        this.cables = cables;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", switches=" + switches +
                '}';
    }
}
