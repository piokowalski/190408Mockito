import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

//    @Mock
    private UserDao userDao;

//    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userDao = mock(UserDao.class);
        userService = new UserService(userDao);
    }

//    @Test
//    public void returnFromMock() {
//        User user = userDao.findUser("admin");
//        System.out.println(user);
//
//        List<User> users = userService.getAllUsers();
//        System.out.println(users);
//    }

    @Test
    public void shouldGetAllUsers() {


        when(userDao.getAllUsers()).thenReturn(List.of(
                new User("Piotrek"),
                new User("Remik"),
                new User("Arek"),
                new User("Artur")
        ));

        // when
        List<User> result = userService.getAllUsers();

        // then
        assertThat(result).hasSize(4);
    }

    @Test
    public void shouldReturnAllUsersMatchingPattern() {
        // Zadanie 2
    }

    @Test
    public void shouldReturnTrueIfUserExists() {
        // Zadanie 3
    }

    @Test
    public void shouldReturnFalseIfUserDoesNotExist() {
        // Zadanie 4
    }

    @Test
    public void shouldThrowExceptionWhenRemovingNonExistingUser() {
        // Zadanie 5
    }
}