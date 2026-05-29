package pl.edu.pk.klockowisko.mapper;

import pl.edu.pk.klockowisko.dto.UserResponse;
import pl.edu.pk.klockowisko.entity.User;

public class UserMapper {
    public static UserResponse toResponse(User user){
        UserResponse res = new UserResponse();
        res.setId(user.getId());
        res.setUsername(user.getUsername());
        return res;
    }
}
