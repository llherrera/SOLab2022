package laboratorioso;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class UI extends javax.swing.JFrame {

    public UI() {
        initComponents();
    }

    ArrayList<Integer> dls = new ArrayList<Integer>();
    ArrayList<Integer> rws = new ArrayList<Integer>();
    String error;
    int pro = 0;
    int marco = 0;
    int[] lisMar = {};
    int[] tablaMemPri;
    int[][] tablap;
    int contIter = 0;
    int numP = 0;
    int temp = 0;

    void reset() {
        dls = new ArrayList<Integer>();
        rws = new ArrayList<Integer>();
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

    void leerIns(String[] arr, int tamP) {
        try {
            int dl = Integer.parseInt(arr[0]);
            if (dl <= tamP && dl >= 0) {
                String rw = arr[1];
                if (rw.equals("E")) {
                    this.dls.add(dl);
                    this.rws.add(0);
                } else if (rw.equals("L")) {
                    this.dls.add(dl);
                    this.rws.add(1);
                } else {
                    error = "Ingrse L, para lectura, o E, para escritura";
                    JOptionPane.showMessageDialog(this, error);
                }
            } else {
                error = "La dirección logica no está dentro del proceso";
                JOptionPane.showMessageDialog(this, error);
            }
        } catch (Exception e) {
            error = "Ingrese una dirección logica";
            JOptionPane.showMessageDialog(this, error);
        }
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
                modeloMP.addRow(new Object[]{i, "SO"});
            } else {
                modeloMP.addRow(new Object[]{i, ""});
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
            modeloP.addRow(new Object[]{i, "", 0, ""});
        }
    }

    void iterar(int c) {
        DefaultTableModel modeloP = (DefaultTableModel) TablaP.getModel();
        DefaultTableModel modeloMP = (DefaultTableModel) TablaMP.getModel();
        int dl = dls.get(c);
        int rw = rws.get(c);
        int pag = dl / marco;
        int mar = 0;
        if (modeloP.getValueAt(pag, 2).equals(0)) {
            mar = lisMar[temp];
            temp++;
            if (temp >= lisMar.length) {
                temp = 0;
            }
            modeloP.setValueAt(mar, pag, 1);
            modeloP.setValueAt(1, pag, 2);
            modeloMP.setValueAt("Proceso", mar, 1);
        }
        if (rw == 0 && modeloP.getValueAt(pag, 3).equals(0)) {
            modeloP.setValueAt(1, pag, 3);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DisTamMarco = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        DisTamSO = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        DisTamPro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        DisIns = new javax.swing.JTextField();
        addInsBot = new javax.swing.JButton();
        DisMarLibres = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaP = new javax.swing.JTable();
        iteBot = new javax.swing.JButton();
        autoBot = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaMP = new javax.swing.JTable();

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

        DisIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisInsActionPerformed(evt);
            }
        });
        getContentPane().add(DisIns, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 64, -1));

        addInsBot.setText("Añadir");
        addInsBot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInsBotActionPerformed(evt);
            }
        });
        getContentPane().add(addInsBot, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 85, 31));

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

        jLabel4.setText("<html> Instrucción<br> (DL : L/E): </html>");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/laboratorioso/disco3.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 20, -1, -1));

        TablaMP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#Marco", "Proceso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TablaMP);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 120, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 650));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void DisTamProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisTamProActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DisTamProActionPerformed

    private void DisInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisInsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DisInsActionPerformed

    private void DisMarLibresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DisMarLibresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DisMarLibresActionPerformed

    private void addInsBotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addInsBotActionPerformed
        String tamP = DisTamPro.getText();
        try {
            int tamPro = Integer.parseInt(tamP);
            if (tamPro != pro) {
                error = "Cambió el tamaño del proceso, se reiniciarán las instrucciones";
                pro = tamPro;
                reset();
                JOptionPane.showMessageDialog(this, error);
            }
            String[] instruc = DisIns.getText().split(":");
            leerIns(instruc, tamPro);
        } catch (Exception e) {
            error = "Ingrese un numero";
            JOptionPane.showMessageDialog(this, error);
        }

    }//GEN-LAST:event_addInsBotActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            int tamM = Integer.parseInt(DisTamMarco.getText());
            if (!potencia2(tamM)) {
                error = "El tamaño del marco debe ser potencia de 2 y mayor que 1";
                JOptionPane.showMessageDialog(this, error);
            } else {
                if (dls.isEmpty()) {
                    error = "Ingrese instrucciones antes de ejecutar";
                    JOptionPane.showMessageDialog(this, error);
                } else {
                    int tamSO = Integer.parseInt(DisTamSO.getText());
                    String[] lisMar = DisMarLibres.getText().split(",");
                    int[] lismar = new int[lisMar.length];
                    for (int i = 0; i < lisMar.length; i++) {
                        lismar[i] = Integer.parseInt(lisMar[i]);
                    }
                    iteBot.setEnabled(true);
                    autoBot.setEnabled(true);
                    tablaMemPri = setMarcos(tamSO, tamM, lismar);
                    setTablaP();
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
        if (contIter < dls.size()) {
            iterar(contIter);
            contIter++;
        } else {
            error = "No se puede iterar";
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
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JTextField DisIns;
    private javax.swing.JTextField DisMarLibres;
    private javax.swing.JTextField DisTamMarco;
    private javax.swing.JTextField DisTamPro;
    private javax.swing.JTextField DisTamSO;
    private javax.swing.JTable TablaMP;
    private javax.swing.JTable TablaP;
    private javax.swing.JButton addInsBot;
    private javax.swing.JButton autoBot;
    private javax.swing.JButton iteBot;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
