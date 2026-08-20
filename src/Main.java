public class Main {
    public static void main(String[] args) {


public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Livro> livros = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {

        cadastrarExemplos();

        int opcao = 0;

        do {
            System.out.println("\n===== SISTEMA DA BIBLIOTECA =====");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Cadastrar Usuario");
            System.out.println("4 - Listar Usuarios");
            System.out.println("5 - Realizar Emprestimo");
            System.out.println("6 - Devolver Livro");
            System.out.println("7 - Sair");
            System.out.print("Escolha uma opcao: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {

                    case 1:
                        cadastrarLivro();
                        break;

                    case 2:
                        listarLivros();
                        break;

                    case 3:
                        cadastrarUsuario();
                        break;

                    case 4:
                        listarUsuarios();
                        break;

                    case 5:
                        realizarEmprestimo();
                        break;

                    case 6:
                        devolverLivro();
                        break;

                    case 7:
                        System.out.println("Sistema encerrado.");
                        break;

                    default:
                        System.out.println("Opcao invalida.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite apenas numeros.");
            }

        } while (opcao != 7);

        scanner.close();
    }

    public static void cadastrarExemplos() {

        livros.add(new Livro(
                "O Pequeno Principe",
                "Antoine de Saint-Exupery",
                1943
        ));

        livros.add(new Livro(
                "Palmeiras campeao Mundial 2026",
                "Gabriel Lima",
                2026
        ));

        usuarios.add(new Aluno(
                "padim junior",
                "11111111111",
                "reidoticuida1234@email.com",
                "Informatica",
                "2A"
        ));

        usuarios.add(new Funcionario(
                "Alisson",
                "22222222222",
                "Alissonflamengoeliminado123@email.com",
                "Bibliotecaria"
        ));
    }

    public static void cadastrarLivro() {

        System.out.println("\n===== CADASTRO DE LIVRO =====");

        System.out.print("Titulo: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        int ano;

        try {
            System.out.print("Ano de publicacao: ");
            ano = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Ano invalido.");
            return;
        }

        Livro livro = new Livro(titulo, autor, ano);

        livros.add(livro);

        System.out.println("Livro cadastrado com sucesso.");
    }

    public static void listarLivros() {

        System.out.println("\n===== LIVROS CADASTRADOS =====");

        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }

        for (int i = 0; i < livros.size(); i++) {

            System.out.println("Livro " + (i + 1));
            livros.get(i).mostrarLivro();
        }
    }

    public static void cadastrarUsuario() {

        System.out.println("\n===== CADASTRO DE USUARIO =====");

        System.out.println("1 - Aluno");
        System.out.println("2 - Funcionario");
        System.out.print("Escolha o tipo: ");

        int tipo;

        try {
            tipo = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Digite apenas numeros.");
            return;
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("E-mail: ");
        String email = scanner.nextLine();

        if (tipo == 1) {

            System.out.print("Curso: ");
            String curso = scanner.nextLine();

            System.out.print("Turma: ");
            String turma = scanner.nextLine();

            Aluno aluno = new Aluno(
                    nome,
                    cpf,
                    email,
                    curso,
                    turma
            );

            usuarios.add(aluno);

            System.out.println("Aluno cadastrado.");

        } else if (tipo == 2) {

            System.out.print("Cargo: ");
            String cargo = scanner.nextLine();

            Funcionario funcionario = new Funcionario(
                    nome,
                    cpf,
                    email,
                    cargo
            );

            usuarios.add(funcionario);

            System.out.println("Funcionario cadastrado.");

        } else {
            System.out.println("Tipo de usuario invalido.");
        }
    }

    public static void listarUsuarios() {

        System.out.println("\n===== USUARIOS CADASTRADOS =====");

        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }

        for (Usuario usuario : usuarios) {
            usuario.listarInformacoes();
        }
    }

    public static void realizarEmprestimo() {

        System.out.println("\n===== EMPRESTIMO =====");

        if (livros.isEmpty()) {
            System.out.println("Nao existem livros cadastrados.");
            return;
        }

        listarLivros();

        try {

            System.out.print("Digite o numero do livro: ");
            int numero = Integer.parseInt(scanner.nextLine());

            if (numero < 1 || numero > livros.size()) {
                System.out.println("Livro invalido.");
                return;
            }

            Livro livro = livros.get(numero - 1);

            if (!livro.isDisponivel()) {
                System.out.println("Esse livro ja esta emprestado.");
                return;
            }

            livro.emprestar();

            System.out.println("Emprestimo realizado com sucesso.");

        } catch (NumberFormatException e) {
            System.out.println("Digite apenas numeros.");
        }
    }

    public static void devolverLivro() {

        System.out.println("\n===== DEVOLUCAO =====");

        if (livros.isEmpty()) {
            System.out.println("Nao existem livros cadastrados.");
            return;
        }

        listarLivros();

        try {

            System.out.print("Digite o numero do livro: ");
            int numero = Integer.parseInt(scanner.nextLine());

            if (numero < 1 || numero > livros.size()) {
                System.out.println("Livro invalido.");
                return;
            }

            Livro livro = livros.get(numero - 1);

            if (livro.isDisponivel()) {
                System.out.println("Esse livro ja esta disponivel.");
                return;
            }

            livro.devolver();

            System.out.println("Livro devolvido com sucesso.");

        } catch (NumberFormatException e) {
            System.out.println("Digite apenas numeros.");
        }
    }
}
    }
}