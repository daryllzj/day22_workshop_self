package paf.day22_workshop_self.model;

import java.sql.Date;

public class RSVP {
    
    private Integer id;

    private String fullName;

    private String email;

    private String phone;

    private Date confirmationDate;

    private String comments;

    public RSVP() {
    }

    public RSVP(Integer id, String fullName, String email, String phone, Date confirmationDate, String comments) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.confirmationDate = confirmationDate;
        this.comments = comments;
    }

    public RSVP(String fullName, String email, String phone, Date confirmationDate) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.confirmationDate = confirmationDate;
    }

    

    public RSVP(String fullName, String email, String phone, Date confirmationDate, String comments) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.confirmationDate = confirmationDate;
        this.comments = comments;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    



}
