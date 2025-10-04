import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Reclamo } from 'src/app/core/models/reclamo';
import { ReclamoService } from 'src/app/core/services/reclamo.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-responder-correo',
  templateUrl: './responder-correo.component.html',
  styleUrls: ['./responder-correo.component.css']
})
export class ResponderCorreoComponent implements OnInit {

  reclamoId!: number;
  reclamo?: Reclamo;
  mensaje: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private reclamoService: ReclamoService
  ) { }

  ngOnInit(): void {
    this.reclamoId = this.route.snapshot.params['reclamoId'];
    this.obtenerReclamoPorId();
  }

  obtenerReclamoPorId(): void {
    this.reclamoService.obtenerReclamoPorId(this.reclamoId).subscribe(
      (data: Reclamo) => {
        this.reclamo = data;
      },
      (error) => {
        console.error(error);
        Swal.fire('Error', 'No se pudo obtener el reclamo.', 'error');
      }
    );
  }

  enviarDisculpas(): void {
    if (!this.mensaje.trim()) {
      Swal.fire('Error', 'El mensaje no puede estar vacío.', 'error');
      return;
    }

    this.reclamoService.enviarDisculpas(this.reclamoId, this.mensaje).subscribe(
      () => {
        Swal.fire('Éxito', 'Las disculpas se han enviado correctamente.', 'success');
        this.router.navigate(['/user-dashboard/configuracion']);
      },
      (error) => {
        console.error(error);
        Swal.fire('Error', 'Hubo un problema al enviar las disculpas.', 'error');
      }
    );
  }
}
