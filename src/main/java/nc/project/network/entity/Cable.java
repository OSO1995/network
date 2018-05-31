package nc.project.network.entity;

import javax.persistence.*;

@Entity
@Table(name = "Cables")
public class Cable {

  @Id
  @SequenceGenerator(name = "cable_id_sequence_gen",
          sequenceName="cable_id_sequence", initialValue = 10)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cable_id_sequence_gen")
  @Column(name = "ID_Cable")
  private Long id;

  @Column(name = "Speed")
  private int speed;

  @Column(name = "Length")
  private int length;

  @Column(name = "Cost")
  private int cost;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "ID_Area",insertable = false,updatable = false)
  private Area area;

  public Cable() {
  }

  public Cable(int speed, int length, int cost) {
    this.speed = speed;
    this.length = length;
    this.cost = cost;
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

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
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
