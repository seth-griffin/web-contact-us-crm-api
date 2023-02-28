package com.gtaaustin.CRM.entity;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name="contact")
public @Data class Contact {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="title")
    private String title;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_date")
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="update_date")
    private Date updateDate;

    @PrePersist
    public void createdAt() {
        this.createDate = this.updateDate = new Date();
    }

    @PreUpdate
    public void updatedAt() {
        this.updateDate = new Date();
    }
}
