package com.example.DataStreamNYC.features;

import com.example.DataStreamNYC.models.User;
import com.example.DataStreamNYC.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UsersUIFeatureTests {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        userRepository.deleteAll();
    }

    @After
    public void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    public void shouldAllowFullCrudFunctionalityForAUser() throws Exception {

        User firstUser = new User(
                "firstUser@email.com",
                "user1First",
                "user1Last",
                "password1"
        );
        firstUser = userRepository.save(firstUser);
        Long firstUserId = firstUser.getId();

        User secondUser = new User(
                "secondUser@email.com",
                "user2First",
                "user2Last",
                "password2"
        );
        secondUser = userRepository.save(secondUser);
        Long secondUserId = secondUser.getId();

        System.setProperty("selenide.browser", "Chrome");

        // Visit the UI in a browser
        open("http://localhost:3000/");

        // Visit the admin view page
        $("#admin-view-link").click();

        // Make sure the link worked and the form is now showing
        $("#users-wrapper").should(appear);

        // There should only be two users
        $$("[data-user-display]").shouldHave(size(2));

        // Test that all data shows up for each user
        $("#user-" + firstUserId + "-first-name").shouldHave(text("user1First"));
        $("#user-" + firstUserId + "-last-name").shouldHave(text("user1Last"));
        $("#user-" + firstUserId + "-email").shouldHave(text("firstUser@email.com"));

        $("#user-" + secondUserId + "-first-name").shouldHave(text("user2First"));
        $("#user-" + secondUserId + "-last-name").shouldHave(text("user2Last"));
        $("#user-" + secondUserId + "-email").shouldHave(text("secondUser@email.com"));

        // Visit the new user page
        $("#new-user-link").click();

        // Make sure the link worked and the form is now showing
        $("#new-user-form").should(appear);

        // Add a new user
        $("#new-user-email").sendKeys("thirdUser@email.com");
        $("#new-user-first-name").sendKeys("thirdFirstName");
        $("#new-user-last-name").sendKeys("thirdLastName");
        $("#new-user-password").sendKeys("password3");
        $("#new-user-submit").click();

        // Now there should be three Users
        $$("[data-user-display]").shouldHave(size(3));

        refresh();

        // Now there should be three Users again after the refresh
        $$("[data-user-display]").shouldHave(size(3));

        // Check that the data is showing up for the third User
        Long thirdUserId = secondUserId + 1;
        $("#user-" + thirdUserId + "-email").shouldHave(text("thirdUser@email.com"));
        $("#user-" + thirdUserId + "-first-name").shouldHave(text("thirdFirstName"));
        $("#user-" + thirdUserId + "-last-name").shouldHave(text("thirdLastName"));

        // Test Deleting the first user
        $("#user-" + firstUserId).should(exist);
        $$("[data-user-display]").shouldHave(size(3));

        $("#delete-user-" + firstUserId).click();
        $("#user-" + firstUserId).shouldNot(exist);

        $$("[data-user-display]").shouldHave(size(2));

        refresh();

        // Double check the user was deleted
        $("#user-" + firstUserId).shouldNot(exist);
        $$("[data-user-display]").shouldHave(size(2));

        // Update a users information
        $("#update-user").click();

        // Make sure the button worked and the form is now showing
        $("#update-user-form").should(appear);

        // update the user
        $("#update-user-email").sendKeys("updatedUser@email.com");
        $("#update-user-first-name").sendKeys("updatedFirstName");
        $("#update-user-last-name").sendKeys("updatedLastName");
        $("#update-user-password").sendKeys("updatedPassword");
        $("#update-user-submit").click();

        // There should still only be two users
        $$("[data-user-display]").shouldHave(size(2));

        refresh();

        // There should still be only two users
        $$("[data-user-display]").shouldHave(size(2));

        // Check that the updated data is showing up correctly for the second User
        $("#user-" + secondUserId + "-first-name").shouldHave(text("updatedFirstName"));
        $("#user-" + secondUserId + "-last-name").shouldHave(text("updatedLastName"));
        $("#user-" + secondUserId + "-email").shouldHave(text("updatedUser@email.com"));
    }
}
