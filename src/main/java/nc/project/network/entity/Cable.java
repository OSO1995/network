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

  @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinColumn(name = "ID_Area",insertable = false,updatable = false)
  private Area area;

  public Cable() {
  }

  public Cable(int speed) {
    this.speed = speed;
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

  @Override
  public String toString() {
    return "Cable{" +
        "id=" + id +
        '}';
  }

  public Area getArea() {
    return area;
  }

  public void setArea(Area area) {
    this.area = area;
  }
}
