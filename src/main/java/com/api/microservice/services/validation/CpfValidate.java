package com.api.microservice.services.validation;

import java.util.InputMismatchException;

public class CpfValidate {
    public static boolean isValidCpf(String CPF) {

        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11)){
            return(false);
        }

        try {
            return calcFristDigitCpf(CPF) && calcSecondDigitCpf(CPF);
        }catch (InputMismatchException error) {
            return false;
        }
    }

    public static boolean calcFristDigitCpf(String cpf){
        int fristDigit = cpf.charAt(9) - 48;
        int calc = 0;
        int weight = 10;

        for (int i = 0; i < 9; i++) {
            calc += (cpf.charAt(i) - 48) * weight;
            System.out.print("\n Valor " +calc);
            weight--;
        }
        calc = calc * 10 % 11;
        System.out.print("\n" +calc);
        System.out.print("\n" +fristDigit);
         if (calc == fristDigit) return true;
         return false;
    }

    public static boolean calcSecondDigitCpf(String cpf){
        int secondDigit = cpf.charAt(10) - 48;
        int calc = 0;
        int weight = 11;

        for (int i = 0; i < 10; i++) {
            calc += (cpf.charAt(i) - 48) * weight;
            weight--;
        }
        calc = calc * 10 % 11;
        System.out.print("\n" +calc);
        System.out.print("\n" +secondDigit);

        if (calc == secondDigit) return true;
        return false;
    }
}
