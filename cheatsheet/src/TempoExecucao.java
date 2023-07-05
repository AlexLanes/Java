public class TempoExecucao {
    public static void main( String[] args ){
        long SEGUNDOS_EXECUTANDO = 11,
             inicio = System.currentTimeMillis() / 1000,
             agora = inicio;

        while( agora - inicio < SEGUNDOS_EXECUTANDO ){
            agora = System.currentTimeMillis() / 1000;
        }
    }
}
