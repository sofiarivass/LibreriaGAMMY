<h1>TRABAJO PRACTICO N°1</h1>
<h2>
  <p>Grupo 4</p>
  <p>Tema: GAMMY(Librería) - Sistema de Gestión de Libros y Ventas</p>
  <p>Miembros:</p>
  <ul>
    <li>Aponte Bobadilla Aldeir Alexander</li>
    <li>Caamaño Pagniez Juan</li>
    <li>Dominguez Mariano Augusto</li>
    <li>Rivas Sofía Denisse</li>
  </ul>
</h2>
<h3>Descripcion:</h3>
<p>Desarrollar un sistema completo para gestionar las existencias y ventas en la librería GAMMY. Este sistema permitirá registrar libros con categorías y cantidades disponibles, ajustar precios, ingresar ventas y generar informes de ventas y estadísticas de popularidad.</p>
<h3>Enfoque:</h3>
<p>El enfoque principal estará en la creación de una interfaz que facilite a los empleados de GAMMY registrar y gestionar libros, ajustar precios y procesar ventas de manera eficiente. La plataforma generará informes de ventas diarios y proporciona estadísticas detalladas sobre la popularidad de los libros.</p>

<h3>Diagrama de entidad/relacion</h3>
<img src=https://github.com/sofiarivass/LibreriaGAMMY/blob/4d983b940481e40c21bf2768405100ff846c0656/entidad-relacion.png>

<h3>Diagra de caso de uso</h3>
<img src=https://github.com/sofiarivass/LibreriaGAMMY/blob/4d983b940481e40c21bf2768405100ff846c0656/casouso-1.png>
<img src=https://github.com/sofiarivass/LibreriaGAMMY/blob/4d983b940481e40c21bf2768405100ff846c0656/casouso-2.png>
<img src=https://github.com/sofiarivass/LibreriaGAMMY/blob/4d983b940481e40c21bf2768405100ff846c0656/casouso-3.png>

<h3>Diagrama de clases</h3>
<p><strong>CLASES:</strong></p>

<p>USUARIO:</p>
<ul>
  <li>contrasenia</li>
  <li>usuario</li>
  <li>tipo (vendedor, vendedor internacional o admin)</li>
  <li>estado (baja/alta)</li>
</ul>

<p>ADMIN extends USUARIO:</p>
<ul>
  <li>listaEmpleados<Usuario></li>
</ul>
    
<p>VENDEDOR extends USUARIO:</p>
<ul>
  <li> listaRegistros<VENTA></li>
  <li>nombre</li>
</ul>

<p>VENDEDORINTERNACIONAL extends USUARIO:</p>
<ul>
  <li>listaRegistros<VENTA></li>
  <li>nombre</li>
</ul>

<p>VENTA:</p>
<ul>
  <li>carrito<LIBRO> (se reinicia en cada venta)</li>
  <li>numero</li>
  <li>cliente</li>
  <li>cantidad</li>
  <li>precio</li>
  <li>fechaVenta</li>
  <li>tipoVenta</li>
  <li>metodoPago</li>
  <li>moneda</li>
  <li>descuento</li>
  <li>estado (concretada, anulada)</li>
</ul>

<p>EXPORTACION extends VENTA:</p>
<ul>
  <li>origen</li>
  <li>destino</li>
  <li>estadoEnvio (pendiente, en transito, entregado)</li>
</ul>

<p>CLIENTE:</p>
<ul>
  <li>ID</li>
  <li>nombre</li>
  <li>telefono</li>
  <li>email</li>
  <li>listaProductos<LIBRO> (puede servir como registro de todas las ventas para brindar recomendaciones)</li>
</ul>

<p>LIBRO:</p>
<ul>
  <li>ISBN</li>
  <li>titulo</li>
  <li>autor</li>
  <li>editorial</li>
  <li>anioPublicacion</li>
  <li>genero</li>
  <li>idioma</li>
  <li>publicoObjetivo</li>
  <li>numPaginas</li>
  <li>firmado (boolean)</li>
  <li>edicion</li>
  <li>edicionEspecial (boolean)</li>
  <li>materialTapa (dura o blanda)</li>
  <li>saga (boolean)</li>
  <li>precio</li>
  <li>stock</li>
</ul>

<p>MENÚ PRINCIPAL:</p>
<ul>
  <li>pide usuario</li>
  <li>pide contrasenia</li>
</ul>

<p>MENÚ ADMIN:</p>
<ul>
  <li>Gestionar empleados</li>
  <ul>
    <li>Crear empleados</li>
    <li>Dar de baja empleados</li>
    <li>Ver empleados</li>
  </ul>
  <li>Estadísticas de venta y popularidad</li>
  <ul>
    <li>Filtrar por saga</li>
    <li>Filtrar por autor</li>
    <li>Filtrar por adaptaciones a películas o series</li>
  </ul>
  <li>Gestionar descuentos</li>
  <ul>
    <li>agregar descuento</li>
    <li>editar descuento</li>
    <ul>
      <li>por saga completa</li>
      <li>por autor (5 o más libros)</li>
      <li>por libro en otro idioma</li>
    </ul>
    <li>eliminar descuento</li>
    <ul>
      <li>por saga completa</li>
      <li>por autor (5 o más libros)</li>
      <li>por libro en otro idioma</li>
    </ul>
  </ul>
</ul>

<p>MENÚ VENDEDOR INTERNACIONAL:</p>
<ul>
  <li>gestionar clientes</li>
  <ul>
    <li>Modificar o eliminar datos de un cliente</li>
  </ul>
  <li>gestionar exportaciones</li>
  <ul>
    <li>realizar exportación</li>
    <ul>
      <li>ingresar cuit del cliente (si encuentra coincidencia, sigue, sino pide los demás datos)</li>
      <li>seleccionar libros</li>
      <li>verificar cantidad mínima</li>
      <li>aplicar descuentos automáticamente (si aplica)</li>
      <li>elegir método de pago</li>
      <li>emitir comprobante</li>
    </ul>
    <li>modificar exportación</li>
    <li>Anular exportación</li>
    <li>todas las exportaciones</li>
  </ul>
  <li>seguimiento de envíos</li>
  <li>gestionar inventario</li>
  <ul>
    <li>cargar productos</li>
    <li>editar productos</li>
    <li>ver productos</li>
    <li>eliminar productos</li>
  </ul>
</ul>

<p>MENÚ VENDEDOR</p>
<ul>
  <li>gestionar clientes</li>
  <ul>
    <li>Modificar o eliminar datos de un cliente</li>
  </ul>
  <li>gestionar ventas</li>
  <ul>
    <li>realizar venta</li>
    <ul>
      <li>ingresar dni del cliente (si encuentra coincidencia, sigue, sino pide los demás datos)</li>
      <li>seleccionar libro</li>
      <li>aplicar descuentos automáticamente (si aplica)</li>
      <li>elegir método de pago</li>
      <li>emitir comprobante</li>
    </ul>
    <li>Anular venta</li>
    <li>modificar Venta</li>
    <li>todas las ventas</li>
  </ul>
</ul>
