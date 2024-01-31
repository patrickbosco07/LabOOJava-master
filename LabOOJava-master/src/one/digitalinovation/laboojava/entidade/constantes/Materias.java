package one.digitalinovation.laboojava.entidade.constantes;

public enum Materias {
    M2 (2),
    M5 (5),
    M10(10);

    private int fatores;

    Materias(int fatores){
        this.fatores = fatores;
    }

    public int getFatores() {
        return fatores;
    }

}
