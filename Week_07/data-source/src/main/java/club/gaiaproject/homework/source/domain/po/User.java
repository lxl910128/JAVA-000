package club.gaiaproject.homework.source.domain.po;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Phoenix Luo
 * @version 2020/11/30
 **/
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)//监听对象创建和删除
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MYSQL时可以这样使用自增
    private Long id;
    
    @Column(nullable = false)
    private String name;
}
