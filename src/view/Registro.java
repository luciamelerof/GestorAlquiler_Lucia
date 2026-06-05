package view;

import dao.impl.UsuarioDAOImpl;
import model.Cliente;
import model.Empleado;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Registro extends JFrame {

    // ── Misma paleta que Login ───────────────────────────────────
    private static final Color COLOR_FONDO        = new Color(15, 23, 42);
    private static final Color COLOR_ACENTO       = new Color(249, 115, 22);
    private static final Color COLOR_ACENTO_HOVER = new Color(234, 88, 12);
    private static final Color COLOR_TEXTO        = new Color(248, 250, 252);
    private static final Color COLOR_SUBTEXTO     = new Color(148, 163, 184);
    private static final Color COLOR_INPUT_BG     = new Color(51, 65, 85);
    private static final Color COLOR_INPUT_BORDER = new Color(71, 85, 105);

    // ── Campos comunes ───────────────────────────────────────────
    private JTextField     txtUsername, txtEmail, txtNombre, txtApellidos, txtDni;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbRol;

    // ── Campos cliente ───────────────────────────────────────────
    private JTextField txtTelefono, txtDireccion, txtCarnet, txtFechaNac;
    private JPanel     panelCliente;

    // ── Campos empleado ──────────────────────────────────────────
    private JComboBox<String> cmbCargo;
    private JTextField        txtSalario, txtFechaContrato;
    private JPanel            panelEmpleado;

    private JButton btnRegistrar;
    private JPanel  panelDinamico;

    public Registro() {
        initUI();
    }

    private void initUI() {
        setTitle("RentACar — Registro");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 500, 700, 20, 20));

        JPanel root = new JPanel(new BorderLayout()) {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, COLOR_FONDO, 0, getHeight(), new Color(15, 23, 60));
                g2.setPaint(gp);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
            }
        };
        root.setOpaque(false);

        // Barra superior
        root.add(crearBarra(), BorderLayout.NORTH);

        // Scroll con formulario
        JPanel formulario = crearFormulario();
        JScrollPane scroll = new JScrollPane(formulario);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        root.add(scroll, BorderLayout.CENTER);

        setContentPane(root);
        hacerArrastrable(root);
    }

    private JPanel crearFormulario() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(10, 45, 30, 45));

        // Título
        JLabel titulo = new JLabel("Crear cuenta", SwingConstants.CENTER);
        titulo.setFont(new Font("Georgia", Font.BOLD, 24));
        titulo.setForeground(COLOR_TEXTO);
        titulo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel sub = new JLabel("Rellena los datos para registrarte", SwingConstants.CENTER);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        sub.setForeground(COLOR_SUBTEXTO);
        sub.setAlignmentX(CENTER_ALIGNMENT);

        // ── Campos comunes ───────────────────────────────────────
        txtUsername  = campo(); txtEmail    = campo();
        txtNombre    = campo(); txtApellidos= campo();
        txtDni       = campo(); txtPassword = new JPasswordField();
        estilizarCampo(txtPassword);

        // Rol
        cmbRol = new JComboBox<>(new String[]{"cliente", "empleado"});
        estilizarCombo(cmbRol);

        // ── Panel dinámico ───────────────────────────────────────
        panelDinamico = new JPanel();
        panelDinamico.setLayout(new BoxLayout(panelDinamico, BoxLayout.Y_AXIS));
        panelDinamico.setOpaque(false);

        // Sub-panel cliente
        panelCliente = new JPanel();
        panelCliente.setLayout(new BoxLayout(panelCliente, BoxLayout.Y_AXIS));
        panelCliente.setOpaque(false);
        txtTelefono  = campo(); txtDireccion = campo();
        txtCarnet    = campo();
        txtFechaNac  = campo(); txtFechaNac.setToolTipText("Formato: AAAA-MM-DD");
        panelCliente.add(etiquetaCampo("Teléfono", txtTelefono));
        panelCliente.add(Box.createVerticalStrut(10));
        panelCliente.add(etiquetaCampo("Dirección", txtDireccion));
        panelCliente.add(Box.createVerticalStrut(10));
        panelCliente.add(etiquetaCampo("Carnet de conducir", txtCarnet));
        panelCliente.add(Box.createVerticalStrut(10));
        panelCliente.add(etiquetaCampo("Fecha nacimiento (AAAA-MM-DD)", txtFechaNac));

        // Sub-panel empleado
        panelEmpleado = new JPanel();
        panelEmpleado.setLayout(new BoxLayout(panelEmpleado, BoxLayout.Y_AXIS));
        panelEmpleado.setOpaque(false);
        cmbCargo = new JComboBox<>(new String[]{"AGENTE","GERENTE","MECANICO","ADMINISTRATIVO"});
        estilizarCombo(cmbCargo);
        txtSalario       = campo();
        txtFechaContrato = campo(); txtFechaContrato.setToolTipText("Formato: AAAA-MM-DD");
        panelEmpleado.add(etiquetaCampo("Cargo", cmbCargo));
        panelEmpleado.add(Box.createVerticalStrut(10));
        panelEmpleado.add(etiquetaCampo("Salario (€)", txtSalario));
        panelEmpleado.add(Box.createVerticalStrut(10));
        panelEmpleado.add(etiquetaCampo("Fecha contrato (AAAA-MM-DD)", txtFechaContrato));

        // Mostrar cliente por defecto
        panelDinamico.add(panelCliente);

        cmbRol.addActionListener(e -> actualizarPanelDinamico());

        // Botón registrar
        btnRegistrar = crearBoton("CREAR CUENTA");
        btnRegistrar.addActionListener(e -> registrar());

        // Enlace volver
        JLabel lblVolver = new JLabel("← Volver al login", SwingConstants.CENTER);
        lblVolver.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblVolver.setForeground(COLOR_ACENTO);
        lblVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblVolver.setAlignmentX(CENTER_ALIGNMENT);
        lblVolver.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });

        // Ensamblar
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(4));
        panel.add(sub);
        panel.add(Box.createVerticalStrut(20));
        panel.add(etiquetaCampo("Usuario",     txtUsername));  panel.add(Box.createVerticalStrut(10));
        panel.add(etiquetaCampo("Contraseña",  txtPassword));  panel.add(Box.createVerticalStrut(10));
        panel.add(etiquetaCampo("Email",        txtEmail));    panel.add(Box.createVerticalStrut(10));
        panel.add(etiquetaCampo("Nombre",       txtNombre));   panel.add(Box.createVerticalStrut(10));
        panel.add(etiquetaCampo("Apellidos",    txtApellidos));panel.add(Box.createVerticalStrut(10));
        panel.add(etiquetaCampo("DNI",          txtDni));      panel.add(Box.createVerticalStrut(10));
        panel.add(etiquetaCampo("Rol",          cmbRol));      panel.add(Box.createVerticalStrut(14));
        panel.add(panelDinamico);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnRegistrar);
        panel.add(Box.createVerticalStrut(12));
        panel.add(lblVolver);

        return panel;
    }

    // ── Actualiza campos según rol seleccionado ──────────────────
    private void actualizarPanelDinamico() {
        panelDinamico.removeAll();
        String rol = (String) cmbRol.getSelectedItem();
        if ("cliente".equals(rol)) {
            panelDinamico.add(panelCliente);
        } else {
            panelDinamico.add(panelEmpleado);
        }
        panelDinamico.revalidate();
        panelDinamico.repaint();
    }

    // ── Lógica de registro con transacción atómica ───────────────
    private void registrar() {
        // Validaciones básicas
        if (txtUsername.getText().trim().isEmpty() ||
                new String(txtPassword.getPassword()).isEmpty() ||
                txtEmail.getText().trim().isEmpty() ||
                txtNombre.getText().trim().isEmpty() ||
                txtApellidos.getText().trim().isEmpty() ||
                txtDni.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Rellena todos los campos obligatorios.",
                    "Campos vacíos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        UsuarioDAOImpl dao = new UsuarioDAOImpl();
        String rol = (String) cmbRol.getSelectedItem();

        try {
            if ("cliente".equals(rol)) {
                Cliente c = new Cliente();
                c.setUsername(txtUsername.getText().trim());
                c.setPassword(new String(txtPassword.getPassword()));
                c.setEmail(txtEmail.getText().trim());
                c.setNombre(txtNombre.getText().trim());
                c.setApellidos(txtApellidos.getText().trim());
                c.setDni(txtDni.getText().trim());
                c.setTelefono(txtTelefono.getText().trim());
                c.setDireccion(txtDireccion.getText().trim());
                c.setCarnetConducir(txtCarnet.getText().trim());
                if (!txtFechaNac.getText().trim().isEmpty()) {
                    c.setFechaNacimiento(LocalDate.parse(txtFechaNac.getText().trim()));
                }
                dao.registrarCliente(c);

            } else {
                Empleado e = new Empleado();
                e.setUsername(txtUsername.getText().trim());
                e.setPassword(new String(txtPassword.getPassword()));
                e.setEmail(txtEmail.getText().trim());
                e.setNombre(txtNombre.getText().trim());
                e.setApellidos(txtApellidos.getText().trim());
                e.setDni(txtDni.getText().trim());
                e.setCargo(Empleado.Cargo.valueOf((String) cmbCargo.getSelectedItem()));
                if (!txtSalario.getText().trim().isEmpty()) {
                    e.setSalario(new BigDecimal(txtSalario.getText().trim()));
                }
                if (!txtFechaContrato.getText().trim().isEmpty()) {
                    e.setFechaContrato(LocalDate.parse(txtFechaContrato.getText().trim()));
                }
                dao.registrarEmpleado(e);
            }

            JOptionPane.showMessageDialog(this,
                    "¡Cuenta creada correctamente! Ya puedes iniciar sesión.",
                    "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
            new Login().setVisible(true);
            dispose();

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this,
                    "Formato de fecha incorrecto. Usa AAAA-MM-DD.",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al registrar: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ── Helpers de UI ────────────────────────────────────────────
    private JTextField campo() {
        JTextField tf = new JTextField();
        estilizarCampo(tf);
        return tf;
    }

    private void estilizarCampo(JComponent c) {
        c.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        c.setForeground(COLOR_TEXTO);
        c.setBackground(COLOR_INPUT_BG);
        c.setCaretColor(COLOR_ACENTO);
        c.setBorder(BorderFactory.createCompoundBorder(
                new Login.RoundBorder(COLOR_INPUT_BORDER, 8),
                new EmptyBorder(7, 10, 7, 10)
        ));
    }

    private void estilizarCombo(JComboBox<?> combo) {
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        combo.setForeground(COLOR_TEXTO);
        combo.setBackground(COLOR_INPUT_BG);
        combo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
    }

    private JPanel etiquetaCampo(String label, JComponent campo) {
        JPanel p = new JPanel(new BorderLayout(0, 4));
        p.setOpaque(false);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(COLOR_SUBTEXTO);
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        p.add(lbl,   BorderLayout.NORTH);
        p.add(campo, BorderLayout.CENTER);
        return p;
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto) {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? COLOR_ACENTO_HOVER : COLOR_ACENTO);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                g2.drawString(getText(), x, y);
                g2.dispose();
            }
        };
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JPanel crearBarra() {
        JPanel barra = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        barra.setOpaque(false);
        JButton btnX = new JButton("✕") {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color c = new Color(239, 68, 68);
                g2.setColor(getModel().isRollover() ? c : new Color(c.getRed(), c.getGreen(), c.getBlue(), 120));
                g2.fillOval(0, 0, getWidth(), getHeight());
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString("✕", (getWidth() - fm.stringWidth("✕")) / 2,
                        (getHeight() + fm.getAscent() - fm.getDescent()) / 2);
                g2.dispose();
            }
        };
        btnX.setPreferredSize(new Dimension(16, 16));
        btnX.setBorderPainted(false);
        btnX.setContentAreaFilled(false);
        btnX.setFocusPainted(false);
        btnX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnX.addActionListener(e -> dispose());
        barra.add(btnX);
        return barra;
    }

    private void hacerArrastrable(JPanel root) {
        final Point[] offset = {null};
        root.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) { offset[0] = e.getPoint(); }
        });
        root.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (offset[0] != null) {
                    Point loc = getLocation();
                    setLocation(loc.x + e.getX() - offset[0].x,
                                loc.y + e.getY() - offset[0].y);
                }
            }
        });
    }
}