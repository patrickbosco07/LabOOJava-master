package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Materias;

public class Caderno extends Produto{
    private Materias materia;

    public Materias materia() {
        return materia;
    }

    public void setMateria(Materias materia) {
        this.materia = materia;
    }

    @Override
    public double calcularFrete() {return (getPreco() * getQuantidade() + (1 + materia.getFatores()));
    }

    @Override
    public String toString() {
        return "Caderno{" +
                "tipo:" + materia +
                ", código:" + getCodigo() +
                ", preço:" + getPreco() +
                "}";
    }
}
