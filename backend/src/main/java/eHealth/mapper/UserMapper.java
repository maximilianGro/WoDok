package eHealth.mapper;

import eHealth.dto.UserRegisterDto;
import eHealth.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserRegisterDto UserToUserRegisterDto(User user) {
        UserRegisterDto userRegisterDto = new UserRegisterDto();
        userRegisterDto.setBirthday(user.getBirthday());
        userRegisterDto.setEmail(user.getEmail());
        userRegisterDto.setCity(user.getCity());
        userRegisterDto.setPassword(user.getPassword());
        userRegisterDto.setCountry(user.getCountry());
        userRegisterDto.setFirstName(user.getFirstName());
        userRegisterDto.setLastName(user.getLastName());
        userRegisterDto.setZip(user.getZip());
        userRegisterDto.setStreet(user.getStreet());
        return userRegisterDto;
    }

}
