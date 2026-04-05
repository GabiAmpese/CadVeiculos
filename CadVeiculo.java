
import java.util.ArrayList;
import java.util.List;


void main(){
    IO.println("Bem vindo ao Sistema CadVeiculos");
    String menu = """
            MENU DE OPÇÕES
            1 - Cadastrar Veículo;
            2 - Listar Veículos;
            3 - Remover Veículo
                1 - Por Índice
                2 - Por Nome
            4 - Buscar Veículo;
            5 - Editar Veículo;
            0 - Sair
            """;
    int opcao;
    do {
        IO.println(menu);
        opcao = Input.scanInt("Digite a opção desejada: ");
        switch (opcao) {
            case 1 -> {
                cadastrar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 2 -> {
                listar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 3 -> {
                excluir();
                IO.readln("Pressione Enter para Continuar");
            }
            case 4 -> {
                buscar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 5 -> {
                editar();
                IO.readln("Pressione Enter para Continuar");
            }
            case 0 -> {
                IO.println("Volte sempre!!!");
            }
            default -> {
                IO.println("Opção Inválida");
                IO.readln("Pressione Enter para Continuar");
            }
                
        }
    } while (opcao != 0);

}


void excluir() {
    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado.");
        return;
    }

    IO.println("Escolha a forma de remoção:");
    IO.println("1 - Por Índice");
    IO.println("2 - Por Nome");
    int opc = Input.scanInt("Digite a opção 1 ou 2: ");

    if (opc == 1) { // 1
        listar();
        int indice = Input.scanInt("Digite o ID do veículo a ser removido: ");
        if (indice > veiculos.size() || indice <= 0) {
            IO.println("Veículo não encontrado!");
        } else {
            veiculos.remove(indice - 1);
            IO.println("Veículo removido com sucesso!");
        }
    } else if (opc == 2) { // 2
        String nome = IO.readln("Digite o nome do veículo a ser removido: ").trim();
        if (nome.isEmpty()) {
            IO.println("Nome do veículo inválido!");
            return;
        }

        for (int i = 0; i < veiculos.size(); i++) {
            if (veiculos.get(i).equalsIgnoreCase(nome)) {
                veiculos.remove(i);
                IO.println("Veículo removido com sucesso!");
                return;
            }
        }

        IO.println("Veículo não encontrado!");
    } else {
        IO.println("Opção inválida de remoção.");
    }
}


void cadastrar() {
    String veiculo = IO.readln("Digite o nome do novo veículo: ");
    veiculo = veiculo.trim();
    
    if (veiculo.isEmpty()) {
        IO.println("Nome do veículo inválido!");
        return;
    }
    
    // 1 - Validação de duplicatas no cadastro, (ignorando maiúsculas/minúsculas)
    for (int i = 0; i < veiculos.size(); i++) {
        if (veiculos.get(i).equalsIgnoreCase(veiculo)) {
            IO.println("Erro: Este veículo já foi cadastrado!");
            return;
        }
    }
    
    veiculos.add(veiculo);
    IO.println("Veículo cadastrado com sucesso!");
}


List<String> veiculos = new ArrayList<>();

void ordenarVeiculos() { // 6 - Ordenação Bubble Sort 
    for (int i = 0; i < veiculos.size() - 1; i++) {
        for (int j = 0; j < veiculos.size() - 1 - i; j++) {
            if (veiculos.get(j).compareToIgnoreCase(veiculos.get(j + 1)) >= 1) {
                String aux = veiculos.get(j);
                veiculos.set(j, veiculos.get(j + 1));
                veiculos.set(j + 1, aux);
            }
        }
    }
}


void listar() {
    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado."); // 4 Mensagem para lista vazia
    } else {
        ordenarVeiculos(); // 6 - Ordenação Bubble Sort antes da listagem
        for (int i = 1; i <= veiculos.size(); i++) {
            IO.println(i + " - " + veiculos.get(i - 1));
        }
    }
    IO.println("\nTotal de veículos cadastrados: " + veiculos.size()); // 3 - Total de veículos na listagem
}


void buscar() {    
    if (veiculos.isEmpty()) {
        IO.println("Nenhum veículo cadastrado."); // 4 Mensagem para lista vazia
        IO.println("\nTotal de veículos cadastrados: " + veiculos.size());
        return;
    }
    String veiculo = IO.readln("Digite o nome do veículo a buscar: ");
    veiculo = veiculo.trim();
    
    ordenarVeiculos(); // 6 - Ordenação Bubble Sort antes da busca
    // 2 - Busca por nome (insensível a maiúsculas/minúsculas)
    for (String veiculoCad : veiculos) {
        if (veiculoCad.toLowerCase().contains(veiculo.toLowerCase())) {
            IO.println("✓ Veículo encontrado: " + veiculoCad);
            IO.println("\nTotal de veículos cadastrados: " + veiculos.size()); // 3- Total de veículos na busca
            return;
        }
    }
    
    IO.println("Veículo não encontrado!");
    IO.println("\nTotal de veículos cadastrados: " + veiculos.size()); // 3- Exibe mesmo se não cair na busca
}


void editar() { // 5 - Editar veículo
    listar();
    int indice = Input.scanInt("Digite o índice do veículo a ser editado: ");
    if (indice > veiculos.size() || indice <= 0) {
        IO.println("Veículo não encontrado!");
        return;
    }
    
    String novoNome = IO.readln("Digite o novo nome do veículo: ");
    novoNome = novoNome.trim();
    
    if (novoNome.isEmpty()) {
        IO.println("Nome do veículo inválido!");
        return;
    }
    
    // Validação de duplicatas no cadastro, (ignorando maiúsculas/minúsculas)
    for (int i = 0; i < veiculos.size(); i++) {
        if (veiculos.get(i).equalsIgnoreCase(novoNome)) {
            IO.println("Erro: Este veículo já foi cadastrado!");
            return;
        }
    }
    
    veiculos.set(indice - 1, novoNome);
    IO.println("Veículo editado com sucesso!");
}