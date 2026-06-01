import view.Login;

import javax.swing.*;

/**
 * Punto de entrada de la aplicación RentACar.
 * Aplica el Look & Feel del sistema y lanza el Login.
 */
public class Main {

    public static void main(String[] args) {
        // Look & Feel nativo del sistema operativo
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Si falla, usa el L&F por defecto de Java
            System.err.println("No se pudo aplicar el Look & Feel: " + e.getMessage());
        }

        // Lanzar en el hilo de Swing (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}