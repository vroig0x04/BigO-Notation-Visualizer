import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class Vista extends JFrame {
    public Graphics2D g2;
    private static final int MARGEN = 70;
    private Controlador ctrl;
    private JPanel panelBotones;
    private JButton[] botones = new JButton[4];
    private static final String[] nBotones = {
            "Algoritmo n", "Algoritmo n*log n", "Algoritmo n^2", "Algoritmo n^3"
    };

    private boolean lineal_dibujado = false;
    private boolean dibujar_logaritmico = false;
    private boolean dibujar_cuadratico = false;
    private boolean dibujar_cubico = false;
    private int ancho;
    private int alto;

    private static final Color BG_DARK = new Color(18, 20, 35);
    private static final Color BG_PANEL = new Color(26, 29, 48);
    private static final Color AXIS_COLOR = new Color(180, 185, 210);
    private static final Color TEXT_COLOR = new Color(220, 225, 245);
    private static final Color[] COLORES = {
            new Color(80, 200, 255), // cian
            new Color(120, 230, 140), // verde
            new Color(255, 180, 60), // naranja
            new Color(255, 90, 130) // rosa
    };

    private PanelGrafica panelGrafica; // referencia para poder repintar

    private static final int DIBUJAR_LINEAL = 0;
    private static final int DIBUJAR_LOGARITMICO = 1;
    private static final int DIBUJAR_CUADRATICO = 2;
    private static final int DIBUJAR_CUBICO = 3;

    public Vista(Controlador controlador) {
        this.ctrl = controlador;
        this.graficaAlgoritmos();
    }

    private void initPanelBotones() {
        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panelBotones.setBackground(new Color(35, 40, 60));

        for (int i = 0; i < botones.length; i++) {
            botones[i] = new JButton(nBotones[i]);
            botones[i].setFocusPainted(false);
            botones[i].setBackground(new Color(60, 120, 200));
            botones[i].setForeground(Color.WHITE);
            botones[i].setFont(new Font("Arial", Font.BOLD, 14));
            botones[i].setPreferredSize(new Dimension(200, 40));
            botones[i].setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            botones[i].setBorder(new RoundedBorder(15));
            botones[i].setBorderPainted(false);
            botones[i].setContentAreaFilled(true);
            botones[i].setOpaque(true);

            final JButton boton = botones[i];
            boton.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent evt) {
                    boton.setBackground(new Color(30, 90, 170));
                }

                public void mouseExited(MouseEvent evt) {
                    boton.setBackground(new Color(60, 120, 200));
                }
            });

            panelBotones.add(botones[i]);
        }

        botones[0].addActionListener(e -> new Thread(this::paintLinealGraph).start());
        botones[1].addActionListener(e -> new Thread(this::paintNlognGraph).start());
        botones[2].addActionListener(e -> new Thread(this::paintNsquaredGraph).start());
        botones[3].addActionListener(e -> new Thread(this::paintNcubicGraph).start());
    }

    private static class RoundedBorder implements javax.swing.border.Border {
        private final int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(radius / 2, radius, radius / 2, radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(c.getBackground());
            g2.fillRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }
    }

    private void graficaAlgoritmos() {
        setTitle("Comparación de Algoritmos");
        setSize(1100, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(BG_DARK);
        setLayout(new BorderLayout());
        initPanelBotones();

        JLabel titulo = new JLabel("Comparación de Complejidades", SwingConstants.CENTER);
        titulo.setFont(new Font("Georgia", Font.BOLD, 20));
        titulo.setForeground(TEXT_COLOR);
        titulo.setBorder(BorderFactory.createEmptyBorder(18, 0, 0, 0));
        titulo.setOpaque(false);
        add(titulo, BorderLayout.NORTH);
        panelGrafica = new PanelGrafica();
        JPanel leyenda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leyenda.setBackground(BG_DARK);
        leyenda.setPreferredSize(new Dimension(160, 0));
        for (int i = 0; i < nBotones.length; i++) {
            JLabel lbl = new JLabel("● " + nBotones[i]);
            lbl.setForeground(COLORES[i]);
            lbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
            leyenda.add(lbl);
        }
        add(leyenda, BorderLayout.EAST);
        add(panelBotones, BorderLayout.SOUTH);
        add(panelGrafica, BorderLayout.CENTER);
        setVisible(true);
    }

    private void paintLinealGraph() {
        if (lineal_dibujado) {
            return;
        }
        lineal_dibujado = true;
        SwingUtilities.invokeLater(() -> panelGrafica.pintarGrafico(DIBUJAR_LINEAL));
    }

    private void paintNlognGraph() {
        if (dibujar_logaritmico) {
            return;
        }
        dibujar_logaritmico = true;
        SwingUtilities.invokeLater(() -> panelGrafica.pintarGrafico(DIBUJAR_LOGARITMICO));
    }

    private void paintNsquaredGraph() {
        if (dibujar_cuadratico) {
            return;
        }
        dibujar_cuadratico = true;
        SwingUtilities.invokeLater(() -> panelGrafica.pintarGrafico(DIBUJAR_CUADRATICO));
    }

    private void paintNcubicGraph() {
        if (dibujar_cubico) {
            return;
        }
        dibujar_cubico = true;
        SwingUtilities.invokeLater(() -> panelGrafica.pintarGrafico(DIBUJAR_CUBICO));
    }

    class PanelGrafica extends JPanel {
        private BufferedImage lienzo;

        public PanelGrafica() {
            setBackground(BG_PANEL);
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        }

        private void initLienzo() {
            if (lienzo == null || lienzo.getWidth() != getWidth() || lienzo.getHeight() != getHeight()) {
                BufferedImage nuevo = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = nuevo.createGraphics();

                if (lienzo != null) {
                    g2.drawImage(lienzo, 0, 0, null);
                } else {
                    g2.setColor(BG_PANEL);
                    g2.fillRect(0, 0, getWidth(), getHeight());
                }
                g2.dispose();
                lienzo = nuevo;
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (lienzo != null) {
                g.drawImage(lienzo, 0, 0, null);
            }
        }

        private void pintarGrafico(int tipoDibujo) {
            initLienzo();
            ancho = getWidth();
            alto = getHeight();

            Graphics2D g2 = lienzo.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            long maxTiempo = 0;
            long[] tiempos = null;
            switch (tipoDibujo) {
                case 0:
                    tiempos = ctrl.getDatosLineal();
                    maxTiempo = obtenerEscalado("Lineal", tiempos);
                    g2.setColor(COLORES[0]);
                    break;
                case 1:
                    tiempos = ctrl.getDatosNLog();
                    maxTiempo = obtenerEscalado("nlogn", tiempos);
                    g2.setColor(COLORES[1]);
                    break;
                case 2:
                    tiempos = ctrl.getDatosN2();
                    maxTiempo = obtenerEscalado("Cuadratico", tiempos);
                    g2.setColor(COLORES[2]);
                    break;
                case 3:
                    tiempos = ctrl.getDatosN3();
                    /*
                     * POR EL AMOR DE LOS COMPILADORES DE JAVA y de JAMES GOSLING FUNCIONA POR FAVOR
                     */
                    maxTiempo = obtenerEscalado("Cubico", tiempos);
                    g2.setColor(COLORES[3]);
                    break;

            }
            int x1, x2, y1, y2 = 0;
            for (int j = 0; j < tiempos.length - 1 && y2 != MARGEN; j++) {
                g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

                x1 = MARGEN + j * (ancho - 2 * MARGEN) / (tiempos.length - 1);
                x2 = MARGEN + (j + 1) * (ancho - 2 * MARGEN) / (tiempos.length - 1);
                y1 = (int) ((double) tiempos[j] / maxTiempo * (alto - 2 * MARGEN));
                y2 = (int) ((double) tiempos[j + 1] / maxTiempo * (alto - 2 * MARGEN));

                // Invertir eje Y y clampear al área del gráfico
                y1 = Math.max(MARGEN, alto - MARGEN - y1);
                y2 = Math.max(MARGEN, alto - MARGEN - y2);

                if (y1 > MARGEN && y2 > MARGEN) {
                    g2.drawLine(x1, y1, x2, y2);
                } else {
                    y2 = MARGEN;
                    g2.drawLine(x1, y1, x2, y2);
                }
                

            }
            // Ejes
            g2.setColor(AXIS_COLOR);
            g2.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawLine(MARGEN, MARGEN, MARGEN, alto - MARGEN + 3);
            g2.drawLine(MARGEN, alto - MARGEN + 3, ancho - MARGEN, alto - MARGEN + 3);

            // Puntas de flecha
            g2.fillPolygon( // Y
                    new int[] { MARGEN, MARGEN - 5, MARGEN + 5 },
                    new int[] { MARGEN, MARGEN + 10, MARGEN + 10 },
                    3);
            g2.fillPolygon( // X
                    new int[] { ancho - MARGEN, ancho - MARGEN - 10, ancho - MARGEN - 10 },
                    new int[] { alto - MARGEN + 3, alto - MARGEN - 2, alto - MARGEN + 8 },
                    3);

            // Etiqueta eje X: "N (casos)"
            g2.setFont(new Font("SansSerif", Font.BOLD, 13));
            g2.setColor(TEXT_COLOR);
            String labelX = "N (casos)";
            int labelXW = g2.getFontMetrics().stringWidth(labelX);
            g2.drawString(labelX,
                    MARGEN + (ancho - 2 * MARGEN) / 2 - labelXW / 2,
                    alto - MARGEN + 28);

            // Etiqueta eje Y: "Tiempo" rotada 90°
            String labelY = "Tiempo";
            Graphics2D g2r = (Graphics2D) g2.create();
            g2r.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2r.setFont(new Font("SansSerif", Font.BOLD, 13));
            g2r.setColor(TEXT_COLOR);
            int labelYW = g2r.getFontMetrics().stringWidth(labelY);
            g2r.translate(MARGEN - 50, MARGEN + (alto - 2 * MARGEN) / 2 + labelYW / 2);
            g2r.rotate(-Math.PI / 2);
            g2r.drawString(labelY, 0, 0);
            g2r.dispose();

            g2.dispose();
            repaint();
        }

        public long obtenerEscalado(String alg, long[] tiempos) {
            return 2349600;
        }

        public long obtenerEscalado2(String alg, long[] tiempos) {
            long max = 0;
            for (int i = 0; i < tiempos.length; i++) {
                if (tiempos[i] > max) {
                    max = tiempos[i];
                }
            }
            switch (alg) {
                case "Lineal":
                    max = (long) (max * Math.log10(max));
                    break;
                case "Cuadratico":
                    max = (long) (Math.sqrt(max) * Math.log10(Math.sqrt(max)));
                    break;
                case "Cubico":
                    max = (long) (Math.cbrt(max) * Math.log10(Math.cbrt(max)));
                    break;
            }
            return max;
        }
    }
}
