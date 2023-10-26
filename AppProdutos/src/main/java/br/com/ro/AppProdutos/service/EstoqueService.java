package br.com.ro.AppProdutos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ro.AppProdutos.exception.ResourceNotFoundException;
import br.com.ro.AppProdutos.model.Estoque;
import br.com.ro.AppProdutos.repository.EstoqueRepository;
import br.com.ro.AppProdutos.service.interfaces.EstoqueServiceInterface;

@Service
public class EstoqueService implements EstoqueServiceInterface{
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	public EstoqueService(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}

	@Override
	public Estoque save(Estoque estoque) {
		return this.estoqueRepository.save(estoque);
	}

	@Override
	public Optional<Estoque> getById(Long id) {
		return this.estoqueRepository.findById(id);
	}

	@Override
	public List<Estoque> getAll() {
		return this.estoqueRepository.findAll();
	}

	@Override
	public Estoque update(Estoque estoque) {
		Long id = estoque.getId();
		Optional<Estoque> upEstoque = this.estoqueRepository.findById(id);
		
		if (upEstoque.isPresent()) {
			Estoque newEstoque = upEstoque.get();
			newEstoque.setProduto(estoque.getProduto());
			newEstoque.setQuantidade(estoque.getQuantidade());
			return this.estoqueRepository.save(newEstoque);		
		}
		return estoque;		
	}

	@Override
	public void delete(Long id) {
		this.estoqueRepository.deleteById(id);		
	}

	@Override
	public Estoque addQuantidade(Long id, int quantidade) {
		return estoqueRepository.findById(id)
				.map(estoque -> {
					estoque.setQuantidade(estoque.getQuantidade() + quantidade);
					return estoqueRepository.save(estoque);
				})
				.orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado com o ID " + id));
	}

	@Override
	public Estoque delQuantidade(Long id, int quantidade) {
		return estoqueRepository.findById(id)
				.map(estoque -> {
					estoque.setQuantidade(estoque.getQuantidade() - quantidade);
					return estoqueRepository.save(estoque);
				})
				.orElseThrow(() -> new ResourceNotFoundException("Estoque não encontrado com o ID " + id));
	}
	
}
