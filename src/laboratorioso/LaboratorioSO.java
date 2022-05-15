package laboratorioso;

public class LaboratorioSO {

    public static void main(String[] args) {
        int[][] M = new int[8][5];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                System.out.print(i+" "+j);
            }
            System.out.println("");
        }
        UI ui = new UI();
        ui.setVisible(true);
        
    }
}