export interface DetalleEntrada {
  descripcion: string;
  cantidad: number | null;
  producto: {
    productoId: string;
    nombre?: string; // opcional si quieres almacenar el nombre
  };
  usuario: {
    id: string;
    username?: string; // opcional si quieres mostrar el nombre
  };
  entrada: {
    fechaEntrada: string;
  };
}
