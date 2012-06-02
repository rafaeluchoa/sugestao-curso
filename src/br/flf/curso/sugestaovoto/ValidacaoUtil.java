package br.flf.curso.sugestaovoto;

/**
 * Classe utilitária com métodos de validação de campos.
 */
public class ValidacaoUtil {

	/**
	 * Retorna true se o valor for diferente de nulo,  
	 * numero de caracteres maior ou igual ao mínimo e
	 * menor igual ao máximo.
	 * 
	 * @param valor
	 * @param minimo
	 * @param maximo
	 * @return
	 */
	public static boolean validaQtdeCarateres(String valor, int minimo, int maximo) {
		
		boolean valido = false;
		if(valor != null) {
			
			int qtde = valor.length(); 
			if(qtde >= minimo && qtde <= maximo) {
				valido = true;
			}
			
		}
		return valido;
		
	}
	
	/**
	 * Returna true, se o email é diferente de nulo e 
	 * tem o formato correto.
	 *  
	 * @param valor
	 * @return
	 */
	public static boolean validaEmail(String valor) {
		
		boolean valido = false;
		if(valor != null) {
			
			int posicaoAt = valor.indexOf("@");
			int posicaoPonto = valor.indexOf(".");
			
			if(posicaoAt > -1 && posicaoPonto > posicaoAt) {
				valido = true;
			}
			
		}
		return valido;
		
	}
	
	/**
	 * Retorna se true, se o valor é nulo ou vazio. 
	 * 
	 * @param valor
	 * @return
	 */
	public static boolean ehVazio(String valor) {
		return(valor == null || valor.trim().equals(""));
	}
	
}
