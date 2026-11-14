import cv2
import dlib

# Detector de rostros de dlib (HOG + SVM), no necesita archivo XML
detector = dlib.get_frontal_face_detector()

video_capture = cv2.VideoCapture(0)  # activar la camara


def detect_bounding_box(frame):
    gray_image = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    # El 1 indica cuantas veces se aumenta la imagen (mas detecciones, mas lento)
    faces = detector(gray_image, 1)

    for face in faces:  # dibujar rectangulo en cada rostro detectado
        x, y = face.left(), face.top()
        w, h = face.width(), face.height()
        cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 255, 0), 2)
    return faces  # retorna los rostros detectados


while True:
    result, video_frame = video_capture.read()  # leer el video
    if result is False:  # si no hay video, salir del ciclo
        break
    faces = detect_bounding_box(video_frame)  # detectar rostros
    cv2.imshow("Rostros detectados en la camara", video_frame)
    if cv2.waitKey(1) & 0xFF == ord('q'):  # salir al presionar la tecla q
        break

video_capture.release()  # liberar la camara
cv2.destroyAllWindows()  # cerrar todas las ventanas