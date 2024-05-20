package apiinventory.demo.assets;

import lombok.Data;
import java.util.Date;
import jakarta.persistence.*;
import apiinventory.demo.employees.Employee;
import org.hibernate.annotations.CreationTimestamp;



@Entity
@Data
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteAt;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date fechaAsignacion;

    @PrePersist
    protected void onCreate() {
        fechaAsignacion = new Date();
    }

    @Column(nullable = false)
    private Integer vidaUtil;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
