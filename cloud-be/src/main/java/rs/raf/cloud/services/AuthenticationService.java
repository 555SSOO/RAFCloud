package rs.raf.cloud.services;

public interface AuthenticationService {

    String isPasswordCorrect(String username, String password);

}
