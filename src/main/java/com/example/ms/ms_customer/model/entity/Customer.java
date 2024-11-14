package com.example.ms.ms_customer.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor

public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name="name",columnDefinition = "text", nullable = false)
    private String name;

    @Column(name="lastname",columnDefinition = "text", nullable = false)
    private String lastname;

    @Column(name="dni",columnDefinition = "text", nullable = false)
    private String dni;

    @Column(name="email",columnDefinition = "text", nullable = false)
    private String email;

    @PrePersist
    public void checkFieldBeforeInsert() {
        this.email = this.email.toLowerCase().trim();
    }

    @PreUpdate
    public void checkFieldsBeforeUpdate() {
        this.checkFieldBeforeInsert();
    }

}
