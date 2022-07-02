package com.example.lcmsapp.service;

import com.example.lcmsapp.config.dto.ApiResponse;
import com.example.lcmsapp.config.dto.ResUserDTO;
import com.example.lcmsapp.config.dto.UserDTO;
import com.example.lcmsapp.entity.Filial;
import com.example.lcmsapp.entity.Role;
import com.example.lcmsapp.entity.User;
import com.example.lcmsapp.entity.enums.PositionType;
import com.example.lcmsapp.repository.FilialRepository;
import com.example.lcmsapp.repository.RoleRepository;
import com.example.lcmsapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final FilialRepository filialRepository;

    private final RoleRepository roleRepository;

    private final List<PositionType> positionTypeList = new ArrayList<>(Arrays.asList(PositionType.values()));

    public ApiResponse add(UserDTO user) {

        User user1 = new User();
        Set<Role> roles = new HashSet<>();
        for (Long rolesId : user.getRolesId()) {
            Optional<Role> optionalRole = roleRepository.findById(rolesId);
            if (optionalRole.isPresent()){
                roles.add(optionalRole.get());
            }
        }
        user1.setRoles(roles);
        user1.setFullName(user.getFullName());
        user1.setPhone(user.getPhone());
        user1.setSalary(user.getSalary());
        Optional<Filial> filialOptional = filialRepository.findById(user.getFilialId());
        if (!filialOptional.isPresent()){
         user1.setFilial(null);
        }else {
            user1.setFilial(filialOptional.get());
        }
        String positionType = user.getPositionType();
        int index = positionTypeList.indexOf(PositionType.valueOf(user.getPositionType().toUpperCase()));

        if (index>=0){
            user1.setPositionType(positionTypeList.get(index));
        }
        else user1.setPositionType(PositionType.OTHER);

        User save = userRepository.save(user1);
        ResUserDTO resUserDTO = userToResUserDTO(save);
        return ApiResponse.builder().data(resUserDTO).message("Saved").success(true).build();
    }

    public ApiResponse getAll() {
        List<User> userList = userRepository.findAll();
        List<ResUserDTO> resUserDTOList = userToResUserDTOList(userList);
        return ApiResponse.builder().success(true).message("Bori shu (*_*)").data(resUserDTOList).build();
    }

    public ApiResponse getOne(UUID uuid) {

        Optional<User> optionalUser = userRepository.findById(uuid);
        if (!optionalUser.isPresent()){
            return ApiResponse.builder().success(false).message("Noto`gri UUID berildi!").build();
        }
        User save = optionalUser.get();
        ResUserDTO resUserDTO = userToResUserDTO(save);
        return ApiResponse.builder().success(true).message("Topildi!").data(resUserDTO).build();
    }


    public ApiResponse update(UUID uuid, UserDTO user) {

        User user1 = userRepository.getById(uuid);
        user1.setFullName(user.getFullName());
        user1.setPhone(user.getPhone());
        user1.setSalary(user.getSalary());
        Optional<Filial> filialOptional = filialRepository.findById(user.getFilialId());
        if (!filialOptional.isPresent()) {
            user1.setFilial(null);
        } else {
            user1.setFilial(filialOptional.get());
        }
        String positionType = user.getPositionType();
        int index = positionTypeList.indexOf(PositionType.valueOf(user.getPositionType().toUpperCase()));
        if (index>=0){
            user1.setPositionType(positionTypeList.get(index));
        }
//        if (positionType.toUpperCase().equals("MANAGER")) user1.setPositionType(PositionType.MANAGER);
//        else if (positionType.toUpperCase().equals("DIRECTOR") ||
//                positionType.toUpperCase().equals("DIREKTOR")) user1.setPositionType(PositionType.DIRECTOR);
//        else if (positionType.toUpperCase().equals("MENTOR")) user1.setPositionType(PositionType.MENTOR);
//        else if (positionType.toUpperCase().equals("OTHER")) user1.setPositionType(PositionType.OTHER);

        User save = userRepository.save(user1);
        ResUserDTO resUserDTO = userToResUserDTO(save);
        return ApiResponse.builder().data(resUserDTO).message("Edited!").success(true).build();
    }
    public ApiResponse delete(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return ApiResponse.builder().success(false).message("Bunaqasi yo`q -_-").build();
        }
        userRepository.deleteById(id);
        return ApiResponse.builder().success(true).message("Deleted!").build();
    }

    public ResUserDTO userToResUserDTO(User user){
//        String positionType;
//        if (user.getPositionType().toString() == null){
//            positionType = "";
//        }
//        else {
//            positionType = user.getPositionType().toString();
//        }
        return ResUserDTO.builder()
                .roles(RolesToRolesName(user.getRoles()))
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .salary(user.getSalary())
                .filialName(user.getFilial().getName())
                .positionType(user.getPositionType().toString())
                .build();
    }
    public List<ResUserDTO> userToResUserDTOList(List<User> userList){
        List<ResUserDTO> collect = userList.stream().map(this::userToResUserDTO).collect(Collectors.toList());
        return collect;
    }
    public Set<String> RolesToRolesName(Set<Role> roles){
        Set<String> rolesName = new HashSet<>();
        for (Role role : roles) {
            rolesName.add(role.getName());
        }
        return rolesName;
    }
}
