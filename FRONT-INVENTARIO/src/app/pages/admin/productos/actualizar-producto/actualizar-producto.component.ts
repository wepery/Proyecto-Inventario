import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ProductoService } from 'src/app/core/services/producto.service';
import { ProveedorService } from 'src/app/core/services/proveedor.service';

@Component({
  selector: 'app-actualizar-producto',
  templateUrl: './actualizar-producto.component.html',
  styleUrls: ['./actualizar-producto.component.css']
})
export class ActualizarProductoComponent implements OnInit {

  productoForm!: FormGroup;
  productoId: number = 0;
  proveedores: any[] = [];

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private productoService: ProductoService,
    private proveedorService: ProveedorService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.productoId = this.route.snapshot.params['productoId'];

    this.productoForm = this.fb.group({
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
      precio: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      stock: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      ubicacion: ['', Validators.required],
      proveedorId: ['', Validators.required]
    });

    // Cargar proveedores
    this.proveedorService.listarProveedorActivadas().subscribe({
      next: (data: any) => this.proveedores = data,
      error: (error) => console.error(error)
    });

    // Cargar producto
    this.productoService.obtenerProductoPorId(this.productoId).subscribe({
      next: (data: any) => {
        this.productoForm.patchValue({
          nombre: data.nombre,
          descripcion: data.descripcion,
          precio: data.precio,
          stock: data.stock,
          ubicacion: data.ubicacion,
          proveedorId: data.proveedor.proveedorId
        });
      },
      error: (error) => console.error(error)
    });
  }

  actualizarProducto(): void {
    if (this.productoForm.invalid) {
      Swal.fire({
        icon: 'error',
        title: 'Faltan datos',
        text: 'Por favor, complete todos los campos correctamente antes de actualizar.'
      });
      return;
    }

    this.productoService.actualizarProducto(this.productoId, this.productoForm.value)
      .subscribe({
        next: () => {
          Swal.fire({
            icon: 'success',
            title: 'Producto actualizado',
            text: 'El producto se ha actualizado correctamente.'
          }).then(() => this.router.navigate(['/admin/producto']));
        },
        error: (error) => {
          console.error(error);
          Swal.fire({
            icon: 'error',
            title: 'Error al actualizar',
            text: 'Ocurrió un error al actualizar el producto.'
          });
        }
      });
  }

  validarNumeroPositivo(event: Event): void {
    const input = event.target as HTMLInputElement;
    let value = input.value.replace(/[^0-9]/g, '');
    input.value = value;
  }

}
