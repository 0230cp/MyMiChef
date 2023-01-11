package cp.demo.dto;

import cp.demo.domain.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
@Builder
@Getter
@Setter
public class UserDTO {


    private String name;

    private int height;

    private int weight;

    private int calorie;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .name(name)
                .height(height)
                .weight(weight)
                .calorie(calorie)
                .build();
    }
}
