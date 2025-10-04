export interface Reclamo {
  reclamoId: number;
  asunto: string;
  usuario: {
    nombre: string;
    email: string;
  };
  mensajeOriginal?: string;
}

export interface Usuario {
  id: number;
  username?: string;
  email?: string;
}

export interface Reclamos {
  asunto: string;
  usuario: Usuario;
}
