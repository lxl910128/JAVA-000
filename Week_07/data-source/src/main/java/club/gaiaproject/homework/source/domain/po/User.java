package club.gaiaproject.homework.source.domain.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 128, unique = true)
    private String username;
    
    @Column(nullable = false, length = 128)
    private String password;
    
    @Column(nullable = false, length = 64)
    private String name;
    
    @Column(nullable = false, length = 64)
    private String department = "";
    
    @Column(nullable = false, length = 32)
    private String phone = "";
    
    @Column(nullable = false, length = 128)
    private String remark = "";
    
    @Column(nullable = false)
    private Integer status = 1;
    
    @Column(nullable = false)
    private Boolean superuser = false;
    
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createTime;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updateTime;
}
