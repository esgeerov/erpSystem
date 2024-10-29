package com.samir.erpSystem.entity;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "client")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "companyName")
    String companyName;
    @Column(name = "clientName")
    String name;
    @Column(name = "phone")
    String phone;
    @CreationTimestamp
    Date createdAt;
    @ColumnDefault(value = "1")
    Integer active;

}
