<?php
header("Content-Type: application/json");

$servername = "localhost";
$username = "root";
$password = ""; 
$dbname = "api_tienda";

$conn = new mysqli($servername, $username, $password, $dbname);
if ($conn->connect_error) {
    die(json_encode(["status" => "error", "message" => "Conexión fallida: " . $conn->connect_error]));
}

$result = $conn->query("SELECT * FROM terceros");

$terceros = [];
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $terceros[] = $row;
    }
}

echo json_encode(["status" => "success", "data" => $terceros]);

$conn->close();
