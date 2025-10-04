import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ProveedorService } from 'src/app/core/services/proveedor.service';

@Component({
  selector: 'app-crear-proveedor',
  templateUrl: './crear-proveedor.component.html',
  styleUrls: ['./crear-proveedor.component.css']
})
export class CrearProveedorComponent implements OnInit {

  proveedorForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private proveedorService: ProveedorService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.proveedorForm = this.fb.group({
      nombre: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      telefono: ['', [Validators.required, Validators.pattern(/^[0-9]{9}$/)]],
      direccion: ['', Validators.required],
      ruc: ['', [Validators.required, Validators.pattern(/^[0-9]{11}$/)]],
    });
  }

  crearProveedor(): void {
    if (this.proveedorForm.invalid) {
      Swal.fire({
        icon: 'error',
        title: 'Faltan datos',
        text: 'Por favor, complete todos los campos correctamente.'
      });
      return;
    }

    const { nombre, email, telefono, direccion, ruc } = this.proveedorForm.value;

    this.proveedorService.agregarProveedor(nombre, ruc, direccion, telefono, email)
      .subscribe({
        next: (respuesta: any) => {
          Swal.fire({
            icon: 'success',
            title: 'Proveedor creado',
            text: 'El proveedor se ha creado correctamente.'
          });
          this.router.navigate(['/admin/proveedor']);
        },
        error: (error: any) => {
          console.error('Error al crear el proveedor:', error);
          Swal.fire({
            icon: 'error',
            title: 'Error al crear el proveedor',
            text: 'Ocurrió un error al crear el proveedor. Por favor, inténtelo de nuevo.'
          });
        }
      });
  }

  limitarLongitud(event: any, maxLength: number) {
    const input = event.target;
    if (input.value.length > maxLength) {
      input.value = input.value.slice(0, maxLength);
    }
  }

  validarNumeroPositivo(event: Event): void {
    const input = event.target as HTMLInputElement;
    let value = input.value;
    value = value.replace(/[^0-9]/g, '');
    input.value = value;
  }

}
