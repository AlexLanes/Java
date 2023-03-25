class Validador {
    /**
     * Validar número de CPF
     * https://www.calculadorafacil.com.br/computacao/validar-cpf
     **/ 
    public static boolean Cpf( String cpf ){
        // remove "." e "-"
        cpf = cpf.replaceAll( "[.-]", "" );

        // verifica tamanho(11) e se possui apenas digitos
        if( !cpf.matches("^[0-9]{11}$") ) 
            return false;
        
        int soma = 0, resto,
            primeiroDigitoVerificador = Character.getNumericValue( cpf.charAt(9) ),
            segundoDigitoVerificador  = Character.getNumericValue( cpf.charAt(10) );
        
        String digitos = cpf.substring( 0, 9 );
        
        /**
         * Validação do primeiroDigitoVerificador.
         * Caso o resto seja 10, utiliza-se 0.
         * Se o resto for diferente do primeiroDigitoVerificador, o cpf é inválido
         **/ 
        for( int index = 0, peso = 1; index <= 8; index++, peso++ ){
            int digito = Character.getNumericValue( digitos.charAt(index) );
            soma += digito * peso;
        }
        
        resto = ( soma % 11 == 10 ) ? 0 : soma % 11;
        if( resto != primeiroDigitoVerificador ) return false;  
        
        /**
         * Validação do segundoDigitoVerificador.
         * Caso o resto seja 10, utiliza-se 0.
         * Se o resto for igual ao segundoDigitoVerificador, o cpf é válido
         **/
        digitos += resto;   // acrescenta o resto na verificação do segundoDigitoVerificador
        resto = soma = 0;   // reseta o resto e soma

        for( int index = 0, peso = 0; index <= 9; index++, peso++ ){
            int digito = Character.getNumericValue( digitos.charAt(index) );
            soma += digito * peso;
        }

        resto = ( soma % 11 == 10 ) ? 0 : soma % 11;
        return resto == segundoDigitoVerificador;
    }
    
    /**
     * Validar número de CPNJ
     * https://www.geradorcnpj.com/algoritmo_do_cnpj.htm
     **/
    public static boolean Cnpj( String cnpj ){
        // remove ".", "-" e "/"
        cnpj = cnpj.replaceAll( "[/.-]", "" );

        // verifica tamanho(14) e se possui apenas digitos
        if( !cnpj.matches("^[0-9]{14}$") ) 
            return false;
        
        int soma = 0, resto,
            primeiroDigitoVerificador = Character.getNumericValue( cnpj.charAt(12) ),
            segundoDigitoVerificador  = Character.getNumericValue( cnpj.charAt(13) );
        
        String digitos = cnpj.substring( 0, 12 );
        
        /** 
         * Validação do primeiroDigitoVerificador
         * Após o loop do primeiro peso 2, irá retornar para 9 até o fim do loop
         * Se o resto for menor que 2, o primeiroDigitoVerificador deve ser automaticamente 0
         * Se o resto for maior ou igual a 2, o primeiroDigitoVerificador deve ser 11 - resto
         **/ 
        for( int index = 0, peso = 5; index <= 11; index++, peso-- ){
            if( peso == 1 ) 
                peso = 9;

            int digito = Character.getNumericValue( digitos.charAt(index) );
            soma += digito * peso;
        }

        resto = soma % 11;
        if( resto < 2 && primeiroDigitoVerificador != 0 ) return false;
        else if( resto >= 2 && 11 - resto != primeiroDigitoVerificador ) return false;
        
        /**
         * Validação do segundoDigitoVerificador
         * Após o loop do primeiro peso 2, irá retornar para 9 até o fim do loop
         * Se o resto for menor que 2, o segundoDigitoVerificador deve ser automaticamente 0
         * Se o resto for maior ou igual a 2, o segundoDigitoVerificador deve ser 11 - resto
         **/ 
        digitos += primeiroDigitoVerificador;   // acrescenta o primeiroDigitoVerificador na verificação do segundoDigitoVerificador
        resto = soma = 0;                       // reseta o resto e soma
        
        for( int index = 0, peso = 6; index <= 12; index++, peso-- ){
            if( peso == 1 ) 
                peso = 9; 

            int digito = Character.getNumericValue( digitos.charAt(index) );
            soma += digito * peso;
        }

        resto = soma % 11;
        if( resto < 2 ) return segundoDigitoVerificador == 0;
        else            return 11 - resto == segundoDigitoVerificador;
    }
}