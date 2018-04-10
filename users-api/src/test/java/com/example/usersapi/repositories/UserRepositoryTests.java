package com.example.usersapi.repositories;

import com.example.usersapi.models.User;
import com.google.common.collect.Iterables;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User firstUser = new User(
                "user1@email.com",
                "first1",
                "last1",
                "password1"
        );

        User secondUser = new User(
                "user2@email.com",
                "first2",
                "last2",
                "password2"
        );

        entityManager.persist(firstUser);
        entityManager.persist(secondUser);
        entityManager.flush();
    }

    @Test
    public void findAll_returnsAllUsers() {
        Iterable<User> usersFromDb = userRepository.findAll();

        assertThat(Iterables.size(usersFromDb), is(2));
    }

    @Test
    public void findAll_returnsUserName() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersUserName = Iterables.get(usersFromDb, 1).getEmail();

        assertThat(secondUsersUserName, is("user2@email.com"));
    }

    @Test
    public void findAll_returnsFirstName() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersFirstName = Iterables.get(usersFromDb, 1).getFirstName();

        assertThat(secondUsersFirstName, is("first2"));
    }

    @Test
    public void findAll_returnsLastName() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersLastName = Iterables.get(usersFromDb, 1).getLastName();

        assertThat(secondUsersLastName, is("last2"));
    }

    @Test
    public void findAll_returnsPassword() {
        Iterable<User> usersFromDb = userRepository.findAll();

        String secondUsersLastName = Iterables.get(usersFromDb, 1).getPassword();

        assertThat(secondUsersLastName, is("password2"));
    }
}