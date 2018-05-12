package nc.project.network.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Area")
public class Area {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_Area")
  private Long id;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "area")
  private Set<Switch> switches;

  public Area() {
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

  @Override
  public String toString() {
    return "Area{" +
        "id=" + id +
        ", switches=" + switches +
        '}';
  }
}
