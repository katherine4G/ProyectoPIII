package Model;
import java.sql.Timestamp;
/*
 * CREATE TABLE RegistrationRequest (
 *    requestId INT AUTO_INCREMENT PRIMARY KEY,
 *    id_user VARCHAR(50),                             -- ID del usuario solicitante
 *    request_type ENUM('Profesor', 'Estudiante') NOT NULL,  -- Tipo de solicitud
 *    status ENUM('Pendiente', 'Aceptada', 'Denegada') DEFAULT 'Pendiente', -- Estado de la solicitud
 *    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Fecha de la solicitud
 *    FOREIGN KEY (id_user) REFERENCES User(id_user) ON DELETE CASCADE
 * );
 * 
 * @author kathe
 */
    enum RequestType {
       PROFESOR, ESTUDIANTE
    }

    enum RequestStatus {
       PENDIENTE, ACEPTADA, DENEGADA
    }

public class RegistrationRequest {
    private int requestId;
    private User id_user;
    private RequestType request_type; 
   // private String request_type; 
   // private String status; 
    private RequestStatus status;
    private Timestamp requested_at; 

    public RegistrationRequest(int requestId, User id_user, RequestType request_type, RequestStatus status, Timestamp requested_at) {
        this.requestId = requestId;
        this.id_user = id_user;
        this.request_type = request_type;
        this.status = status;
        this.requested_at = requested_at;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public RequestType getRequest_type() {
        return request_type;
    }

    public void setRequest_type(RequestType request_type) {
        this.request_type = request_type;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Timestamp getRequested_at() {
        return requested_at;
    }

    public void setRequested_at(Timestamp requested_at) {
        this.requested_at = requested_at;
    }


    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public User getIdUser() {
        return id_user;
    }

    public void setIdUser(User id_user) {
        this.id_user = id_user;
    }

    public Timestamp getRequestedAt() {
        return requested_at;
    }

    public void setRequestedAt(Timestamp requested_at) {
        this.requested_at = requested_at;
    }

    @Override
    public String toString() {
        return "RegistrationRequestAPI{" +
                "requestId=" + requestId +
                ", id_user=" + id_user +
                ", request_type='" + request_type + '\'' +
                ", status='" + status + '\'' +
                ", requested_at=" + requested_at +
                '}';
    }
}
