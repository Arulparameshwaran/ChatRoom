from fastapi import FastAPI
from pydantic import BaseModel
from transformers import pipeline

app = FastAPI()

# Load model ONCE (important)
classifier = pipeline(
    "text-classification",
    model="j-hartmann/emotion-english-distilroberta-base",
    top_k=1
)

class TextInput(BaseModel):
    text: str

@app.post("/emotion_api")
def analyze_emotion(input: TextInput):
    result = classifier(input.text)[0][0]
    return {
        "emotion": result["label"],
        "score": float(result["score"])
    }
