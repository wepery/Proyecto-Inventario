import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EntradaService } from 'src/app/core/services/entrada.service';

// Opcional: Mensajes constantes
const ERROR_MESSAGES = {
  loadFail: 'No se pudo cargar la entrada. Intente nuevamente.',
};

@Component({
  selector: 'app-detalle-entrada',
  templateUrl: './detalle-entrada.component.html',
  styleUrls: ['./detalle-entrada.component.css'],
})
export class DetalleEntradaComponent implements OnInit {
  detalleEntrada: any = null;
  detalleEntradaId: number = 0;

  constructor(
    private readonly entradaService: EntradaService,
    private readonly router: Router,
    private readonly route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.detalleEntradaId = Number(this.route.snapshot.paramMap.get('detalleEntradaId'));
    if (!this.detalleEntradaId) {
      // Redirigir o mostrar error si el ID no es válido
      this.router.navigate(['/entradas']);
      return;
    }

    this.cargarEntrada(this.detalleEntradaId);
  }

  private cargarEntrada(id: number): void {
    this.entradaService.obtenerEntradaPorId(id).subscribe({
      next: (data) => {
        this.detalleEntrada = data;
      },
      error: (err) => {
        console.error('Error al cargar entrada:', err);
        alert(ERROR_MESSAGES.loadFail); // O usar MatSnackBar si tienes
      },
    });
  }
}
