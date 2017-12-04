package br.ifrn.poo.JFinancas.visao;

import java.util.Scanner;

import br.ifrn.poo.JFinancas.controle.UsuarioController;
import br.ifrn.poo.JFinancas.exceptions.UsuarioNaoCadastradoException;
import br.ifrn.poo.JFinancas.modelo.Usuario;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Usuario uc;
		
		boolean cc = true;
		String nome;
		float saldo;
		
		while (cc) {
			System.out.println("--------------------------------------------------");
			System.out.println("1 - cadastrar usuario");
			System.out.println("2 - acessar usuario");
			System.out.println("--------------------------------------------------");
			
			switch (sc.nextInt()) {
				case 1:
					nome = sc.next();
					saldo = sc.nextFloat();
					UsuarioController.registrarUsuario(nome, saldo);
					break;
				case 2:
					nome = sc.next();
					try {
						uc = UsuarioController.recuperarUsuario(nome);
						System.out.println(uc.getNome() + " - " + uc.getSaldo());
					} catch (UsuarioNaoCadastradoException e) {
						e.printStackTrace();
					}
					break;
				default:
					cc = false;
					break;
			}
		}

	}

}
