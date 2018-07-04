package com.umn.ac.id.trashare.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "reset_password_member")
public class ResetPasswordMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reset_password_bank_sampah")
    private int idResetPasswordMember;

    @OneToOne
    @JoinColumn(name = "id_bank_sampah")
    private Member idMember;

    @Column(name = "token")
    private String token;

    @Column(name = "date_created")
    private Date dateCreated;

    public ResetPasswordMember(Member idMember, String token, Date dateCreated) {
        this.idMember = idMember;
        this.token = token;
        this.dateCreated = dateCreated;
    }

    public ResetPasswordMember() {
    }

    public int getIdResetPasswordMember() {
        return idResetPasswordMember;
    }

    public void setIdResetPasswordMember(int idResetPasswordMember) {
        this.idResetPasswordMember = idResetPasswordMember;
    }

    public Member getIdMember() {
        return idMember;
    }

    public void setIdMember(Member idMember) {
        this.idMember = idMember;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
