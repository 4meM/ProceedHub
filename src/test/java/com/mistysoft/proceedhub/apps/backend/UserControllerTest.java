package com.mistysoft.proceedhub.apps.backend;

import com.mistysoft.proceedhub.modules.shared.security.JwtUtil;
import com.mistysoft.proceedhub.modules.user.application.LoginUser;
import com.mistysoft.proceedhub.modules.user.application.RegisterUser;
import com.mistysoft.proceedhub.modules.user.application.SearchUser;
import com.mistysoft.proceedhub.modules.user.application.dto.UserDTO;
import com.mistysoft.proceedhub.modules.user.domain.User;
import com.mistysoft.proceedhub.modules.user.domain.UserId;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private RegisterUser registerUser;

    @Mock
    private LoginUser loginUser;

    @Mock
    private SearchUser searchUser;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        UserId userId = new UserId(UUID.randomUUID().toString());
        User user = User.builder()
                .id(userId)
                .username("testuser")
                .email("test@example.com")
                .password("password")
                .roles(Set.of())
                .build();

        when(registerUser.execute(user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles())).thenReturn(user);

        ResponseEntity<String> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User created successfully", response.getBody());
        verify(registerUser, times(1)).execute(user.getUsername(), user.getEmail(), user.getPassword(), user.getRoles());
    }

    @Test
    void testLoginUser() {
        UserId userId = new UserId(UUID.randomUUID().toString());
        User user = User.builder()
                .id(userId)
                .username("testuser")
                .password("password")
                .build();

        HttpServletResponse response = mock(HttpServletResponse.class);
        String token = "testtoken";

        doNothing().when(loginUser).execute(user.getUsername(), user.getPassword());
        when(jwtUtil.generateToken(user.getUsername())).thenReturn(token);

        ResponseEntity<String> result = userController.loginUser(user, response);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Login successful", result.getBody());
        verify(loginUser, times(1)).execute(user.getUsername(), user.getPassword());
        verify(jwtUtil, times(1)).generateToken(user.getUsername());
        verify(response, times(1)).addCookie(any(Cookie.class));
    }

    @Test
    void testLoginUserInvalid() {
        UserId userId = new UserId(UUID.randomUUID().toString());
        User user = User.builder()
                .id(userId)
                .username("testuser")
                .password("password")
                .build();

        HttpServletResponse response = mock(HttpServletResponse.class);

        doThrow(new IllegalArgumentException("Invalid password")).when(loginUser).execute(user.getUsername(), user.getPassword());

        ResponseEntity<String> result = userController.loginUser(user, response);

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        assertEquals("Invalid password", result.getBody());
        verify(loginUser, times(1)).execute(user.getUsername(), user.getPassword());
        verify(jwtUtil, never()).generateToken(user.getUsername());
        verify(response, never()).addCookie(any(Cookie.class));
    }

    @Test
    void testGetUserByUsername() {
        String username = "testuser";
        UserId userId = new UserId(UUID.randomUUID().toString());
        User user = User.builder()
                .id(userId)
                .username(username)
                .email("example@mail")
                .roles(Set.of())
                .build();

        when(searchUser.findByUsername(username)).thenReturn(Optional.of(user));

        ResponseEntity<UserDTO> response = userController.getUserByUsername(username);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assert verifies that the members of the actual object are equal to the members of the expected object
        assertEquals(Objects.requireNonNull(response.getBody()).getUsername(), user.getUsername());
        assertEquals(response.getBody().getEmail(), user.getEmail());
        assertEquals(response.getBody().getRoles(), user.getRoles());

        verify(searchUser, times(1)).findByUsername(username);
    }

    @Test
    void testGetUserByUsernameNotFound() {
        String username = "testuser";

        when(searchUser.findByUsername(username)).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response = userController.getUserByUsername(username);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(searchUser, times(1)).findByUsername(username);
    }

    @Test
    void testVerifyToken() {
        String token = "testtoken";
        Claims claims = mock(Claims.class);
        String username = "testuser";
        UserId userId = new UserId(UUID.randomUUID().toString());
        User user = User.builder()
                .id(userId)
                .username(username)
                .email("example@mail")
                .roles(Set.of())
                .build();

        when(jwtUtil.getClaimsFromToken(token)).thenReturn(claims);
        when(claims.getSubject()).thenReturn(username);
        when(searchUser.findByUsername(username)).thenReturn(Optional.of(user));

        ResponseEntity<UserDTO> response = userController.verifyToken(token);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Objects.requireNonNull(response.getBody()).getUsername(), user.getUsername());
        assertEquals(response.getBody().getEmail(), user.getEmail());
        assertEquals(response.getBody().getRoles(), user.getRoles());
        verify(jwtUtil, times(1)).getClaimsFromToken(token);
        verify(searchUser, times(1)).findByUsername(username);
    }

    @Test
    void testVerifyTokenEmpty() {
        String token = "";

        ResponseEntity<UserDTO> response = userController.verifyToken(token);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        verify(jwtUtil, never()).getClaimsFromToken(token);
    }

    @Test
    void testVerifyTokenNull() {
        String token = null;

        ResponseEntity<UserDTO> response = userController.verifyToken(token);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        verify(jwtUtil, never()).getClaimsFromToken(token);
    }

    @Test
    void testVerifyTokenInvalid() {
        String token = "invalidtoken";

        when(jwtUtil.getClaimsFromToken(token)).thenThrow(new RuntimeException());

        ResponseEntity<UserDTO> response = userController.verifyToken(token);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        verify(jwtUtil, times(1)).getClaimsFromToken(token);
    }

    @Test
    void testVerifyTokenNotFound() {
        String token = "testtoken";
        Claims claims = mock(Claims.class);
        String username = "testuser";

        when(jwtUtil.getClaimsFromToken(token)).thenReturn(claims);
        when(claims.getSubject()).thenReturn(username);
        when(searchUser.findByUsername(username)).thenReturn(Optional.empty());

        ResponseEntity<UserDTO> response = userController.verifyToken(token);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        verify(jwtUtil, times(1)).getClaimsFromToken(token);
        verify(searchUser, times(1)).findByUsername(username);
    }
}