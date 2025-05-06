package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class VendaTest {
    Produto produto;

    @BeforeEach
    public void setUp() {
        produto = new Produto("Caneca", 5.50, 3);
    }

    //  1 - Testar venda com quantidade menor que o estoque disponível.
    @Test
    public void testarVendaComQuantidadeMenorQueEstoque() {
        Venda venda = new Venda(produto, 2);

        Assertions.assertTrue(venda.realizarVenda());
    }

    //  2 - Testar venda com quantidade igual ao estoque disponível.
    @Test
    public void testarVendaComQuantidadeIgualAoEstoque() {
        Venda venda = new Venda(produto, 3);

        Assertions.assertTrue(venda.realizarVenda());
    }

    //  3 - Testar venda com quantidade maior que o estoque disponível (deve falhar).
    @Test
    public void testarVendaComQuantidadeMaiorQueEstoque() {
        Venda venda = new Venda(produto, 7);

        Assertions.assertFalse(venda.realizarVenda());
    }

    //  4 - Testar cálculo do total da venda corretamente.
    @Test
    public void testarCalculoTotalVenda() {
        Venda venda = new Venda(produto, 2);

        venda.realizarVenda();

        Assertions.assertEquals(11.0, venda.getTotalVenda());
    }

    //  5 - Testar aumento de estoque do produto após uma venda.
    @Test
    public void testarAumentoEstoqueAposVenda() {
        Venda venda = new Venda(produto, 2);

        venda.realizarVenda();

        Assertions.assertEquals(1, produto.getEstoque());
    }

    //  6 - Testar diminuição do estoque do produto após uma venda bem-sucedida.
    @Test
    public void testarDiminucaoEstoqueAposVenda() {
        Venda venda = new Venda(produto, 2);

        venda.realizarVenda();

        Assertions.assertEquals(1, produto.getEstoque());
    }

    //  7 - Testar realizar venda de um produto que não existe (deve falhar).
    @Test
    void testVendaProdutoNulo() {
        Venda venda = new Venda(null, 1);
//      Se ao realizar a venda e o código não consegue acessar o produto então vai causar uma exceção.
//      Então para ver se o teste vai causar um NullPointerException eu uso esse método.
//       https://www.baeldung.com/junit-assert-exception
        Assertions.assertThrows(NullPointerException.class, venda::realizarVenda);
    }

    //  8 - Testar criação de venda com quantidade negativa (deve falhar).
    @Test
    public void testarCriacaoVendaComQuantidadeNegativa() {
        Venda venda = new Venda(produto, -2);

        Assertions.assertTrue(venda.realizarVenda());
    }

    //  9 - Testar alteração do estoque após a tentativa de venda com estoque insuficiente.
    @Test
    public void testarAlteracaoEstoqueAposEstoqueInsuficiente() {
        Venda venda = new Venda(produto, 5);

        Assertions.assertFalse(venda.realizarVenda());
    }

    //  10 - Testar criação de vários produtos e realizar vendas com estoque compartilhado
    //  Então eu tenho que criar mais de 1 produto e realizar a venda e ver se os estoques
    //  estão sendo compartilhados.
    @Test
    public void testarCriacaoVariosProdutosEVendasComEstoqueCompartilhado() {
        Produto produto2 = new Produto("Garrafa", 15.00, 8);

        Venda venda1 = new Venda(produto, 2);
        Venda venda2 = new Venda(produto2, 4);

        venda1.realizarVenda();
        venda2.realizarVenda();

        Assertions.assertEquals(1, produto.getEstoque());
        Assertions.assertEquals(4, produto2.getEstoque());

    }

    //  11 - Testar calcular total de venda quando o preço do produto for alterado antes da venda.
    @Test
    public void testarCalculoTotalVendaComPrecoAlterado() {
        produto.setPreco(10.0);

        Venda venda = new Venda(produto, 2);
        venda.realizarVenda();

        Assertions.assertEquals(20.0, venda.getTotalVenda());
    }

    //  12 - Testar comportamento da venda quando o estoque inicial é zero.
    @Test
    public void testarVendaComEstoqueInicialZero() {
        produto.setEstoque(0);
        Venda venda = new Venda(produto, 2);

        Assertions.assertFalse(venda.realizarVenda());
    }

//  13 - Testar aumento do estoque após uma reposição e verificar se a venda é bem-sucedida posteriormente.
    @Test
    public void testarAumentoEstoqueEVendaBemSucedida() {
        produto.aumentarEstoque(3);

        Assertions.assertEquals(6, produto.getEstoque());

        Venda venda = new Venda(produto, 2);

        Assertions.assertTrue(venda.realizarVenda());
    }

}
