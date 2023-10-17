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
@Table(name = "personnel")
@NamedQueries({
    @NamedQuery(name = "Personnel_1.findAll", query = "SELECT p FROM Personnel_1 p"),
    @NamedQuery(name = "Personnel_1.findById", query = "SELECT p FROM Personnel_1 p WHERE p.id = :id"),
    @NamedQuery(name = "Personnel_1.findByName", query = "SELECT p FROM Personnel_1 p WHERE p.name = :name"),
    @NamedQuery(name = "Personnel_1.findBySurname", query = "SELECT p FROM Personnel_1 p WHERE p.surname = :surname"),
    @NamedQuery(name = "Personnel_1.findBySalary", query = "SELECT p FROM Personnel_1 p WHERE p.salary = :salary"),
    @NamedQuery(name = "Personnel_1.findByAdvanceValue", query = "SELECT p FROM Personnel_1 p WHERE p.advanceValue = :advanceValue"),
    @NamedQuery(name = "Personnel_1.findByRequestStatus", query = "SELECT p FROM Personnel_1 p WHERE p.requestStatus = :requestStatus")})
public class Personnel_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "SALARY")
    private String salary;
    @Column(name = "Advance_Value")
    private String advanceValue;
    @Column(name = "Request_Status")
    private String requestStatus;

    public Personnel_1() {
    }

    public Personnel_1(Integer id) {
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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAdvanceValue() {
        return advanceValue;
    }

    public void setAdvanceValue(String advanceValue) {
        this.advanceValue = advanceValue;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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
        if (!(object instanceof Personnel_1)) {
            return false;
        }
        Personnel_1 other = (Personnel_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.cp2labproject.Personnel_1[ id=" + id + " ]";
    }
    
}
