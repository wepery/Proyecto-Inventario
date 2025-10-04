import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import baserUrl from 'src/app/core/models/helper';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class UsuarioService {


  constructor(private http: HttpClient) { }

  //listar usuario admin
  public obtenerAdminUsuarioRoles(): Observable<any> {
    return this.http.get(`${baserUrl}/usuarios/admin/activadas`);
  }
  //listar usuario normal
  public obtenerNormalUsuarioRoles(): Observable<any> {
    return this.http.get(`${baserUrl}/usuarios/normal/activadas`);
  }
  //listar usuario admin desactivados
  public obtenerAdminUsuarioRolesDesactivados(): Observable<any> {
    return this.http.get(`${baserUrl}/usuarios/admin/desactivadas`);
  }
  //listar usuario normal desactivados
  public obtenerNormalUsuarioRolesDesactivados(): Observable<any> {
    return this.http.get(`${baserUrl}/usuarios/normal/desactivadas`);
  }

  //registrar usuarios admin
  public añadirUsuarioAdmin(user: any) {
    return this.http.post(`${baserUrl}/usuarios/admin/`, user);
  }

  public desactivarUsuario1(usuarioRolId: any): Observable<any> {
    return this.http.post(`${baserUrl}/usuarios/desactivar/${usuarioRolId}`, {});
  }
  desactivarUsuario(usuarioRolId: any): Observable<string> {
    const url = `${baserUrl}/usuarios/desactivar/${usuarioRolId}`;
    return this.http.put(url, null, { responseType: 'text' });
  }

  activarUsuario(usuarioRolId: any): Observable<string> {
    const url = `${baserUrl}/usuarios/activar/${usuarioRolId}`;
    return this.http.put(url, null, { responseType: 'text' });
  }


  obtenerUsuarioPorId(usuarioRolId: any): Observable<any> {
    return this.http.get(`${baserUrl}/usuarios/listarId/${usuarioRolId}`);
  }
  actualizarUsuario(id: number, usuarioActualizado: any): Observable<any> {
    const url = `${baserUrl}/usuarios/${id}`;
    return this.http.put<any>(url, usuarioActualizado);
  }
   añadirUsuario(user:any){
      return this.http.post(`${baserUrl}/usuarios/guardar-admin`,user);
    }
   añadirNormal(user:any){
      return this.http.post(`${baserUrl}/usuarios/guardar-normal`,user);
    }
}
