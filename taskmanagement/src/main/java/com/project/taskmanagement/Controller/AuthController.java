package com.project.taskmanagement.Controller;



//import io.swagger.annotations.Api;
import com.project.taskmanagement.Config.JwtHelper;
import com.project.taskmanagement.Config.SecurityConfig;
import com.project.taskmanagement.CustomException.ResourceNotFoundException;
import com.project.taskmanagement.DTO.JwtRequest;
import com.project.taskmanagement.DTO.JwtResponse;
import com.project.taskmanagement.DTO.UserDTO;
import com.project.taskmanagement.Entity.User;
import com.project.taskmanagement.Service.UserService;
import com.project.taskmanagement.ServiceImpl.CustomUserServiceImpl;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
//@Api(value = "AuthController", description = "APIs for Authentication!!")
//@CrossOrigin(
//        origins = "http://localhost:4200",
//        allowedHeaders = {"Authorization"},
//        methods = {RequestMethod.GET,RequestMethod.POST},
//        maxAge = 3600
//)
public class AuthController {

    @Autowired
    private CustomUserServiceImpl userDetailsService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private SecurityConfig Config;
    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper helper;

//    @Value("${googleClientId}")
//    private String googleClientId;
//    @Value("${newPassword}")
//    private String newPassword;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());
        User userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
        UserDTO userDto = modelMapper.map(userDetails, UserDTO.class);
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .user(userDto).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new ResourceNotFoundException(" Invalid Username or Password  !!");
        }

    }


    @GetMapping("/current")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        String name = principal.getName();
        return new ResponseEntity<>(modelMapper.map(userDetailsService.loadUserByUsername(name), UserDTO.class), HttpStatus.OK);
    }


    //login with Google api

//    @PostMapping("/google")
//    public ResponseEntity<JwtResponse> loginWithGoogle(@RequestBody Map<String, Object> data) throws IOException {
//
//
//        //get the id token from request
//        String idToken = data.get("idToken").toString();
//
//        NetHttpTransport netHttpTransport = new NetHttpTransport();
//
//        JacksonFactory jacksonFactory = JacksonFactory.getDefaultInstance();
//
//        GoogleIdTokenVerifier.Builder verifier = new GoogleIdTokenVerifier.Builder(netHttpTransport, jacksonFactory).setAudience(Collections.singleton(googleClientId));
//
//
//        GoogleIdToken googleIdToken = GoogleIdToken.parse(verifier.getJsonFactory(), idToken);
//
//
//        GoogleIdToken.Payload payload = googleIdToken.getPayload();
//
//        logger.info("Payload : {}", payload);
//
//        String email = payload.getEmail();
//
//        User user = null;
//
//        user = userService.findUserByEmailOptional(email).orElse(null);
//
//        if (user == null) {
//            //create new user
//            user = this.saveUser(email, data.get("name").toString(), data.get("photoUrl").toString());
//        }
//        ResponseEntity<JwtResponse> jwtResponseResponseEntity = this.login(JwtRequest.builder().email(user.getEmail()).password(newPassword).build());
//        return jwtResponseResponseEntity;
//
//
//    }

//    private User saveUser(String email, String name, String photoUrl) {
//
//        UserDto newUser = UserDto.builder()
//                .name(name)
//                .email(email)
//                .password(newPassword)
//                .build();
//
//        UserDto user = userService.createUser(newUser);
//
//        return this.modelMapper.map(user, User.class);
//
//
//    }


}


