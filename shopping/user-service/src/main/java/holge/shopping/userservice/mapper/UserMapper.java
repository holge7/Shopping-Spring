package holge.shopping.userservice.mapper;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import commons.dto.UserDTO;
import holge.shopping.userservice.entity.Rol;
import holge.shopping.userservice.entity.User;

@Component
public class UserMapper {
	// Set manual rols becouse in User we have Set<Rol> and in UserDTO we have Set<ERol>
	
	private ModelMapper modelMapper;
	
	public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

	}
	
	public UserDTO toDto(User user) {
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		
        userDTO.rol = user.rol.stream()
        		.map(rol -> rol.getRol())
        		.collect(Collectors.toSet());
        return userDTO;
	} 
	
    public User toEntity(UserDTO dto) {
        User user = modelMapper.map(dto, User.class);
        user.rol = dto.rol.stream()
        		.map(rol -> new Rol(rol))
        		.collect(Collectors.toSet());
        return user;
    }
	
}
