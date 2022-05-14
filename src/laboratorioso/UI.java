package laboratorioso;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UI extends javax.swing.JFrame {

    public UI() {
        initComponents();
    }

    ArrayList<Integer> dl = new ArrayList<Integer>();
    ArrayList<Boolean> rw = new ArrayList<Boolean>();
    String error;
    int pro = 0;
    int marco = 0;
    int so = 0;
    int[] tablaP;

    void addAL(ArrayList arr, Object o) {
        arr.add(o);
    }

    void reset() {
        dl = new ArrayList<Integer>();
        rw = new ArrayList<Boolean>();
    }

    boolean potencia2(int num) {
        boolean res = false;
        if (num > 1 && Integer.bitCount(num) == 1) {
            res = true;
        }
        return res;
    }

    void soutV(Object[] o) {
        for (int i = 0; i < o.length; i++) {
            System.out.println("" + o[i]);
        }
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
                    addAL(this.dl, dl);
                    addAL(this.rw, 0);
                } else if (rw.equals("L")) {
                    addAL(this.dl, dl);
                    addAL(this.rw, 1);
                } else {
                    error = "Ingrse L, para lectura, o E, para escritura";
                    JOptionPane.showMessageDialog(this, error);
                }
            } else {
                error = "La dirección logica no está dentro del proceso";
                JOptionPane.showMessageDialog(this, error);
            }
            String rw = arr[1];

        } catch (Exception e) {
            error = "Ingrese una dirección logica";
            JOptionPane.showMessageDialog(this, error);
        }
        soutAL(this.dl);
        soutAL(this.rw);
    }

    int[] setMarcos(int tamSO, int tamM, int[] lisM) {
        int numMarSO = tamSO / tamM;
        if (tamSO % tamM != 0) {numMarSO++;}
        int numMarcos = numMarSO + lisM.length;
        int[] tablaPaginas = new int[numMarcos];
        
        for (int i = 0; i < tablaPaginas.length; i++) {
            tablaPaginas[i] = -1;
        }
        for (int i = 0; i < tablaPaginas.length; i++) {
            int n = -1;
            if (!existe(i,lisM)) {
                do {
                    n = (int)(Math.random()*10);
                } while (existe(n, tablaPaginas));
                tablaPaginas[i]=n;
            }    
        }
        return tablaPaginas;
    }

    boolean existe(int n, int[] arr){
        boolean res = false;
        for (int i = 0; i < arr.length; i++) {
            if (n == arr[i]) {
                res = true;
                break;
            }
        }
        return res;
    }
    
    void ejecutar(int tamM, int tamSO, int[] lismar) {

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
        jLabel4 = new javax.swing.JLabel();
        DisIns = new javax.swing.JTextField();
        addInsBot = new javax.swing.JButton();
        DisMarLibres = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tamaño marcos:");

        jLabel2.setText("Tamaño SO:");

        DisTamPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisTamProActionPerformed(evt);
            }
        });

        jLabel3.setText("Tamaño proceso:");

        jLabel4.setText("<html> Instrucción<br> (DL : L/E): </html>");

        DisIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisInsActionPerformed(evt);
            }
        });

        addInsBot.setText("Añadir");
        addInsBot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInsBotActionPerformed(evt);
            }
        });

        DisMarLibres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DisMarLibresActionPerformed(evt);
            }
        });

        jLabel6.setText("<html>Lista de marcos <br/> libres, separe <br/> por coma </html>");

        jButton1.setText("Empezar programa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel3)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DisMarLibres, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DisTamPro, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DisTamSO, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DisTamMarco, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(DisIns, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addInsBot, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(818, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DisTamMarco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DisTamSO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DisTamPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DisIns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addInsBot, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(DisMarLibres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(279, Short.MAX_VALUE))
        );

        jLabel6.getAccessibleContext().setAccessibleName("\"<html>Line1 <br/> Line2 <br/> Line3</html>\"");

        pack();
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
            System.out.println("proceso:" + pro);
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
                if (dl.isEmpty()) {
                    error = "Ingrese instrucciones antes de ejecutar";
                    JOptionPane.showMessageDialog(this, error);
                } else {
                    int tamSO = Integer.parseInt(DisTamSO.getText());
                    String[] lisMar = DisMarLibres.getText().split(",");
                    int[] lismar = new int[lisMar.length];
                    for (int i = 0; i < lisMar.length; i++) {
                        lismar[i] = Integer.parseInt(lisMar[i]);
                    }
                    tablaP = setMarcos(tamSO, tamM, lismar);
                }
            }
        } catch (Exception e) {
            error = "Digite numeros para proceder la ejecución";
            JOptionPane.showMessageDialog(this, error);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton addInsBot;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
