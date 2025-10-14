import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import baserUrl from 'src/app/core/models/helper';
import { API_ENDPOINTS } from 'src/app/core/constants/api-endpoints';
import { SalidaValidator } from 'src/app/core/validator/salida.validator';

@Injectable({
  providedIn: 'root'
})
export class SalidaService {
  constructor(private http: HttpClient) {}

  /** ========================
   *  LISTAR TODAS LAS SALIDAS
   * ======================== */
  listarSalidas(): Observable<any[]> {
    return this.http.get<any[]>(`${baserUrl}${API_ENDPOINTS.salidas.base}`);
  }

  /** ================================
   *  CREAR SALIDA CON DETALLES
   * ================================ */
  crearSalidaConDetalles(listaDetalleSalida: any[]): Observable<any> {
    if (!SalidaValidator.esListaValida(listaDetalleSalida)) {
      throw new Error('La lista de detalles de salida no es válida');
    }
    return this.http.post(`${baserUrl}${API_ENDPOINTS.salidas.base}`, listaDetalleSalida);
  }

  /** ========================
   *  ACTUALIZAR DETALLE SALIDA
   * ======================== */
  actualizarDetalleSalida(detalleSalidaId: number, detalleSalida: any): Observable<any> {
    if (!SalidaValidator.esDetalleValido(detalleSalida)) {
      throw new Error('Los datos del detalle de salida son inválidos');
    }
    const url = `${baserUrl}${API_ENDPOINTS.salidas.base}/${detalleSalidaId}`;
    return this.http.put(url, detalleSalida);
  }

  /** ========================
   *  OBTENER SALIDA POR ID
   * ======================== */
  obtenerSalidaPorId(detalleSalidaId: number): Observable<any> {
    return this.http.get(`${baserUrl}${API_ENDPOINTS.salidas.base}/${detalleSalidaId}`);
  }
}
