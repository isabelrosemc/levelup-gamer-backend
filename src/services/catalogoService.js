import api from './api';

// Normaliza un producto que viene de GET /api/productos
function normalizarProducto(p) {
  return {
    ...p,
    categoriaNombre: typeof p.categoria === 'object'
      ? p.categoria?.nombre ?? ''
      : p.categoria ?? '',
    categoriaId: typeof p.categoria === 'object'
      ? p.categoria?.id ?? null
      : p.categoriaId ?? null,
  };
}

export async function listarCategorias() {
  const { data } = await api.get('/categorias');
  return data;
}

export async function listarProductos() {
  const { data } = await api.get('/productos');
  return Array.isArray(data) ? data.map(normalizarProducto) : [];
}

export async function obtenerProductoPorCodigo(codigo) {
  const { data } = await api.get(`/productos/${codigo}`);
  return normalizarProducto(data);
}

export async function crearProducto(formData) {
  const payload = {
    codigo: formData.codigo?.trim(),
    nombre: formData.nombre?.trim(),
    descripcion: formData.descripcion ?? '',
    precio: Number(formData.precio),
    stock: Number(formData.stock),
    stockCritico: Number(formData.stockCritico ?? 0),
    descuentoOferta: Number(formData.descuentoOferta ?? 0),
    rating: Number(formData.rating ?? 5),
    imagen: formData.imagen ?? '',
    categoriaId: Number(formData.categoriaId),
  };
  const { data } = await api.post('/productos', payload);
  return normalizarProducto(data);
}

export async function actualizarProducto(codigo, formData) {
  const payload = {
    codigo: formData.codigo?.trim() || codigo,
    nombre: formData.nombre?.trim(),
    descripcion: formData.descripcion ?? '',
    precio: Number(formData.precio),
    stock: Number(formData.stock),
    stockCritico: Number(formData.stockCritico ?? 0),
    descuentoOferta: Number(formData.descuentoOferta ?? 0),
    rating: Number(formData.rating ?? 5),
    imagen: formData.imagen ?? '',
    categoriaId: Number(formData.categoriaId),
  };
  const { data } = await api.put(`/productos/${codigo}`, payload);
  return normalizarProducto(data);
}

export async function eliminarProducto(codigo) {
  await api.delete(`/productos/${codigo}`);
}