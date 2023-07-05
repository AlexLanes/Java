package com.rpachallenge;

// std
import java.io.File;
import java.util.Scanner;
import java.util.HashMap;
import java.time.Duration;
import java.util.ArrayList;
import java.nio.file.FileSystems;
import java.io.FileNotFoundException;

// externo
import org.openqa.selenium.edge.EdgeDriver;

public class App {
    private static ArrayList< HashMap<String, String> > parserCsv() throws FileNotFoundException {
        var csv = new ArrayList< HashMap<String, String> >();
        String cwd = FileSystems.getDefault().getPath("")
                                .toAbsolutePath().toString();
        var file = new File(cwd + "\\main\\files\\challenge.csv");

        try( var scanner = new Scanner(file) ){
            var headers = scanner.nextLine().split(",");
            
            while( scanner.hasNextLine() ){
                var linha = new HashMap<String, String>();
                var colunas = scanner.nextLine().split(",");
                for( int i = 0; i < colunas.length; i++ )
                    linha.put(headers[i], colunas[i]);
                csv.add(linha);
            }

        }

        return csv;
    }

    public static void main( String[] args ) throws InterruptedException {
        var dePara = new DePara();
        var navegador = new EdgeDriver();
        navegador.manage().window().maximize();
        navegador.manage().timeouts().implicitlyWait( Duration.ofSeconds(10) );

        try {
            var csv = App.parserCsv();
            navegador.get("https://www.rpachallenge.com/");

            // iniciar
            navegador.findElement(Locators.start).click();
            
            // percorrer cada linha do csv
            for( var linha: csv ){
                var inputs = navegador.findElements(Locators.inputs);

                // percorrer cada input na página 
                // e atribuir valor da linha do csv
                for( var input: inputs ){
                    var atributo = input.getAttribute("ng-reflect-name");
                    var coluna = dePara.get(atributo);
                    input.sendKeys( linha.get(coluna) );
                }

                // próxima página
                navegador.findElement(Locators.submit).click();
            }

            // Finalizar
            System.out.println( "-------------------------------------------------\n" +
                "Status: " + navegador.findElement(Locators.status).getText() + "\n" +
                "Mensagem: " + navegador.findElement(Locators.mensagem).getText() +
                "\n-------------------------------------------------"
            );
        
        } catch( Exception e ){
            System.out.println("Exceção: " + e.getMessage());

        } finally {
            Thread.sleep(5000);
            navegador.quit();
        }
    }
}
