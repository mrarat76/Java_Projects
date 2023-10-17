/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cp2labproject;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Monster
 */
@Entity
@Table(name = "manager")
@NamedQueries({
    @NamedQuery(name = "Manager_1.findAll", query = "SELECT m FROM Manager_1 m"),
    @NamedQuery(name = "Manager_1.findById", query = "SELECT m FROM Manager_1 m WHERE m.id = :id"),
    @NamedQuery(name = "Manager_1.findByName", query = "SELECT m FROM Manager_1 m WHERE m.name = :name"),
    @NamedQuery(name = "Manager_1.findBySurname", query = "SELECT m FROM Manager_1 m WHERE m.surname = :surname")})
public class Manager_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;

    public Manager_1() {
    }

    public Manager_1(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manager_1)) {
            return false;
        }
        Manager_1 other = (Manager_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cp2labproject.Manager_1[ id=" + id + " ]";
    }
    
}
