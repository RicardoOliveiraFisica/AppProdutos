package br.com.ro.AppProdutos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ro.AppProdutos.model.Produto;
import br.com.ro.AppProdutos.repository.ProdutoRepository;
import br.com.ro.AppProdutos.service.interfaces.ProdutoServiceInterface;

@Service
public class ProdutoService implements ProdutoServiceInterface {
	private ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoService(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	public Produto save(Produto produto) {
		return this.produtoRepository.save(produto);
	}

	@Override
	public Optional<Produto> getById(Long id) {
		return this.produtoRepository.findById(id);
	}

	@Override
	public List<Produto> getAll() {
		return this.produtoRepository.findAll();
	}

	@Override
	public Produto update(Produto produto) {
		Long id = produto.getId();
		Optional<Produto> upProduto = this.produtoRepository.findById(id);
		
		if (upProduto.isPresent()) {
			Produto newProduto = upProduto.get();
			newProduto.setCodigoBarras(produto.getCodigoBarras());
			newProduto.setNome(produto.getNome());
			newProduto.setPreco(produto.getPreco());
			return this.produtoRepository.save(newProduto);		
		}
		return produto;		
	}

	@Override
	public void delete(Long id) {
		this.produtoRepository.deleteById(id);
	}

}
