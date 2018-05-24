package nc.project.network.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Tariffs")
public class Tariff {

  @Id
  @SequenceGenerator(name = "tariff_id_sequence_gen",
          sequenceName="tariff_id_sequence", initialValue = 10)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tariff_id_sequence_gen")
  @Column(name = "ID_Tariff")
  private Long id;

  @Column(name = "Description")
  private String description;

  @Column(name = "Speed")
  private int speed;

//  @OneToMany(fetch = FetchType.LAZY, mappedBy = "tariff")
//  private Set<User> users;

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

//  public Set<UserBase> getUsers() {
//    return users;
//  }
//
//  public void setUsers(Set<UserBase> users) {
//    this.users = users;
//  }

  @Override
  public String toString() {
    return "Tariff{" +
        "id=" + id +
        ", description='" + description + '\'' +
        ", speed=" + speed +
//        ", users=" + users +
        '}';
  }
}
