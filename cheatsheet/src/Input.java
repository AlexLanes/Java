import java.util.ArrayList;
import java.util.Scanner;

public class Input {
    public static void main( String[] args ) throws Exception {
        ArrayList< String > lista = new ArrayList<>();
        Scanner scanner = new Scanner( System.in );

        System.out.println( "Digite alguma coisa, 'sair' para encerrar o loop: " );

        while( true ){
            String texto = scanner.next();

            if( "sair".equals(texto) ) break;
            else lista.add( texto );
        }
            
        scanner.close();
        System.out.println( "Você escreveu: " + lista );
    }
}
