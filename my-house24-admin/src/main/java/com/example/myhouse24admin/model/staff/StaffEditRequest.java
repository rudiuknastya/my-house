package com.example.myhouse24admin.model.staff;

import com.example.myhouse24admin.entity.StaffStatus;
import com.example.myhouse24admin.validators.emailValidation.StaffEmailOwner;
import com.example.myhouse24admin.validators.phoneValidation.PhoneOwner;
import jakarta.validation.constraints.*;

@PhoneOwner(message = "{validation-phone-exist}")
@StaffEmailOwner(message = "{validation-email-exist}")
public record StaffEditRequest(
        Long id,
        @Size(min = 2, max = 50, message = "{validation-size-min-max}")
        @NotBlank(message = "{validation-field-required}")
        String firstName,
        @Size(min = 2, max = 50, message = "{validation-size-min-max}")
        @NotBlank(message = "{validation-field-required}")
        String lastName,
        @Pattern(regexp = "\\+?380(50|66|95|99|67|68|96|97|98|63|93|73)[0-9]{7}", message = "{validation-phone-from-pattern}")
        String phoneNumber,
        @Pattern(regexp = "^[a-zA-Z0-9+._-]+@([a-zA-z]{2,10}\\.)+[a-zA-z]{2,5}$", message = "{validation-email-from-pattern}")
        String email,
        @Size(min = 8, max = 72, message = "{validation-size-min-max}")
        String password,
        @Size(min = 8, max = 72, message = "{validation-size-min-max}")
        String confirmPassword,
        @NotNull(message = "{validation-role-required}")
        Long roleId,
        StaffStatus status
) {
}
