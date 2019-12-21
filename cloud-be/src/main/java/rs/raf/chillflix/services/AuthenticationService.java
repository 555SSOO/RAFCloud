package rs.raf.chillflix.services;

public interface AuthenticationService {

    boolean isPasswordCorrect(String username, String password);

}
