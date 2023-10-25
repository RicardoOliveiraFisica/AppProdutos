package br.com.ro.AppProdutos.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Produto {
	private Long id;
	private String codigoBarras;
	private BigDecimal preco;
	
	public Produto() {}
	
	public Produto(Long id, String codigoBarras, BigDecimal preco) {
		this.id = id;
		this.codigoBarras = codigoBarras;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
