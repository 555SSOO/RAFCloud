package rs.raf.cloud.services;

public interface AuthenticationService {

    boolean isPasswordCorrect(String username, String password);

}
