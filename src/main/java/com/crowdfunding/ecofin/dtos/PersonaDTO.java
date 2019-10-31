package com.crowdfunding.ecofin.dtos;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Document("persona")
public class PersonaDTO {

    @Id
    private ObjectId id;

    @NotEmpty(message = "Debes ingresar un nombre")
    private String nombre;

    @NotEmpty(message = "Debes ingresar un correo")
    @Email(message = "el formato del correo no es valido")
    private String email;

    @NotEmpty(message = "El perfil no es correcto")
    private List<String> perfil;

    @NotEmpty(message = "Ingresa una contraseña")
    private String password;

    @NotEmpty(message = "Debes ingresar una dirección")
    private String direccion;

    @NotEmpty(message = "Indica tu profesión")
    private String profesion;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaNacimiento;

    @NotEmpty(message = "Ingresa tu sexo")
    private String sexo;

    @NotEmpty(message = "Ingresa tu país de origen")
    private String pais;

    private List<String> proyectos;

    private boolean activo;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public String getId(){
        return id.toString();
    }

    //public ObjectId getId() { return id; }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPerfil() {
        return perfil;
    }

    public void setPerfil(List<String> perfil) {
        this.perfil = perfil;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean status) {
        this.activo = status;
    }

    public List<String> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<String> proyectos) {
        this.proyectos = proyectos;
    }
}
