package cp.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class MenuEntity {

    @Id
    @Column(name = "menuId", columnDefinition = "INT")
    private String menuid;

    @Column(name = "menuName", columnDefinition = "varchar")
    private String menuname;

    @Column(name = "nationName", columnDefinition = "varchar")
    private String nationname;

    @Column(name = "calorie", columnDefinition = "varchar")
    private String calorie;

    @Column(name = "levelName", columnDefinition = "varchar")
    private String levelname;

    @Column(name = "recipe", columnDefinition = "TEXT")
    private String recipe;
}