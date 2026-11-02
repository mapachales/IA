from sklearn.linear_model import Perceptron

# Perceptron utilizando la compuerta logica AND como ejemplo de entrenamiento.
# Se espera que el perceptron pueda distinguir correctamente los casos que dan 0 de los que dan 1,
# y solo dar 1 cuando ambas entradas sean 1.

# Datos de entrenamiento. Cada fila recibe dos parametros de entrada.
X = [[0, 0], [0, 1], [1, 0], [1, 1]]

# Salidas esperadas. Solo da 1 cuando ambas entradas son 1.
y = [0, 0, 0, 1]

# Crear el perceptron utilizando la clase Perceptron de sklearn. 
perceptron = Perceptron(max_iter=100, random_state=42) 
# Parametro 1: Define el numero maximo de epocas a realizar para que la neurona aprenda. Si encuentra la solucion antes se detiene.
# Parametro 2: Punto inicial de los pesos.

# Entrenar el perceptron con los datos de entrenamiento
perceptron.fit(X, y)
# Al inicio, predice las salidas usando los pesos iniciales,
# despues calcula el error, corrige los pesos y repite el proceso
# hasta que se cumpla el numero maximo de epocas o hasta que no haya error.

# Probar la neurona con nuevas entradas para ver si aprendio correctamente.
casos_prueba = [[1, 1], [0, 1], [1, 0], [0, 0]]
predicciones = perceptron.predict(casos_prueba)

print("--- RESULTADOS DEL PERCEPTRÓN ---")
for entrada, resultado in zip(casos_prueba, predicciones):
  print(f"Entrada: {entrada} -> El perceptrón predice: {resultado}")

# Impresión de resultados.
print("\nPesos sinápticos aprendidos (w):", perceptron.coef_)
print("Sesgo aprendido (bias / b):", perceptron.intercept_)