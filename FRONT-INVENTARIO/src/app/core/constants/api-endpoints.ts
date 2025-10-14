export const API_ENDPOINTS = {
  usuarios: {
    adminActivos: '/usuarios/admin/activadas',
    normalActivos: '/usuarios/normal/activadas',
    adminDesactivados: '/usuarios/admin/desactivadas',
    normalDesactivados: '/usuarios/normal/desactivadas',
    registrarAdmin: '/usuarios/guardar-admin',
    registrarNormal: '/usuarios/guardar-normal',
    activar: '/usuarios/activar',
    desactivar: '/usuarios/desactivar',
    obtenerPorId: '/usuarios/listarId',
    actualizar: '/usuarios'
  },

  salidas: {
    base: '/salidas'
  },
    reportes: {
    salidas: '/pdf/generar-salidas',
    entradas: '/pdf/generar-entradas',
    proveedores: '/pdf/generar-proveedor',
    productos: '/pdf/generar-productos',
    usuariosAdmin: '/pdf/generar-administrador',
    usuariosOperador: '/pdf/generar-operador'
  },
   reclamos: {
    base: '/reclamo',
    activados: '/reclamo/activadas',
    desactivados: '/reclamo/desactivadas',
    enviarDisculpas: '/reclamo'
  }
};
