package com.weproud.dto;

import com.weproud.config.BeanConfigs;
import com.weproud.entities.Hello;
import com.weproud.entities.User;
import lombok.Getter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Logan. k
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BeanConfigs.class})
public class UserDtoTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void map_to_dto() throws Exception {
        User user = new User("logan.k", 23, "logan.k@gmail.com");
        UserDto userDto = this.modelMapper.map(user, UserDto.class);

        assertThat(userDto.getName()).isEqualTo(user.getName());
    }

    @Test
    public void dto_to() throws Exception {

    }

    @Getter
    public static class HelloUser {
        private Hello hello;
        private User user;

        public HelloUser(final Hello hello, final User user) {
            this.hello = hello;
            this.user = user;
        }
    }

    @Getter
    private class HelloUserDto {
        private HelloDto helloDto;
        private UserDto userDto;
    }
}