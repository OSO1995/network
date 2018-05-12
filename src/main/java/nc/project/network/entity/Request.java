package nc.project.network.entity;


import javax.persistence.*;

@Entity
@Table(name = "Request")
public class Request {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_Request")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_User")
  private User user;

  @Column(name = "Request")
  private String request;

  public Request() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  @Override
  public String toString() {
    return "Request{" +
        "id=" + id +
        ", user=" + user +
        ", request='" + request + '\'' +
        '}';
  }
}
