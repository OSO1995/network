package nc.project.network.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID_User")
  private Long id;

  @Column(name = "login")
  private String username;

  @Column(name = "password")
  private String password;

  @Transient
  private String confirmPassword;

  @Column(name = "First_Name")
  private String firstName;

  @Column(name = "Second_Name")
  private String secondName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_Tariff")
  private Tariff tariff;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ID_Cable")
  private Cable cable;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
  private Set<Request> requests;

  @ManyToMany
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "ID_User"),
      inverseJoinColumns = @JoinColumn(name = "ID_Role"))
  private Set<Role> roles;

  public User() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSecondName() {
    return secondName;
  }

  public void setSecondName(String secondName) {
    this.secondName = secondName;
  }

  public Tariff getTariff() {
    return tariff;
  }

  public void setTariff(Tariff tariff) {
    this.tariff = tariff;
  }

  public Cable getCable() {
    return cable;
  }

  public void setCable(Cable cable) {
    this.cable = cable;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public Set<Request> getRequests() {
    return requests;
  }

  public void setRequests(Set<Request> requests) {
    this.requests = requests;
  }

  @Override
  public String toString() {
    return "User{" +
        "username='" + username + '\'' +
        ", firstName='" + firstName + '\'' +
        ", secondName='" + secondName + '\'' +
        ", tariff=" + tariff +
        '}';
  }
}
