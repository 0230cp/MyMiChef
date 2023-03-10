package cp.demo.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@Table(name = "menutbl")
@Entity
@NoArgsConstructor
@Builder
public class MenuEntity {

    @Id
    @Column(name = "menuId", columnDefinition = "varchar(256)")
    private String menuId;

    @Column(name = "menuName", columnDefinition = "varchar(256)")
    private String menuName;

    @Column(name = "nationName", columnDefinition = "varchar(256)")
    private String nationName;

    @Column(name = "calorie", columnDefinition = "varchar(256)")
    private String calorie;

    @Column(name = "levelName", columnDefinition = "varchar(256)")
    private String levelName;

    @Column(name = "recipe", columnDefinition = "TEXT")
    private String recipe;
}