package rs.raf.cloud.services;

public interface UserService {

    boolean validateToken(String token, String username);

}
