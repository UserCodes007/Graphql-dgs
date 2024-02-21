package com.example.GraphqlDgs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="users")
public class User {
        @Column(length = 20,nullable=false)
        private  String name;
        @Id
        private Long id;
        @Column(length=30,nullable = false,unique = true)
        private String email;
        @Column(length = 10,nullable = false)
        private String password;

        public User(String name, Long id, String email, String password) {
            this.name = name;
            this.id = id;
            this.email = email;
            this.password = password;
        }

    public User() {}
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        return Objects.equals(id, other.id);
    }

    public String getName() {
            return name;
        }

        public Long getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
        public User id(Long id) {
            this.id = id;
            return this;
        }
        public User password(String password) {
            this.password = password;
            return this;
        }
        public User email(String email) {
            this.email = email;
            return this;
        }

        public User name(String name) {
            this.name = name;
            return this;
        }
    }

