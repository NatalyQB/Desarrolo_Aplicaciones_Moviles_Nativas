<?php
header("Content-Type: application/json; charset=UTF-8");

$conexion = new mysqli("localhost", "root", "", "api_tienda");

error_log("Datos recibidos: " . print_r($_POST, true));

$codigo = isset($_POST['codigo']) ? $conexion->real_escape_string($_POST['codigo']) : null;
$nombre = isset($_POST['nombre']) ? $conexion->real_escape_string($_POST['nombre']) : null;
$precio = isset($_POST['precio']) ? $conexion->real_escape_string($_POST['precio']) : null;


if ($codigo && $nombre && $precio) {

    $sql = "INSERT INTO productos (codigo, nombre, precio) VALUES ('$codigo', '$nombre', '$precio')";

    if ($conexion->query($sql) === TRUE) {
        echo json_encode(["status" => "success", "message" => "Producto agregado correctamente"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Error al agregar producto: " . $conexion->error]);
    }
} else {
    echo json_encode(["status" => "error", "message" => "Datos incompletos"]);
}


$conexion->close();
?>