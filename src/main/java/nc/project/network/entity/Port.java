package nc.project.network.entity;


import javax.persistence.*;

@Entity
@Table(name = "Ports")
public class Port {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_Port")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinColumn(name = "ID_Cable")
  private Cable cable;

  @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
  @JoinColumn(name = "ID_Switch", insertable=false, updatable=false)
  private Switch switcch;


  public Port() {
  }

  public Port(Cable cable) {
    this.cable = cable;
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

  public Switch getSwitcch() {
    return switcch;
  }

  public void setSwitcch(Switch switcch) {
    this.switcch = switcch;
  }

  @Override
  public String toString() {
    return "Port{" +
        "id=" + id +
        ", cable=" + cable +
//        ", aSwitch=" + aSwitch +
        '}';
  }
}
