public class Floyd {

    static int[][] P;
    static final int N = 5;
    public static void main(String[] args) {
        int[][] M = {{0,3,10,999,999},
                     {3,0,999,5,999},
                     {10,999,0,6,15},
                     {999,5,6,0,4},
                     {999,999,999,4,0}};
        P = new int[N][N];
        System.out.println("Matrix to find the shortest path of.");
        printMatrix(M);
        System.out.println("Shortest Path Matrix.");
        printMatrix(FloydAlgo(M));
        System.out.println("PathMatrix.");
        printMatrix(P);
    }

    private static int[][] FloydAlgo(int[][] m) {
        for(int k = 0;k<N; k++){
            for(int i = 0; i<N;i++){
                for(int j = 0;j<N;j++){
                    if (m[i][k] + m[k][j] < m[i][j]){
                        m[i][j]=m[i][k]+m[k][j];
                        P[i][j] = k;
                    }
                }

            }
        }
        return m;
    }

    public static void printMatrix(int[][] m) {
        for(int i = 0; i<N;i++){
            for(int j = 0;j<N;j++) {
                System.out.print(m[i][j]+" ; ");
            }
            System.out.println();
        }

    }
}
