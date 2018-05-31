package nc.project.network.entity;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Roles")
public class Role implements GrantedAuthority {

  @Id
  @SequenceGenerator(name = "role_id_sequence_gen",
          sequenceName="role_id_sequence", initialValue = 10)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_sequence_gen")
  @Column(name = "ID_Role")
  private Long id;

  @Column(name = "Name")
  private String roleName;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles",cascade = CascadeType.ALL)
  private Set<User> users;

  public Role() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  @Override
  public String toString() {
    return "Role{" +
            "id=" + id +
            ", name='" + roleName + '\'' +
            '}';
  }

  @Override
  public String getAuthority() {
    return roleName;
  }
}
