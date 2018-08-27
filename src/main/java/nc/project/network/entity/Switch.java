package nc.project.network.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Switches")
public class Switch implements Device {

    @Id
    @SequenceGenerator(name = "switch_id_sequence_gen",
            sequenceName = "switch_id_sequence", initialValue = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "switch_id_sequence_gen")
    @Column(name = "ID_Switch")
    private Long id;

    @Column(name = "Status")
    private boolean enable;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Area")
    private Area area;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_Switch")
    private Set<Port> ports;

    public Switch() {
    }

    public Switch(boolean enable, Set<Port> ports) {
        this.enable = enable;
        this.ports = ports;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Set<Port> getPorts() {
        return ports;
    }

    public void setPorts(Set<Port> ports) {
        this.ports = ports;
    }

    @Override
    public String toString() {
        return "Switch{" +
                "id=" + id +
                '}';
    }
}
