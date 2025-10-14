import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import baserUrl from 'src/app/core/models/helper';
import { API_ENDPOINTS } from 'src/app/core/constants/api-endpoints';
import { ReclamoValidator } from 'src/app/core/validator/reclamo.validator';

@Injectable({
  providedIn: 'root'
})
export class ReclamoService {
  constructor(private http: HttpClient) {}

  /** ========================
   *  LISTAR RECLAMOS ACTIVOS
   * ======================== */
  listarReclamosActivos(): Observable<any[]> {
    return this.http.get<any[]>(`${baserUrl}${API_ENDPOINTS.reclamos.activados}`);
  }

  /** ========================
   *  LISTAR RECLAMOS DESACTIVADOS
   * ======================== */
  listarReclamosDesactivados(): Observable<any[]> {
    return this.http.get<any[]>(`${baserUrl}${API_ENDPOINTS.reclamos.desactivados}`);
  }

  /** ========================
   *  AGREGAR NUEVO RECLAMO
   * ======================== */
  agregarReclamo(reclamo: any): Observable<any> {
    if (!ReclamoValidator.esReclamoValido(reclamo)) {
      throw new Error('Datos de reclamo inválidos');
    }
    return this.http.post(`${baserUrl}${API_ENDPOINTS.reclamos.base}`, reclamo);
  }

  /** ========================
   *  OBTENER RECLAMO POR ID
   * ======================== */
  obtenerReclamoPorId(reclamoId: number): Observable<any> {
    return this.http.get(`${baserUrl}${API_ENDPOINTS.reclamos.base}/${reclamoId}`);
  }

  /** ========================
   *  ENVIAR DISCULPAS
   * ======================== */
  enviarDisculpas(reclamoId: number, mensaje: string): Observable<string> {
    if (!ReclamoValidator.esMensajeValido(mensaje)) {
      throw new Error('El mensaje de disculpas no es válido');
    }
    const url = `${baserUrl}${API_ENDPOINTS.reclamos.enviarDisculpas}/${reclamoId}`;
    return this.http.post<string>(url, mensaje);
  }
}
