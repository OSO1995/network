package nc.project.network.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
