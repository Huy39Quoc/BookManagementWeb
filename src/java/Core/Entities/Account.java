/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Core.Entities;

/**
 *
 * @author ChanRoi
 */
public class Account {
    private int Id;
    private String Name;
    private String Email;
    private String Password;
    private String Role;
    private String Status;
    
    public Account(){
        this.Id = 0;
        this.Name = null;
        this.Email = null;
        this.Password = null;
        this.Role = null;
        this.Status = "active";
    }
    
    public Account(int Id, String Name, String Email, String Password, String Role, String Status){
        this.Id = Id;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
        this.Role = Role;
        this.Status = Status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    @Override
    public String toString(){
        return String.format("%d, %s, %s, %s, %s, %s"
                , this.Id, this.Name, this.Email, this.Password, this.Role, this.Status);
    }
}
