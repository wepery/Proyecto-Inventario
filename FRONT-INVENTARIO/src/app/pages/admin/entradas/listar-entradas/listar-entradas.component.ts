import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EntradaService } from 'src/app/core/services/entrada.service';
import { ReportesService } from 'src/app/core/services/reportes.service';

// Mensajes constantes
const ERROR_MESSAGES = {
  loadFail: 'No se pudieron obtener las entradas.',
  downloadFail: 'Error al descargar el PDF.',
};

@Component({
  selector: 'app-listar-entradas',
  templateUrl: './listar-entradas.component.html',
  styleUrls: ['./listar-entradas.component.css'],
})
export class ListarEntradasComponent implements OnInit {
  detalleEntrada: any[] = [];

  constructor(
    private readonly http: HttpClient,
    private readonly entradaService: EntradaService,
    private readonly router: Router,
    private readonly reportesService: ReportesService
  ) {}

  ngOnInit(): void {
    this.obtenerEntradas();
  }

  private obtenerEntradas(): void {
    this.entradaService.listarEntradas().subscribe({
      next: (data: any[]) => {
        this.detalleEntrada = data;
      },
      error: (err) => {
        console.error('Error al obtener las entradas:', err);
        alert(ERROR_MESSAGES.loadFail); // O usar MatSnackBar si deseas
      },
    });
  }

  descargarPDF(): void {
    this.reportesService.descargarEntrada().subscribe({
      next: (data: Blob) => this.generarPDF(data),
      error: (err) => {
        console.error('Error al descargar PDF:', err);
        alert(ERROR_MESSAGES.downloadFail);
      },
    });
  }

  private generarPDF(data: Blob): void {
    const blob = new Blob([data], { type: 'application/pdf' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'informe_detalle_entradas_productos.pdf';
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    window.URL.revokeObjectURL(url);
  }
}
