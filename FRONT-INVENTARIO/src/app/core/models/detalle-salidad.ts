export interface DetalleSalida {
  producto: {
    productoId: string;
  };
  descripcion: string;
  cantidad: number;
  salida: {
    fechaSalida: string;
  };
  usuario: {
    id: string;
  };
}
