<?php
header("Content-Type: application/json; charset=UTF-8");

$conexion = new mysqli("localhost", "root", "", "api_tienda");

if ($conexion->connect_error) {
    die("Error en la conexión: " . $conexion->connect_error);
}

$sql = "SELECT * FROM productos";
$resultado = $conexion->query($sql);

$productos = [];

if ($resultado->num_rows > 0) {
    while ($fila = $resultado->fetch_assoc()) {
        $productos[] = $fila;
    }
}


echo json_encode(["status" => "success", "data" => $productos]);

$conexion->close();
?>