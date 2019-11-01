package com.crowdfunding.ecofin.dtos;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document("proyecto")
public class ProyectoDTO {

    @Id
    private ObjectId id;
    private String nombre;
    private String foto;
    private Map<String, String> propietario;
    private String descripcion;
    private String categoria;
    private Integer valorProyecto;
    private Integer montoAcumulado;
    private Integer porcentaje;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date finishDate;

    private Integer diasFaltantes;
    private String pais;
    private Integer visitas;
    private Integer prioridad;
    private List<Map<String, Object>> contribuyentes;
    private Integer activo;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;


    public String getId(){
        return id.toString();
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Map<String, String> getPropietario() {
        return propietario;
    }

    public void setPropietario(Map<String, String> propietario) {
        this.propietario = propietario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getValorProyecto() {
        return valorProyecto;
    }

    public void setValorProyecto(Integer valorProyecto) {
        this.valorProyecto = valorProyecto;
    }

    public Integer getMontoAcumulado() {
        return montoAcumulado;
    }

    public void setMontoAcumulado(Integer montoAcumulado) {
        this.montoAcumulado = montoAcumulado;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getDiasFaltantes() {
        return diasFaltantes;
    }

    public void setDiasFaltantes(Integer diasFaltantes) {
        this.diasFaltantes = diasFaltantes;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getVisitas() {
        return visitas;
    }

    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }

    public List<Map<String, Object>> getContribuyentes() {
        return contribuyentes;
    }

    public void setContribuyentes(List<Map<String, Object>> contribuyentes) {
        this.contribuyentes = contribuyentes;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer status) {
        this.activo = status;
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

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }

    public Integer getPrioridad() { return prioridad; }

    public void setPrioridad(Integer prioridad) { this.prioridad = prioridad; }
}
