package com.maven.springboot.myjpa;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneBookRequest implements IPhoneBook {
    @JsonIgnore
    private Long id;

    @NotBlank
    @Size(min=2, max=20)
    private String name;
    @NotBlank
    @Size(min=4, max=8)
    private ECategory category;
    @NotBlank
    @Size(min=0, max=20)
    private String phoneNumber;
    @Size(min=0, max=200)
    private String email;
}
