import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
//given
        List<User> allUsers = List.of(
                new User("Mostow"),
                new User("Kowaliow"),
                new User("Durczak"),
                new User("Klinton")
        );

        when(userDao.getAllUsers()).thenReturn(allUsers);

//when
        List<User> result = userService.findUsers("ow");
//then
        assertThat(result).hasSize(2)
                .contains(
                        new User("Kowaliow"),
                        new User("Mostow")
                );
    }

//    @Test
//    public void shouldReturnTrueIfUserExists() {
//        // given
////CAPTOR HERE !!!
//
//        ArgumentCaptor<String> nameCaptor = ArgumentCaptor.forClass(String.class);
//
////USING CAPTOR !!!
//        User admin = new User("admin");
//        when(userDao.findUser(nameCaptor.capture()))
//                .thenReturn(admin);
//
//        // when
//        boolean result = userService.doesUserExist("admin");
//        // then
//
//        assertThat(result).isEqualTo(true);
//        assertThat(nameCaptor.getValue()).isEqualTo("admin");

    @Test
    public void shouldReturnTrueIfUserExists2() {
// Using verify() method
        // given

        User admin = new User("admin");
        when(userDao.findUser(eq("admin")))
                .thenReturn(admin);

        // when

        boolean result = userService.doesUserExist("admin");

        // then

        assertThat(result).isEqualTo(true);
        verify(userDao, times(1))
                .findUser("admin");
        verifyNoMoreInteractions(userDao);

    }

    @Test
    public void shouldReturnFalseIfUserDoesNotExist() {
        // given (zachowanie domyślne)
        when(userDao.findUser(eq("janek1")))
                .thenReturn(null);

        // when
        boolean result = userService.doesUserExist("janek1");

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldThrowExceptionWhenRemovingNonExistingUser() {
        // given
        doThrow(new RuntimeException()).when(userDao).deleteUser(any());

        // when & then

        assertThrows(RuntimeException.class,
                () -> userService.deleteUser(new User("ania13")));

        //STARY SPOSOOB
//        try {
//            userService.deleteUser(new User("ania13"));
//            fail("Should throw exception");
//        } catch (Exception e) {
//            assertThat(e).isInstanceOf(RuntimeException.class);
//        }
    }
}