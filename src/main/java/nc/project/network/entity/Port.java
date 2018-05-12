package nc.project.network.entity;


import javax.persistence.*;

@Entity
@Table(name = "Ports")
public class Port {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_Port")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_Cable")
  private Cable cable;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_Switch")
  private Switch aSwitch;

  public Port() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Cable getCable() {
    return cable;
  }

  public void setCable(Cable cable) {
    this.cable = cable;
  }

  public Switch getaSwitch() {
    return aSwitch;
  }

  public void setaSwitch(Switch aSwitch) {
    this.aSwitch = aSwitch;
  }

  @Override
  public String toString() {
    return "Port{" +
        "id=" + id +
        ", cable=" + cable +
        ", aSwitch=" + aSwitch +
        '}';
  }
}
