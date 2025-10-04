# Inventario de Compras y Ventas

## Descripción del Proyecto
Este proyecto es un sistema de **Inventario de Compras y Ventas** desarrollado con **Spring Boot** y **Angular**. El sistema permite gestionar productos, proveedores, compras, ventas y usuarios de manera eficiente. Está diseñado con **dos roles principales**: 

- **Administrador**: Responsable de la gestión completa del sistema, incluyendo usuarios, proveedores y productos.  
- **Operador**: Responsable de ejecutar operaciones relacionadas con compras, ventas y control de inventario según su rol.

Cada usuario solo puede acceder y realizar las acciones permitidas según su rol, garantizando la seguridad y el control de las operaciones.

---

## Objetivo
El objetivo principal del sistema es **optimizar la gestión de inventario** y controlar las compras y ventas de manera estructurada y segura, proporcionando herramientas para la toma de decisiones eficientes.

---

## Módulos del Sistema

1. **Usuarios**
   - Gestión de usuarios.
   - Asignación de roles y permisos.
   - Control de acceso basado en roles.

2. **Proveedores**
   - Registro y actualización de proveedores.
   - Seguimiento de información de contacto y productos suministrados.

3. **Productos**
   - Registro y actualización de productos.
   - Control de stock y precios.
   - Gestión de categorías de productos.

4. **Compras**
   - Registro de compras a proveedores.
   - Control de entradas de productos al inventario.
   - Historial de compras.

5. **Ventas**
   - Registro de ventas a clientes.
   - Generación de facturas.
   - Control de stock disponible después de cada venta.

6. **Reportes**
   - Reportes de ventas, compras y stock.
   - Estadísticas por periodo de tiempo.
   
---

## Responsabilidades por Rol

- **Administrador**
  - Crear, actualizar y eliminar usuarios.
  - Gestionar proveedores y productos.
  - Supervisar compras y ventas.
  - Generar reportes completos del sistema.

- **Operador**
  - Registrar compras y ventas.
  - Actualizar inventario según las operaciones realizadas.
  - Consultar productos y stock disponible.
  
---

## Tecnologías y Dependencias Utilizadas

- **Backend**
  - Spring Boot
  - Spring Data JPA
  - MySQL
  - Maven
  - Hibernate

- **Frontend**
  - Angular
  - TypeScript
  - HTML5 / CSS3
  - Angular Material
  - Font Awesome

- **Otras herramientas**
  - JSP (para algunas vistas internas)
  - Git para control de versiones
  - Postman para pruebas de API
  - Node.js para Angular CLI

---

## Funcionalidades Clave

- Control de inventario con entradas y salidas de productos.
- Registro de compras y ventas con historial.
- Gestión de usuarios con roles diferenciados.
- Reportes de stock, ventas y compras.
- Interfaz responsiva y moderna usando Angular Material.

---


   ```bash
   git clone https://github.com/tu-usuario/inventario-compras-ventas.git
