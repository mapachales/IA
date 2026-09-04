class Nodo {
    Nodo izq, der;
    int n;

    public Nodo(int n) {
        this.n = n;
    }

    public void insertar (int valor) {
        if (valor <= n) {
            if (izq == null) {
                izq = new Nodo(valor);
            } else {
                izq.insertar(valor);
                }
            } else {
                if (der == null) {
                    der = new Nodo(valor);
                } else {
                    der.insertar(valor);
                }
            }
        }

        public boolean vacio() {
            if (der == null) {
                return true;
            } else {
                return false;
                {
                if (izq == null) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        // Desconozco como implementar el orden de busqueda
        // así como tambien no tengo certeza de que los demás metodos sean correctos.

}