import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import baserUrl from 'src/app/core/models/helper';
import { API_ENDPOINTS } from 'src/app/core/constants/api-endpoints';
import { ReportesValidator } from 'src/app/core/validator/reportes.validator';

@Injectable({
  providedIn: 'root'
})
export class ReportesService {
  constructor(private http: HttpClient) {}

  /** ========================
   *  DESCARGAR REPORTES PDF
   * ======================== */

  descargarSalida(): Observable<Blob> {
    return this.descargarReporte(API_ENDPOINTS.reportes.salidas);
  }

  descargarEntrada(): Observable<Blob> {
    return this.descargarReporte(API_ENDPOINTS.reportes.entradas);
  }

  descargarProveedor(): Observable<Blob> {
    return this.descargarReporte(API_ENDPOINTS.reportes.proveedores);
  }

  descargarProducto(): Observable<Blob> {
    return this.descargarReporte(API_ENDPOINTS.reportes.productos);
  }

  descargarUsuarioAdmin(): Observable<Blob> {
    return this.descargarReporte(API_ENDPOINTS.reportes.usuariosAdmin);
  }

  descargarUsuarioOperador(): Observable<Blob> {
    return this.descargarReporte(API_ENDPOINTS.reportes.usuariosOperador);
  }

  /** ========================
   *  MÉTODO REUTILIZABLE
   * ======================== */
  private descargarReporte(endpoint: string): Observable<Blob> {
    if (!ReportesValidator.esRutaValida(endpoint)) {
      throw new Error('Ruta de reporte inválida');
    }
    const url = `${baserUrl}${endpoint}`;
    return this.http.get(url, { responseType: 'blob' });
  }
}
