package pe.edu.upeu.calcfx.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="producto")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idProducto;
    String nombre;
    @ManyToOne
    @JoinColumn(name="id_categoria",nullable=false)
    Categoria categoria;
}
