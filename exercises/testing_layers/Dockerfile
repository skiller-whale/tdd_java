# Start with Eclipse Temurin (OpenJDK) with Maven
FROM maven:3.9.6-eclipse-temurin-17

# Switch to root to install additional packages
USER root

WORKDIR /app

# Install Node.js 20 and xz-utils for watchexec
RUN apt-get update && \
    apt-get install -y curl xz-utils && \
    curl -fsSL https://deb.nodesource.com/setup_20.x | bash - && \
    apt-get install -y nodejs && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Ensure node and npm are in PATH for all users (npm comes with Node.js from NodeSource)
RUN ln -sf /usr/bin/node /usr/local/bin/node && \
    ln -sf /usr/bin/npm /usr/local/bin/npm

# Set environment variables to ensure tools are found
ENV PATH="/usr/local/bin:/usr/bin:$PATH"
ENV JAVA_HOME="/opt/java/openjdk"

# Install dependencies for Playwright browsers
RUN apt-get update && \
    apt-get install -y wget gnupg libnss3 libxss1 libasound2 libdrm2 libxcomposite1 libxdamage1 libxrandr2 libgbm1 libatk-bridge2.0-0 libgtk-3-0 && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Install watchexec (musl static binary)
RUN wget https://github.com/watchexec/watchexec/releases/download/v1.24.2/watchexec-1.24.2-x86_64-unknown-linux-musl.tar.xz && \
    tar -xf watchexec-1.24.2-x86_64-unknown-linux-musl.tar.xz && \
    mv watchexec-*/watchexec /usr/local/bin/watchexec && \
    rm -rf watchexec-* watchexec-1.24.2-x86_64-unknown-linux-musl.tar.xz

# Copy project files
COPY . .

# Make scripts executable
RUN chmod +x ./run-tests.sh

# Install npm dependencies
RUN npm install

# Pre-download Maven dependencies to cache them in Docker layer
RUN mvn dependency:go-offline -B

# Install Playwright browsers
RUN npx playwright install chromium

# Verify tools are available
RUN echo "=== Tool Verification ===" && \
    java -version && \
    node --version && \
    npm --version && \
    mvn --version

EXPOSE 8080

CMD ["npm", "run", "start"]
