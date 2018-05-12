package nc.project.network.service;

import nc.project.network.entity.User;

public interface UserService {

  void save(User user);

  User findByUsername(String username);

}
