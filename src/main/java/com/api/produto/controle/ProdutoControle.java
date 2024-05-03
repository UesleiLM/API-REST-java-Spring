package com.api.produto.controle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.produto.modelo.ProdutoModelo;
import com.api.produto.modelo.RespostaModelo;
import com.api.produto.repositorio.ProdutoRepositorio;

@RestController
@RequestMapping("/api")
public class ProdutoControle {

	//Ações
	@Autowired
	private ProdutoRepositorio acoes;
	
	//Listar Produtos
	@RequestMapping(value="/produtos", method=RequestMethod.GET)
	public @ResponseBody List<ProdutoModelo> Listar() {
		return acoes.findAll();
	}
	
	//Cadastrar produtos
	@RequestMapping(value="/produtos", method = RequestMethod.POST)
	public @ResponseBody RespostaModelo Cadastrar(@RequestBody ProdutoModelo produto) {
		RespostaModelo resposta = new RespostaModelo();
		
		try {
			acoes.save(produto);
			resposta.setMensagem("Produto cadastrado com sucesso!");
		}catch(Exception erro) {
			resposta.setMensagem("Erro ao cadastrar Produto" +erro.getMessage());
		}
		return resposta;
	}
	
	//filtrar por id
	@RequestMapping(value="/produtos/{codigo}", method = RequestMethod.GET)
	public @ResponseBody ProdutoModelo filtrarCodigo(@PathVariable Integer codigo){
		return acoes.findByCodigo(codigo);
	}
	
	//Alterar produto(update na table)
	@RequestMapping(value="/produtos", method = RequestMethod.PUT)
	public @ResponseBody RespostaModelo altera(@RequestBody ProdutoModelo produto) {
		RespostaModelo resposta = new RespostaModelo();
		
		try {
			acoes.save(produto);
			resposta.setMensagem("Produto alterado com sucesso!");
		}catch(Exception erro) {
			resposta.setMensagem("Erro ao alterar Produto" +erro.getMessage());
		}
		return resposta;
	
	}
	
	//Detetar produto
	@RequestMapping(value="/produtos/{codigo}", method = RequestMethod.DELETE)
	public @ResponseBody RespostaModelo remover(@PathVariable Integer codigo) {
		RespostaModelo resposta = new RespostaModelo();
		
		try {
		ProdutoModelo produto = filtrarCodigo(codigo);
		this.acoes.delete(produto);
		resposta.setMensagem("Produto removido com sucesso");
		}catch(Exception erro){
			resposta.setMensagem("Falha ao remover" +erro.getMessage());
		}
		
		return resposta;
	}
	

	
	/*Sobre
	@RequestMapping(value="/sobre", method=RequestMethod.GET)
	public @ResponseBody String Sobre() {
		return "Sobre a API...";
	}
	
	Contato
	@RequestMapping(value="/contato", method=RequestMethod.GET)
	public @ResponseBody String Contato() {
		return "Fale conosco :)";
	}*/
	
}
