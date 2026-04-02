public class Input {
    public static int scanInt(String message) {
        while (true) {
            try {
                // Melhorando código feito em aula para evitar erros de entrada
                // Tenta converter a entrada para inteiro
                int resultado = Integer.parseInt(IO.readln(message));
                return resultado;
            } catch (NumberFormatException e) {
                // Número inválido: repete a pergunta até o usuário digitar um int válido
                IO.println("Valor inválido! Digite um número inteiro.");
            }
        }
    }
}
