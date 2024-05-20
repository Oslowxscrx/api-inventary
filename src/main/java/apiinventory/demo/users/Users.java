package apiinventory.demo.users;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import apiinventory.demo.common.enums.Role;

import java.util.Date;

@Entity
@Data
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreUsuario;

    @Column(unique = true, nullable = false)
    private String correoUsuario;

    @Column(nullable = false)
    private String contraseñaUsuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'user'")
    private Role role;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
}
