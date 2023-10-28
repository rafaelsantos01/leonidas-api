package com.spantanTech.leonidas.modules.users.services.Authentication.refresh;

import com.spantanTech.leonidas.modules.users.entities.Users;
import com.spantanTech.leonidas.modules.users.repository.UsersRepository;
import com.spantanTech.leonidas.modules.users.services.Authentication.dto.LoginResponseDTO;
import com.spantanTech.leonidas.security.TokenService;
import com.spantanTech.leonidas.security.context.SetUserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {

    @Autowired
    private SetUserSession userService;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private TokenService tokenService;

    public LoginResponseDTO handle(String token) {
        Users execute = userService.execute();

        Users users = usersRepository.findById(execute.getId()).orElse(null);

        String accessToken = tokenService.generateToken(users);
        String refreshToken = tokenService.generateRefreshToken(users);

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        loginResponseDTO.setAccess_token(accessToken);
        loginResponseDTO.setRefresh_token(refreshToken);

        return loginResponseDTO;
    }
}
