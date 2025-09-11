package com.restaurant.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.restaurant.dto.ProductoMenuDTO;
import com.restaurant.model.Productosmenu;
import com.restaurant.service.ProductoMenuService;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoMenuController {
    
    @Autowired
    private ProductoMenuService productoMenuService;
    
    @PostMapping
    public ResponseEntity<Productosmenu> crearProducto(@RequestBody ProductoMenuDTO dto) {
        try {
            Productosmenu nuevoProducto = productoMenuService.crearProducto(dto);
            return ResponseEntity.ok(nuevoProducto);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<ProductoMenuDTO>> listarProductos() {
        List<ProductoMenuDTO> productos = productoMenuService.listarProductos();
        return ResponseEntity.ok(productos);
    }
    
    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoMenuDTO> obtenerProducto(@PathVariable Long id) {
        Optional<ProductoMenuDTO> producto = productoMenuService.obtenerProductoPorId(id);
        return producto.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
    
    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Productosmenu> actualizarProducto(@PathVariable Long id, 
                                                           @RequestBody ProductoMenuDTO dto) {
        try {
            Productosmenu productoActualizado = productoMenuService.actualizarProducto(id, dto);
            return ResponseEntity.ok(productoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productoMenuService.eliminarProducto(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}