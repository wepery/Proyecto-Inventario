export class SalidaValidator {
  /** Valida si la lista de detalles es válida */
  static esListaValida(lista: any[]): boolean {
    return Array.isArray(lista) && lista.length > 0 && lista.every(item => this.esDetalleValido(item));
  }

  /** Valida un solo detalle de salida */
  static esDetalleValido(detalle: any): boolean {
    if (!detalle) return false;

    const { productoId, cantidad, fechaSalida } = detalle;

    const idValido = Number.isInteger(productoId) && productoId > 0;
    const cantidadValida = typeof cantidad === 'number' && cantidad > 0;
    const fechaValida = typeof fechaSalida === 'string' && fechaSalida.trim().length > 0;

    return idValido && cantidadValida && fechaValida;
  }
}
