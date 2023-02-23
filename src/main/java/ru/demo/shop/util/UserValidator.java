package ru.demo.shop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.demo.shop.models.User;
import ru.demo.shop.services.UserDetailService;

@Component
public class UserValidator implements Validator {

    private final UserDetailService userDetailService;

    @Autowired
    public UserValidator(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userDetailService.loadUserByUsername(user.getUsername());
        }catch(UsernameNotFoundException ignored){
            return;
        }
        errors.rejectValue("username", "", "Человек стаким имененм пользователя уже существует.");
    }
}
