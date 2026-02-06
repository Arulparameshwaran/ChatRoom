from flask import Flask, request, jsonify
from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer

app = Flask(__name__)
analyzer = SentimentIntensityAnalyzer()

@app.route("/analyze", methods=["POST"])
def analyze_sentiment():
    data = request.json

    
    message = data.get("content", "Happy")

    score = analyzer.polarity_scores(message)
    compound = score["compound"]

    if compound >= 0.05:
        sentiment = "POSITIVE"
    elif compound <= -0.05:
        sentiment = "NEGATIVE"
    else:
        sentiment = "NEUTRAL"

    return jsonify({
        "sentiment": sentiment,
        "score": compound
    })

if __name__ == "__main__":
    app.run(port=5000)
