package nc.project.network.entity;


import javax.persistence.*;

@Entity
@Table(name = "Request")
public class Request {

  @Id
  @SequenceGenerator(name = "request_id_sequence_gen",
          sequenceName="request_id_sequence", initialValue = 10)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_id_sequence_gen")
  @Column(name = "ID_Request")
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ID_User")
  private UserBase user;

  @Column(name = "Request")
  private String request;

  public Request() {
  }

  public Request(User user, String request) {
    this.user = user;
    this.request = request;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserBase getUser() {
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
