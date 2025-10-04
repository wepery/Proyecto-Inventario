import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ProductoService } from 'src/app/core/services/producto.service';
import { ProveedorService } from 'src/app/core/services/proveedor.service';

@Component({
  selector: 'app-crear-producto',
  templateUrl: './crear-producto.component.html',
  styleUrls: ['./crear-producto.component.css']
})
export class CrearProductoComponent implements OnInit {

  productoForm!: FormGroup;
  proveedores: any[] = [];

  constructor(
    private fb: FormBuilder,
    private productoService: ProductoService,
    private proveedorService: ProveedorService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.productoForm = this.fb.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      precio: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      stock: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      ubicacion: ['', Validators.required],
      proveedorId: ['', Validators.required]
    });

    this.obtenerProveedor();
  }

  formSubmit(): void {
    if (this.productoForm.invalid) {
      Swal.fire({
        icon: 'error',
        title: 'Faltan datos',
        text: 'Por favor, ingrese todos los atributos correctamente antes de guardar el producto.'
      });
      return;
    }

    this.productoService.agregarProducto(
      this.productoForm.value.nombre,
      this.productoForm.value.precio,
      this.productoForm.value.descripcion,
      this.productoForm.value.stock,
      this.productoForm.value.ubicacion,
      this.productoForm.value.proveedorId
    ).subscribe({
      next: () => {
        Swal.fire(
          'Producto guardado',
          'El producto se ha registrado correctamente',
          'success'
        );
        this.productoForm.reset();
        this.router.navigate(['/admin/producto']);
      },
      error: (error) => {
        console.error(error);
        Swal.fire(
          'Error',
          'Ocurrió un error al registrar el producto',
          'error'
        );
      }
    });
  }

  obtenerProveedor(): void {
    this.proveedorService.listarProveedorActivadas().subscribe({
      next: (data: any) => this.proveedores = data,
      error: (error: any) => console.error('Error al obtener proveedores:', error)
    });
  }

}
