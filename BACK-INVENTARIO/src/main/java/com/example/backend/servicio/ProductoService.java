package com.example.backend.servicio;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.backend.entidades.Producto;
import com.example.backend.repositorios.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public void actualizarStock(Long idProducto, int cantidad) {
        Optional<Producto> optionalProducto = productoRepository.findById(idProducto);

        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            int stockActualizado = producto.getStock() + cantidad;
            producto.setStock(stockActualizado);
            productoRepository.save(producto);
        } else {
            // Manejar el caso en que el producto no existe
            throw new RuntimeException("El producto con ID " + idProducto + " no existe");
        }
    }
    
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }
    // Otros métodos de servicio, si los tienes
}