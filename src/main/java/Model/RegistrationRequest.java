/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kathe
 */
public class RegistrationRequest {
    private int requestId;
    private int userId;  // ID del usuario solicitante
    private String requestType;  // Tipo de solicitud ('Profesor' o 'Estudiante')
    private String status;  // Estado de la solicitud
    private String requestedAt;  // Fecha en la que se realizó la solicitud

    // Constructor vacío y con parámetros
    public RegistrationRequest() {}

    public RegistrationRequest(int requestId, int userId, String requestType, String status, String requestedAt) {
        this.requestId = requestId;
        this.userId = userId;
        this.requestType = requestType;
        this.status = status;
        this.requestedAt = requestedAt;
    }

    // Getters y Setters
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(String requestedAt) {
        this.requestedAt = requestedAt;
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "requestId=" + requestId +
                ", userId=" + userId +
                ", requestType='" + requestType + '\'' +
                ", status='" + status + '\'' +
                ", requestedAt='" + requestedAt + '\'' +
                '}';
    }
}
