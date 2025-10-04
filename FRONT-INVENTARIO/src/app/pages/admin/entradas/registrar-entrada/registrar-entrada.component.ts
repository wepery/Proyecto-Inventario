import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DetalleEntrada } from 'src/app/core/models/detalle-entrada';
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

  fechaEntrada: string = "";
  listaDetalleEntrada: any[] = [];
  producto: any[] = [];
  isLoggedIn = false;
  user: any = null;

  detalleEntrada: any = {

    descripcion: '',
    cantidad: '',

    producto: {
      productoId: '',
    },
    usuario: {
      id: '',
    },
    entrada: {
      fechaEntrada: '',
    },
  };

  constructor(
    private fb: FormBuilder,
    private productoService: ProductoService,
    private login: LoginService,
    private entradaService: EntradaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.detalleEntradaForm = this.fb.group({
      productoId: ['', Validators.required],
      descripcion: ['', Validators.required],
      cantidad: [null, [Validators.required, Validators.min(1)]],
      fechaEntrada: ['', Validators.required]
    });

    this.obtenerProducto();
    this.obtenerUsuario();
  }
  enviarEntrada() {
    if (this.listaDetalleEntrada.length === 0) {
      swal.fire('Error', 'Agregue al menos un registro antes de enviar.', 'error');
      return;
    }

    this.listaDetalleEntrada.forEach(d => d.usuario.id = this.user.id);

    this.entradaService.crearEntradaConDetalles(this.listaDetalleEntrada)
      .subscribe({
        next: () => {
          swal.fire('Éxito', 'La entrada se ha enviado correctamente.', 'success');
          this.listaDetalleEntrada = [];
          this.detalleEntradaForm.reset();
          this.router.navigate(['/admin/entradas']);
        },
        error: err => {
          console.error(err);
          swal.fire('Error', 'Hubo un problema al enviar la entrada.', 'error');
        }
      });
  }



  obtenerProducto() {
    this.productoService.listarProductoActivadas().subscribe(
      (producto: any) => {
        this.producto = producto;
      },
      (error: any) => {
        console.log("Error al obtener las categorías: ", error);
      }
    );
  }
  obtenerUsuario() {
    this.isLoggedIn = this.login.isLoggedIn();
    this.user = this.login.getUser();
    this.login.loginStatusSubjec.asObservable().subscribe(
      data => {
        this.isLoggedIn = this.login.isLoggedIn();
        this.user = this.login.getUser();
      }
    )
  }

  agregarProducto() {
    if (this.detalleEntradaForm.invalid) return;

    const detalle: DetalleEntrada = {
      descripcion: this.detalleEntradaForm.value.descripcion,
      cantidad: this.detalleEntradaForm.value.cantidad,
      producto: { productoId: this.detalleEntradaForm.value.productoId },
      usuario: { id: this.user.id },
      entrada: { fechaEntrada: this.detalleEntradaForm.value.fechaEntrada }
    };
    this.listaDetalleEntrada.push(detalle);
    this.detalleEntradaForm.reset();
  }


  guardarValor(event: any) {
    const inputElement = event.target as HTMLInputElement;
    let inputValue = inputElement.value;

    inputValue = inputValue.replace(/[^0-9-]/g, '');
    if (inputValue.charAt(0) === '-' && inputValue.length > 1) {
      inputValue = '-' + inputValue.replace('-', '');
    } else {
      inputValue = inputValue.replace('-', '');
    }
    inputElement.value = inputValue;
  }




}
