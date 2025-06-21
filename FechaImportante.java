import java.time.LocalDate;

public class FechaImportante {
    private LocalDate fecha;
    private String descripcion; // "Cumpleaños", "Pago", etc.

    public FechaImportante(LocalDate fecha, String descripcion) {
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion + ": " + fecha.toString();
    }
}
