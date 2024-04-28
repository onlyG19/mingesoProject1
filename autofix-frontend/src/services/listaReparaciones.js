const listaReparaciones = [
    { id: 1, name: "Reparaciones del Sistema de Frenos" },
    { id: 2, name: "Servicio del Sistema de Refrigeración" },
    { id: 3, name: "Reparaciones del Motor" },
    { id: 4, name: "Reparaciones de la Transmisión" },
    { id: 5, name: "Reparación del Sistema Eléctrico" },
    { id: 6, name: "Reparaciones del Sistema de Escape" },
    { id: 7, name: "Reparación de Neumáticos y Ruedas" },
    { id: 8, name: "Reparaciones de la Suspensión y la Dirección" },
    { id: 9, name: "Reparación del Sistema de Aire Acondicionado y Calefacción" },
    { id: 10, name: "Reparaciones del Sistema de Combustible" },
    { id: 11, name: "Reparación y Reemplazo del Parabrisas y Cristales" },
    // Add more types of repairs as needed
];

function getReparacionNameById(id) {
    const reparacion = listaReparaciones.find(reparacion => reparacion.id === id);
    return reparacion ? reparacion.name : null;
}

export { getReparacionNameById, listaReparaciones };