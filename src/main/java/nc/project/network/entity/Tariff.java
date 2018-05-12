package nc.project.network.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Tariffs")
public class Tariff {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_Tariff")
  private Long id;

  @Column(name = "Description")
  private String description;

  @Column(name = "Speed")
  private int speed;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "tariff")
  private Set<User> users;

  public Tariff() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Tariff{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", speed=" + speed +
        ", users=" + users +
        '}';
  }
}
