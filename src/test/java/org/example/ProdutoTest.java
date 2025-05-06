package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProdutoTest {

//    Anotações
    // assertEquals para validar igualdade dos valores esperados.
    // assertTrue para garantir que a condição é verdadeira
    // assertThrows para garantir que uma exceção esperada seja lançada.


    //  1 - Testar criação de produto com valores válidos.
    @Test
    public void testarCriarProdutoValido() {
        String nome = "Caneca";
        double preco = 5.50;
        int estoque = 2;
        Produto produto = new Produto(nome, preco, estoque);

        Assertions.assertEquals(nome, produto.getNome());
        Assertions.assertEquals(preco, produto.getPreco());
        Assertions.assertEquals(estoque, produto.getEstoque());
    }

    //  2 - Testar criação de produto com preço negativo (deve ser inválido).
    @Test
    public void testarCriarProdutoComPrecoNegativo() {
        String nome = "Caneca";
        double preco = -5.50;
        int estoque = 2;
        Produto produto = new Produto(nome, preco, estoque);

//        AssertTrue é usado para verificar se uma condição é verdadeira, ou seja,
//        se o sistema entende como "correto" armazenar preço negativo.

        Assertions.assertTrue(produto.getPreco() < 0);
    }

    //  3 - Testar criação de produto com estoque negativo (deve ser inválido).
    @Test
    public void testarCriarProdutoComEstoqueNegativo() {
        String nome = "Caneca";
        double preco = 5.50;
        int estoque = -2;
        Produto produto = new Produto(nome, preco, estoque);

        Assertions.assertTrue(produto.getEstoque() < 0);
    }

    //  4 - Testar alteração do nome do produto para um valor válido.
    @Test
    public void testarAlterarNomeProduto() {
        String nome = "Caneca";
        double preco = 5.50;
        int estoque = 2;
        Produto produto = new Produto(nome, preco, estoque);

        produto.setNome("Xícara");

        Assertions.assertEquals("Xícara", produto.getNome());
    }

    //  5 - Testar alteração do preço do produto para um valor válido.
    @Test
    public void testarAlterarPrecoProduto() {
        String nome = "Caneca";
        double preco = 5.50;
        int estoque = 2;
        Produto produto = new Produto(nome, preco, estoque);

        produto.setPreco(2.59);
        Assertions.assertEquals(2.59, produto.getPreco());
    }

    //  6 - Testar alteração do estoque para um valor positivo.
    @Test
    public void testarAlterarEstoqueProduto() {
        String nome = "Caneca";
        double preco = 5.50;
        int estoque = 2;
        Produto produto = new Produto(nome, preco, estoque);

        produto.setEstoque(3);
        Assertions.assertEquals(3, produto.getEstoque());
    }

    //  7 - Testar alteração do preço do produto para um valor negativo (deve falhar).
    @Test
    public void testarAlterarPrecoProdutoInvalido() {
        String nome = "Caneca";
        double preco = 5.50;
        int estoque = 2;
        Produto produto = new Produto(nome, preco, estoque);

        produto.setPreco(-3.68);
        Assertions.assertTrue(produto.getPreco() < 0);
    }

}
