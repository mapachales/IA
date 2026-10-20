import numpy as np # Libreria para cálculos numéricos y vectores.
import matplotlib.pyplot as plt # Crear graficas.
from sklearn.datasets import fetch_california_housing # Descargar dataset.
import pandas as pd # Libreria para manipular tablas de datos.

# California housing analiza el mercado inmboiliario de California, y contiene información sobre el ingreso medio de la zona y el valor medio de las viviendas.
# MedInc: Es la variable independiente X, representa el nivel socioeconomico de las familias en una zona.
# MedHouseVal: Es la variable dependiente Y, representa el precio promedio de las casas en esa zona.
# Se desea evaluar si existe una relacion directa entre los ingresos de los habitantes y el costo de las casas en el area que viven.
# A partir de los resultados, se quiere construir una regla matematica que pueda predecir el precio de una casa sabiendo el ingreso de los vecinos.

# Configurar el estilo de las gráficas
plt.style.use('seaborn-v0_8-whitegrid' if 'seaborn-v0_8-whitegrid' in plt.style.available else 'default')

# 1. CARGA DE DATOS (California Housing)
# Se descargan los datos de California Housing y se seleccionan las primeras 50 muestras para el análisis, dandole formato de Pnadas.
california = fetch_california_housing(as_frame=True)

# La variable independiente X (ingreso medio de la zona).
X = california.data['MedInc'].values[:50] # Extrae los valores de las primeras 50 muestras.

# La variable dependiente Y (valor medio de la vivienda en $100,000).
Y = california.target.values[:50] # Extrae los valores de las primeras 50 muestras.

# Muestra en consola cuantas muestras se han cargado y analizado.
print(f"Datos cargados con éxito: {len(X)} registros analizados.")

# 2. INICIALIZACIÓN DE PARÁMETROS
# W nos indica cuanto aumenta el valor de una vivienda por cada unidad que incrementa el ingreso de la zona.
# Si este es positivo, significa que a mayor ingreso, mayor valor de la vivienda. Si es negativo, significa que a mayor ingreso, menor valor de la vivienda.
w = 0.0 # Peso inicial (Inclinación de la recta)
# El sesgo b representa el valor estimado de una vivienda en una zona con ingresos nulos.
b = 0.0 # Interseccion inicial (Altura donde la recta cruza en el eje Y)

# Hiperparámetros
learning_rate = 0.005  # Tasa de aprendizaje: Controla que tan grandes son los pasos que da el algoritmo en cada vuelta.
epochs = 1500          # Iteraciones de entrenamiento: Cuantas veces repetira el proceso de prediccion y ajuste.
n = len(X)             # N guarda el numero total de muestras para usarlo en el calculo promedio del error.

# Crea una lista vacia donde se guarda el valor del error en cada epoca para graficar la evolucion.
historial_costos = []

# 3. ENTRENAMIENTO (Gradiente Descendente)
for epoch in range(epochs): # Bucle que se repeite tantas veces como se indique en la variable epochs.
    # Predicción actual
    Y_pred = w * X + b # Aplica la ecuación de la recta a todas las X para obtener las predicciones.
    
    # Cálculo del Costo (Error Cuadrático Medio - MSE)
    cost = (1 / (2 * n)) * np.sum((Y_pred - Y) ** 2)
    historial_costos.append(cost)
    # Resta las predicciones de los valores reales Y y las eleva al cuadrado, despues lo suma y calcula el promedio.
    # Este valor representa que tan equivocado esta el modelo en ese instante y guarda el numero en la lista.
    
    # Derivadas parciales (Gradientes)
    dw = (2 / n) * np.sum((Y_pred - Y) * X) # Derivada respecto a w. Indica cuanto y hacia donde debe caminar la pendiente para reducicr el error.
    db = (2 / n) * np.sum(Y_pred - Y) # Derivada respecto a b. Indica cuanto y hacia donde debe moverse el corte con el eje Y.
    
    # Actualización de pesos
    w = w - learning_rate * dw
    b = b - learning_rate * db
    # Ajusta w y b en direccion opuesta al error multiplicado por la tasa de aprendizaje.

# Muestra la ecuacion lineal que el modelo aprendio con w y b finales-
print("¡Entrenamiento completado!")
print(f"Ecuación final aprendida: Valor Casa = {w:.4f} * (Ingreso Medio) + {b:.4f}")

# 4. VISUALIZACIÓN DE RESULTADOS
plt.figure(figsize=(10, 6))

# Dibujar los puntos reales (California Housing)
plt.scatter(X, Y, color='royalblue', s=70, label='Datos Reales (California Housing)', zorder=5)
# Scatter dibuja los pintos de los datos reales en azul en el plano.
# Los punros azules representan los datos reales de los vecindarios.

# Dibujar la línea de tendencia aprendida por el modelo
X_linea = np.linspace(min(X) - 0.5, max(X) + 0.5, 100)
Y_linea = w * X_linea + b
plt.plot(X_linea, Y_linea, color='crimson', linewidth=3, label=f'Modelo Lineal: Y = {w:.2f}X + {b:.2f}')
# Genera una serie de puntos continuos para dibujar la linea de tendencia aprendida por el modelo.
# La linea roja es la regla aprendida por el modelo. Si se desea estimar en un futuro cuanto cuesta una casa con un ingreso especifico,
# se busca esa cifra en el eje X y se intercepta la linea roja para obtener la predicción.

# Etiquetas y formato estético
plt.title('Regresión Lineal From Scratch: Ingreso Medio vs Valor de la Vivienda', fontsize=12, fontweight='bold', pad=15)
plt.xlabel('Ingreso Medio (MedInc)', fontsize=11)
plt.ylabel('Valor de la Casa ($100,000s)', fontsize=11)
plt.legend(fontsize=11)
plt.grid(True, linestyle='--', alpha=0.6)
# Configura los titulos, nombres de los ejes, leyenda y muestra la graf principal.

plt.show()

# Gráfica de la función de costo
plt.figure(figsize=(8, 4))
plt.plot(range(epochs), historial_costos, color='darkorange', linewidth=2)
plt.title('Evolución de la Función de Costo (Error Cuadrático Medio)', fontsize=11, fontweight='bold')
plt.xlabel('Épocas / Iteraciones', fontsize=10)
plt.ylabel('Costo (MSE)', fontsize=10)
plt.grid(True, linestyle='--', alpha=0.6)
plt.show()
# Dibuja una segunda grafica que muestra como el costo o error fue disminuyendo gradualmente en cada epoca a medida que el algoritmo aprendia.
# Al principio en la epoca 0, el error era muy alto porque el modelo no conocia la relacion entre w y b. Conforme fue aprendiendo,
# el algoritmo se fue ajustando y logro que el error bajara.