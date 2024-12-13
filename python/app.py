from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np
import urllib.request
import os

app = Flask(__name__)

# Download the TFLite model from the given URL
MODEL_URL = "https://storage.googleapis.com/suicide_model/suicide_detection_model.tflite"
MODEL_PATH = "suicide_detection_model.tflite"

if not os.path.exists(MODEL_PATH):
    urllib.request.urlretrieve(MODEL_URL, MODEL_PATH)

# Load the TFLite model
interpreter = tf.lite.Interpreter(model_path=MODEL_PATH)
interpreter.allocate_tensors()

# Get input and output details
input_details = interpreter.get_input_details()
output_details = interpreter.get_output_details()

def preprocess_input(text):
    """
    Preprocess the input text to match the model's expected input format.
    This step may include tokenization, padding, and encoding based on how the model was trained.
    """
    # Example: Convert text to a simple array of ASCII values (placeholder for real preprocessing)
    max_length = input_details[0]['shape'][1]  # Assumes input is of shape (batch_size, max_length)
    input_data = [ord(c) for c in text][:max_length]  # Truncate or limit length
    input_data += [0] * (max_length - len(input_data))  # Padding
    return np.array([input_data], dtype=np.float32)

def predict(text):
    """
    Perform prediction on the input text.
    """
    input_data = preprocess_input(text)
    interpreter.set_tensor(input_details[0]['index'], input_data)
    interpreter.invoke()
    output_data = interpreter.get_tensor(output_details[0]['index'])
    return output_data.tolist()

@app.route('/predict', methods=['POST'])
def predict_endpoint():
    """
    Endpoint to handle prediction requests.
    Request should be JSON with a 'text' field.
    """
    data = request.get_json()
    if not data or 'text' not in data:
        return jsonify({"error": "Invalid input. Provide a 'text' field."}), 400

    text = data['text']
    try:
        prediction = predict(text)
        return jsonify({"prediction": prediction})
    except Exception as e:
        return jsonify({"error": str(e)}), 500

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=int(os.environ.get('PORT', 8080)),debug=True)
