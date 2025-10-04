import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { EntradaService } from 'src/app/core/services/entrada.service';
import { LoginService } from 'src/app/core/services/login.service';
import { ProductoService } from 'src/app/core/services/producto.service';
import swal from 'sweetalert2';

@Component({
  selector: 'app-registrar-entrada',
  templateUrl: './registrar-entrada.component.html',
  styleUrls: ['./registrar-entrada.component.css']
})
export class RegistrarEntradaComponent implements OnInit {

  detalleEntradaForm!: FormGroup;
  producto: any[] = [];
  isLoggedIn = false;
  user: any = null;
  listaDetalleEntrada: any[] = [];

  constructor(
    private fb: FormBuilder,
    private productoService: ProductoService,
    private login: LoginService,
    private entradaService: EntradaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Inicializa el FormGroup
    this.detalleEntradaForm = this.fb.group({
      productoId: ['', Validators.required],
      descripcion: ['', Validators.required],
      cantidad: [null, [Validators.required, Validators.min(1)]],
      fechaEntrada: ['', Validators.required]
    });

    this.obtenerProducto();
    this.obtenerUsuario();
  }

  // Obtiene los productos activos
  obtenerProducto() {
    this.productoService.listarProductoActivadas().subscribe(
      (data: any) => this.producto = data,
      (error: any) => console.error('Error al obtener productos:', error)
    );
  }

  // Obtiene el usuario logueado
  obtenerUsuario() {
    this.isLoggedIn = this.login.isLoggedIn();
    this.user = this.login.getUser();
    this.login.loginStatusSubjec.asObservable().subscribe(() => {
      this.isLoggedIn = this.login.isLoggedIn();
      this.user = this.login.getUser();
    });
  }

  // Agrega un detalle al listado
  agregarProducto() {
    if (this.detalleEntradaForm.invalid) {
      swal.fire('Error', 'Complete todos los campos antes de agregar.', 'error');
      return;
    }

    const detalle = {
      producto: { productoId: this.detalleEntradaForm.value.productoId },
      descripcion: this.detalleEntradaForm.value.descripcion,
      cantidad: this.detalleEntradaForm.value.cantidad,
      usuario: { id: this.user.id },
      entrada: { fechaEntrada: this.detalleEntradaForm.value.fechaEntrada }
    };

    this.listaDetalleEntrada.push(detalle);
    this.detalleEntradaForm.reset();
  }

  // Envía todos los detalles al backend
  enviarEntrada() {
    if (this.listaDetalleEntrada.length === 0) {
      swal.fire('Error', 'Agregue al menos un registro antes de enviar.', 'error');
      return;
    }

    // Asigna el usuario a todos los detalles
    this.listaDetalleEntrada.forEach(d => d.usuario.id = this.user.id);

    this.entradaService.crearEntradaConDetalles(this.listaDetalleEntrada)
      .subscribe({
        next: () => {
          swal.fire('Éxito', 'La entrada se ha enviado correctamente.', 'success');
          this.listaDetalleEntrada = [];
          this.detalleEntradaForm.reset();
          this.router.navigate(['/admin/entradas']);
        },
        error: (err) => {
          console.error(err);
          swal.fire('Error', 'Hubo un problema al enviar la entrada.', 'error');
        }
      });
  }

  // Validación de número positivo para cantidad
  guardarValor(event: any) {
    const input = event.target as HTMLInputElement;
    let value = input.value.replace(/[^0-9]/g, '');
    input.value = value;
  }

  // Permite eliminar un detalle de la lista
  eliminarDetalle(index: number) {
    this.listaDetalleEntrada.splice(index, 1);
  }

}
