# Gunakan base image Python 3.10
FROM python:3.10-slim


# Update pip dan install system dependencies
RUN pip install --upgrade pip && \
    apt-get update && \
    apt-get install -y --no-install-recommends \
    build-essential \
    && rm -rf /var/lib/apt/lists/*

# Copy requirements file
COPY requirements.txt .

# Install Python dependencies
RUN pip install --no-cache-dir -r requirements.txt

# Copy application code
COPY . .


CMD exec gunicorn --bind 0.0.0.0:$PORT app:app