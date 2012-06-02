package br.flf.curso.sugestaovoto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet de controle para o sistema de sugestão de curso.
 */
@WebServlet("/curso")
public class CursoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * Recebe o request e dispatcha o para comando (cmd) correto.
	 */
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			String cmd = request.getParameter("cmd");
			if (cmd == null || cmd.equals("") || cmd.equals("listar")) {
				listar(request, response);

			} else if (cmd.equals("mostrarFormSugerir")) {
				mostrarFormSugerir(request, response);

			} else if (cmd.equals("mostrarFormVotar")) {
				mostrarFormVotar(request, response);

			} else if (cmd.equals("sugerir")) {
				sugerir(request, response);

			} else if (cmd.equals("votar")) {
				votar(request, response);

			} else {
				listar(request, response);
				
			}

		} catch (Exception e) {

			// utilizar um Logger.
			e.printStackTrace();

			request.setAttribute("erro", e.getMessage());
			request.getRequestDispatcher("erro.jsp").forward(request, response);

		}

	}
	
	private void redirectListar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+"/curso");
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CursoDAO dao = new CursoDAO();
		List<Curso> cursos = dao.listarTodosCursosComQtdeVotos();
		request.setAttribute("cursos", cursos);
		request.getRequestDispatcher("listaCursos.jsp").forward(request,
				response);

	}

	private void mostrarFormSugerir(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("formSugerir.jsp").forward(request,
				response);

	}

	private void mostrarFormVotar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		if(!ValidacaoUtil.ehVazio(nome)) {
			
			CursoDAO dao = new CursoDAO();
			Curso curso = dao.carregarCurso(nome);
			if(curso != null) {
				request.setAttribute("curso", curso);
				
			}
		}
		
		request.getRequestDispatcher("formVotar.jsp")
				.forward(request, response);
	}

	private void sugerir(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");
		String email = request.getParameter("email");

		List<String> mensagens = new ArrayList<String>();

		// nome
		if (ValidacaoUtil.ehVazio(nome)) {
			mensagens.add("Campo nome é obrigatório.");

		} else {

			if (!ValidacaoUtil.validaQtdeCarateres(nome, 10, 50)) {
				mensagens
						.add("Campo nome deve ter no mínimo 10 e no máximo de 50 caracteres.");

			}

		}

		if (ValidacaoUtil.ehVazio(descricao)) {
			mensagens.add("Campo descrição é obrigatório.");

		} else {

			if (!ValidacaoUtil.validaQtdeCarateres(descricao, 20, 150)) {
				mensagens
						.add("Campo descrição deve ter no mínimo 20 e no máximo de 150 caracteres.");

			}

		}

		if (ValidacaoUtil.ehVazio(email)) {
			mensagens.add("Campo e-mail é obrigatório.");

		} else {

			if (!ValidacaoUtil.validaQtdeCarateres(email, 5, 255)) {
				mensagens.add("Campo e-mail deve ter no mínimo 5 e no máximo de 255 caracteres.");
			}
			
			if (!ValidacaoUtil.validaEmail(email)) {
				mensagens.add("Campo e-mail inválido.");
			}

		}

		if (mensagens.isEmpty()) {

			CursoDAO dao = new CursoDAO();

			dao.incluirCurso(nome, descricao, email);
			
			mensagens.add("Sugestão de curso efetuada com sucesso.");
			request.setAttribute("mensagens", mensagens);
			
			redirectListar(request, response);

		} else {

			request.setAttribute("mensagens", mensagens);
			mostrarFormSugerir(request, response);

		}

	}

	private void votar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");

		List<String> mensagens = new ArrayList<String>();

		// nome
		if (ValidacaoUtil.ehVazio(nome)) {
			mensagens.add("Campo nome é obrigatório.");

		} else {

			if (!ValidacaoUtil.validaQtdeCarateres(nome, 10, 50)) {
				mensagens.add("Campo nome deve ter no mínimo 10 e no máximo de 50 caracteres.");

			}

		}

		if (ValidacaoUtil.ehVazio(email)) {
			mensagens.add("Campo e-mail é obrigatório.");

		} else {

			if (!ValidacaoUtil.validaQtdeCarateres(email, 5, 255)) {
				mensagens.add("Campo e-mail deve ter no mínimo 5 e no máximo de 255 caracteres.");

			} else {
				
				if (!ValidacaoUtil.validaEmail(email)) {
					mensagens.add("Campo e-mail inválido.");
				}
			
				CursoDAO dao = new CursoDAO();
				if(dao.existeVoto(nome, email)) {
					mensagens.add("O e-mail " + email + " já votou nesse curso.");
					
				}
				
			}

		}
		
		if (mensagens.isEmpty()) {

			CursoDAO dao = new CursoDAO();

			dao.incluirVoto(nome, email);
			mensagens.add("Voto efetuado com sucesso.");
			request.setAttribute("mensagens", mensagens);

			redirectListar(request, response);

		} else {

			request.setAttribute("mensagens", mensagens);
			mostrarFormVotar(request, response);

		}

	}

}
