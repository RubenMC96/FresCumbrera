package com.rmc.app.domain;

import java.time.LocalDate;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Compra {
    @Id
    @GeneratedValue
    private Long id;
    private String numFactura;
    private LocalDate fechaCompra;
    private Integer totalProductos;
    private Double importe;
    //private List<ProductoDTO> listaProductos;

    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;

    public Compra(Long id,String numFactura,LocalDate fechaCompra,Integer totalProductos, Double importe){
        this.id = id;
        this.numFactura = numFactura;
        this.fechaCompra = fechaCompra;
        this.totalProductos = totalProductos;
        this.importe = importe;
        //this.listaProductos = lista;
    };
}
