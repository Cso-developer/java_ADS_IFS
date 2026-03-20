
import java.util.Scanner;



public class CadastroDeAlunos {
	static int quantidadeAlunosCadastrados = 0; 
	static int quantidadeMaximaAlunos = 2;
	static String[][] alunos = new String[2][4];
	static int matriculaIndex = 0;
	static int nomeIndex = 1;
	static int primeiraNotaIndex = 2;
	static int segundaNotaIndex = 3;

	
	public static void main(String[] args) {
		int opcao=0;
		
		do {
			opcao = menuInicial();
			switch(opcao) {
				case 1:{
					cadastrarAluno();
					break;
				}
				case 2: {
					buscarMatricula();
					break;
				}
				case 3: {
					editarNotas();
					break;
				}
				case 4: {
					excluirAluno();
					break;
				}
				case 5: {
					exibirTodosAlunos();
					break;
				}
				case 0: {
					System.out.println("Programa encerrado");
					break;

				}
				default: {
					System.out.println("Opção inválida");
					break;
				}
					
					
				
			}
			
		}while(opcao !=0);
	
		
	}


	
	public static int menuInicial() {
		System.out.println("______________________________________");
		System.out.println("___________Escolha uma opção__________");
		System.out.println("----Digite 0- Encerrar----------------");
		System.out.println("----Digite 1- Cadastrar aluno---------");
		System.out.println("----Digite 2- Buscar matrícula -------");
		System.out.println("----Digite 3- Editar notas -----------");
		System.out.println("----Digite 4- Excluir cadastrado------");
		System.out.println("----Digite 5- Exibir todos -----------");
		System.out.println("______________________________________");
		return Integer.parseInt(lerDados("Digite a opção desejada:"));
	}
	
	public static String lerDados (String mensagem) {
		Scanner scanner = new Scanner(System.in);
		String input;
		System.out.println(mensagem);
		input = scanner.nextLine();
		
		return input;
	}

	public static void cadastrarAluno() {

		if (quantidadeAlunosCadastrados == quantidadeMaximaAlunos) {
			System.out.println("Base de dados cheia, não é possível cadastrar");
			return;
		}

		String matricula = lerMatricula();

		if (matricula == null) {
			return;
		}
	
		if (obterAlunoPorMatricula(matricula) != null) {
			System.out.println("Aluno já cadastrado");
			return;
		}

		String nome = lerDados("Digite o nome do aluno");

		if (nome.isEmpty()) {
			System.out.println("Nome inválido");
			return;
		}

		Float primeiraNota = lerNota('1');

		if (primeiraNota == null) {
			return;
		}

		Float segundaNota = lerNota('2');

		if (segundaNota == null) {
			return;
		}

		String[] aluno = new String[6];
		aluno[matriculaIndex] = matricula;
		aluno[nomeIndex] = nome;
		aluno[primeiraNotaIndex] = primeiraNota.toString();
		aluno[segundaNotaIndex] = segundaNota.toString();
		inserirAlunoNaBase(aluno);
	}

	public static String lerMatricula() {
		String matricula = lerDados("Digite o númmero da matricula");

		if (matricula.isEmpty()) {
			System.out.println("Matrícula inválida");
			return null;
		}

		return matricula;
	}

	public static Float lerNota(char numeral) {
		int notaMinima = 0;
		int notaMaxima = 10;
		String nota = lerDados("Infrome o valor da " + numeral + "ª nota");
		String mensagemErro = numeral + "ª nota inválida";

		if (nota.isEmpty()) {
			System.out.println(mensagemErro);
			return null;
		}

		Float notaFormatada = Float.parseFloat(nota);

		if (notaFormatada < notaMinima || notaFormatada > notaMaxima) {
			System.out.println(mensagemErro);
			return null;
		}

		return notaFormatada;
	}

	public static Float calcularMedia(String primeiraNota, String segundaNota) {
		return (Float.parseFloat(primeiraNota) + Float.parseFloat(segundaNota)) / 2;
	}

	public static String obterSituacao(Float media) {
		if (media < 2) {
			return "REPROVADO";
		}
		else if (media < 6) {
			return "RECUPERAÇÃo";
		}
		else {
			return "APROVADO";
		}
	}

	public static String[] obterAlunoPorMatricula(String matricula) {
		for (int i = 0; i < alunos.length; i++) {
			if (alunos[i][matriculaIndex] != null && alunos[i][matriculaIndex].equals(matricula)) {
				return alunos[i];
			}
		}
		return null;
	}

	public static void inserirAlunoNaBase(String[] aluno) {
		for (int i = 0; i < aluno.length; i++) {
			if (alunos[i][matriculaIndex] == null) {
				alunos[i] = aluno;
				quantidadeAlunosCadastrados++;
				break;
			}

		}
	}

	public static void buscarMatricula() {
		String matricula = lerMatricula();

		if (matricula == null) {
			return;
		}

		String[] aluno = obterAlunoPorMatricula(matricula);

		if (aluno != null) {
			exibirAluno(aluno);
		}
		else {
			System.out.println("Aluno não cadastrado");
		}
	}

	public static void editarNotas() {
		String matricula = lerMatricula();

		if (matricula == null) {
			return;
		}

		String[] aluno = obterAlunoPorMatricula(matricula);
		if (aluno != null) {
			Float primeiraNota = lerNota('1');

			if (primeiraNota  == null) {
				return;
			}

			Float segundaNota = lerNota('2');

			if (segundaNota == null) {
				return;
			}

			aluno[primeiraNotaIndex] = primeiraNota.toString();
			aluno[segundaNotaIndex] = segundaNota.toString();
			
		}
		else {
			System.out.println("Aluno não cadastrado");
		}
	}

	public static void excluirAluno() {
		String matricula = lerMatricula();

		if (matricula == null) {
			return;
		}

		if (matricula.isEmpty()) {
			System.out.println("Matrícula inválida");
		}

		String[] aluno = obterAlunoPorMatricula(matricula);

		if (aluno != null) {
			aluno[matriculaIndex] = null;
			aluno[nomeIndex] = null;
			aluno[primeiraNotaIndex] = null;
			aluno[segundaNotaIndex] = null;
			quantidadeAlunosCadastrados--;
		}
		else {
			System.out.println("Aluno não encontrado");
		}
		
	}

	public static void exibirTodosAlunos() {
		for (int i = 0; i < alunos.length; i++) {
			String[] aluno = alunos[i];
			if (aluno[matriculaIndex] != null) { 
				exibirAluno(aluno);
			}
		}
	}


	public static void exibirAluno(String[] aluno) {
		Float media = calcularMedia(aluno[primeiraNotaIndex], aluno[segundaNotaIndex]);
		String situacao = obterSituacao(media);
		System.out.println("______________________________________");
		System.out.println("________________Boletim_______________");
		System.out.println("--------------------------------------");
		System.out.println("Matrícula: " + aluno[matriculaIndex]);
		System.out.println("Nome:      " + aluno[nomeIndex]);
		System.out.println("Média:     " + media);
		System.out.println("Situação   " + situacao);
		System.out.println("--------------------------------------");
	}

}