export class ReportesValidator {
  /** Verifica si la ruta del endpoint es una cadena válida */
  static esRutaValida(endpoint: string): boolean {
    return typeof endpoint === 'string' && endpoint.trim().length > 0 && endpoint.startsWith('/');
  }
}
