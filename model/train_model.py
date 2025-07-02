import tensorflow as tf
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras import layers, models
import os

# Konfigurasi
IMG_SIZE = (100, 100)  # Ukuran gambar lebih kecil agar training cepat
BATCH_SIZE = 16
EPOCHS = 5             # Bisa kamu ubah nanti jadi 10 jika kuat

# Path ke folder Training (ubah sesuai letak datamu)
DATASET_PATH = "C:/Users/PRATIKUM/Documents/GitHub/buah/fruits-360_100x100/fruits-360/Training"

# Cek apakah path benar
if not os.path.exists(DATASET_PATH):
    raise Exception(f"Path tidak ditemukan: {DATASET_PATH}")

# Image augmentation & normalisasi
datagen = ImageDataGenerator(
    rescale=1./255,
    validation_split=0.2
)

# Data training
train_generator = datagen.flow_from_directory(
    DATASET_PATH,
    target_size=IMG_SIZE,
    batch_size=BATCH_SIZE,
    class_mode='categorical',
    subset='training'
)

# Data validasi
val_generator = datagen.flow_from_directory(
    DATASET_PATH,
    target_size=IMG_SIZE,
    batch_size=BATCH_SIZE,
    class_mode='categorical',
    subset='validation'
)

# Buat model sederhana (lebih cepat dilatih dari MobileNet)
model = models.Sequential([
    layers.Conv2D(16, (3, 3), activation='relu', input_shape=(IMG_SIZE[0], IMG_SIZE[1], 3)),
    layers.MaxPooling2D(2, 2),
    layers.Conv2D(32, (3, 3), activation='relu'),
    layers.MaxPooling2D(2, 2),
    layers.Flatten(),
    layers.Dense(64, activation='relu'),
    layers.Dense(train_generator.num_classes, activation='softmax')
])

model.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

model.summary()

# Training (cepat, hanya 5 epoch)
model.fit(
    train_generator,
    epochs=EPOCHS,
    validation_data=val_generator
)

# Simpan model ke .h5
model.save("model_buah_keras.h5")

# Simpan label ke labels.txt
labels = list(train_generator.class_indices.keys())
with open("labels.txt", "w") as f:
    for label in labels:
        f.write(label + "\n")

print("Model dan labels.txt berhasil disimpan.")
