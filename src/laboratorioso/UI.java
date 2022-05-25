package laboratorioso;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class UI extends javax.swing.JFrame {

    public UI() {
        initComponents();
        DisTamMarco.setText("4");
        DisTamSO.setText("8");
        DisTamPro.setText("32");
        DisMarLibres.setText("1,2");
    }
    File archivo;

    public static final String SEPARADOR = ",";

    int[] dls;
    int[] rws;
    ArrayList<String> dlsS;
    ArrayList<String> rwsS;
    String error;
    int pro = 0;
    int marco = 0;
    int[] lisMar = {};
    int[] tablaMemPri;
    int[][] tablap;
    int contIter = 0;
    int sigMarco = 0;
    int numP = 0;

    void preset() {
        DefaultTableModel modeloMP = (DefaultTableModel) TablaMP.getModel();
        int num = modeloMP.getRowCount();
        for (int i = 0; i < num; i++) {
            modeloMP.removeRow(0);
        }

        DefaultTableModel modeloP = (DefaultTableModel) TablaP.getModel();
        num = modeloP.getRowCount();
        for (int i = 0; i < num; i++) {
            modeloP.removeRow(0);
        }

        Graphics g = panel.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(29, 99, 250, panel.getHeight() - 50);
    }

    void setInst() {
        dls = new int[dlsS.size()];
        rws = new int[rwsS.size()];

        for (int i = 0; i < dlsS.size(); i++) {
            dls[i] = Integer.parseInt(dlsS.get(i));
            if (rwsS.get(i).equals("E")) {
                rws[i] = 0;
            }
            if (rwsS.get(i).equals("L")) {
                rws[i] = 1;
            }
        }
    }

    int getMayor(int[] arr) {
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > res) {
                res = arr[i];
            }
        }
        return res;
    }

    void reset(int n) {
        dls = new int[n];
        rws = new int[n];
    }

    boolean potencia2(int num) {
        boolean res = false;
        if (num > 1 && Integer.bitCount(num) == 1) {
            res = true;
        }
        return res;
    }

    void soutAL(ArrayList arr) {
        for (Object o : arr) {
            System.out.println("" + o);
        }
    }

    boolean verificatTam() {
        boolean res = false;

        return res;
    }

    int[] setMarcos(int tamSO, int tamM, int[] lisM) {
        int numMarSO = tamSO / tamM;
        marco = tamM;
        lisMar = lisM;
        if (tamSO % tamM != 0) {
            numMarSO++;
        }
        int numMarcos = numMarSO + lisM.length;
        int[] memPrin = new int[numMarcos];

        for (int i = 0; i < memPrin.length; i++) {
            memPrin[i] = -1;
        }
        for (int i = 0; i < memPrin.length; i++) {
            int n = -1;
            if (!existe(i, lisM)) {
                do {
                    n = (int) (Math.random() * 10);
                } while (existe(n, memPrin));
                memPrin[i] = n;
            }
        }
        setTablaMP(memPrin);
        return memPrin;
    }

    boolean existe(int n, int[] arr) {
        boolean res = false;
        for (int i = 0; i < arr.length; i++) {
            if (n == arr[i]) {
                res = true;
                break;
            }
        }
        return res;
    }

    void setTablaMP(int[] tabla) {
        DefaultTableModel modeloMP = (DefaultTableModel) TablaMP.getModel();
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i] != -1) {
                modeloMP.addRow(new Object[]{i, "SO", -1});
            } else {
                modeloMP.addRow(new Object[]{i, "", -1});
            }
        }
    }

    void setTablaP() {
        int numPag = pro / marco;
        if (pro % marco != 0) {
            numPag++;
        }
        numP = numPag;
        DefaultTableModel modeloP = (DefaultTableModel) TablaP.getModel();
        for (int i = 0; i < numPag; i++) {
            modeloP.addRow(new Object[]{i, -1, 0, 0});
        }
    }

    void dibujarProceso() {
        Graphics g = panel.getGraphics();
        g.setColor(Color.BLACK);
        int c = 0, masx = 0, masy = 0;
        while (c < numP) {
            g.drawRect(30 + masx, 100 + masy, 20, 20);
            if (30 + masx < panel.getWidth() - 60) {
                masx += 30;
            } else {
                masx = 0;
                masy += 30;
            }
            c++;
        }
    }

    void iterar() {
        DefaultTableModel modeloP = (DefaultTableModel) TablaP.getModel();
        DefaultTableModel modeloMP = (DefaultTableModel) TablaMP.getModel();
        int dl = dls[contIter];
        int rw = rws[contIter];
        int pag = dl / marco;
        int mar = lisMar[sigMarco % lisMar.length];
        System.out.println(pag+" "+mar);
        if (modeloP.getValueAt(pag, 2).equals(0)) {
            if (modeloMP.getValueAt(mar, 2).equals(-1)) {
                //swap in

                swapInProcess(mar, pag, rw);
            } else {
                //animacio swap out si Modi==1
                //animacion swap in

                swapOutProcess(mar);
                swapInProcess(mar, pag, rw);
            }
            sigMarco++;
        } else {
            if (rw == 0) {
                modeloP.setValueAt(1, pag, 3);
            }
        }

        /*modeloP.setValueAt(mar, pag, 1);
        modeloP.setValueAt(1, pag, 2);
        if (rw == 0 && modeloP.getValueAt(pag, 3).equals(0)) {
            modeloP.setValueAt(1, pag, 3);
        }*/
    }

    void swapInProcess(int mar, int pag, int rw) {
        DefaultTableModel modeloP = (DefaultTableModel) TablaP.getModel();
        DefaultTableModel modeloMP = (DefaultTableModel) TablaMP.getModel();
        modeloP.setValueAt(mar, pag, 1);
        modeloP.setValueAt(1, pag, 2);
        if (rw == 0) {
            modeloP.setValueAt(1, pag, 3);
        }
        modeloMP.setValueAt("Proceso", mar, 1);
        modeloMP.setValueAt(pag, mar, 2);
    }

    void swapOutProcess(int mar) {
        DefaultTableModel modeloP = (DefaultTableModel) TablaP.getModel();
        DefaultTableModel modeloMP = (DefaultTableModel) TablaMP.getModel();
        int pagi = (int) modeloMP.getValueAt(mar, 2);
        modeloP.setValueAt(-1, pagi, 1);
        modeloP.setValueAt(0, pagi, 2);
        modeloP.setValueAt(0, pagi, 3);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileC = new javax.swing.JFileChooser();
        DisTamMarco = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        DisTamSO = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        DisTamPro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        DisMarLibres = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaP = new javax.swing.JTable();
        iteBot = new javax.swing.JButton();
        autoBot = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaMP = new javax.swing.JTable();
        addInsBot = new javax.swing.JButton();
        panel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1300, 649));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(DisTamMarco, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 64, -1));

        jLabel1.setText("Tamaño marcos:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 45, -1, -1));
        getContentPane().add(DisTamSO, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 64, -1));

        jLabel2.setText("Tamaño SO:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 83, -1, -1));

        DisTamPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisTamProActionPerformed(evt);
            }
        });
        getContentPane().add(DisTamPro, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 64, -1));

        jLabel3.setText("Tamaño proceso:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 121, -1, -1));

        DisMarLibres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisMarLibresActionPerformed(evt);
            }
        });
        getContentPane().add(DisMarLibres, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 64, -1));

        jLabel6.setText("<html>Lista de marcos <br/> libres, separe <br/> por coma </html>");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 210, -1, -1));
        jLabel6.getAccessibleContext().setAccessibleName("\"<html>Line1 <br/> Line2 <br/> Line3</html>\"");

        jButton1.setText("Empezar programa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 210, 77));

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1300, 620));
        jPanel1.setPreferredSize(new java.awt.Dimension(1300, 650));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pag", "Mar", "Val_Inv", "Modi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TablaP);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 410, -1));

        iteBot.setText("Iterar");
        iteBot.setEnabled(false);
        iteBot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iteBotActionPerformed(evt);
            }
        });
        jPanel1.add(iteBot, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 460, -1, -1));

        autoBot.setText("too fast");
        autoBot.setEnabled(false);
        autoBot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoBotActionPerformed(evt);
            }
        });
        jPanel1.add(autoBot, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 490, 80, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/laboratorioso/disco3.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 20, -1, -1));

        TablaMP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Marco", "Proceso", "PagProceso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaMP);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 20, 200, -1));

        addInsBot.setText("Añadir Instrucciones");
        addInsBot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInsBotActionPerformed(evt);
            }
        });
        jPanel1.add(addInsBot, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 140, 31));
        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 20, 300, 600));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DisTamProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisTamProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DisTamProActionPerformed

    private void DisMarLibresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisMarLibresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DisMarLibresActionPerformed

    private void addInsBotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInsBotActionPerformed
        BufferedReader bufferLectura = null;
        try {
            dlsS = new ArrayList<>();
            rwsS = new ArrayList<>();
            bufferLectura = new BufferedReader(new FileReader("Entrada.csv"));
            String linea = bufferLectura.readLine();
            int c = 0, t = 0;
            while (linea != null) {
                String[] campos = linea.split(SEPARADOR);
                for (String a : Arrays.asList(campos)) {
                    if (c == 0 && t == 0) {
                        dlsS.add(a.substring(1, a.length()));
                        c = 1;
                    } else if (t == 0 && c != 0) {
                        dlsS.add(a);
                    } else {
                        rwsS.add(a);
                    }
                }
                t++;
                linea = bufferLectura.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        setInst();
    }//GEN-LAST:event_addInsBotActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int tamM = Integer.parseInt(DisTamMarco.getText());
            if (!potencia2(tamM)) {
                error = "El tamaño del marco debe ser potencia de 2 y mayor que 1";
                JOptionPane.showMessageDialog(this, error);
            } else {
                if (dls.length == 0) {
                    error = "Ingrese instrucciones antes de ejecutar";
                    JOptionPane.showMessageDialog(this, error);
                } else {
                    int tamPro = Integer.parseInt(DisTamPro.getText());
                    if (tamPro < getMayor(dls)) {
                        error = "El tamaño del proceso debe ser mayor a: " + getMayor(dls);
                        JOptionPane.showMessageDialog(this, error);
                    } else {
                        preset();
                        int tamSO = Integer.parseInt(DisTamSO.getText());
                        String[] lisMar = DisMarLibres.getText().split(",");
                        int[] lismar = new int[lisMar.length];
                        for (int i = 0; i < lisMar.length; i++) {
                            lismar[i] = Integer.parseInt(lisMar[i]);
                        }
                        iteBot.setEnabled(true);
                        autoBot.setEnabled(true);
                        pro = Integer.parseInt(DisTamPro.getText());
                        tablaMemPri = setMarcos(tamSO, tamM, lismar);
                        setTablaP();
                        dibujarProceso();
                    }

                }
            }
        } catch (Exception e) {
            error = "Digite numeros para proceder la ejecución";
            JOptionPane.showMessageDialog(this, error);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void autoBotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoBotActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_autoBotActionPerformed

    private void iteBotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iteBotActionPerformed
        if (contIter < dls.length) {
            iterar();
            contIter++;
        } else {
            error = "No hay instruciones para ejecutar";
            JOptionPane.showMessageDialog(this, error);
        }
    }//GEN-LAST:event_iteBotActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DisMarLibres;
    private javax.swing.JTextField DisTamMarco;
    private javax.swing.JTextField DisTamPro;
    private javax.swing.JTextField DisTamSO;
    private javax.swing.JTable TablaMP;
    private javax.swing.JTable TablaP;
    private javax.swing.JButton addInsBot;
    private javax.swing.JButton autoBot;
    private javax.swing.JFileChooser fileC;
    private javax.swing.JButton iteBot;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
