package algorithms;

import java.util.Iterator;

/**
 * Dans cette question, vous devez implementer le produit scalaire de
 * deux vecteurs donnes sous la forme d'iterateurs.
 * Pour rappel, le produit scalaire entre les vecteurs [4, 5] et [6, 7]
 * est égal à 4 * 6 + 5 * 7. Il se calcule donc en Theta(n) où n est
 * la taille des deux vecteurs
 */

public class DotProductIterator {
    /**
     * Classe qui implemente un iterateur vers les elements d'un
     * tableau contenant des "double". Vous devez implementer cette
     * classe, en respectant la semantique des iterateurs Java.
     */
    public static class VectorIterator implements Iterator<Double> {
        private final double[] values;
        private int currentIndex;
        /**
         * Constructeur de l'iterateur.
         * @param values Le tableau de valeurs sur lequel iterer.
         */
        VectorIterator(double[] values) {
            if (values == null) {
                throw new IllegalArgumentException("Le tableau de valeurs ne peut pas être null.");
            }
            this.values = values;
            this.currentIndex = 0;
        }

        /**
         * Teste si l'iterateur a atteint la fin du tableau.
         * @return "false" si et seulement si l'iterateur a atteint la
         * fin du tableau.
         */
        @Override
        public boolean hasNext() {
            // TODO
             return currentIndex < values.length;
        }

        /**
         * Retourne l'element actuellement pointe par l'iterateur,
         * puis avance l'iterateur sur l'element suivant du tableau.
         * @return La valeur de l'element courant.
         * @throws IllegalStateException Si et seulement si cette
         * methode est appelee alors qu'on a deja atteint le dernier
         * element du tableau.
         */
        @Override
        public Double next() {
            if (hasNext()) {
                int ind = currentIndex;
                currentIndex++;
                return values[ind];
            }
            throw new IllegalStateException();
        }
    }

    /**
     * Calcule le produit scalaire (dot product) de deux vecteurs,
     * cad. la somme ponderee des elements des deux vecteurs. Les deux
     * vecteurs sont donnes sous la forme d'iterateurs Java.
     * @param a Iterateur vers les elements du premier vecteur.
     * @param b Iterateur vers les elements du second vecteur.
     * @return Le produit scalaire.
     * @throws IllegalArgumentException Si les deux iterateurs ne
     * contiennent pas le meme nombre d'elements.
     */
    public static double computeDotProduct(Iterator<Double> a, Iterator<Double> b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Les iterateurs ne peuvent pas être null.");
        }

        double dotProduct = 0.0;
        while (a.hasNext() && b.hasNext()) {
            dotProduct += a.next() * b.next();
        }
        if (a.hasNext() || b.hasNext()) {
            throw new IllegalArgumentException("Les deux iterateurs ne contiennent pas le même nombre d'elements.");
        }
        return dotProduct;
    }
}
