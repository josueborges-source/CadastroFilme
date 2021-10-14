package br.com.cadastrofilme.principal;

import java.util.List;
import java.util.Scanner;

import br.com.cadastrofilme.crudhibernate.FilmeJpaDAO;
import br.com.cadastrofilme.model.Filme;

public class CadastroFilmeMain {

	public static void main(String[] args) {

		CadastroFilmeMain cadastroFilmes = new CadastroFilmeMain();

		/// Se não há nada cadastrado no BD, inserir 20 filmes

		List<Filme> filmes = FilmeJpaDAO.getInstance().findAll();

		if (filmes.size() < 0) {
			cadastroFilmes.cadastrarVinteFilmes();
		}

		/// Seleção de filmes:

		int quantidadeFilmesEscolhidos = 0;
		int paginaASerAcessada = 0;

		Scanner scanner = new Scanner(System.in);

		System.out.print("Qual página deseja acessar? ");
		paginaASerAcessada = scanner.nextInt();

		System.out.print("Quantos filmes deseja filtrar? ");
		quantidadeFilmesEscolhidos = scanner.nextInt();

		cadastroFilmes.mostrarFilmesSelecionados(paginaASerAcessada, quantidadeFilmesEscolhidos);

		scanner.close();
	}

	private void mostrarFilmesSelecionados(int paginaASerAcessada, int quantidadeFilmesEscolhidos) {
		List<Filme> listaDeFilmes = FilmeJpaDAO.getInstance().returnFilmeList(paginaASerAcessada,
				quantidadeFilmesEscolhidos);

		for (Filme filme : listaDeFilmes) {
			System.out.println(filme);
		}

	}

	private void cadastrarVinteFilmes() {
		for (int j = 1; j <= 20; j++) {
			Filme filme = new Filme();

			filme.setNome("Filme " + j);
			filme.setDescricao("Descricao " + j);
			filme.setAno(1900 + j);

			FilmeJpaDAO.getInstance().persist(filme);
		}

	}

}
