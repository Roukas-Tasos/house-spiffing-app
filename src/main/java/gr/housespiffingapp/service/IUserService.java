package gr.housespiffingapp.service;

import gr.housespiffingapp.core.exceptions.AppObjectAlreadyExists;
import gr.housespiffingapp.core.exceptions.AppObjectInvalidArgumentException;
import gr.housespiffingapp.core.exceptions.AppObjectNotFoundException;
import gr.housespiffingapp.dto.userDTO.UserInsertDTO;
import gr.housespiffingapp.dto.userDTO.UserReadOnlyDTO;
import gr.housespiffingapp.dto.userDTO.UserUpdateDTO;
import gr.housespiffingapp.model.User;

import java.util.List;

public interface IUserService {

    UserReadOnlyDTO findById(Long id) throws AppObjectNotFoundException;

    List<UserReadOnlyDTO> findAll();

    UserReadOnlyDTO saveUser(UserInsertDTO userInsertDTO)
            throws AppObjectAlreadyExists, AppObjectInvalidArgumentException;

//    UserReadOnlyDTO updateUser(Long id, UserUpdateDTO userUpdateDTO)
//            throws AppObjectNotFoundException, AppObjectInvalidArgumentException;

    void deleteUser(Long id) throws AppObjectNotFoundException;

}
