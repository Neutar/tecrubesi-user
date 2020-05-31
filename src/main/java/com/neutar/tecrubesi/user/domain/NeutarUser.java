package com.neutar.tecrubesi.user.domain;

import com.neutar.tecrubesi.user.dto.NeutarUserUpdateDto;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Setter(AccessLevel.NONE)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NeutarUser {
    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    private String surname;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "E-mail is mandatory")
    @Email(message = "Wrong e-mail format")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private NeutarUserDetail neutarUserDetail;

    public void addPointsToUser(Long points){
        neutarUserDetail.addPointsToUser(points);
    }

    public void addBadgeToUser(Badge badge){
        neutarUserDetail.addBadgeToUser(badge);
    }

    public void updateUser(NeutarUserUpdateDto userUpdateDto){
        this.name = userUpdateDto.getName();
        this.surname = userUpdateDto.getSurname();
        this.username = userUpdateDto.getUsername();
        this.neutarUserDetail.updateUserDetails(userUpdateDto);
    }


}
