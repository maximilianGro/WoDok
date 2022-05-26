package eHealth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserLoginDto {
    @NotNull(message = "Email must not be null")
    @Email
    private String email;

    @NotNull(message = "Password must not be null")
    private String password;

    @NotNull(message = "LockedCounter must not be null")
    private int lockedCounter;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLockedCounter() {
        return lockedCounter;
    }

    public void setLockedCounter(int lockedCounter) {
        this.lockedCounter = lockedCounter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserLoginDto)) {
            return false;
        }
        UserLoginDto userLoginDto = (UserLoginDto) o;
        return Objects.equals(email, userLoginDto.email)
                && Objects.equals(password, userLoginDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }

    @Override
    public String toString() {
        return "UserLoginDto{"
                + "email='" + email + '\''
                + ", password='" + password + '\''
                + '}';
    }


    public static final class UserLoginDtoBuilder {
        private String email;
        private String password;

        private UserLoginDtoBuilder() {
        }

        public static UserLoginDtoBuilder anUserLoginDto() {
            return new UserLoginDtoBuilder();
        }

        public UserLoginDtoBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public UserLoginDtoBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserLoginDto build() {
            UserLoginDto userLoginDto = new UserLoginDto();
            userLoginDto.setEmail(email);
            userLoginDto.setPassword(password);
            return userLoginDto;
        }
    }
}
