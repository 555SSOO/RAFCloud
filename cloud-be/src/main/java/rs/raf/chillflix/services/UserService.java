package rs.raf.chillflix.services;

import rs.raf.chillflix.entities.User;

public interface UserService {

    User getUserFromId(String id) throws Exception;

}
