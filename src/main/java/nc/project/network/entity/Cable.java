package nc.project.network.entity;

import javax.persistence.*;

@Entity
@Table(name = "Cables")
public class Cable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_Cable")
  private Long id;

  @Column(name = "Speed")
  private int speed;

  @Column(name = "Status")
  private boolean enable;

  public Cable() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public boolean isEnable() {
    return enable;
  }

  public void setEnable(boolean enable) {
    this.enable = enable;
  }

  @Override
  public String toString() {
    return "Cable{" +
        "id=" + id +
        ", speed=" + speed +
        ", enable=" + enable +
        '}';
  }
}
